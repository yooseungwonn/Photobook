package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.OrdersVo;
import java.util.List;
import java.util.Map;

public interface OrderDao {
   int orderInsert(String var1, String var2, Long var3);

   List<OrdersVo> selectAllOrdersByUserId(String var1);

   List<OrdersVo> selectAllOrders();

   OrdersVo selectByOrderId(String var1);

   String getUserIdByUserName(String var1);

   String getAlbumIdByOrderId(String var1);

   int updateByOrderId(String var1, OrdersVo var2);

   OrdersVo selectRecentOrderByUserId(String var1);

   String count();

   Map<String, Object> Salecount();

   List<Map<String, Object>> getTopAlbum();

   int delete(String var1);
}
