package himedia.photobook.services.users;

import himedia.photobook.repositories.dao.OrderDao;
import himedia.photobook.repositories.dao.PhotoDaoImpl;
import himedia.photobook.repositories.dao.RefundDaoImpl;
import himedia.photobook.repositories.dao.ShipmentsDaoImpl;
import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.tools.DataConverter;
import himedia.photobook.tools.FileModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userOrderService")
public class UsersOrderServiceImpl {
   @Autowired
   private OrderDao orderDaoImpl;
   @Autowired
   private ShipmentsDaoImpl shipDao;
   @Autowired
   private RefundDaoImpl refundDao;
   @Autowired
   private PhotoDaoImpl photoDaoImpl;
   @Autowired
   private RefundDaoImpl refundDaoImpl;
   private DataConverter dataConverter = new DataConverter();
   private FileModule fileModule = new FileModule();

   public List<Map<String, Object>> getOrderInfos(String userId) {
      List<Map<String, Object>> orderInfoList = new ArrayList();
      Map<String, Object> orderInfos = null;
      List<OrdersVo> orderList = this.orderDaoImpl.selectAllOrdersByUserId(userId);
      String orderStatus = null;
      String refundStatus = null;
      String status = null;
      Iterator var9 = orderList.iterator();

      while(var9.hasNext()) {
         OrdersVo ordersVo = (OrdersVo)var9.next();
         orderInfos = new HashMap();
         orderStatus = null;
         refundStatus = null;
         status = null;
         orderInfos.put("ordersVo", ordersVo);
         orderStatus = this.shipDao.selectStatusByOrderID(ordersVo.getOrderId());
         refundStatus = this.refundDao.selectStatusByOrderID(ordersVo.getOrderId());
         if (refundStatus != null) {
            status = this.dataConverter.statusToWord(refundStatus);
         } else if (orderStatus != null) {
            status = this.dataConverter.statusToWord(orderStatus);
         } else {
            status = this.dataConverter.statusToWord("G");
         }

         orderInfos.put("status", status);
         orderInfoList.add(orderInfos);
      }

      return orderInfoList;
   }

   public int getOrderedImagesCount(String orderId) {
      return this.photoDaoImpl.selectCountByOrderId(orderId);
   }

   public boolean createRefundByOrderId(String orderId) {
      boolean result = false;
      if (this.refundDaoImpl.selectStatusByOrderID(orderId) == null) {
         result = 1 == this.refundDaoImpl.insert(orderId);
      }

      if (this.shipDao.selectStatusByOrderID(orderId) != null) {
         this.shipDao.delete(orderId);
      }

      return result;
   }

   public String getOrderedImagePath(String userId, String orderId) {
      String imgSrc = userId + "/" + orderId;
      if (this.fileModule.getOsName().contains("nux")) {
         imgSrc = "/nux/photobook-images/order/" + imgSrc;
      } else {
         imgSrc = "/win/photobook-images/order/" + imgSrc;
      }

      return imgSrc;
   }
}
