package himedia.photobook.tools;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VoConfiguration {
   public List<String> getAlbumMaterialList() {
      List<String> materialList = (List)Stream.of(himedia.photobook.tools.VoConfiguration.ALBUM_MATERIAL.values()).map(Enum::name).collect(Collectors.toList());
      return materialList;
   }

   public List<String> getAlbumColorList() {
      List<String> colorList = (List)Stream.of(himedia.photobook.tools.VoConfiguration.ALBUM_COLOR.values()).map(Enum::name).collect(Collectors.toList());
      return colorList;
   }

   public List<String> getAlbumSizeList() {
      List<String> sizeList = (List)Stream.of(himedia.photobook.tools.VoConfiguration.ALBUM_SIZE.values()).map(Enum::name).collect(Collectors.toList());
      return sizeList;
   }
}
