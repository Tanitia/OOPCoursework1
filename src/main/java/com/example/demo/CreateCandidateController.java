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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class CreateCandidateController {

    //nav prep
    private Stage stage;
    private Scene scene;
    private Parent root;

    //gui data flow
    @FXML
    private TextField ACNameBox;
    @FXML
    private TextField ACDescriptionBox;

    @FXML
    private TextField ACIDBox;

    @FXML
            private Label ACErrorLabel;

    public void confirmCandidateCreate(ActionEvent actionEvent) throws IOException {
        //new object from staff class
        Staff staff = new Staff();

        boolean success = staff.candidateCreationLogic(ACNameBox.getText(),ACDescriptionBox.getText(),ACIDBox.getText(),ACErrorLabel);
        if (success){
            //redirect on success
            root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }



    public void ACBackGo(ActionEvent actionEvent) throws IOException {
        //redirect
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
