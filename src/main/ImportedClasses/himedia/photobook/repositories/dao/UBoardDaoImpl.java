package himedia.photobook.repositories.dao;

import himedia.photobook.exceptions.UBoardDaoException;
import himedia.photobook.repositories.vo.BoardVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UBoardDaoImpl implements UBoardDao {
   @Autowired
   private SqlSession sqlSession;

   public List<BoardVo> selectAllList() {
      return this.sqlSession.selectList("board.selectAll");
   }

   public int insert(BoardVo uboardVo) {
      try {
         int insertedCount = this.sqlSession.insert("board.insert", uboardVo);
         return insertedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UBoardDaoException("게시글 등록중 뭔가 잘못되었습니다.");
      }
   }

   public BoardVo getContent(String userId, Long boardId) {
      Map<String, Object> map = new HashMap();
      map.put("userId", userId);
      map.put("boardId", boardId);
      return (BoardVo)this.sqlSession.selectOne("board.getContent", map);
   }

   public int update(BoardVo boardVo) {
      try {
         int updatedCount = this.sqlSession.update("board.update", boardVo);
         return updatedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UBoardDaoException("게시글 업데이트 실패~~");
      }
   }

   public int delete(String userId, Long boardId) {
      try {
         Map<String, Object> map = new HashMap();
         map.put("userId", userId);
         map.put("boardId", boardId);
         return this.sqlSession.delete("board.delete", map);
      } catch (Exception var4) {
         var4.printStackTrace();
         throw new UBoardDaoException("게시글 삭제 실패~~~");
      }
   }

   public List<BoardVo> getContentById(String userId) {
      return this.sqlSession.selectList("board.getContentById", userId);
   }

   public List<BoardVo> listPage(RowBounds rowBounds) {
      return this.sqlSession.selectList("board.selectAll", (Object)null, rowBounds);
   }

   public int getTotalCount() {
      return (Integer)this.sqlSession.selectOne("board.getTotalCount");
   }
}
