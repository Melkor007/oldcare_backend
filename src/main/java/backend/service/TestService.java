package backend.service;
import backend.domain.Deployment;
import backend.domain.Test;
import backend.exception.BadResourceException;
import backend.exception.ResourceAlreadyExistsException;
import backend.exception.ResourceNotFoundException;
import backend.mapper.TestMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.backend.domain.PageRequest;
//import org.springframework.data.backend.domain.Pageable;
//import org.springframework.data.backend.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    public int create(Test t){
        return testMapper.create(t);
    }

    public List<Test> findAll() {
        return testMapper.findAll();
    }

    public int review(String project_name, String approver, String opinion) {
        return testMapper.review(project_name,approver,opinion);
    }

    public int delete(String project_name) {
        return testMapper.delete(project_name);
    }
}
