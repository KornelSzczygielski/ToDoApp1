package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sample.animations.Shaker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLabel;

    @FXML
    void initialize() {
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addItem.fxml'.";


        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttobShaker = new Shaker(addButton);
            buttobShaker.shake();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000),notTaskLabel);

            System.out.println("Added Clicked!");
            addButton.relocate(0,20);
            notTaskLabel.relocate(0,85);

            addButton.setOpacity(0);
            notTaskLabel.setOpacity(0);

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();
        });
    }
}
