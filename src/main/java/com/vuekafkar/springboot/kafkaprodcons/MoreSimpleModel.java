package com.vuekafkar.springboot.kafkaprodcons;

public class MoreSimpleModel {
    private String title;
    private String description;

    public MoreSimpleModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MoreSimpleModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
