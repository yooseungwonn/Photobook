package himedia.photobook.repositories.vo;

public class InventoryVo {
   private String albumId;
   private Long albumPrice;
   private Long aQuantity;

   public InventoryVo() {
   }

   public InventoryVo(String albumId, Long albumPrice, Long aQuantity) {
      this.albumId = albumId;
      this.albumPrice = albumPrice;
      this.aQuantity = aQuantity;
   }

   public String getAlbumId() {
      return this.albumId;
   }

   public void setAlbumId(String albumId) {
      this.albumId = albumId;
   }

   public Long getAlbumPrice() {
      return this.albumPrice;
   }

   public void setAlbumPrice(Long albumPrice) {
      this.albumPrice = albumPrice;
   }

   public Long getaQuantity() {
      return this.aQuantity;
   }

   public void setaQuantity(Long aQuantity) {
      this.aQuantity = aQuantity;
   }

   public String toString() {
      String var10000 = this.albumId;
      return "InventoryVo [albumId=" + var10000 + ", albumPrice=" + String.valueOf(this.albumPrice) + ", aQuantity=" + String.valueOf(this.aQuantity) + "]";
   }
}
