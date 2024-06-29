package com.elhatron.amnesia.SQLConnection.Messages.Messages.Service;

import com.elhatron.amnesia.SQLConnection.Messages.Messages.DAO.MessagesDAO;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.ErrorCode;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImplementation implements MessagesService{

    private MessagesDAO messagesDao;

    @Autowired
    public MessageServiceImplementation(MessagesDAO messagesDao) {
        this.messagesDao = messagesDao;
    }
    @Override
    public Messages findById(int id) {
        return messagesDao.findById(id);
    }

    @Override
    public List<Messages> findByReciver(String reciver) {
        return messagesDao.findByReciver(reciver);
    }

    @Transactional
    @Override
    public ErrorCode recieveAndStoreMessage(Messages message) {
        return messagesDao.recieveAndStoreMessage(message);
    }

    @Transactional
    @Override
    public void deleteMessage(Messages message) {
        messagesDao.deleteMessage(message);
    }
}
