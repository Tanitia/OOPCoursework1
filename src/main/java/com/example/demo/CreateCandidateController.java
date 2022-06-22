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
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField ACNameBox;
    @FXML
    private TextField ACDescriptionBox;

    @FXML
    private TextField ACIDBox;

    @FXML
            private Label ACErrorLabel;

    List<Candidate> candidateList = new ArrayList<>();
    List<String> candidate;
    public void confirmCandidateCreate(ActionEvent actionEvent) throws IOException {
        boolean success = candidateCreationLogic();
        if (success){
            root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }

    public boolean candidateCreationLogic(){
        boolean uniqueCandidateID = true;

        if(!ACNameBox.getText().equals("") && !ACDescriptionBox.getText().equals("") && !ACIDBox.getText().equals("")) {
            File CSVFile = new File("candidatedetails.txt");
            String CurrentLine;
            Scanner CSVReader = null;
            try {
                CSVReader = new Scanner(CSVFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (CSVReader.hasNextLine()) {
                CurrentLine = CSVReader.nextLine();
                candidate = asList(CurrentLine.split(","));//converts String to list of Strings
                candidateList.add(new Candidate(candidate.get(0), candidate.get(2), candidate.get(1), Integer.valueOf(candidate.get(3))));//Populates list with disks both game and music
            }
            for (int i = 0; i < candidateList.size(); i++) {
                if(candidateList.get(i).getCandidateID().equals(ACIDBox.getText())){
                    uniqueCandidateID = false;
                    ACErrorLabel.setText("ID is not unique");
                }


            }
            if(uniqueCandidateID) {
                Candidate currentCandidate = new Candidate(ACNameBox.getText(), ACDescriptionBox.getText(), ACIDBox.getText(), 0);
                currentCandidate.Save();
                return true;


            }
        } else{ACErrorLabel.setText("Please ensure all fields are entered");}
        return false;

    }

    public void ACBackGo(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
