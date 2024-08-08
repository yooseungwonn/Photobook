package himedia.photobook.repositories.vo;

import java.util.Date;

public class OrdersVo {
   private String orderId;
   private String userId;
   private String albumId;
   private Date orderDate;
   private String total;
   private Long oQuantity;

   public OrdersVo() {
   }

   public OrdersVo(String orderId, String userId, String albumId, Date orderDate, String total, Long oQuantity) {
      this.orderId = orderId;
      this.userId = userId;
      this.albumId = albumId;
      this.orderDate = orderDate;
      this.total = total;
      this.oQuantity = oQuantity;
   }

   public String getOrderId() {
      return this.orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getAlbumId() {
      return this.albumId;
   }

   public void setAlbumId(String albumId) {
      this.albumId = albumId;
   }

   public Date getOrderDate() {
      return this.orderDate;
   }

   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   public String getTotal() {
      return this.total;
   }

   public void setTotal(String total) {
      this.total = total;
   }

   public Long getoQuantity() {
      return this.oQuantity;
   }

   public void setoQuantity(Long oQuantity) {
      this.oQuantity = oQuantity;
   }

   public String toString() {
      String var10000 = this.orderId;
      return "OrdersVo [orderId=" + var10000 + ", userId=" + this.userId + ", albumId=" + this.albumId + ", orderDate=" + String.valueOf(this.orderDate) + ", total=" + this.total + ", oQuantity=" + String.valueOf(this.oQuantity) + "]";
   }
}
