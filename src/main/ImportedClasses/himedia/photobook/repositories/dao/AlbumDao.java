package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.AlbumVo;
import java.util.List;

public interface AlbumDao {
   AlbumVo findAlbumIdByOptions(String var1, String var2, String var3);

   AlbumVo selectOneById(String var1);

   List<AlbumVo> searchAlbum(String var1);

   List<AlbumVo> selectAll();

   String findOptionsByOrderId(String var1);

   AlbumVo selectByAlbumId(String var1);

   int delete(String var1);

   int insertAlbum(AlbumVo var1);

   int updateAlbum(AlbumVo var1);
}
