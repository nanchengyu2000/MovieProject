package bean;

import entity.Administrator;
import entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserManage {
    public User getUser(String name);
    public Administrator getAdmi(String name);
}
