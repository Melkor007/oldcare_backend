package backend.mapper;
import backend.domain.Deployment;
import backend.domain.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeploymentMapper {

    int create(Deployment deployment);

    List<Deployment> findAll();

    @Insert("insert into deployment_review values (#{project_name},#{approver},#{opinion})")
    int review(String project_name, String approver,String opinion);

    @Delete("delete from deployment where deployment_name = #{project_name}")
    int delete(String project_name);
}
