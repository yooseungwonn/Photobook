package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.ShipmentsVo;
import java.util.List;

public interface ShipmentsDao {
   String selectStatusByOrderID(String var1);

   ShipmentsVo selectShipmentInfoByOrderID(String var1);

   List<ShipmentsVo> selectAll();

   int updateDateAndStatusByShipmentId(ShipmentsVo var1);

   List<ShipmentsVo> searchAllByOrderId(String var1);

   int insert(String var1);

   String count();

   int delete(String var1);
}
