package com.elhatron.amnesia.SQLConnection.Messages.Messages.DAO;

import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.ErrorCode;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.Messages;

import java.util.List;

public interface MessagesDAO {
    Messages findById(int id);

    List<Messages> findByReciver(String reciver);

    ErrorCode recieveAndStoreMessage(Messages message);

    public void deleteMessage(Messages message);
}
