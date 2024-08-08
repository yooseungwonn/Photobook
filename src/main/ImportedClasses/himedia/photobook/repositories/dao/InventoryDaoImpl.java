package himedia.photobook.repositories.dao;

import himedia.photobook.exceptions.UsersAlbumException;
import himedia.photobook.repositories.vo.InventoryVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDaoImpl implements InventoryDao {
   @Autowired
   private SqlSession sqlSession;

   public List<InventoryVo> listInventory() {
      return this.sqlSession.selectList("inventory.listInventory");
   }

   public InventoryVo findAlbumPriceByAlbumId(String albumId) {
      Map<String, String> ai = new HashMap();
      ai.put("albumId", albumId);
      InventoryVo inventoryVo = (InventoryVo)this.sqlSession.selectOne("inventory.findAlbumPriceByAlbumId", ai);
      return inventoryVo;
   }

   public int updateProduct(InventoryVo vo) {
      try {
         int updatedCount = this.sqlSession.update("inventory.updatePrice", vo);
         return updatedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersAlbumException("업데이트 도중 예외 발생!");
      }
   }

   public List<InventoryVo> listPage(RowBounds rowBounds) {
      return this.sqlSession.selectList("inventory.listInventory", (Object)null, rowBounds);
   }

   public int getTotalCount() {
      return (Integer)this.sqlSession.selectOne("inventory.getTotalCount");
   }

   public int delete(String albumId) {
      return this.sqlSession.delete("inventory.deleteInventory", albumId);
   }

   public int insertInventory(InventoryVo inventoryVo) {
      return this.sqlSession.insert("inventory.insertInventory", inventoryVo);
   }

   public int updateQuantity(InventoryVo inventoryVo) {
      return this.sqlSession.update("inventory.updateQuantity", inventoryVo);
   }

   public InventoryVo selectOneByAlbumId(String albumId) {
      return (InventoryVo)this.sqlSession.selectOne("inventory.selectOneByAlbumId", albumId);
   }
}
