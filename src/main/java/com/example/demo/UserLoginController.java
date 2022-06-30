package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;


public class UserLoginController {


    static VotingScreenController VoterController ;

    //nav prep
    private Stage stage;
    private Scene scene;
    private Parent root;

    //data-gui flow
    @FXML
    private TextField ULIIDBox;

    @FXML
    private PasswordField ULIPasswordBox;

    @FXML
    private Label ULIErrorLabel;


    public void logInAction(ActionEvent actionEvent) throws IOException {
        //nav on success
        boolean success = confirmLogin();
        if (success) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("voting_screen.fxml"));
            Parent root = loader.load();

            VoterController = loader.getController();
            VoterController.setVoterID(ULIIDBox.getText());

            Scene scene = new Scene(root);
            Stage dash = new Stage();
            dash.setScene(scene);
            dash.show();

            Stage stage = (Stage) ULIIDBox.getScene().getWindow();
            stage.close();

        }
    }
    public boolean confirmLogin() throws IOException {
        //lists for value holding
        List<List<String>> userList = new ArrayList<>();
        List<String> user;
        File CSVFile = new File("userdetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        while (CSVReader.hasNextLine()) {
            CurrentLine = CSVReader.nextLine();
            user = asList(CurrentLine.split(","));//converts String to list of Strings
            userList.add(user);
        }


        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get(2).equals(ULIIDBox.getText())
                    && userList.get(i).get(3).equals(ULIPasswordBox.getText())
                    && userList.get(i).get(4).equals("false")) {

                return true;

            } else if (userList.get(i).get(2).equals(ULIIDBox.getText())
                    && userList.get(i).get(3).equals(ULIPasswordBox.getText())
                    && userList.get(i).get(4).equals("true")) {
                ULIErrorLabel.setText("You have already voted");
                break;
            }
            else {
                ULIErrorLabel.setText("Incorrect username or password");
            }
        }
        return false;
    }

    public void ULIBackButton(ActionEvent actionEvent) throws IOException {
        //nav
        root = FXMLLoader.load(getClass().getResource("voting_portal_landing.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
