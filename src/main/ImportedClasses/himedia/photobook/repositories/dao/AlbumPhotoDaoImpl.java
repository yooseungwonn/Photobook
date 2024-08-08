package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.AlbumPhotoVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumPhotoDaoImpl implements AlbumPhotoDao {
   @Autowired
   private SqlSession session;

   public int insert(AlbumPhotoVo albumPhotoVo) {
      return this.session.insert("albumPhoto.insert", albumPhotoVo);
   }

   public int updatePath(AlbumPhotoVo albumPhotoVo) {
      return this.session.insert("albumPhoto.updatePath", albumPhotoVo);
   }

   public String getAlbumPhotoPath(String albumId) {
      return (String)this.session.selectOne("albumPhoto.getAlbumPhotoPath", albumId);
   }

   public int delete(String albumId) {
      return this.session.delete("albumPhoto.delete", albumId);
   }
}
