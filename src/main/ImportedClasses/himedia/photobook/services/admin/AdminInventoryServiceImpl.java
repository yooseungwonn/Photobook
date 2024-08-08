package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.InventoryDao;
import himedia.photobook.repositories.vo.InventoryVo;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInventoryServiceImpl implements AdminInventoryService {
   @Autowired
   private InventoryDao inventoryDaoImpl;

   public List<InventoryVo> getInvenInfos() {
      List<InventoryVo> invenList = this.inventoryDaoImpl.listInventory();
      return invenList;
   }

   public List<InventoryVo> getPagedInventory(int page, int pageSize) {
      int offset = (page - 1) * pageSize;
      RowBounds rowBounds = new RowBounds(offset, pageSize);
      System.out.println(rowBounds);
      return this.inventoryDaoImpl.listPage(rowBounds);
   }

   public InventoryVo findAlbumPriceByAlbumId(String albumId) {
      InventoryVo inventoryVo = this.inventoryDaoImpl.findAlbumPriceByAlbumId(albumId);
      System.out.println("service:" + String.valueOf(inventoryVo));
      return inventoryVo;
   }

   public boolean updateQuantity(InventoryVo inventoryVo) {
      InventoryVo invenVo = this.inventoryDaoImpl.findAlbumPriceByAlbumId(inventoryVo.getAlbumId());
      inventoryVo.setaQuantity(inventoryVo.getaQuantity() + invenVo.getaQuantity());
      int updatedCount = this.inventoryDaoImpl.updateQuantity(inventoryVo);
      return updatedCount == 1;
   }

   public int getTotalCount() {
      return this.inventoryDaoImpl.getTotalCount();
   }
}
