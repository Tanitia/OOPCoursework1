package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class AdminNewElectionController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    public void createElection(ActionEvent actionEvent) throws IOException {
        File electionFile = new File("electionDetails.txt");
        if (electionFile.createNewFile()) {
            System.out.println("File created: " + electionFile.getName());
            FileWriter myWriter = new FileWriter("adminDetails.txt");
            myWriter.write("Admin" + "," + "Admin" + "\n");
            myWriter.close();
        } else {
            System.out.println("File already exists.");
        }
    }

    public void returnToAdminPortal(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
