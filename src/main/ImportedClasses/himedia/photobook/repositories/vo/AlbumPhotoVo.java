package himedia.photobook.repositories.vo;

public class AlbumPhotoVo {
   private String albumPhotoId;
   private String albumId;
   private String photoPath;
   private String albumPhotoType;

   public AlbumPhotoVo() {
   }

   public AlbumPhotoVo(String albumPhotoId, String albumId, String photoPath, String albumPhotoType) {
      this.albumPhotoId = albumPhotoId;
      this.albumId = albumId;
      this.photoPath = photoPath;
      this.albumPhotoType = albumPhotoType;
   }

   public String getAlbumPhotoId() {
      return this.albumPhotoId;
   }

   public void setAlbumPhotoId(String albumPhotoId) {
      this.albumPhotoId = albumPhotoId;
   }

   public String getAlbumId() {
      return this.albumId;
   }

   public void setAlbumId(String albumId) {
      this.albumId = albumId;
   }

   public String getPhotoPath() {
      return this.photoPath;
   }

   public void setPhotoPath(String photoPath) {
      this.photoPath = photoPath;
   }

   public String getAlbumPhotoType() {
      return this.albumPhotoType;
   }

   public void setAlbumPhotoType(String albumPhotoType) {
      this.albumPhotoType = albumPhotoType;
   }

   public String toString() {
      return "AlbumPhotoVo [albumPhotoId=" + this.albumPhotoId + ", albumId=" + this.albumId + ", photoPath=" + this.photoPath + ", albumPhotoType=" + this.albumPhotoType + "]";
   }
}
