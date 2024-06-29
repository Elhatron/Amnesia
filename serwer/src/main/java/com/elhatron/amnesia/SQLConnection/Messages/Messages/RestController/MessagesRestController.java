package com.elhatron.amnesia.SQLConnection.Messages.Messages.RestController;

import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity.Messages;
import com.elhatron.amnesia.SQLConnection.Messages.Messages.Service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messages")
public class MessagesRestController {
    private MessagesService messagesService;

    private static final Logger logger = LoggerFactory.getLogger(MessagesRestController.class);

    @Autowired
    public MessagesRestController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/getMessageById/{messageId}")
    public Messages getMessageById(@PathVariable int messageId){
        Messages message = messagesService.findById(messageId);

        if (message == null) {
            return new Messages(-1,null,null,null);
        } else {
            return message;
        }
    }

    @GetMapping("/getMessagesByReciver/{reciverName}")
    public List<Messages> getMessageByReciver(@PathVariable String reciverName){
        List<Messages> messages = messagesService.findByReciver(reciverName);

        List<Messages> messagesToRecive = messages;

        if ( messages.size() > 0 ){
            for ( int i = 0; i < messages.size(); i++){
                messagesService.deleteMessage(messages.get(i));
            }
        }

        return messagesToRecive;
    }

    @PostMapping("/reciveMessage")
    public ErrorCode recieveMessage(@RequestBody Messages message) {
        message.setId(0);
        logger.info("Received message: {}", message);

        //System.out.println(message.getMessage().length());
        try {
            if (message.getMessage().length()>256){
                return new ErrorCode(message.getSender(), 2);
            } else {
                return messagesService.recieveAndStoreMessage(message);
            }
        } catch (Exception e){
            return new ErrorCode(message.getSender(), 1);
        }
    }

    @GetMapping("/getErrorMessage")
    public String getErrorMessage(){
        return "{\"message\":\"empty message\"}";
    }

}
