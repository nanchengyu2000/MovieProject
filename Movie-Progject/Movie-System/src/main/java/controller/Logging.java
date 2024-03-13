package controller;

import bean.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/log") //register
public class Logging {
    @Autowired
    private Register register;
    @RequestMapping("/login")
    @CrossOrigin
    @ResponseBody
    public int login(String name,String password){
        int login = register.login(name,password);
        return login;
    }
    @RequestMapping("/logon")
    @CrossOrigin
    @ResponseBody
    public int logon(String name,String password){
        int logon = register.logon(name,password);
        return logon;
    }
    @RequestMapping("/admiLogon")
    @CrossOrigin
    @ResponseBody
    public int admiLogon(String name,String password){
        int i = register.admiLogon(name,password);
        return i;
    }
}
