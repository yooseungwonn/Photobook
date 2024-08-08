package himedia.photobook.repositories.dao;

import himedia.photobook.exceptions.UsersAlbumException;
import himedia.photobook.repositories.vo.AlbumVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumDaoImpl implements AlbumDao {
   @Autowired
   private SqlSession sqlSession;

   public AlbumVo findAlbumIdByOptions(String material, String color, String albumSize) {
      Map<String, String> options = new HashMap();
      options.put("material", material);
      options.put("color", color);
      options.put("albumSize", albumSize);
      AlbumVo albumVo = (AlbumVo)this.sqlSession.selectOne("album.findAlbumIdByOptions", options);
      return albumVo;
   }

   public AlbumVo selectOneById(String albumId) {
      try {
         return (AlbumVo)this.sqlSession.selectOne("album.selectByID", albumId);
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersAlbumException("UserAlbumException::SelectOneById01 [에러 발생]");
      }
   }

   public List<AlbumVo> searchAlbum(String keyword) {
      return this.sqlSession.selectList("album.albumList", keyword);
   }

   public List<AlbumVo> selectAll() {
      return this.sqlSession.selectList("album.selectAll");
   }

   public String findOptionsByOrderId(String orderId) {
      return (String)this.sqlSession.selectOne("album.findOptionsByOrderId", orderId);
   }

   public AlbumVo selectByAlbumId(String albumId) {
      try {
         return (AlbumVo)this.sqlSession.selectOne("album.selectByAlbumId", albumId);
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersAlbumException("[에러 발생]");
      }
   }

   public int updateAlbum(AlbumVo albumVo) {
      try {
         int updatedCount = this.sqlSession.update("album.updateAlbum", albumVo);
         return updatedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersAlbumException("업데이트 도중 예외 발생!");
      }
   }

   public int delete(String albumId) {
      return this.sqlSession.delete("album.deleteAlbum", albumId);
   }

   public int insertAlbum(AlbumVo vo) {
      return this.sqlSession.insert("album.insertAlbum", vo);
   }
}
