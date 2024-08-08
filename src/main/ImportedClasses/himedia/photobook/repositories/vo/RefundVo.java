package himedia.photobook.repositories.vo;

public class RefundVo {
   private Long refundId;
   private String orderId;
   private String refundStatus;

   public RefundVo() {
   }

   public RefundVo(Long refundId, String orderId, String refundStatus) {
      this.refundId = refundId;
      this.orderId = orderId;
      this.refundStatus = refundStatus;
   }

   public Long getRefundId() {
      return this.refundId;
   }

   public void setRefundId(Long refundId) {
      this.refundId = refundId;
   }

   public String getOrderId() {
      return this.orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getRefundStatus() {
      return this.refundStatus;
   }

   public void setRefundStatus(String refundStatus) {
      this.refundStatus = refundStatus;
   }

   public String toString() {
      String var10000 = String.valueOf(this.refundId);
      return "RefundVo [refundId=" + var10000 + ", orderId=" + this.orderId + ", refundStatus=" + this.refundStatus + "]";
   }
}
