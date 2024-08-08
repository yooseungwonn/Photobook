package himedia.photobook.services.users;

import himedia.photobook.repositories.dao.AlbumDao;
import himedia.photobook.repositories.dao.InventoryDao;
import himedia.photobook.repositories.dao.OrderDao;
import himedia.photobook.repositories.dao.PhotoDao;
import himedia.photobook.repositories.vo.AlbumVo;
import himedia.photobook.repositories.vo.InventoryVo;
import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.repositories.vo.PhotoVo;
import himedia.photobook.tools.FileModule;
import himedia.photobook.tools.VoConfiguration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserPhotobookService {
   @Autowired
   private AlbumDao albumDaoImpl;
   @Autowired
   private InventoryDao inventoryDaoImpl;
   @Autowired
   private OrderDao orderDaoImpl;
   @Autowired
   private PhotoDao photoDaoImpl;
   private String DEFUALT_PATH = "C:/photobook/order/";
   private FileModule fileModule = new FileModule();
   private VoConfiguration voConfiguration = new VoConfiguration();

   public AlbumVo findAlbumIdByOptions(String material, String color, String albumSize) {
      AlbumVo albumVo = this.albumDaoImpl.findAlbumIdByOptions(material, color, albumSize);
      return albumVo;
   }

   public InventoryVo findAlbumPriceByAlbumId(String albumId) {
      InventoryVo inventoryVo = this.inventoryDaoImpl.findAlbumPriceByAlbumId(albumId);
      return inventoryVo;
   }

   public boolean orderInsert(String userId, String albumId, Long oQuantity, List<MultipartFile> files) {
      boolean result = 1 == this.orderDaoImpl.orderInsert(userId, albumId, oQuantity);
      OrdersVo orderVo = this.orderDaoImpl.selectRecentOrderByUserId(userId);
      PhotoVo photoVo = null;
      if (this.fileModule.getOsName().contains("nux")) {
         this.DEFUALT_PATH = "/photobook/order/";
      }

      if (orderVo != null) {
         String savePath = this.DEFUALT_PATH + userId + "/" + orderVo.getOrderId();
         Long number = 1L;

         for(Iterator var11 = files.iterator(); var11.hasNext(); number = number + 1L) {
            MultipartFile file = (MultipartFile)var11.next();
            String originalFileName = file.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf("."));
            String photoPath = null;

            try {
               photoPath = this.fileModule.saveFile(file, savePath, number.toString(), extName);
            } catch (Exception var16) {
               var16.printStackTrace();
            }

            photoVo = new PhotoVo((String)null, orderVo.getOrderId(), photoPath, number);
            result = result && 1 == this.photoDaoImpl.insert(photoVo);
         }
      }

      return result;
   }

   public Map<String, List<String>> getAlbumOptions() {
      Map<String, List<String>> albumOptions = new HashMap();
      albumOptions.put("colorList", this.voConfiguration.getAlbumColorList());
      albumOptions.put("materialList", this.voConfiguration.getAlbumMaterialList());
      albumOptions.put("sizeList", this.voConfiguration.getAlbumSizeList());
      return albumOptions;
   }
}
