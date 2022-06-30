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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;


public class AdminNewElectionController {

    //prep for gui/controller flow
    @FXML
    private TextField NENameBox;
@FXML
    private Label NEErrorLabel;

    List<Candidate> candidateList = new ArrayList<>();
    List<String> candidate;

    List<Voter> voterList = new ArrayList<>();
    List<String> voter;

    //nav prep
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void createElection(ActionEvent actionEvent) throws IOException {
        //polymorphism - constructor used to call Staff method
        //without admin needing to log in
        Staff admin = new Staff("Admin","Admin");
        boolean success = admin.createElection(NENameBox, NEErrorLabel);
        if (success){
            //nav on success
            root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void returnToAdminPortal(ActionEvent actionEvent) throws IOException {
        //nav
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
