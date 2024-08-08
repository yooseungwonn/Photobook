package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.AlbumDao;
import himedia.photobook.repositories.dao.InventoryDao;
import himedia.photobook.repositories.dao.OrderDao;
import himedia.photobook.repositories.dao.PhotoDaoImpl;
import himedia.photobook.repositories.dao.RefundDao;
import himedia.photobook.repositories.dao.ShipmentsDao;
import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.tools.DataConverter;
import himedia.photobook.tools.FileModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminOrderService {
   @Autowired
   private OrderDao orderDaoImpl;
   @Autowired
   private UsersDao usersDaoImpl;
   @Autowired
   private ShipmentsDao shipmentsDaoImpl;
   @Autowired
   private AlbumDao albumDaoImpl;
   @Autowired
   private RefundDao refundDaoImpl;
   @Autowired
   private InventoryDao inventoryDaoImpl;
   @Autowired
   private PhotoDaoImpl photoDaoImpl;
   private DataConverter converter = new DataConverter();
   private FileModule fileModule = new FileModule();

   public List<Map<String, Object>> getOrderAdmin() {
      List<Map<String, Object>> orderInfoList = new ArrayList();
      List<OrdersVo> orderList = this.orderDaoImpl.selectAllOrders();
      System.out.println("orderList + " + String.valueOf(orderList));
      Iterator var4 = orderList.iterator();

      while(var4.hasNext()) {
         OrdersVo order = (OrdersVo)var4.next();
         Map<String, Object> orderMap = new HashMap();
         orderMap.put("ordersVo", order);
         UsersVo user = this.usersDaoImpl.selectOneUserById(order.getUserId());
         orderMap.put("usersVo", user);
         String status = this.shipmentsDaoImpl.selectStatusByOrderID(order.getOrderId());
         String rstatus = this.refundDaoImpl.selectStatusByOrderID(order.getOrderId());
         if (rstatus != null) {
            rstatus = this.converter.statusToWord(rstatus);
            orderMap.put("status", rstatus);
            orderInfoList.add(orderMap);
         } else if (status == null) {
            status = "G";
            status = this.converter.statusToWord(status);
            orderMap.put("status", status);
            orderInfoList.add(orderMap);
         } else {
            status = this.converter.statusToWord(status);
            orderMap.put("status", status);
            orderInfoList.add(orderMap);
         }
      }

      return orderInfoList;
   }

   public Map<String, Object> getOrderDetail(String orderId) {
      Map<String, Object> orderDetail = new HashMap();
      String albumId = this.orderDaoImpl.getAlbumIdByOrderId(orderId);
      AlbumVo album = this.albumDaoImpl.selectByAlbumId(albumId);
      orderDetail.put("album", album);
      OrdersVo order = this.orderDaoImpl.selectByOrderId(orderId);
      orderDetail.put("order", order);
      UsersVo user = this.usersDaoImpl.selectOneUserById(order.getUserId());
      orderDetail.put("user", user);
      int photoCount = this.photoDaoImpl.selectCountByOrderId(order.getOrderId());
      orderDetail.put("imagesCount", photoCount);
      String var10000 = order.getUserId();
      String imgSrc = var10000 + "/" + orderId;
      if (this.fileModule.getOsName().contains("nux")) {
         imgSrc = "/nux/photobook-images/order/" + imgSrc;
      } else {
         imgSrc = "/win/photobook-images/order/" + imgSrc;
      }

      orderDetail.put("imgSrc", imgSrc);
      return orderDetail;
   }

   public String getSearchUserId(String keyword) {
      String userId = this.orderDaoImpl.getUserIdByUserName(keyword);
      return userId;
   }

   public List<Map<String, Object>> searchOrderInfo(String keyword) {
      List<Map<String, Object>> orderInfo = new ArrayList();
      List<UsersVo> usersList = this.usersDaoImpl.selectUserByKeyword(keyword);
      Iterator var5 = usersList.iterator();

      while(var5.hasNext()) {
         UsersVo usersVo = (UsersVo)var5.next();
         List<OrdersVo> ordersList = this.orderDaoImpl.selectAllOrdersByUserId(usersVo.getUserId());
         Iterator var8 = ordersList.iterator();

         while(var8.hasNext()) {
            OrdersVo ordersVo = (OrdersVo)var8.next();
            Map<String, Object> map = new HashMap();
            String status = this.shipmentsDaoImpl.selectStatusByOrderID(ordersVo.getOrderId());
            String rstatus = this.refundDaoImpl.selectStatusByOrderID(ordersVo.getOrderId());
            if (rstatus != null) {
               rstatus = this.converter.statusToWord(rstatus);
               map.put("status", rstatus);
            } else if (status != null && !status.isEmpty()) {
               status = this.converter.statusToWord(status);
               map.put("status", status);
            } else {
               status = "G";
               status = this.converter.statusToWord(status);
               map.put("status", status);
            }

            map.put("userName", usersVo.getUserName());
            map.put("ordersVo", ordersVo);
            System.out.println(map);
            orderInfo.add(map);
         }
      }

      return orderInfo;
   }

   public List<OrdersVo> getOrdersByUserId(String userId) {
      return this.orderDaoImpl.selectAllOrdersByUserId(userId);
   }

   public String getShipmentStatusByOrderId(String orderId) {
      String shipmentStatus = this.shipmentsDaoImpl.selectStatusByOrderID(orderId);
      return shipmentStatus;
   }

   public String getOptionsByOrderId(String orderId) {
      String options = this.albumDaoImpl.findOptionsByOrderId(orderId);
      return options;
   }

   public AlbumVo selectByAlbumId(String albumId) {
      AlbumVo options = this.albumDaoImpl.selectByAlbumId(albumId);
      System.out.println("admin orderService의 옵션" + String.valueOf(options));
      return options;
   }

   public String getAlbumIdByOrderId(String orderId) {
      String albumId = this.orderDaoImpl.getAlbumIdByOrderId(orderId);
      return albumId;
   }

   public boolean createShipmentByOrderId(String orderId) {
      boolean result = false;
      if (this.shipmentsDaoImpl.selectStatusByOrderID(orderId) == null) {
         OrdersVo order = this.orderDaoImpl.selectByOrderId(orderId);
         InventoryVo inventoryVo = this.inventoryDaoImpl.selectOneByAlbumId(order.getAlbumId());
         if (inventoryVo.getaQuantity() >= order.getoQuantity()) {
            inventoryVo.setaQuantity(inventoryVo.getaQuantity() - order.getoQuantity());
            result = 1 == this.shipmentsDaoImpl.insert(orderId);
            this.inventoryDaoImpl.updateQuantity(inventoryVo);
         }
      }

      if (this.refundDaoImpl.selectStatusByOrderID(orderId) != null) {
         this.refundDaoImpl.delete(orderId);
      }

      return result;
   }

   public boolean createRefundByOrderId(String orderId) {
      boolean result = false;
      if (this.refundDaoImpl.selectStatusByOrderID(orderId) == null) {
         result = 1 == this.refundDaoImpl.insert(orderId);
      }

      if (this.shipmentsDaoImpl.selectStatusByOrderID(orderId) != null) {
         this.shipmentsDaoImpl.delete(orderId);
      }

      return result;
   }

   public String count() {
      String count = this.orderDaoImpl.count();
      return count;
   }

   public Map<String, Object> Salecount() {
      return this.orderDaoImpl.Salecount();
   }

   public List<Map<String, Object>> getTopAlbum() {
      return this.orderDaoImpl.getTopAlbum();
   }

   public int delete(String orderId) {
      this.photoDaoImpl.delete(orderId);
      return this.orderDaoImpl.delete(orderId);
   }
}
