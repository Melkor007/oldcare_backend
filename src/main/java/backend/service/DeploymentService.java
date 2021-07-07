package backend.service;
import backend.domain.Deployment;
import backend.exception.BadResourceException;
import backend.exception.ResourceAlreadyExistsException;
import backend.exception.ResourceNotFoundException;
import backend.mapper.DeploymentMapper;

import java.util.ArrayList;
import java.util.List;

import backend.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.backend.domain.PageRequest;
//import org.springframework.data.backend.domain.Pageable;
//import org.springframework.data.backend.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class DeploymentService {
    @Autowired
    private DeploymentMapper deploymentMapper;

    public int create(Deployment d){
        return deploymentMapper.create(d);
    }

    public int review(String project_name, String approver,String opinion){
        return deploymentMapper.review(project_name,approver,opinion);
    }

    public List<Deployment> findAll(){
        return deploymentMapper.findAll();
    }

    public int delete(String project_name){
        return deploymentMapper.delete(project_name);
    }
}
