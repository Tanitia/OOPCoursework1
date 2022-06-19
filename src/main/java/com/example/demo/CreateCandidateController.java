package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCandidateController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ACNameBox;
    @FXML
    private TextField ACDescriptionBox;

    @FXML
    private TextField ACIDBox;
    public void redirectAdminPortal(ActionEvent actionEvent) throws IOException {

        Candidate currentCandidate = new Candidate(ACNameBox.getText() , ACDescriptionBox.getText() , ACIDBox.getText(), 0);
        currentCandidate.Save();

        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
