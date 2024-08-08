package himedia.photobook.repositories.dao;

import himedia.photobook.exceptions.UBoardDaoException;
import himedia.photobook.repositories.vo.CommentsVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentsDaoImpl implements CommentsDao {
   @Autowired
   SqlSession sqlSession;

   public int insert(CommentsVo commentsVo) {
      try {
         int insertedCount = this.sqlSession.insert("comments.insert", commentsVo);
         return insertedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UBoardDaoException("게시글 등록중 뭔가 잘못되었습니다.");
      }
   }

   public CommentsVo getCommentsByBoardId(Long boardId) {
      CommentsVo commentsVo = (CommentsVo)this.sqlSession.selectOne("comments.getCommentsByBoardId", boardId);
      return commentsVo;
   }

   public int updateStatus(Long boardId) {
      return this.sqlSession.update("board.updateStatus", boardId);
   }

   public String count() {
      return (String)this.sqlSession.selectOne("board.countBoard");
   }
}
