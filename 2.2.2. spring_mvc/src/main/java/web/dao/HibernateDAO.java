package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HibernateDAO implements DAO {

    @PersistenceContext
    private EntityManager entityManager;

    private static int id;
    private List<User> people;
    {
        people = new ArrayList<>();
        people.add(new User(++id,"Tom","Injewatkin","Chernogorsk"));
        people.add(new User(++id,"Vlad", "Injewatkin","barn"));
        people.add(new User(++id,"Vlawffqqqqfcvc", "ggdsqwe","bbxaae"));
        people.add(new User(++id,"Vlaffwfwgh", "faqrfqqqq","ggg"));
    }
    @Override
    public List<User> allPeople(){
        return people;
    }

    @Override
    @Transactional
    public User personById(int id){
        return people.stream().filter(user -> user.getId()==id).findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save (User user){
        entityManager.persist(user);
        entityManager.close();
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User userToUpdate = personById(id);
        userToUpdate.setName(user.getName());
    }

    @Override
    @Transactional
    public void delete(int id) {
        people.removeIf(p->p.getId()==id);
    }
}
