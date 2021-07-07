package backend.mapper;
import backend.domain.Project;
import backend.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int create(Project project);

    List<Project> findAll();

    int change(Project roject);

    @Select("select * from project where applicant = #{applicant} and phase != 'deleted'")
    List<Project> findByApplicant(String applicant);

    @Select("select * from project where project_id = #{project_id}")
    Project findById(@Param("project_id") String project_id);

    @Select("select * from project where phase = #{phase}")
    List<Project> findByPhase(@Param("phase") String phase);


    @Insert("insert into review values (#{project_name},#{approver},#{opinion})")
    int insertToReview(@Param("project_name") String project_name,
                       @Param("approver") String approver,
                       @Param("opinion") String opinion);

    @Update("update project set phase = #{phase} where project_name = #{project_name}")
    int updatePhase(@Param("project_name") String project_name,
                    @Param("phase") String phase);

    @Select("select * from project where applicant = #{applicant} and phase = #{phase}")
    List<Project> findByApplicantAndPhase(String applicant, String phase);

    @Update("update project set phase = 'deleted' where project_id = #{id}")
    int deleteById(String id);

    @Select("select * from project where phase <> 'deleted'")
    List<Project> allNotDeleted();
}
