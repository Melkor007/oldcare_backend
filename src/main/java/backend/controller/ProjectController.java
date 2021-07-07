package backend.controller;
import backend.domain.Project;
import backend.exception.ResourceNotFoundException;
import backend.service.ProjectService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private HttpServletRequest req;

    private Tool tool;

    @PostMapping(value = "/myProjects")
    public HashMap myProjects(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("name");
        List<Project> all = projectService.findByApplicant(username);
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
        }
        map.put("projects",arr);
        return map;
    }

    @PostMapping(value = "/finishedProjects")
    public HashMap finishedProjects(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String role = tool.getStr("role");
        List<Project> all;
        if(role.equals("admin")){
            all = projectService.findByPhase("归档");
        }
        else{
            String applicant = tool.getStr("name");
            all = projectService.findByApplicantAndPhase(applicant,"归档");
        }
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
        }
        map.put("projects",arr);
        return map;
    }

    @PostMapping(value = "/deleteProject")
    public HashMap deleteProject(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String id = tool.getStr("id");
        int result = projectService.deleteById(id);
        System.out.println(result);
        map.put("code",result);
        return map;
    }

    @PostMapping(value = "/deletedProjects")
    public HashMap deletedProjects(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String username = tool.getStr("name");
        String role = tool.getStr("role");
        List<Project> all;
        if(role.equals("admin")){
            all = projectService.findByPhase("deleted");
        }
        else{
            all = projectService.findByApplicantAndPhase(username,"deleted");
        }

        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
        }
        map.put("projects",arr);
        return map;
    }

    @GetMapping(value = "/approve")
    public HashMap approveList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Project> all = projectService.findByPhase("初始");
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
            arr[i].put("level",all.get(i).getLevel());
            arr[i].put("date1",all.get(i).getDate1());
            arr[i].put("date2",all.get(i).getDate2());
            arr[i].put("type",all.get(i).getType());
            arr[i].put("money",all.get(i).getMoney());
            arr[i].put("introduction",all.get(i).getIntro());
            arr[i].put("innovate",all.get(i).getInnovate());
            arr[i].put("direction",all.get(i).getDirection());
        }
        map.put("approve",arr);
        return map;
    }

    @PostMapping(value = "/approve")
    public HashMap approve(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String project_name = tool.getStr("name");
        String approver = tool.getStr("approver");
        String opinion = tool.getStr("advice");

//        System.out.println("pname:"+project_name);
//        System.out.println("approver:"+approver);
//        System.out.println("opinion:"+opinion);
        int result = projectService.insertToReview(project_name,approver,opinion);
        map.put("code",result);
        return map;
    }

    @GetMapping(value = "/change")
    public HashMap changeList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Project> all = projectService.findByPhase("初始");
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
            arr[i].put("level",all.get(i).getLevel());
            arr[i].put("date1",all.get(i).getDate1());
            arr[i].put("date2",all.get(i).getDate2());
            arr[i].put("type",all.get(i).getType());
            arr[i].put("money",all.get(i).getMoney());
            arr[i].put("introduction",all.get(i).getIntro());
            arr[i].put("innovate",all.get(i).getInnovate());
            arr[i].put("direction",all.get(i).getDirection());
        }
        map.put("change",arr);
        return map;
    }

    @PostMapping(value = "/change")
    public HashMap change(){
        tool = new Tool();
        tool.setReq(req);
        HashMap<String,Object> map = new HashMap<>();
        String id = tool.getStr("id");
        String name = tool.getStr("name");
        String applicant = tool.getStr("applicant");
        String phase = tool.getStr("phase");
        String level = tool.getStr("level");
        String date1 = tool.getStr("date1");
        String date2 = tool.getStr("date2");
        String type = tool.getStr("type");
        String money = tool.getStr("money");
        String intro = tool.getStr("introduction");
        String innovate = tool.getStr("innovate");
        String direction = tool.getStr("direction");

        Project p = new Project(id,name,applicant,phase,level,type,money,date1,date2,intro,innovate,direction);
        int result = projectService.change(p);
        map.put("code",result);
        return map;
    }

    @PostMapping(value="/allProjects")
    public HashMap allProject(){
        HashMap<String,Object> map = new HashMap<>();
//        String username = req.getParameter("params[name]");
//        System.out.println(username);
//        System.out.println(req.getParameterMap().keySet());
        List<Project> all = projectService.allNotDeleted();
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("phase",all.get(i).getPhase());
        }
        map.put("num",numProject);
        map.put("projects",arr);
        return map;
    };

    @PostMapping(value="/apply")
    public HashMap apply(){
        System.err.println("get application");
        tool = new Tool();
        tool.setReq(req);
        HashMap<String,Object> map = new HashMap<>();
        String id = tool.getStr("id");
        String name = tool.getStr("name");
        String applicant = tool.getStr("applicant");
        String phase = tool.getStr("phase");
        String level = tool.getStr("level");
        String date1 = tool.getStr("date1");
        String date2 = tool.getStr("date2");
        String type = tool.getStr("type");
        String money = tool.getStr("money");
        String intro = tool.getStr("introduction");
        String innovate = tool.getStr("innovate");
        String direction = tool.getStr("direction");

        Project p = new Project(id,name,applicant,phase,level,type,money,date1,date2,intro,innovate,direction);
        projectService.create(p);
        map.put("code",1);
        return map;
    };

    @PostMapping(value = "/statistics")
    public HashMap statistics(){
        String id = req.getParameter("projectID");
//        String id = "0";
        HashMap<String,Object> map = new HashMap<>();
        Project p = projectService.findById(id);
        map.put("code",1);
        map.put("name",p.getProject_id());
        return map;
    };
}
