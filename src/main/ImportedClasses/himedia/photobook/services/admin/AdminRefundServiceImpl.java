package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.OrderDao;
import himedia.photobook.repositories.dao.RefundDao;
import himedia.photobook.repositories.dao.ShipmentsDao;
import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.repositories.vo.RefundVo;
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
public class AdminRefundServiceImpl {
   private DataConverter dataConverter = new DataConverter();
   @Autowired
   private RefundDao refundDaoImpl;
   @Autowired
   private OrderDao orderDaoImpl;
   @Autowired
   private UsersDao usersDaoImpl;
   @Autowired
   private ShipmentsDao shipmentsDaoImpl;

   public List<Map<String, Object>> getRefundInfos() {
      List<Map<String, Object>> refundInfoList = new ArrayList();
      Map<String, Object> refundInfos = null;
      OrdersVo ordersVo = null;
      UsersVo usersVo = null;
      String status = null;
      List<RefundVo> refundList = this.refundDaoImpl.selectAllRefunds();
      Iterator var8 = refundList.iterator();

      while(var8.hasNext()) {
         RefundVo refundVo = (RefundVo)var8.next();
         refundInfos = new HashMap();
         ordersVo = this.orderDaoImpl.selectByOrderId(refundVo.getOrderId());
         usersVo = this.usersDaoImpl.selectOneUserById(ordersVo.getUserId());
         status = this.dataConverter.statusToWord(refundVo.getRefundStatus());
         refundInfos.put("ordersVo", ordersVo);
         refundInfos.put("userName", usersVo.getUserName());
         refundInfos.put("refundVo", refundVo);
         refundInfos.put("status", status);
         refundInfoList.add(refundInfos);
      }

      return refundInfoList;
   }

   public boolean updateStatusToFinish(String orderId) {
      RefundVo refundVo = new RefundVo();
      refundVo.setOrderId(orderId);
      refundVo.setRefundStatus("F");
      return 1 == this.refundDaoImpl.updateStatus(refundVo);
   }

   public boolean delete(String orderId) {
      return 1 == this.refundDaoImpl.delete(orderId);
   }

   public List<Map<String, Object>> searchInfosByUserName(String keyword) {
      List<Map<String, Object>> searchInfos = new ArrayList();
      Map<String, Object> infoMap = null;
      RefundVo refundVo = null;
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
            refundVo = this.refundDaoImpl.selectOneByOrderId(ordersVo.getOrderId());
            if (refundVo != null) {
               status = this.dataConverter.statusToWord(refundVo.getRefundStatus());
               infoMap.put("usersVo", usersVo);
               infoMap.put("userName", usersVo.getUserName());
               infoMap.put("refundVo", refundVo);
               infoMap.put("ordersVo", ordersVo);
               infoMap.put("status", status);
               searchInfos.add(infoMap);
            }
         }
      }

      return searchInfos;
   }

   public List<Map<String, Object>> searchInfosByOrderId(String keyword) {
      List<Map<String, Object>> searchInfos = new ArrayList();
      Map<String, Object> infoMap = null;
      RefundVo refundVo = null;
      OrdersVo ordersVo = null;
      String status = null;
      UsersVo usersVo = null;
      List<ShipmentsVo> shipmentsList = this.shipmentsDaoImpl.searchAllByOrderId(keyword);
      Iterator var10 = shipmentsList.iterator();

      while(var10.hasNext()) {
         ShipmentsVo shipmentsVo = (ShipmentsVo)var10.next();
         infoMap = new HashMap();
         refundVo = this.refundDaoImpl.selectOneByOrderId(shipmentsVo.getOrderId());
         if (refundVo != null) {
            ordersVo = this.orderDaoImpl.selectByOrderId(refundVo.getOrderId());
            status = refundVo.getRefundStatus();
            usersVo = this.usersDaoImpl.selectOneUserById(ordersVo.getUserId());
            infoMap.put("usersVo", usersVo);
            infoMap.put("userName", usersVo.getUserName());
            infoMap.put("status", status);
            infoMap.put("ordersVo", ordersVo);
            infoMap.put("refundVo", refundVo);
            searchInfos.add(infoMap);
         }
      }

      return searchInfos;
   }

   public String count() {
      String count = this.refundDaoImpl.count();
      return count;
   }
}
