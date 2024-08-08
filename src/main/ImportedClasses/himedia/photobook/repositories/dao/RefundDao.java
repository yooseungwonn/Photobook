package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.RefundVo;
import java.util.List;

public interface RefundDao {
   String selectStatusByOrderID(String var1);

   List<RefundVo> selectAllRefunds();

   int insert(String var1);

   int updateStatus(RefundVo var1);

   int delete(String var1);

   RefundVo selectOneByOrderId(String var1);

   String count();
}
