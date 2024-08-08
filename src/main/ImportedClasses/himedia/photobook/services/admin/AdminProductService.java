package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.AlbumDao;
import himedia.photobook.repositories.dao.AlbumPhotoDao;
import himedia.photobook.repositories.dao.InventoryDao;
import himedia.photobook.repositories.vo.AlbumPhotoVo;
import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.tools.FileModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminProductService {
   @Autowired
   private AlbumDao albumDaoImpl;
   @Autowired
   private InventoryDao inventoryDaoImpl;
   @Autowired
   private AlbumPhotoDao albumPhotoDaoImpl;
   private FileModule fileModule = new FileModule();
   private String DEFUALT_PATH = "C:/photobook/album/";

   public List<AlbumVo> searchAlbum(String searchCategory, String keyword) {
      return this.albumDaoImpl.searchAlbum(keyword);
   }

   public List<InventoryVo> listInventory() {
      return this.inventoryDaoImpl.listInventory();
   }

   public boolean updateAlbum(AlbumVo vo) {
      int updatedCount = this.albumDaoImpl.updateAlbum(vo);
      return updatedCount == 1;
   }

   public int insertAlbum(AlbumVo vo) {
      return this.albumDaoImpl.insertAlbum(vo);
   }

   public List<Map<String, Object>> getProductInfos() {
      List<Map<String, Object>> productInfoList = new ArrayList();
      List<AlbumVo> albumlist = this.albumDaoImpl.selectAll();
      Iterator var4 = albumlist.iterator();

      while(var4.hasNext()) {
         AlbumVo albumVo = (AlbumVo)var4.next();
         Map<String, Object> productMap = new HashMap();
         InventoryVo inventoryVo = this.inventoryDaoImpl.selectOneByAlbumId(albumVo.getAlbumId());
         String imgSrc = this.albumPhotoDaoImpl.getAlbumPhotoPath(albumVo.getAlbumId());
         productMap.put("albumVo", albumVo);
         productMap.put("inventoryVo", inventoryVo);
         if (this.fileModule.getOsName().contains("nux")) {
            imgSrc = "/nux/photobook-images/album/" + imgSrc;
         } else {
            imgSrc = "/win/photobook-images/album/" + imgSrc;
         }

         productMap.put("imgSrc", imgSrc);
         productInfoList.add(productMap);
      }

      return productInfoList;
   }

   public List<Map<String, Object>> getProductInfos(String keyword) {
      List<Map<String, Object>> productInfoList = new ArrayList();
      List<AlbumVo> albumlist = this.albumDaoImpl.searchAlbum(keyword);
      Iterator var5 = albumlist.iterator();

      while(var5.hasNext()) {
         AlbumVo albumVo = (AlbumVo)var5.next();
         Map<String, Object> productMap = new HashMap();
         InventoryVo inventoryVo = this.inventoryDaoImpl.selectOneByAlbumId(albumVo.getAlbumId());
         String imgSrc = this.albumPhotoDaoImpl.getAlbumPhotoPath(albumVo.getAlbumId());
         productMap.put("albumVo", albumVo);
         productMap.put("inventoryVo", inventoryVo);
         if (this.fileModule.getOsName().contains("nux")) {
            imgSrc = "/nux/photobook-images/album/" + imgSrc;
         } else {
            imgSrc = "/win/photobook-images/album/" + imgSrc;
         }

         productMap.put("imgSrc", imgSrc);
         productInfoList.add(productMap);
      }

      return productInfoList;
   }

   public Map<String, Object> getAlbumInfoMap(String albumId) {
      AlbumVo albumVo = this.albumDaoImpl.selectByAlbumId(albumId);
      InventoryVo inventory = this.inventoryDaoImpl.selectOneByAlbumId(albumId);
      String imgSrc = this.albumPhotoDaoImpl.getAlbumPhotoPath(albumVo.getAlbumId());
      Map<String, Object> productMap = new HashMap();
      if (inventory != null) {
         productMap.put("album", albumVo);
         productMap.put("inventory", inventory);
         if (this.fileModule.getOsName().contains("nux")) {
            imgSrc = "/nux/photobook-images/album/" + imgSrc;
         } else {
            imgSrc = "/win/photobook-images/album/" + imgSrc;
         }

         productMap.put("imgSrc", imgSrc);
      }

      return productMap;
   }

   public boolean updateProduct(AlbumVo albumVo, Long albumPrice, MultipartFile multipartFile) {
      boolean result = false;
      int updatedCount = this.albumDaoImpl.updateAlbum(albumVo);
      result = 1 == updatedCount;
      InventoryVo inventoryVo = new InventoryVo();
      inventoryVo.setAlbumId(albumVo.getAlbumId());
      inventoryVo.setAlbumPrice(albumPrice);
      result = result && 1 == this.inventoryDaoImpl.updateProduct(inventoryVo);
      if (this.fileModule.getOsName().contains("nux")) {
         this.DEFUALT_PATH = "/photobook/album/";
      }

      AlbumPhotoVo albumPhotoVo = null;
      if (result) {
         String var10000 = this.DEFUALT_PATH;
         String savePath = var10000 + albumVo.getAlbumId();
         String originalFileName = multipartFile.getOriginalFilename();
         String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
         String photoPath = null;

         try {
            photoPath = this.fileModule.saveFile(multipartFile, savePath, "mainImg", extName);
         } catch (Exception var13) {
            var13.printStackTrace();
         }

         var10000 = albumVo.getAlbumId();
         photoPath = "/" + var10000 + "/" + photoPath;
         albumPhotoVo = new AlbumPhotoVo((String)null, albumVo.getAlbumId(), photoPath, (String)null);
         result = result && 1 == this.albumPhotoDaoImpl.updatePath(albumPhotoVo);
      }

      return result;
   }

   public boolean deleteProduct(String albumId) {
      System.out.println("album delete 실행");
      boolean result = 1 == this.inventoryDaoImpl.delete(albumId);
      System.out.println("inventoryDaoImpl delete 결과 " + result);
      result = result && 1 == this.albumPhotoDaoImpl.delete(albumId);
      System.out.println("albumPhotoDaoImpl delete 결과 " + result);
      result = result && 1 == this.albumDaoImpl.delete(albumId);
      System.out.println("albumDaoImpl delete 결과 " + result);
      return result;
   }

   public boolean insertProduct(AlbumVo albumVo, Long albumPrice, MultipartFile multipartFile) {
      boolean result = false;
      int albumInsertedCount = this.albumDaoImpl.insertAlbum(albumVo);
      result = 1 == albumInsertedCount;
      InventoryVo inventoryVo = new InventoryVo();
      inventoryVo.setAlbumId(albumVo.getAlbumId());
      inventoryVo.setAlbumPrice(albumPrice);
      inventoryVo.setaQuantity(0L);
      int inventoryInsertedCount = this.inventoryDaoImpl.insertInventory(inventoryVo);
      result = result && 1 == inventoryInsertedCount;
      if (this.fileModule.getOsName().contains("nux")) {
         this.DEFUALT_PATH = "/photobook/album/";
      }

      AlbumPhotoVo albumPhotoVo = null;
      if (result) {
         String var10000 = this.DEFUALT_PATH;
         String savePath = var10000 + albumVo.getAlbumId();
         String originalFileName = multipartFile.getOriginalFilename();
         String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
         String photoPath = null;

         try {
            photoPath = this.fileModule.saveFile(multipartFile, savePath, "mainImg", extName);
         } catch (Exception var14) {
            var14.printStackTrace();
         }

         var10000 = albumVo.getAlbumId();
         photoPath = "/" + var10000 + "/" + photoPath;
         albumPhotoVo = new AlbumPhotoVo((String)null, albumVo.getAlbumId(), photoPath, "M");
         result = result && 1 == this.albumPhotoDaoImpl.insert(albumPhotoVo);
      }

      return result;
   }
}
