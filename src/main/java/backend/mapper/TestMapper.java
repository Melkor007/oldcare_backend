package backend.mapper;
import backend.domain.Project;
import backend.domain.Test;
import backend.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface TestMapper {

    int create(Test test);

    List<Test> findAll();

    @Insert("insert into test_review values (#{project_name},#{approver},#{opinion})")
    int review(String project_name, String approver, String opinion);

    @Delete("delete from test where project_name = #{project_name}")
    int delete(String project_name);
}
