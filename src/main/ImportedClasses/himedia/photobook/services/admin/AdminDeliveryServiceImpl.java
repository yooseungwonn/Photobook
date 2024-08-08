package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.AlbumDao;
import himedia.photobook.repositories.dao.OrderDao;
import himedia.photobook.repositories.dao.RefundDao;
import himedia.photobook.repositories.dao.ShipmentsDao;
import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.repositories.vo.ShipmentsVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.tools.DataConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDeliveryServiceImpl {
   private DataConverter dataConverter = new DataConverter();
   @Autowired
   private OrderDao orderDaoImpl;
   @Autowired
   private UsersDao usersDaoImpl;
   @Autowired
   private ShipmentsDao shipmentsDaoImpl;
   @Autowired
   private RefundDao refundDaoImpl;
   @Autowired
   private AlbumDao albumDaoImpl;

   public List<Map<String, Object>> getDeliveryInfos() {
      List<Map<String, Object>> deliveryInfoList = new ArrayList();
      Map<String, Object> deliveryInfos = null;
      OrdersVo ordersVo = null;
      UsersVo usersVo = null;
      String status = null;
      List<ShipmentsVo> shipmentsList = this.shipmentsDaoImpl.selectAll();
      Iterator var8 = shipmentsList.iterator();

      while(var8.hasNext()) {
         ShipmentsVo shipmentsVo = (ShipmentsVo)var8.next();
         deliveryInfos = new HashMap();
         status = this.shipmentsStatusToString(shipmentsVo);
         ordersVo = this.orderDaoImpl.selectByOrderId(shipmentsVo.getOrderId());
         usersVo = this.usersDaoImpl.selectOneUserById(ordersVo.getUserId());
         deliveryInfos.put("shipmentsVo", shipmentsVo);
         deliveryInfos.put("shipmentDate", this.dataConverter.kstToLocal(shipmentsVo.getShipmentDate()));
         deliveryInfos.put("status", status);
         deliveryInfos.put("ordersVo", ordersVo);
         deliveryInfos.put("usersVo", usersVo);
         deliveryInfoList.add(deliveryInfos);
      }

      return deliveryInfoList;
   }

   public Map<String, Object> getDeliveryDetailInfo(String orderId) {
      Map<String, Object> deliveryDetailInfo = new HashMap();
      OrdersVo ordersVo = this.orderDaoImpl.selectByOrderId(orderId);
      UsersVo usersVo = this.usersDaoImpl.selectOneUserById(ordersVo.getUserId());
      ShipmentsVo shipmentsVo = this.shipmentsDaoImpl.selectShipmentInfoByOrderID(orderId);
      AlbumVo albumVo = this.albumDaoImpl.selectByAlbumId(ordersVo.getAlbumId());
      List<AlbumVo> albumList = this.albumDaoImpl.selectAll();
      deliveryDetailInfo.put("ordersVo", ordersVo);
      deliveryDetailInfo.put("usersVo", usersVo);
      deliveryDetailInfo.put("shipmentDate", this.dataConverter.kstToLocal(shipmentsVo.getShipmentDate()));
      deliveryDetailInfo.put("orderDate", this.dataConverter.kstToLocal(ordersVo.getOrderDate()));
      deliveryDetailInfo.put("shipmentsVo", shipmentsVo);
      deliveryDetailInfo.put("albumVo", albumVo);
      deliveryDetailInfo.put("albumList", albumList);
      return deliveryDetailInfo;
   }

   public boolean updateDeliveryInfo(Map<String, Object> updateDeliveryInfo) {
      boolean result = false;
      ShipmentsVo shipmentsVo = null;
      OrdersVo ordersVo = null;
      String targetOrderId = (String)updateDeliveryInfo.get("targetOrderId");
      if (updateDeliveryInfo.get("shipmentsVo") instanceof ShipmentsVo) {
         shipmentsVo = (ShipmentsVo)updateDeliveryInfo.get("shipmentsVo");
      }

      result = 1 == this.shipmentsDaoImpl.updateDateAndStatusByShipmentId(shipmentsVo);
      if (updateDeliveryInfo.get("ordersVo") instanceof OrdersVo) {
         ordersVo = (OrdersVo)updateDeliveryInfo.get("ordersVo");
      }

      result = result && 1 == this.orderDaoImpl.updateByOrderId(targetOrderId, ordersVo);
      return result;
   }

   public List<Map<String, Object>> searchInfosByOrderId(String keyword) {
      List<Map<String, Object>> searchInfos = new ArrayList();
      Map<String, Object> infoMap = null;
      UsersVo usersVo = null;
      String status = null;
      OrdersVo ordersVo = null;
      List<ShipmentsVo> shipmentsList = this.shipmentsDaoImpl.searchAllByOrderId(keyword);
      Iterator var9 = shipmentsList.iterator();

      while(var9.hasNext()) {
         ShipmentsVo shipmentsVo = (ShipmentsVo)var9.next();
         infoMap = new HashMap();
         status = shipmentsVo.getShipmentStatus();
         ordersVo = this.orderDaoImpl.selectByOrderId(shipmentsVo.getOrderId());
         usersVo = this.usersDaoImpl.selectOneUserById(ordersVo.getUserId());
         status = this.shipmentsStatusToString(shipmentsVo);
         infoMap.put("usersVo", usersVo);
         infoMap.put("shipmentsVo", shipmentsVo);
         infoMap.put("shipmentDate", this.dataConverter.kstToLocal(shipmentsVo.getShipmentDate()));
         infoMap.put("status", status);
         searchInfos.add(infoMap);
      }

      return searchInfos;
   }

   public List<Map<String, Object>> searchInfosByUserName(String keyword) {
      List<Map<String, Object>> searchInfos = new ArrayList();
      Map<String, Object> infoMap = null;
      ShipmentsVo shipmentsVo = null;
      String status = null;
      List<OrdersVo> ordersList = null;
      List<UsersVo> usersList = this.usersDaoImpl.searchUsers(keyword);
      Iterator var9 = usersList.iterator();

      while(var9.hasNext()) {
         UsersVo usersVo = (UsersVo)var9.next();
         ordersList = this.orderDaoImpl.selectAllOrdersByUserId(usersVo.getUserId());
         Iterator var11 = ordersList.iterator();

         while(var11.hasNext()) {
            OrdersVo ordersVo = (OrdersVo)var11.next();
            infoMap = new HashMap();
            shipmentsVo = this.shipmentsDaoImpl.selectShipmentInfoByOrderID(ordersVo.getOrderId());
            if (shipmentsVo != null) {
               status = this.shipmentsStatusToString(shipmentsVo);
               infoMap.put("usersVo", usersVo);
               infoMap.put("shipmentsVo", shipmentsVo);
               infoMap.put("shipmentDate", this.dataConverter.kstToLocal(shipmentsVo.getShipmentDate()));
               infoMap.put("status", status);
               searchInfos.add(infoMap);
            }
         }
      }

      return searchInfos;
   }

   private String shipmentsStatusToString(ShipmentsVo shipmentsVo) {
      String result = null;
      result = shipmentsVo.getShipmentStatus();
      if (result.equals("R")) {
         String refundStatus = this.refundDaoImpl.selectStatusByOrderID(shipmentsVo.getOrderId());
         if (refundStatus != null) {
            result = refundStatus;
         }
      }

      result = this.dataConverter.statusToWord(result);
      return result;
   }

   public String count() {
      String scount = this.shipmentsDaoImpl.count();
      return scount;
   }
}
