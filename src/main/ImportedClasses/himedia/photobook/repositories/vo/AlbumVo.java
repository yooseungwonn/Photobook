package himedia.photobook.repositories.vo;

public class AlbumVo {
   private String albumId;
   private String material;
   private String color;
   private String albumSize;

   public AlbumVo() {
   }

   public AlbumVo(String albumId, String material, String color, String albumSize) {
      this.albumId = albumId;
      this.material = material;
      this.color = color;
      this.albumSize = albumSize;
   }

   public String getAlbumId() {
      return this.albumId;
   }

   public void setAlbumId(String albumId) {
      this.albumId = albumId;
   }

   public String getMaterial() {
      return this.material;
   }

   public void setMaterial(String material) {
      this.material = material;
   }

   public String getColor() {
      return this.color;
   }

   public void setColor(String color) {
      this.color = color;
   }

   public String getAlbumSize() {
      return this.albumSize;
   }

   public void setAlbumSize(String albumSize) {
      this.albumSize = albumSize;
   }

   public String toString() {
      return "AlbumVo [albumId=" + this.albumId + ", material=" + this.material + ", color=" + this.color + ", albumSize=" + this.albumSize + "]";
   }
}
