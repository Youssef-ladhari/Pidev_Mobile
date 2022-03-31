package com.pidev.entities;

public class Tag {
    private int id;
    private String label;

    public Tag() {
    }

    public Tag(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Tag( String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
