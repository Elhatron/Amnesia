package com.elhatron.amnesia.SQLConnection.Messages.Messages.Service;

import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.ErrorCode;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.Messages;

import java.util.List;

public interface MessagesService {
    Messages findById(int id);

    List<Messages> findByReciver(String reciver);

    ErrorCode recieveAndStoreMessage(Messages message);

    void deleteMessage(Messages message);
}
