package backend.controller;
import backend.domain.Deployment;
import backend.domain.Project;
import backend.service.DeploymentService;
import backend.service.ProjectService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
public class DeploymentController {
    @Autowired
    private HttpServletRequest req;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DeploymentService deploymentService;

    private Tool tool;

    @GetMapping(value = "/deploy")
    public HashMap deployList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Project> all = projectService.findByPhase("待部署");
        int numProject = all.size();
        HashMap<String,Object>[] arr = new HashMap[numProject];
        for(int i=0;i<numProject;i++){
            arr[i] = new HashMap<>();
//            arr[i].put("id",all.get(i).getProject_id());
            arr[i].put("name",all.get(i).getProject_name());
//            arr[i].put("applicant",all.get(i).getApplicant());
//            arr[i].put("phase",all.get(i).getPhase());
//            arr[i].put("level",all.get(i).getLevel());
//            arr[i].put("date1",all.get(i).getDate1());
//            arr[i].put("date2",all.get(i).getDate2());
//            arr[i].put("type",all.get(i).getType());
//            arr[i].put("money",all.get(i).getMoney());
//            arr[i].put("introduction",all.get(i).getIntro());
//            arr[i].put("innovate",all.get(i).getInnovate());
//            arr[i].put("direction",all.get(i).getDirection());
        }
        map.put("deploy",arr);
        return map;
    };

    @PostMapping(value="/deploy")
    public HashMap deploy(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String name = tool.getStr("name");
        String applicant = tool.getStr("applicant");
        String environment = tool.getStr("environment");
        String date1 = tool.getStr("date1");
        String date2 = tool.getStr("date2");
        String reason = tool.getStr("reason");

        Deployment d = new Deployment(name,applicant,environment,date1,date2,reason);
        int result = deploymentService.create(d);
        projectService.updatePhase(name,"部署");
        map.put("code",result);
        return map;
    };

    @GetMapping(value="/DeployReview")
    public HashMap deploymentReviewList(){
        HashMap<String,Object> map = new HashMap<>();
        List<Deployment> all = deploymentService.findAll();
        int numDeployment = all.size();
        HashMap<String,Object>[] arr = new HashMap[numDeployment];
        for(int i=0;i<numDeployment;i++){
            arr[i] = new HashMap<>();
            arr[i].put("name",all.get(i).getDeployment_name());
            arr[i].put("applicant",all.get(i).getApplicant());
            arr[i].put("environment",all.get(i).getEnvironment());
            arr[i].put("date1",all.get(i).getDate1());
            arr[i].put("date2",all.get(i).getDate2());
            arr[i].put("reason",all.get(i).getReason());
        }
        map.put("deployReview",arr);
        return map;
    }

    @PostMapping(value = "/DeployReview")
    public HashMap reviewDeployment(){
        HashMap<String,Object> map = new HashMap<>();
        tool = new Tool();
        tool.setReq(req);
        String project_name = tool.getStr("name");
        String approver = tool.getStr("approver");
        String opinion = tool.getStr("opinion");

        int result = deploymentService.review(project_name,approver,opinion);
        projectService.updatePhase(project_name,"待测试");
        deploymentService.delete(project_name);
        map.put("code",result);
        return map;
    }
}
