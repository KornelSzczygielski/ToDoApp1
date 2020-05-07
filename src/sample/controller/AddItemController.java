package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.Task;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddItemController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLabel;


    @FXML
    private ImageView refreshButton;

    @FXML
    private JFXListView<?> datecreatedList;

    @FXML
    private JFXListView<?> descriptionList;

    @FXML
    private TableColumn<Task,String> col_date;

    @FXML
    private TableColumn<Task,String> col_desc;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private JFXButton refreshClicker;

    private Connection connection;
    private ObservableList<Task> list;
    private DatabaseHandler databaseHandler;

    ObservableList<Task> observableList = FXCollections.observableArrayList();

    private void TableView() {
        System.out.println("213");
        try {

            list = FXCollections.observableArrayList();

            String query = "SELECT * FROM tasks";

            connection = databaseHandler.getDbConnection();

            ResultSet set = connection.createStatement().executeQuery(query);

            while (set.next()) {

                Task task = new Task();
                task.setDatecreated(set.getString("datecreated"));
                task.setDescription(set.getString("description"));

                System.out.println(task);
                System.out.println(task.getDatecreated());
                System.out.println(task.getDescription());

                list.add(new Task(set.getString("datecreated"),set.getString("description")));


/*                col_date.setCellValueFactory(new PropertyValueFactory<>("datecreated"));
                col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));*/

                col_date.setCellValueFactory(new PropertyValueFactory<>(task.getDatecreated()));
                col_desc.setCellValueFactory(new PropertyValueFactory<>(task.getDescription()));
//                tableView.getColumns().addAll(col_date, col_desc);
                System.out.println(col_date);
                tableView.setItems(list);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
                    TableView();

                });
        refreshClicker.setOnAction(event -> {

            try {


                connection = databaseHandler.getDbConnection();

                ResultSet set = connection.createStatement().executeQuery("select * from tasks");

                while (set.next()) {


                    list.add(new Task(set.getString("datecreated"),set.getString("description")));


                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            col_date.setCellValueFactory(new PropertyValueFactory<>("datecreated"));
            col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            System.out.println(col_date);
            tableView.setItems(list);
            tableView.getColumns().addAll(col_date, col_desc);


        });





        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000),notTaskLabel);

            System.out.println("Added Clicked!");
            addButton.relocate(0,20);
            notTaskLabel.relocate(0,70);


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


            try {

                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                FadeTransition rootTransition = new FadeTransition(Duration.millis(2000),formPane);
                rootTransition.setFromValue(0f);
                rootTransition.setToValue(1f);
                rootTransition.setCycleCount(1);
                rootTransition.setAutoReverse(false);
                rootTransition.play();

                rootPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
