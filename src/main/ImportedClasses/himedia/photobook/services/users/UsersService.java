package himedia.photobook.services.users;

import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.UsersVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
   @Autowired
   private UsersDao usersDaoImpl;

   public boolean register(UsersVo user) {
      UsersVo existingUser = this.usersDaoImpl.selectUserByEmail(user.getEmail());
      if (existingUser != null) {
         return false;
      } else {
         String encryptedPassword = this.encryptPassword(user.getPassword());
         user.setPassword(encryptedPassword);
         int result = this.usersDaoImpl.insert(user);
         return result > 0;
      }
   }

   public UsersVo login(String email, String password) {
      String encryptedPassword = this.encryptPassword(password);
      UsersVo user = this.usersDaoImpl.selectUserByEmailAndPassword(email, encryptedPassword);
      return user != null && user.getPassword().equals(encryptedPassword) ? user : null;
   }

   private String encryptPassword(String password) {
      return "encrypted_" + password;
   }

   public boolean updateUser(UsersVo userVo) {
      int updatedCount = this.usersDaoImpl.updateUser(userVo);
      return updatedCount == 1;
   }

   public List<String> getUserNameByUserId(String userId) {
      return this.usersDaoImpl.getUserNameByUserId(userId);
   }

   public boolean isAuthenticated(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      if (session != null) {
         UsersVo authUser = (UsersVo)session.getAttribute("authUser");
         return authUser != null;
      } else {
         return false;
      }
   }
}
