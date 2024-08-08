package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.PhotoVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("photoDaoImpl")
public class PhotoDaoImpl implements PhotoDao {
   @Autowired
   private SqlSession session;

   public int insert(PhotoVo photoVo) {
      return this.session.insert("photo.insert", photoVo);
   }

   public int selectCountByOrderId(String orderId) {
      return (Integer)this.session.selectOne("photo.selectCountByOrderId", orderId);
   }

   public int delete(String orderId) {
      return this.session.delete("photo.delete", orderId);
   }
}
