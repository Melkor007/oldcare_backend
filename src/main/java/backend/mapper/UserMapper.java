package backend.mapper;
import backend.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username}")
    User find(@Param("username") String username);

    int create(User user);

    List<User> findAll();

    int update(User u);

    @Delete("delete from users where username = #{username}")
    int delete(String username);

    @Update("update users set role = #{role} where username = #{username}")
    int alterRole(String username, String role);
}
