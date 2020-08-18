package com.sprint.summerproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class TeamNotice {
    @Id
    String id; //MongoDB自动添加的id
    Integer	type; //type=1表示加入团队通知，2表示被踢出团队通知
    String sentId; //发送者的用户id
    String sentName; //发送者
    String content; //通知内容
    Boolean read; //false表示未读，true表示已读
    Date time; //发送时间

    public TeamNotice(Integer type, String sentId, String sentName, String content, Date time) {
        this.type = type;
        this.sentId = sentId;
        this.sentName = sentName;
        this.content = content;
        this.read = false;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSentId() {
        return sentId;
    }

    public void setSentId(String sentId) {
        this.sentId = sentId;
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

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
