package himedia.photobook.repositories.dao;

import himedia.photobook.exceptions.UsersDaoException;
import himedia.photobook.repositories.vo.UsersVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDaoImpl implements UsersDao {
   @Autowired
   private SqlSession sqlSession;

   public int insert(UsersVo vo) {
      try {
         vo.setRole("U");
         return this.sqlSession.insert("users.insert", vo);
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersDaoException("회원 가입 중 에러!");
      }
   }

   public UsersVo selectUserByEmail(String email) {
      List<UsersVo> userList = this.sqlSession.selectList("users.selectUserByEmail", email);
      return userList != null && !userList.isEmpty() ? (UsersVo)userList.get(0) : null;
   }

   public UsersVo selectUserByEmailAndPassword(String email, String password) {
      Map<String, String> userMap = new HashMap();
      userMap.put("email", email);
      userMap.put("password", password);
      UsersVo userVo = (UsersVo)this.sqlSession.selectOne("users.selectUserByEmailAndPassword", userMap);
      return userVo;
   }

   public UsersVo getUserById(String userId) {
      return (UsersVo)this.sqlSession.selectOne("users.getUserById", userId);
   }

   public List<UsersVo> searchUsers(String keyword) {
      return this.sqlSession.selectList("users.searchUsers", keyword);
   }

   public void deleteUsers(String userId) {
      this.sqlSession.delete("users.deleteUsers", userId);
   }

   public int updateUsers(UsersVo user) {
      try {
         System.out.println(user);
         int updatedCount = this.sqlSession.update("users.updateUsers", user);
         return updatedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersDaoException("업데이트 도중 예외 발생!");
      }
   }

   public int updateUser(UsersVo user) {
      try {
         int updatedCount = this.sqlSession.update("users.updateUser", user);
         return updatedCount;
      } catch (Exception var3) {
         var3.printStackTrace();
         throw new UsersDaoException("예외 발생!");
      }
   }

   public UsersVo selectOneUserById(String Id) {
      UsersVo est = (UsersVo)this.sqlSession.selectOne("users.selectUserById", Id);
      return est;
   }

   public List<String> getUserNameByUserId(String userId) {
      return this.sqlSession.selectList("users.getUserNameByUserId", userId);
   }

   public List<UsersVo> selectUserByName(String userName) {
      return this.sqlSession.selectList("users.selectUserByName", userName);
   }

   public List<UsersVo> selectUserByKeyword(String keyword) {
      return this.sqlSession.selectList("users.selectUserByKeyword", keyword);
   }
}
