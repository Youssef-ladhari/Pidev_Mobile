package com.pidev.entities;

public class Event {

    private int id;
    private String nom_event;
    private String type_event;
    private String status_event;
    private String date_event;
    private String lieu_event;
    private String description_event;



    public Event() {}

    public Event(String nom_event, String type_event, String status_event, String date_event, String lieu_event, String description_event) {
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.status_event = status_event;
        this.date_event = date_event;
        this.lieu_event = lieu_event;
        this.description_event = description_event;
    }

    public Event(int id, String nom_event, String type_event, String status_event, String date_event, String lieu_event, String description_event) {
        this.id = id;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.status_event = status_event;
        this.date_event = date_event;
        this.lieu_event = lieu_event;
        this.description_event = description_event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getStatus_event() {
        return status_event;
    }

    public void setStatus_event(String status_event) {
        this.status_event = status_event;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom_event='" + nom_event + '\'' +
                ", type_event='" + type_event + '\'' +
                ", status_event='" + status_event + '\'' +
                ", date_event=" + date_event +
                ", lieu_event='" + lieu_event + '\'' +
                ", description_event='" + description_event + '\'' +
                '}';
    }
}
