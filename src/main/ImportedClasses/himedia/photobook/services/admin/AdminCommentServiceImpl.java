package himedia.photobook.services.admin;

import himedia.photobook.repositories.dao.CommentsDao;
import himedia.photobook.repositories.vo.CommentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCommentServiceImpl implements AdminCommentService {
   @Autowired
   private CommentsDao commentsDaoImpl;

   public boolean write(CommentsVo commentsVo) {
      int insertedCount = this.commentsDaoImpl.insert(commentsVo);
      this.commentsDaoImpl.updateStatus(commentsVo.getBoardId());
      return insertedCount == 1;
   }

   public CommentsVo getCommentsByBoardId(Long boardId) {
      CommentsVo commentsVo = this.commentsDaoImpl.getCommentsByBoardId(boardId);
      return commentsVo;
   }

   public boolean hasComment(Long boardId) {
      CommentsVo comment = this.commentsDaoImpl.getCommentsByBoardId(boardId);
      return comment != null;
   }

   public String count() {
      String count = this.commentsDaoImpl.count();
      return count;
   }
}
