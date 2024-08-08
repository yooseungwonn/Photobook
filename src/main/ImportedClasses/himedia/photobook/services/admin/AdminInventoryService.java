package himedia.photobook.services.admin;

import himedia.photobook.repositories.vo.InventoryVo;
import java.util.List;

public interface AdminInventoryService {
   List<InventoryVo> getInvenInfos();

   InventoryVo findAlbumPriceByAlbumId(String var1);

   boolean updateQuantity(InventoryVo var1);

   int getTotalCount();

   List<InventoryVo> getPagedInventory(int var1, int var2);
}
