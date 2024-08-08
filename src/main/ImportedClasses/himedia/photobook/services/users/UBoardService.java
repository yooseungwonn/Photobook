package himedia.photobook.services.users;

import himedia.photobook.repositories.vo.BoardVo;
import java.util.List;
import java.util.Map;

public interface UBoardService {
   List<Map<String, Object>> getBoardInfos();

   Map<String, Object> getContent(String var1, Long var2);

   List<Map<String, Object>> getContentByName(String var1);

   BoardVo getBoardVo(String var1, Long var2);

   boolean write(BoardVo var1);

   boolean update(BoardVo var1);

   boolean delete(String var1, Long var2);

   List<BoardVo> getPagedBoard(int var1, int var2);

   int getTotalCount();
}
