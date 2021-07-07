package backend.controller;

import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;

public class Tool {
    private HttpServletRequest req;
    public void setReq(HttpServletRequest req){
        this.req = req;
    }
    private String get(String s){
        String paramized = "params["+s+"]";
        return req.getParameter(paramized);
    }
    public int getInt(String s){
        String str = this.get(s);
        return Integer.parseInt(str);
    }
    public String getStr(String s){
        return this.get(s);
    }
}
