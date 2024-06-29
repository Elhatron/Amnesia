package com.elhatron.amnesia.SQLConnection.Messages.Messages.DAO;

import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.ErrorCode;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.Messages;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessagesDAOImplementation implements MessagesDAO{
    private EntityManager entityManager;

    @Autowired
    public MessagesDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public Messages findById(int id) {
        Messages message = entityManager.find(Messages.class, id);

        return message;
    }

    @Override
    public List<Messages> findByReciver(String reciver) {
        TypedQuery<Messages> query = entityManager.createQuery("SELECT e FROM Messages e WHERE e.reciver = :reciver", Messages.class);
        query.setParameter("reciver", reciver);
        List<Messages> messages = query.getResultList();

        return messages;
    }

    @Override
    public ErrorCode recieveAndStoreMessage(Messages message) {
        try {
            Messages dbMessage = entityManager.merge(message);
            return new ErrorCode(message.getSender(), 0);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return new ErrorCode(message.getSender(), 1);
        }
    }

    @Override
    public void deleteMessage(Messages message) {
        entityManager.remove(message);
    }
}
