package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.AlbumPhotoVo;

public interface AlbumPhotoDao {
   int insert(AlbumPhotoVo var1);

   int updatePath(AlbumPhotoVo var1);

   String getAlbumPhotoPath(String var1);

   int delete(String var1);
}
