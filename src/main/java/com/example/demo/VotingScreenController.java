package com.example.demo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

import static java.util.Arrays.asList;

public class VotingScreenController implements Initializable{

    @FXML
    private ListView<String> votingCandidateListView;

    @FXML
    private Label selectCandidateLabel;

    private String username;

    List<Candidate> candidateList = new ArrayList<>();
    List<String> candidate;

    List<Voter> voterList = new ArrayList<>();
    List<String> voter;
    String currentCandidate;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
            candidateList.add(new Candidate(candidate.get(0), candidate.get(1), candidate.get(2), Integer.valueOf(candidate.get(3))));//Populates list with disks both game and music
        }
        CSVFile = new File("userdetails.txt");
        try {
            CSVReader = new Scanner(CSVFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (CSVReader.hasNextLine()) {
            CurrentLine = CSVReader.nextLine();
            voter = asList(CurrentLine.split(","));//converts String to list of Strings
            voterList.add(new Voter(voter.get(0), voter.get(1), voter.get(2),
                    voter.get(3), Boolean.parseBoolean(voter.get(4))));
        }
        for (int i = 0; i < candidateList.size(); i++) {
            votingCandidateListView.getItems().add(candidateList.get(i).getName());
            }


        votingCandidateListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

                currentCandidate = votingCandidateListView.getSelectionModel().getSelectedItem();

                selectCandidateLabel.setText(currentCandidate);

            }
        });
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void confirmVote(ActionEvent actionEvent) throws FileNotFoundException {
        Voter currentVoter;
        for (int i = 0; i < voterList.size(); i++) {
            if (voterList.get(i).getVoterUsername().equals(username)){
                currentVoter = voterList.get(i);
                if (!currentVoter.gethasVoted()) {
                    for (int x = 0; x < candidateList.size(); x++) {
                        if (selectCandidateLabel.getText() == candidateList.get(x).getName()) {
                            candidateList.get(x).Increment();
                        }
                    }
                    PrintWriter pw = new PrintWriter("candidatedetails.txt");
                    pw.close();
                    for (int x = 0; x < candidateList.size(); x++) {
                        candidateList.get(x).Save();

                    }
                    currentVoter.sethasVoted();
            }
        }

    }
    }
}
