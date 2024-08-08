package himedia.photobook.controllers.admin;

import himedia.photobook.services.admin.AdminOrderService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminOrderController {
   @Autowired
   private AdminOrderService adminOrderService;

   @GetMapping({"/om"})
   public String order(Model model) {
      List<Map<String, Object>> orderList = this.adminOrderService.getOrderAdmin();
      model.addAttribute("orderList", orderList);
      return "/WEB-INF/views/admin/admin_order_management.jsp";
   }

   @GetMapping({"/Norder"})
   public String Norder(Model model) {
      List<Map<String, Object>> orderList = this.adminOrderService.getOrderAdmin();
      model.addAttribute("orderList", orderList);
      return "/WEB-INF/views/admin/admin_none_order.jsp";
   }

   @GetMapping({"/order/detail"})
   public String orderDetail(@RequestParam("orderId") String orderId, Model model) {
      Map<String, Object> orderDetail = this.adminOrderService.getOrderDetail(orderId);
      model.addAttribute("orderDetail", orderDetail);
      return "/WEB-INF/views/admin/admin_order_detail.jsp";
   }

   @GetMapping({"/order/search"})
   public String searchUserId(@RequestParam(value = "keyword",required = false) String keyword, Model model) {
      model.addAttribute("orderList", this.adminOrderService.searchOrderInfo(keyword));
      return "/WEB-INF/views/admin/admin_order_management.jsp";
   }

   @PostMapping({"/order/createShipment"})
   public String createOrder(@ModelAttribute("createOrderId") String orderId) {
      this.adminOrderService.createShipmentByOrderId(orderId);
      return "redirect:/admin/om";
   }

   @PostMapping({"/order/createRefund"})
   public String createRefund(@ModelAttribute("createOrderId") String orderId) {
      this.adminOrderService.createRefundByOrderId(orderId);
      return "redirect:/admin/om";
   }

   @PostMapping({"/acreateShipment"})
   public String acreateOrder(@ModelAttribute("createOrderId") String orderId) {
      this.adminOrderService.createShipmentByOrderId(orderId);
      return "redirect:/admin/Norder";
   }

   @PostMapping({"/acreateRefund"})
   public String acreateRefund(@ModelAttribute("createOrderId") String orderId) {
      this.adminOrderService.createRefundByOrderId(orderId);
      return "redirect:/admin/Norder";
   }

   @PostMapping({"/order/cancel"})
   public String cancel(@RequestParam("orderId") String orderId) {
      this.adminOrderService.delete(orderId);
      return "redirect:/admin/om";
   }

   @PostMapping({"/acancel"})
   public String acancel(@RequestParam("orderId") String orderId) {
      this.adminOrderService.delete(orderId);
      return "redirect:/admin/Norder";
   }
}
