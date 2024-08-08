package himedia.photobook.repositories.vo;

public class PhotoVo {
   private String photoId;
   private String orderId;
   private String photoPath;
   private Long photoSeqNo;

   public PhotoVo() {
   }

   public PhotoVo(String photoId, String orderId, String photoPath, Long photoSeqNo) {
      this.photoId = photoId;
      this.orderId = orderId;
      this.photoPath = photoPath;
      this.photoSeqNo = photoSeqNo;
   }

   public String getPhotoId() {
      return this.photoId;
   }

   public void setPhotoId(String photoId) {
      this.photoId = photoId;
   }

   public String getOrderId() {
      return this.orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getPhotoPath() {
      return this.photoPath;
   }

   public void setPhotoPath(String photoPath) {
      this.photoPath = photoPath;
   }

   public Long getPhotoSeqNo() {
      return this.photoSeqNo;
   }

   public void setPhotoSeqNo(Long photoSeqNo) {
      this.photoSeqNo = photoSeqNo;
   }

   public String toString() {
      String var10000 = this.photoId;
      return "PhotoVo [photoId=" + var10000 + ", orderId=" + this.orderId + ", photoPath=" + this.photoPath + ", photoSeqNo=" + String.valueOf(this.photoSeqNo) + "]";
   }
}
