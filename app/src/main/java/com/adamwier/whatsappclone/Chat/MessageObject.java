package com.adamwier.whatsappclone.Chat;

public class MessageObject {

    String messageId, message, senderId;

    public MessageObject(String messageId, String senderId, String message){
        this.messageId = messageId;
        this.message = message;
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getMessageId() {
        return messageId;
    }
}
