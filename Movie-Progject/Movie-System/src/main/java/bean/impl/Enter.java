package bean.impl;

import bean.Register;
import entity.Administrator;
import entity.AdministratorExample;
import entity.User;
import entity.UserExample;
import mapper.AdministratorMapper;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Enter implements Register {
    @Autowired
    private User user;
    @Autowired
    private UserExample userExample;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdministratorExample administratorExample;
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public int login(String name,String password) {
        userExample.createCriteria().andUsenameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        userExample.clear();
        if (!users.isEmpty()){
            return 2;  //用户名已被占用
        }
        user.setImg("image/default.jpg");
        user.setUsename(name);
        user.setPassword(password);
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public int logon(String name, String password) {
        int result=0;
        userExample.createCriteria().andUsenameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        userExample.clear();
        if (!users.isEmpty()){
            for (User user : users) {
                String password1 = user.getPassword();
                if (password.equals(password1)){
                    result=1;
                }
            }
        }
        return result;
    }

    @Override
    public int admiLogon(String name, String password) {
        int result=0;
        administratorExample.createCriteria().andAdministratornameEqualTo(name);
        List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
        administratorExample.clear();
        if (!administrators.isEmpty()){
            for (Administrator administrator : administrators) {
                String password1 = administrator.getPassword();
                if (password.equals(password1)){
                    result=1;
                }
            }
        }
        return result;
    }
}
