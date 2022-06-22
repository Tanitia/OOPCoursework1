package com.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.util.Arrays.asList;


public class ViewResultsController implements Initializable {

    @FXML
    private ListView<String> VRElectionListview;

    @FXML
    private Label VRWinnerLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;
    List<Election> electionList = new ArrayList<>();
    List<String> election;
    String currentElection;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        boolean success = viewResultsLogic();
        if(success){}

    }

    public boolean viewResultsLogic(){
        File CSVFile = new File("electiondetails.txt");
        String CurrentLine;
        Scanner CSVReader = null;
        try {
            CSVReader = new Scanner(CSVFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (CSVReader.hasNextLine()) {
            CurrentLine = CSVReader.nextLine();
            election = asList(CurrentLine.split(","));//converts String to list of Strings
            electionList.add(new Election(election.get(0), election.get(1), Boolean.parseBoolean(election.get(2))));//Populates list with disks both game and music
        }


        for (int i = 0; i < electionList.size(); i++) {
            VRElectionListview.getItems().add(electionList.get(i).getElectionName());
        }


        VRElectionListview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                currentElection = VRElectionListview.getSelectionModel().getSelectedItem();
                for (int i = 0; i < electionList.size(); i++) {
                    if (currentElection.equals(electionList.get(i).getElectionName())){
                        VRWinnerLabel.setText(electionList.get(i).getElectionWinner());
                    }
                }


            }
        });
        return true;
    }

    public void VRBackButton(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
