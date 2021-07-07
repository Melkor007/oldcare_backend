package backend.controller;
import backend.domain.Test;
import backend.domain.Project;
import backend.service.TestService;
import backend.service.ProjectService;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class TestController {
    @Autowired
    private HttpServletRequest req;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TestService testService;

    private Tool tool;

    @GetMapping(value = "/test")
    public HashMap testList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Project> all = projectService.findByPhase("待测试");
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
            arr[i].put("projectName",all.get(i).getProject_name());
        }
        map.put("test",arr);
        return map;
    }

    @PostMapping(value = "/test")
    public HashMap test(){
        HashMap<String,Object> map =new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String project_name = tool.getStr("projectName");
        String applicant = tool.getStr("applicant");
        String date1 = tool.getStr("date1");
        String date2 = tool.getStr("date2");
        String content = tool.getStr("content");
        String version = tool.getStr("version");
        String tips = tool.getStr("tips");
        Test t = new Test(project_name,applicant,date1,date2,content,version,tips);
        int result = testService.create(t);
        projectService.updatePhase(project_name,"测试");
        map.put("code",result);
        return map;
    }

    @GetMapping(value="/testReview")
    public HashMap deploymentReviewList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Test> all = testService.findAll();
        int numTest = all.size();
        HashMap<String,Object>[] arr = new HashMap[numTest];
        for(int i=0;i<numTest;i++){
            arr[i] = new HashMap<>();
            arr[i].put("projectName",all.get(i).getProject_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("date1",all.get(i).getDate1());
            arr[i].put("date2",all.get(i).getDate2());
            arr[i].put("content",all.get(i).getContent());
            arr[i].put("version",all.get(i).getVersion());
            arr[i].put("tips",all.get(i).getTips());
        }
        map.put("testReview",arr);
        return map;
    }

    @PostMapping(value = "/testReview")
    public HashMap reviewDeployment(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String project_name = tool.getStr("projectName");
        String approver = tool.getStr("approver");
        String opinion = tool.getStr("opinion");

        int result = testService.review(project_name,approver,opinion);
        projectService.updatePhase(project_name,"归档");
        testService.delete(project_name);
        map.put("code",result);
        return map;
    }
}
