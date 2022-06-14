package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField ULINameBox;

    @FXML
    private PasswordField ULIPasswordBox;


    public void logInAction(ActionEvent actionEvent) throws IOException {
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


        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).get(0).equals(ULINameBox.getText()) && userList.get(i).get(3).equals(ULIPasswordBox.getText())){
                System.out.println("True");
                root = FXMLLoader.load(getClass().getResource("voting_portal_landing.fxml"));
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
