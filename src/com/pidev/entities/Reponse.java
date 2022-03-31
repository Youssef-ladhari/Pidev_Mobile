package com.pidev.entities;

import java.util.Date;

public class Reponse {

    private int id;
    private String contenu,attachment;
    private String datePublication;
    private Question question;
    private User user;

    public Reponse() {
    }

    public Reponse(int id, String contenu, String attachment, String datePublication, Question question,User user) {
        this.id = id;
        this.contenu = contenu;
        this.attachment = attachment;
        this.datePublication = datePublication;
        this.question = question;
        this.user = user;
    }
    public Reponse( String contenu, String attachment, String datePublication, Question question,User user) {
        this.contenu = contenu;
        this.attachment = attachment;
        this.datePublication = datePublication;
        this.question = question;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", attachment='" + attachment + '\'' +
                ", datePublication=" + datePublication +
                ", question=" + question +
                ", user=" + user +
                '}';
    }
}
