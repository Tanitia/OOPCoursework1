package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class EndElectionController {

    List<Candidate> candidateList = new ArrayList<>();
    List<String> candidate;

    List<Voter> voterList = new ArrayList<>();
    List<String> voter;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void EndElection(ActionEvent actionEvent) throws IOException {
        List<String> electionDetailsList = new ArrayList<>();
        List<Election> electionList = new ArrayList<>();
        Election election;
        File CSVFile = new File("electionDetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        if (!CSVReader.hasNextLine()) {
            System.out.println("Error - no available election");
        } else {
            while (CSVReader.hasNextLine()) {
                CurrentLine = CSVReader.nextLine();
                electionDetailsList = asList(CurrentLine.split(","));//converts String to list of Strings
                election = new Election(electionDetailsList.get(0), electionDetailsList.get(1), Boolean.parseBoolean(electionDetailsList.get(2)));
                electionList.add(election);//Populates list with disks both game and music
            }
            for (int i = 0; i < electionList.size(); i++) {
                if (!electionList.get(i).getElectionStatus()) {
                    CSVFile = new File("candidatedetails.txt");
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
                    int tieVotes = 0;
                    boolean tie = false;
                    if (!(candidateList.size()<1)){
                        Candidate winner = candidateList.get(0);

                        for (int j = 1; j < candidateList.size() ; j++) {
                            if (winner.compare(candidateList.get(j)) && candidateList.get(j).getNumVotes()> tieVotes){
                                winner = candidateList.get(j);
                                tie = false;
                            }
                            else if(winner.getNumVotes()==candidateList.get(j).getNumVotes()){
                                tie = true;
                            }

                        }
                        if(!tie){
                            electionList.get(i).endElection(winner.getName());
                        }
                        else{
                            electionList.get(i).endElection("inconclusive");
                        }
                    }
                    else {
                        electionList.get(i).endElection("n/a");
                    }
                }

            }
            PrintWriter pw = new PrintWriter("electionDetails.txt");
            pw.close();
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
            for (int k = 0; k < voterList.size(); k++) {
                voterList.get(k).sethasVotedFalse();

            }
            for (int k = 0; k < electionList.size(); k++) {
                electionList.get(k).Save();

            }
            pw = new PrintWriter("userDetails.txt");
            pw.close();
            for (int k = 0; k < voterList.size(); k++) {
                voterList.get(k).Save();

            }

            pw = new PrintWriter("candidateDetails.txt");
            pw.close();
        }
        root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

        public void EEBack (ActionEvent actionEvent) throws IOException {
            root = FXMLLoader.load(getClass().getResource("admin_portal.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
