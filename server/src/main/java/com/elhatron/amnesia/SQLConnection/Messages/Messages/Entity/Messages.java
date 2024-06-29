package com.elhatron.amnesia.SQLConnection.Messages.Messages.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Zarządza baza danych
    @Column(name = "id")
    private int id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "reciver")
    private String reciver;

    @Column(name = "message")
    private String message;

    public int getId() {    return id;  }

    public void setId(int id) { this.id = id;   }

    public String getSender() { return sender;  }

    public void setSender(String sender) {  this.sender = sender;   }

    public String getReciver() {    return reciver; }

    public void setReciver(String reciver) {    this.reciver = reciver; }

    public String getMessage() {    return message; }

    public void setMessage(String message) {    this.message = message; }

    public Messages() {}

    public Messages(int id, String sender, String reciver, String message) {
        this.id = id;
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{"+
                "id=" + id + "\""+
                ", sender=" + sender + "\""+
                ", reciver=" + reciver + "\""+
                ", message=" + message + "\""+
                "}";
    }
}
