package backend.service;
import backend.domain.User;
import backend.exception.BadResourceException;
import backend.exception.ResourceAlreadyExistsException;
import backend.exception.ResourceNotFoundException;
import backend.mapper.ContactMapper;

import java.util.ArrayList;
import java.util.List;

import backend.mapper.UserMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.backend.domain.PageRequest;
//import org.springframework.data.backend.domain.Pageable;
//import org.springframework.data.backend.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User find(String username){
        return userMapper.find(username);
    }

    public int create(User user){
        return userMapper.create(user);
    }

    public List<User> findAll(){
        return userMapper.findAll();
    }

    public int update(User u) {
        return userMapper.update(u);
    }

    public int delete(String username) {
        return userMapper.delete(username);
    }

    public int alterRole(String username, String role) {
        return userMapper.alterRole(username,role);
    }
}
