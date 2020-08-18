package com.sprint.summerproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Notice {
    @Id
    String id;
    String sendId;
    String sentName; //发送者
    String content; //通知内容
    Date time; //发送时间

    public Notice(String sendId, String sentName, String content, Date time) {
        this.sendId = sendId;
        this.sentName = sentName;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getSentName() {
        return sentName;
    }

    public void setSentName(String sentName) {
        this.sentName = sentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

