package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

public interface DAO {
    public List<User> allPeople();
    public User personById(int id);
    public void save(User user);
    public void update(int id, User user);
    public void delete(int id);

}
