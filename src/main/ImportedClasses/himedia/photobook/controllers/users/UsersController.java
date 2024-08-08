package himedia.photobook.controllers.users;

import himedia.photobook.repositories.vo.UsersVo;
import himedia.photobook.services.admin.AdminOrderService;
import himedia.photobook.services.users.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/users"})
public class UsersController {
   @Autowired
   private UsersService usersService;
   @Autowired
   private AdminOrderService adminOrderService;

   @GetMapping({"/login"})
   public String login() {
      return "/WEB-INF/views/users/users_login.jsp";
   }

   @RequestMapping({"/home", "/index", "", "/"})
   public String home(Model model, HttpSession session) {
      UsersVo userVo = (UsersVo)session.getAttribute("authUser");
      if (userVo != null) {
         model.addAttribute("userName", userVo.getUserName());
      }

      return "/WEB-INF/views/users/users_index.jsp";
   }

   @PostMapping({"/login"})
   public ModelAndView loginAction(@RequestParam(value = "email",required = false,defaultValue = "") String email, @RequestParam(value = "password",required = false,defaultValue = "") String password, HttpSession session) {
      if (email.length() != 0 && password.length() != 0) {
         UsersVo authUser = this.usersService.login(email, password);
         if (authUser != null) {
            session.setAttribute("authUser", authUser);
            session.setAttribute("userName", authUser.getUserName());
            session.setAttribute("success", "환영합니다." + authUser.getUserName() + "님");
            return new ModelAndView("redirect:/users/home");
         } else {
            ModelAndView mv = new ModelAndView("/WEB-INF/views/users/users_login.jsp");
            mv.addObject("error", "이메일이나 비밀번호 틀림. 다시.");
            return mv;
         }
      } else {
         return new ModelAndView("redirect:/users/home");
      }
   }

   @GetMapping({"/register"})
   public String register() {
      return "/WEB-INF/views/users/users_register.jsp";
   }

   @PostMapping({"/register"})
   public ModelAndView registerProcess(UsersVo user) {
      boolean isRegistered = this.usersService.register(user);
      if (isRegistered) {
         return new ModelAndView("redirect:/users/login");
      } else {
         ModelAndView mv = new ModelAndView("/WEB-INF/views/users/users_register.jsp");
         mv.addObject("error", "이메일중복.다시.");
         return mv;
      }
   }

   @RequestMapping({"/logout"})
   public String logout(HttpSession session) {
      session.removeAttribute("authUser");
      session.invalidate();
      return "redirect:/";
   }

   @RequestMapping({"/profile"})
   public String profile(HttpSession session, Model model) {
      UsersVo currentUser = (UsersVo)session.getAttribute("authUser");
      if (currentUser == null) {
         return "redirect:/users/login";
      } else {
         model.addAttribute("authuser", currentUser);
         return "/WEB-INF/views/users/users_profile.jsp";
      }
   }

   @PostMapping({"/profile/update"})
   public String updateUser(UsersVo updatedUser, HttpSession session, Model model) {
      UsersVo currentUser = (UsersVo)session.getAttribute("authUser");
      if (currentUser != null) {
         currentUser.setUserName(updatedUser.getUserName());
         currentUser.setPassword(updatedUser.getPassword());
         currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
         currentUser.setAddress(updatedUser.getAddress());
         boolean isUpdated = this.usersService.updateUser(currentUser);
         if (isUpdated) {
            session.setAttribute("authUser", currentUser);
            return "redirect:/users/home";
         } else {
            return "redirect:/users/profile?error=1";
         }
      } else {
         return "redirect:/users/login";
      }
   }
}
