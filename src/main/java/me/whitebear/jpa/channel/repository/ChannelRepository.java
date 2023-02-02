package me.whitebear.jpa.channel.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import me.whitebear.jpa.channel.Channel;
import org.springframework.stereotype.Repository;

@Repository
public class ChannelRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Channel insertChannel(Channel channel) {
        entityManager.persist(channel);
        return channel;
    }

    public Channel selectChannel(Long id) {
        return entityManager.find(Channel.class, id);
    }
}
