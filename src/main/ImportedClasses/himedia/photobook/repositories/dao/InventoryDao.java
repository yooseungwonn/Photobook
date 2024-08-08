package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.InventoryVo;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface InventoryDao {
   InventoryVo findAlbumPriceByAlbumId(String var1);

   List<InventoryVo> listInventory();

   List<InventoryVo> listPage(RowBounds var1);

   int getTotalCount();

   int updateProduct(InventoryVo var1);

   int delete(String var1);

   int updateQuantity(InventoryVo var1);

   InventoryVo selectOneByAlbumId(String var1);

   int insertInventory(InventoryVo var1);
}
