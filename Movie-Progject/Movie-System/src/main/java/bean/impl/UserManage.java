package bean.impl;

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
public class UserManage implements bean.UserManage {
    @Autowired
    private UserExample userExample;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdministratorExample administratorExample;
    @Autowired
    private AdministratorMapper administratorMapper;
    @Override
    public User getUser(String name) {
        User use = null;
        userExample.createCriteria().andUsenameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        userExample.clear();
        for (User user : users) {
            use=user;
        }
        return use;
    }

    @Override
    public Administrator getAdmi(String name) {
        Administrator use = null;
        administratorExample.createCriteria().andAdministratornameEqualTo(name);
        List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
        administratorExample.clear();
        for (Administrator user : administrators) {
            use=user;
        }
        return use;
    }
}
