package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.CommentsVo;

public interface CommentsDao {
   CommentsVo getCommentsByBoardId(Long var1);

   int insert(CommentsVo var1);

   int updateStatus(Long var1);

   String count();
}
