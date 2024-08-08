package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.UsersVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCustomerService {
   @Autowired
   private UsersDao usersDaoImpl;

   public List<UsersVo> searchUsers(String searchCategory, String keyword) {
      return this.usersDaoImpl.searchUsers(keyword);
   }

   public UsersVo getUserById(String userId) {
      return this.usersDaoImpl.getUserById(userId);
   }

   public void deleteUsers(String userId) {
      this.usersDaoImpl.deleteUsers(userId);
   }

   public boolean updateUsers(UsersVo userVo) {
      int updatedCount = this.usersDaoImpl.updateUsers(userVo);
      return updatedCount == 1;
   }
}
