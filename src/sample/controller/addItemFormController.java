package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.Database.DatabaseHandler;
import sample.model.Task;
import sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class addItemFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField datacreatedField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    void initialize() {

        saveTaskButton.setOnAction(event -> {
            createTask();
        });

    }

    private void createTask(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String date = datacreatedField.getText();
        String desc = descriptionField.getText();



        Task task = new Task(date,desc);

        databaseHandler.signUpTask(task);

    }
}
