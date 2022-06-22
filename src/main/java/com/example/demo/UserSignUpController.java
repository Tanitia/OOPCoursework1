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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;


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


    public void confirmSignup(ActionEvent actionEvent) throws IOException {
        boolean success = voterSignup();
        if (success) {
            root = FXMLLoader.load(getClass().getResource("user_login.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public boolean voterSignup() throws FileNotFoundException {
        if (!USUNameBox.getText().equals("") && !USUAddressBox.getText().equals("")
                && !USUIDBox.getText().equals("") && !USUPasswordBox.getText().equals("")) {
            List<List<String>> userList = new ArrayList<>();
            List<String> user;
            File CSVFile = new File("userdetails.txt");
            String CurrentLine;
            Scanner CSVReader = new Scanner(CSVFile);
            while (CSVReader.hasNextLine()) {
                CurrentLine = CSVReader.nextLine();
                user = asList(CurrentLine.split(","));//converts String to list of Strings
                userList.add(user);//Populates list with disks both game and music
            }
            boolean uniqueID = true;
            for (int i = 0; i < userList.size(); i++) {
                if (USUIDBox.getText().equals(userList.get(i).get(2))) {
                    uniqueID = false;
                }
            }
            if (uniqueID) {
                Voter currentVoter = new Voter(USUNameBox.getText(), USUAddressBox.getText(), USUIDBox.getText(), USUPasswordBox.getText());
                currentVoter.Save();
                return true;
            }
        }
        return false;

    }

    public void USUGoBack(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("voting_portal_landing.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
