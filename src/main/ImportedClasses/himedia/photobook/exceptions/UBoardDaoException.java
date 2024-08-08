package himedia.photobook.exceptions;

import himedia.photobook.repositories.vo.BoardVo;

public class UBoardDaoException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private BoardVo UboardVo = null;

   public UBoardDaoException() {
   }

   public UBoardDaoException(String message) {
      super(message);
   }

   public UBoardDaoException(String message, BoardVo UboardVo) {
      super(message);
      this.UboardVo = UboardVo;
   }
}
