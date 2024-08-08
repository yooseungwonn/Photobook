package himedia.photobook.repositories.vo;

import java.util.Date;

public class ShipmentsVo {
   private Long shipmentId;
   private String orderId;
   private Date shipmentDate;
   private String shipmentStatus;

   public ShipmentsVo() {
   }

   public ShipmentsVo(Long shipmentId, String orderId, Date shipmentDate, String shipmentStatus) {
      this.shipmentId = shipmentId;
      this.orderId = orderId;
      this.shipmentDate = shipmentDate;
      this.shipmentStatus = shipmentStatus;
   }

   public Long getShipmentId() {
      return this.shipmentId;
   }

   public void setShipmentId(Long shipmentId) {
      this.shipmentId = shipmentId;
   }

   public String getOrderId() {
      return this.orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public Date getShipmentDate() {
      return this.shipmentDate;
   }

   public void setShipmentDate(Date shipmentDate) {
      this.shipmentDate = shipmentDate;
   }

   public String getShipmentStatus() {
      return this.shipmentStatus;
   }

   public void setShipmentStatus(String shipmentStatus) {
      this.shipmentStatus = shipmentStatus;
   }

   public String toString() {
      String var10000 = String.valueOf(this.shipmentId);
      return "ShipmentsVo [shipmentId=" + var10000 + ", orderId=" + this.orderId + ", shipmentDate=" + String.valueOf(this.shipmentDate) + ", shipmentStatus=" + this.shipmentStatus + "]";
   }
}
