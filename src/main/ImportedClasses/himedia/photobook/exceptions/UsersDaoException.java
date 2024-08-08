package himedia.photobook.exceptions;

import himedia.photobook.repositories.vo.UsersVo;

public class UsersDaoException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private UsersVo userVo = null;

   public UsersDaoException() {
   }

   public UsersDaoException(String message) {
      super(message);
   }

   public UsersVo getUserVo() {
      return this.userVo;
   }

   public void setUserVo(UsersVo userVo) {
      this.userVo = userVo;
   }
}
