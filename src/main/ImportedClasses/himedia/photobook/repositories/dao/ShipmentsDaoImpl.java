package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.ShipmentsVo;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentsDaoImpl implements ShipmentsDao {
   @Autowired
   private SqlSession session;

   public String selectStatusByOrderID(String orderId) {
      return (String)this.session.selectOne("shipments.selectStatusByOrderID", orderId);
   }

   public ShipmentsVo selectShipmentInfoByOrderID(String orderId) {
      return (ShipmentsVo)this.session.selectOne("shipments.selectShipmentInfoByOrderID", orderId);
   }

   public List<ShipmentsVo> selectAll() {
      return this.session.selectList("shipments.selectAll");
   }

   public int updateDateAndStatusByShipmentId(ShipmentsVo updateVo) {
      return this.session.update("shipments.updateDateAndStatusByShipmentId", updateVo);
   }

   public List<ShipmentsVo> searchAllByOrderId(String keyword) {
      return this.session.selectList("shipments.searchAllByOrderId", keyword);
   }

   public int insert(String orderId) {
      return this.session.insert("shipments.insert", orderId);
   }

   public String count() {
      return (String)this.session.selectOne("shipments.countShipment");
   }

   public int delete(String orderId) {
      return this.session.delete("shipments.delete", orderId);
   }
}
