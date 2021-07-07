package backend.controller;
import backend.domain.FFF;
import backend.domain.User;
import backend.exception.ResourceNotFoundException;
import backend.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest req;

    private Tool tool;

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello world";
    }


    @PostMapping(value="/login")
    public HashMap login(@RequestParam Map m){
//        System.out.println(m.keySet());
//        System.out.println(m.get("name"));
        HashMap<String,Object> map = new HashMap<>();
//       Map<String,String[]> map1 = req.getParameterMap();
//        System.out.println(map1.size());
//        System.out.println(map1.keySet());
//        System.out.println(map1.values());
        String username = req.getParameter("params[name]");
        String password = req.getParameter("params[pass]");
//        String username = "admin";
//        String password = "admin";
        System.out.println(username);
        System.out.println(password);
//        System.out.println(uu);
//        System.out.println(s);
        User u = userService.find(username);
        if(u.getPassword().equals(password)){
            map.put("code",1);
            map.put("role",u.getRole());
        }
        else{
            map.put("code","fuckyou");
        }
        return map;
    };

    @PostMapping(value="/register")
    public HashMap register(HttpServletRequest req){
        HashMap<String,Object> map = new HashMap<>();
        String username = req.getParameter("username");
//        String username = "waht";
        String password = req.getParameter("password");
//        String password = "456";
        //String role = "";
        String role = "norm";
        String email = req.getParameter("email");
//        String email = "holy";
        String gender = req.getParameter("gender");
//        String gender = "女";
        User u = new User(username,password,role,email,gender);
        int code = userService.create(u);
        map.put("code",code);
        return map;
    };

    @GetMapping("/rights")
    public HashMap right()
    {
        HashMap<String,Object> map = new HashMap<>();
        List<User> userList = userService.findAll();
        int numUser = userList.size();
        HashMap<String,Object>[] arr = new HashMap[numUser];
        for(int i=0;i<numUser;i++){
            arr[i] = new HashMap<>();
            arr[i].put("username",userList.get(i).getUsername());
            arr[i].put("role",userList.get(i).getRole());
        }
        map.put("code",1);
        map.put("rights",arr);
        return map;
    };

    @PutMapping("/rights")
    public HashMap alterRole(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("username");
        String role = tool.getStr("role");
        int result =userService.alterRole(username,role);
        map.put("code",result);
        return map;
    }
    @GetMapping("/members")
    public HashMap members()
    {
        HashMap<String,Object> map = new HashMap<>();
        List<User> userList = userService.findAll();
        int numUser = userList.size();
        HashMap<String,Object>[] arr = new HashMap[numUser];
        for(int i=0;i<numUser;i++){
            arr[i] = new HashMap<>();
            arr[i].put("username",userList.get(i).getUsername());
            arr[i].put("gender",userList.get(i).getGender());
            arr[i].put("email",userList.get(i).getEmail());
            arr[i].put("role",userList.get(i).getRole());
        }
        map.put("code",1);
        map.put("users",arr);
        return map;
    };

    @PostMapping(value = "/members")
    public HashMap addMembers(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("name");
        String password = tool.getStr("pass");
        String gender = tool.getStr("gender");
        String email = tool.getStr("email");
        String role = tool.getStr("role");
        User u = new User(username,password,role,email,gender);
        int result =  userService.create(u);
        map.put("code",result);
        return map;
    }

    @PutMapping(value = "/members")
    public HashMap changeMembers(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("username");
        String password = tool.getStr("pass");
        String gender = tool.getStr("gender");
        String email = tool.getStr("email");
        String role = "tempRole";
        User u = new User(username,password,role,email,gender);
        int result =  userService.update(u);
        map.put("code",result);
        return map;
    }

    @PostMapping(value = "/deleteMembers")
    public HashMap deleteMembers(){
        HashMap<String,Object>map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("username");
        int result = userService.delete(username);
        map.put("code",result);
        return map;
    }
}
