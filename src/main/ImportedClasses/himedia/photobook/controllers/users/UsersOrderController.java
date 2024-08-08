package himedia.photobook.controllers.users;

import himedia.photobook.repositories.vo.OrdersVo;
import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.admin.AdminOrderService;
import himedia.photobook.services.users.UsersOrderServiceImpl;
import jakarta.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/users"})
public class UsersOrderController {
   @Autowired
   private UsersOrderServiceImpl orderService;
   @Autowired
   private AdminOrderService adminOrderService;

   @GetMapping({"/order"})
   public String order(Model model, HttpSession session) {
      UsersVo user = (UsersVo)session.getAttribute("authUser");
      String userId = null;
      if (user != null) {
         userId = user.getUserId();
         List<Map<String, Object>> orderInfos = this.orderService.getOrderInfos(userId);
         model.addAttribute("orderInfos", orderInfos);
      }

      return "/WEB-INF/views/users/users_order.jsp";
   }

   @PostMapping({"/order/detail"})
   public String detail(@RequestParam("ordersId") String ordersId, @RequestParam("albumId") String albumId, @RequestParam("orderDate") String orderDate, @RequestParam("oQuantity") Long oQuantity, @RequestParam("userId") String userId, @RequestParam("status") String status, Model model) {
      OrdersVo orderVo = new OrdersVo();
      orderVo.setUserId(userId);
      orderVo.setAlbumId(albumId);
      orderVo.setOrderId(ordersId);
      orderVo.setoQuantity(oQuantity);
      orderVo.setAlbumId(albumId);
      int imgsCount = this.orderService.getOrderedImagesCount(ordersId);
      model.addAttribute("orderVo", orderVo);
      model.addAttribute("orderDate", orderDate);
      model.addAttribute("status", status);
      model.addAttribute("imagesCount", imgsCount);
      model.addAttribute("imgSrc", this.orderService.getOrderedImagePath(userId, ordersId));
      return "/WEB-INF/views/users/order/order_detail.jsp";
   }

   @PostMapping({"/order/createRefund"})
   public String createRefund(@ModelAttribute("createOrderId") String orderId, RedirectAttributes redirect) {
      boolean createResult = this.orderService.createRefundByOrderId(orderId);
      if (!createResult) {
         redirect.addFlashAttribute("error", "이미 환불 중인 주문입니다.");
      }

      return "redirect:/users/order";
   }

   @PostMapping({"/order/cancel"})
   public String cancel(@RequestParam("orderId") String orderId) {
      this.adminOrderService.delete(orderId);
      return "redirect:/users/order";
   }

   @PostMapping({"/acancel"})
   public String acancel(@RequestParam("orderId") String orderId) {
      this.adminOrderService.delete(orderId);
      return "redirect:/admin/Norder";
   }
}
