package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.PhotoVo;

public interface PhotoDao {
   int insert(PhotoVo var1);

   int selectCountByOrderId(String var1);

   int delete(String var1);
}
