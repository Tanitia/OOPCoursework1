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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class AdminDeleteUsersController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField DUUserIDBox;

    @FXML
    private Label DUErrorLabel;
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        boolean success = deleteUserMethod();
        if(success) {
            root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public boolean deleteUserMethod() throws FileNotFoundException {
        List<Voter> voterList = new ArrayList<>();
        Voter voterItem;
        List<String> voter;
        File CSVFile = new File("userdetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        while (CSVReader.hasNextLine()) {
            CurrentLine = CSVReader.nextLine();
            voter = asList(CurrentLine.split(","));//converts String to list of Strings
            voterItem = new Voter(voter.get(0), voter.get(1), voter.get(2), voter.get(3));
            voterList.add(voterItem);//Populates list with disks both game and music

        }
        if(voterList.size()>1){
            boolean validID = false;
            for (int i = 0; i < voterList.size(); i++) {
                if (DUUserIDBox.getText().equals(voterList.get(i).getVoterID())){
                    validID = true;
                    voterList.remove(i);

                }
            }
            if (validID == true){
                PrintWriter pw = new PrintWriter("userdetails.txt");
                pw.close();
                for (int i = 0; i < voterList.size(); i++) {
                    voterList.get(i).Save();
                }

                return true;
            }
            else {
                DUErrorLabel.setText("No user found with that ID");
            }

        } else {
            DUErrorLabel.setText("No users currently exist");
        }
        return false;
    }
    public void DUBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
