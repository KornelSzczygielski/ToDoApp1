package sample.model;

import com.jfoenix.controls.JFXTextField;

public class Task {
    private String datecreated;
    private String description;


    public Task() {
    }

    public Task(String datecreated, String description) {
        this.datecreated = datecreated;
        this.description = description;

    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
