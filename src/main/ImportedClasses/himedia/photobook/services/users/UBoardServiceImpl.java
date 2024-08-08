package himedia.photobook.services.users;

import himedia.photobook.repositories.dao.UBoardDaoImpl;
import himedia.photobook.repositories.dao.UsersDao;
import himedia.photobook.repositories.vo.BoardVo;
import himedia.photobook.repositories.vo.UsersVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UBoardServiceImpl implements UBoardService {
   @Autowired
   private UBoardDaoImpl uBoardDaoImpl;
   @Autowired
   private UsersDao usersDaoImpl;

   public Map<String, Object> getContent(String userId, Long boardId) {
      BoardVo boardVo = this.uBoardDaoImpl.getContent(userId, boardId);
      Map<String, Object> contentMap = new HashMap();
      UsersVo usersVo = this.usersDaoImpl.selectOneUserById(boardVo.getUserId());
      contentMap.put("boardVo", boardVo);
      contentMap.put("usersVo", usersVo);
      return contentMap;
   }

   public BoardVo getBoardVo(String userId, Long boardId) {
      return this.uBoardDaoImpl.getContent(userId, boardId);
   }

   public boolean write(BoardVo boardVo) {
      int insertedCount = this.uBoardDaoImpl.insert(boardVo);
      return insertedCount == 1;
   }

   public boolean update(BoardVo boardVo) {
      int updatedCount = this.uBoardDaoImpl.update(boardVo);
      return updatedCount == 1;
   }

   public List<Map<String, Object>> getBoardInfos() {
      List<Map<String, Object>> result = new ArrayList();
      List<BoardVo> list = this.uBoardDaoImpl.selectAllList();
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         BoardVo boardVo = (BoardVo)var4.next();
         Map<String, Object> boardMap = new HashMap();
         UsersVo usersVo = this.usersDaoImpl.selectOneUserById(boardVo.getUserId());
         boardMap.put("boardVo", boardVo);
         boardMap.put("usersVo", usersVo);
         result.add(boardMap);
      }

      return result;
   }

   public boolean delete(String userId, Long boardId) {
      int deletedCount = this.uBoardDaoImpl.delete(userId, boardId);
      return deletedCount == 1;
   }

   public List<Map<String, Object>> getContentByName(String userName) {
      List<Map<String, Object>> contentList = new ArrayList();
      Map<String, Object> map = null;
      List<UsersVo> userList = this.usersDaoImpl.selectUserByName(userName);
      Iterator var6 = userList.iterator();

      while(var6.hasNext()) {
         UsersVo usersVo = (UsersVo)var6.next();
         List<BoardVo> boardList = this.uBoardDaoImpl.getContentById(usersVo.getUserId());
         Iterator var9 = boardList.iterator();

         while(var9.hasNext()) {
            BoardVo boardVo = (BoardVo)var9.next();
            map = new HashMap();
            map.put("boardVo", boardVo);
            map.put("usersVo", usersVo);
            contentList.add(map);
         }
      }

      return contentList;
   }

   public List<BoardVo> getPagedBoard(int page, int pageSize) {
      int offset = (page - 1) * pageSize;
      RowBounds rowBounds = new RowBounds(offset, pageSize);
      return this.uBoardDaoImpl.listPage(rowBounds);
   }

   public int getTotalCount() {
      return this.uBoardDaoImpl.getTotalCount();
   }
}
