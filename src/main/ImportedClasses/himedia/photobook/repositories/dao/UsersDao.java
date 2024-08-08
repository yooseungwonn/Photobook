package himedia.photobook.repositories.dao;

import himedia.photobook.repositories.vo.UsersVo;
import java.util.List;

public interface UsersDao {
   int insert(UsersVo var1);

   UsersVo selectUserByEmail(String var1);

   UsersVo selectUserByEmailAndPassword(String var1, String var2);

   UsersVo getUserById(String var1);

   List<UsersVo> searchUsers(String var1);

   void deleteUsers(String var1);

   int updateUsers(UsersVo var1);

   int updateUser(UsersVo var1);

   UsersVo selectOneUserById(String var1);

   List<String> getUserNameByUserId(String var1);

   List<UsersVo> selectUserByName(String var1);

   List<UsersVo> selectUserByKeyword(String var1);
}
