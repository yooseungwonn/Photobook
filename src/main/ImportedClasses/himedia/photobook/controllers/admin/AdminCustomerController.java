package himedia.photobook.controllers.admin;

import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.admin.AdminCustomerService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/admin"})
public class AdminCustomerController {
   @Autowired
   private AdminCustomerService adminCustomerService;

   @GetMapping({"/customerManagement", "/customerManage", "/cm"})
   public String customerManagement() {
      return "/WEB-INF/views/admin/admin_customer_management.jsp";
   }

   @GetMapping({"/search"})
   public String searchUSers(@RequestParam Map<String, String> params, Model model) {
      String searchCategory = (String)params.getOrDefault("search-category", (Object)null);
      String keyword = (String)params.getOrDefault("keyword", (Object)null);
      List<UsersVo> userList = this.adminCustomerService.searchUsers(searchCategory, keyword);
      model.addAttribute("userList", userList);
      return "/WEB-INF/views/admin/admin_customer_management.jsp";
   }

   @GetMapping({"/update"})
   public String updateUsersPage(Model model, @RequestParam("userId") String userId) {
      model.addAttribute("userId", userId);
      model.addAttribute("user", this.adminCustomerService.getUserById(userId));
      return "/WEB-INF/views/admin/admin_customer_update.jsp";
   }

   @PostMapping({"/updateUsers"})
   public String updateUsers(UsersVo updatedUser, HttpSession session, @RequestParam("userId") String userId) {
      UsersVo currentUser = (UsersVo)session.getAttribute("authUser");
      updatedUser.setUserId(userId);
      boolean isUpdated = this.adminCustomerService.updateUsers(updatedUser);
      return isUpdated ? "redirect:/admin/search" : "redirect:/admin/update?error=1";
   }

   @GetMapping({"/delete"})
   public String deleteUsers(@RequestParam("userId") String userId) {
      System.out.println("deleteUsers userId: " + userId);
      this.adminCustomerService.deleteUsers(userId);
      return "redirect:/admin/cm";
   }
}
