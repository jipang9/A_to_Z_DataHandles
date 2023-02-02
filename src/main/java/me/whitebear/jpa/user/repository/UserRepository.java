package me.whitebear.jpa.user.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.whitebear.jpa.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  @PersistenceContext
  EntityManager entityManager;

  public User insertUser(User User){
    entityManager.persist(User);
    return User;
  }

  public User selectUser(Long id){return entityManager.find(User.class, id);}
}
