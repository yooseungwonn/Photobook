package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.OrdersVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("orderDaoImpl")
public class OrderDaoImpl implements OrderDao {
   @Autowired
   private SqlSession sqlSession;

   public int orderInsert(String userId, String albumId, Long oQuantity) {
      Map<String, Object> orderMap = new HashMap();
      orderMap.put("userId", userId);
      orderMap.put("albumId", albumId);
      orderMap.put("oQuantity", oQuantity);
      return this.sqlSession.insert("order.orderInsert", orderMap);
   }

   public List<OrdersVo> selectAllOrdersByUserId(String userId) {
      return this.sqlSession.selectList("order.selectAllOrdersByUserId", userId);
   }

   public List<OrdersVo> selectAllOrders() {
      return this.sqlSession.selectList("order.selectAllOrders");
   }

   public OrdersVo selectByOrderId(String orderId) {
      return (OrdersVo)this.sqlSession.selectOne("order.selectByOrderId", orderId);
   }

   public String getUserIdByUserName(String keyword) {
      return (String)this.sqlSession.selectOne("users.getUserIdByUserName", keyword);
   }

   public String getAlbumIdByOrderId(String orderId) {
      return (String)this.sqlSession.selectOne("order.getAlbumIdByOrderId", orderId);
   }

   public int updateByOrderId(String updateId, OrdersVo ordersVo) {
      Map<String, Object> updateMap = new HashMap();
      updateMap.put("updateId", updateId);
      updateMap.put("orderId", ordersVo.getOrderId());
      updateMap.put("albumId", ordersVo.getAlbumId());
      updateMap.put("orderDate", ordersVo.getOrderDate());
      updateMap.put("oQuantity", ordersVo.getoQuantity());
      updateMap.put("total", ordersVo.getTotal());
      return this.sqlSession.update("order.updateByOrderId", updateMap);
   }

   public OrdersVo selectRecentOrderByUserId(String userId) {
      return (OrdersVo)this.sqlSession.selectOne("order.selectRecentOrderByUserId", userId);
   }

   public String count() {
      return (String)this.sqlSession.selectOne("order.countOrder");
   }

   public Map<String, Object> Salecount() {
      return (Map)this.sqlSession.selectOne("order.countSale");
   }

   public List<Map<String, Object>> getTopAlbum() {
      return this.sqlSession.selectList("order.getTopAlbum");
   }

   public int delete(String orderId) {
      return this.sqlSession.delete("order.delete", orderId);
   }
}
