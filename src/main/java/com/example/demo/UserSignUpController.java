package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;


public class UserSignUpController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField USUNameBox;
    @FXML
    private TextField USUAddressBox;

    @FXML
    private TextField USUIDBox;

    @FXML
    private TextField USUPasswordBox;

    @FXML
    private TextField USUConfirmBox;


    public void redirectUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            FileWriter myWriter = new FileWriter("userdetails.txt", true);
            myWriter.write(USUNameBox.getText() + "," + USUAddressBox.getText() + "," + USUIDBox.getText() +
                    "," + USUPasswordBox.getText() + "\n");
            myWriter.close();
            System.out.println("File has been written to");
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        root = FXMLLoader.load(getClass().getResource("user_login.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println(USUNameBox.getText());
    }

}
