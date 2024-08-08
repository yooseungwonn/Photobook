package himedia.photobook.controllers.admin;

import himedia.photobook.services.admin.AdminRefundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminRefundController {
   @Autowired
   private AdminRefundServiceImpl adminRefundServiceImpl;

   @GetMapping({"/refund"})
   public String refund(Model model) {
      model.addAttribute("refundInfos", this.adminRefundServiceImpl.getRefundInfos());
      return "/WEB-INF/views/admin/admin_refund.jsp";
   }

   @GetMapping({"/refund/searchResult"})
   public String searchResult(Model model) {
      model.addAttribute("refundInfos", model.getAttribute("searchInfos"));
      System.out.println(model.getAttribute("searchInfos"));
      return "/WEB-INF/views/admin/admin_refund.jsp";
   }

   @PostMapping({"/refund/changeStatus"})
   public String changeStatus(@RequestParam("orderId") String orderId) {
      this.adminRefundServiceImpl.updateStatusToFinish(orderId);
      return "redirect:/admin/refund";
   }

   @PostMapping({"/refund/cancle"})
   public String cancle(@RequestParam("orderId") String orderId) {
      this.adminRefundServiceImpl.delete(orderId);
      return "redirect:/admin/refund";
   }

   @PostMapping({"/refund/search"})
   public String search(Model model, @RequestParam("keyword") String keyword, @RequestParam("search-category") String category) {
      if (category.equals("orderId")) {
         model.addAttribute("refundInfos", this.adminRefundServiceImpl.searchInfosByOrderId(keyword));
      } else if (category.equals("usersName")) {
         model.addAttribute("refundInfos", this.adminRefundServiceImpl.searchInfosByUserName(keyword));
      }

      return "/WEB-INF/views/admin/admin_refund.jsp";
   }

   @GetMapping({"Nrefund"})
   public String Nrefund(Model model) {
      model.addAttribute("refundInfos", this.adminRefundServiceImpl.getRefundInfos());
      return "/WEB-INF/views/admin/admin_none_refund.jsp";
   }
}
