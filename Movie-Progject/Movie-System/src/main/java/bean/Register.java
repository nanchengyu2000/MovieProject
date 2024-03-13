package bean;

import org.springframework.stereotype.Service;

@Service
public interface Register {
    public int login(String name,String password);  //注册
    public int logon(String name,String password);  //登录
    public int admiLogon(String name,String password);  //管理员登录
}
