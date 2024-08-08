package himedia.photobook.services.admin;

import himedia.photobook.repositories.vo.CommentsVo;

public interface AdminCommentService {
   CommentsVo getCommentsByBoardId(Long var1);

   boolean write(CommentsVo var1);

   boolean hasComment(Long var1);

   String count();
}
