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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class AdminLoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField ALINameBox;
    @FXML
    private PasswordField ALIPasswordBox;

    @FXML private Label ALIErrorLabel;


    public void adminLoginOK(ActionEvent actionEvent) throws IOException {
        List<List<String>> adminList = new ArrayList<>();
        List<String> user;
        File CSVFile = new File("adminDetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        while (CSVReader.hasNextLine()) {
            CurrentLine = CSVReader.nextLine();
            user = asList(CurrentLine.split(","));//converts String to list of Strings
            adminList.add(user);//Populates list with disks both game and music
        }
        System.out.println(adminList);

        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).get(0).equals(ALINameBox.getText()) && adminList.get(i).get(1).equals(ALIPasswordBox.getText())) {
                System.out.println("True");
                root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else{
                ALIErrorLabel.setText("Incorrect credentials");
            }
        }
    }
}
