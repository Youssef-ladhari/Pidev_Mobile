package com.pidev.entities;

import java.util.Date;

public class Question {
    private int id;
    private String titre,contenu,attachment;
    private String date;
    private Tag tag;
    private Categorie categorie;
    private User user;

    public Question() {
    }

    public Question(int id, String titre, String contenu, String attachment, String date, Tag tag, Categorie categorie, User user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.attachment = attachment;
        this.date = date;
        this.tag = tag;
        this.categorie = categorie;
        this.user = user;
    }

    public Question(int id, String titre, String contenu, String attachment, String date, Tag tag, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.attachment = attachment;
        this.date = date;
        this.tag = tag;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", attachment='" + attachment + '\'' +
                ", date=" + date +
                ", tag=" + tag +
                ", categorie=" + categorie +
                ", user=" + user +
                '}';
    }
}
