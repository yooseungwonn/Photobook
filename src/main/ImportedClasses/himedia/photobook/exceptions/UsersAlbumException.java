package himedia.photobook.exceptions;

import himedia.photobook.repositories.vo.AlbumVo;

public class UsersAlbumException extends RuntimeException {
   private static final long serialVersionUID = 1L;
   private AlbumVo albumVo = null;

   public UsersAlbumException() {
   }

   public UsersAlbumException(String message) {
      super(message);
   }

   public UsersAlbumException(String message, AlbumVo vo) {
      super(message);
   }

   public AlbumVo getAlbumVo() {
      return this.albumVo;
   }

   public void setAlbumVo(AlbumVo albumVo) {
      this.albumVo = albumVo;
   }
}
