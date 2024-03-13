package controller;

import bean.UserManage;
import entity.Administrator;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class Member {
    @Autowired
    private UserManage userManage;
    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(String name){
        User user = userManage.getUser(name);
        return user;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/getAdmi")
    public Administrator getAdmi(String name){
        Administrator admi = userManage.getAdmi(name);
        return admi;
    }
}
