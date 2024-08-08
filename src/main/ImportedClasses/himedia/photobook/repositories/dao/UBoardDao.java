package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.BoardVo;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface UBoardDao {
   List<BoardVo> selectAllList();

   int insert(BoardVo var1);

   BoardVo getContent(String var1, Long var2);

   List<BoardVo> getContentById(String var1);

   int update(BoardVo var1);

   int delete(String var1, Long var2);

   List<BoardVo> listPage(RowBounds var1);

   int getTotalCount();
}
