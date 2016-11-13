package com.biel.notetree.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Biel on 11/11/2016.
 */
@Entity
public class Note {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    private Account account;

    private String path;
    private String title;
    private String content;
    private Date deadline;
    private double estimatedCompletionTime;

    Note() {
    }

    public Note(Account account, String path, String title, String content) {
        this.account = account;
        this.path = path;
        this.title = title;
        this.content = content;
    }

    public Note(Account account, String path, String content) {
        this.account = account;
        this.path = path;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public double getEstimatedCompletionTime() {
        return estimatedCompletionTime;
    }

    public void setEstimatedCompletionTime(double estimatedCompletionTime) {
        this.estimatedCompletionTime = estimatedCompletionTime;
    }
}
