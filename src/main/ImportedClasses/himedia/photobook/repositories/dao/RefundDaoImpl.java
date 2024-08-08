package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.RefundVo;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RefundDaoImpl implements RefundDao {
   @Autowired
   private SqlSession session;

   public String selectStatusByOrderID(String orderId) {
      return (String)this.session.selectOne("refund.selectStatusByOrderID", orderId);
   }

   public List<RefundVo> selectAllRefunds() {
      return this.session.selectList("refund.selectAllRefunds");
   }

   public RefundVo selectOneByOrderId(String orderId) {
      return (RefundVo)this.session.selectOne("refund.selectOneByOrderId", orderId);
   }

   public int insert(String orderId) {
      return this.session.insert("refund.insert", orderId);
   }

   public int updateStatus(RefundVo refundVo) {
      return this.session.update("refund.updateStatus", refundVo);
   }

   public int delete(String orderId) {
      return this.session.delete("refund.delete", orderId);
   }

   public String count() {
      return (String)this.session.selectOne("refund.countRefund");
   }
}
