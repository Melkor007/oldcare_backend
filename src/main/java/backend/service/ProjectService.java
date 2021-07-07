package backend.service;
import backend.domain.Project;
import backend.exception.BadResourceException;
import backend.exception.ResourceAlreadyExistsException;
import backend.exception.ResourceNotFoundException;
import backend.mapper.ProjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.backend.domain.PageRequest;
//import org.springframework.data.backend.domain.Pageable;
//import org.springframework.data.backend.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    public List<Project> findByApplicant(String applicant){
        return projectMapper.findByApplicant(applicant);
    }
    public Project findById(String id){
        return projectMapper.findById(id);
    };
    public List<Project> findByPhase(String phase){
        return projectMapper.findByPhase(phase);
    };
    public int create(Project p){
        return projectMapper.create(p);
    }
    public List<Project> all(){
        return projectMapper.findAll();
    }
    public List<Project> allNotDeleted(){
        return projectMapper.allNotDeleted();
    }
    public int insertToReview(String project_name,String approver, String opinion){
        int res = projectMapper.insertToReview(project_name,approver,opinion);
        res = projectMapper.updatePhase(project_name,"待部署");
        return res;
    }
    public int change(Project p){
        return projectMapper.change(p);
    }
    public int updatePhase(String project_name,String phase){
        return projectMapper.updatePhase(project_name,phase);
    }

    public List<Project> findByApplicantAndPhase(String applicant, String phase) {
        return projectMapper.findByApplicantAndPhase(applicant,phase);
    }

    public int deleteById(String id) {
        return projectMapper.deleteById(id);
    }
}
