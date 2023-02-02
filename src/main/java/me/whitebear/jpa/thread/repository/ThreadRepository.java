package me.whitebear.jpa.thread.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.whitebear.jpa.thread.Thread;
import org.springframework.stereotype.Repository;

@Repository
public class ThreadRepository {

  @PersistenceContext
  EntityManager entityManager;

  public Thread insertThread(Thread thread){
    entityManager.persist(thread);
    return thread;
  }

  public Thread selectThread(Long id){return entityManager.find(Thread.class, id);}
}
