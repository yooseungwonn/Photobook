package himedia.photobook.interceptors;

import himedia.photobook.services.users.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {
   public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
      ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
      UsersService userService = (UsersService)context.getBean(UsersService.class);
      if (userService.isAuthenticated(req)) {
         return true;
      } else {
         resp.sendRedirect(req.getContextPath() + "/users/login");
         return false;
      }
   }
}
