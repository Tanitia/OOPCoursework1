package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Staff extends User{
    protected String Username;
    protected String Password;
    public Staff(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }
    public Staff(){
        this.Username = "Username";
        this.Password = "Password";
    }
    public boolean createElection(TextField NENameBox, Label NEErrorLabel ) throws FileNotFoundException {
        List<Candidate> candidateList = new ArrayList<>();
        List<String> candidate;

        List<Voter> voterList = new ArrayList<>();
        List<String> voter;

        List<String> electionDetailsList = new ArrayList<>();
        List<Election> electionList = new ArrayList<>();
        Election election;
        File CSVFile = new File("electionDetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        if (!CSVReader.hasNextLine()) {
            election = new Election(NENameBox.getText());
            election.Save();
        } else {
            while (CSVReader.hasNextLine()) {
                CurrentLine = CSVReader.nextLine();
                electionDetailsList = asList(CurrentLine.split(","));//converts String to list of Strings
                election = new Election(electionDetailsList.get(0), electionDetailsList.get(1), Boolean.parseBoolean(electionDetailsList.get(2)));
                electionList.add(election);//Populates list with disks both game and music
            }
            boolean uniqueName = true;
            for (int i = 0; i < electionList.size(); i++) {
                if (electionList.get(i).getElectionName().equals(NENameBox.getText())) {
                    uniqueName = false;

                }
            }
            if (!NENameBox.getText().equals("")) {
                if (uniqueName) {
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
                            if (!(candidateList.size() < 1)) {
                                Candidate winner = candidateList.get(0);

                                for (int j = 1; j < candidateList.size(); j++) {
                                    if (winner.compare(candidateList.get(j)) && candidateList.get(j).getNumVotes() > tieVotes) {
                                        winner = candidateList.get(j);
                                        tie = false;
                                    } else if (winner.getNumVotes() == candidateList.get(j).getNumVotes()) {
                                        tie = true;
                                    }

                                }
                                if (!tie) {
                                    electionList.get(i).endElection(winner.getName());
                                } else {
                                    electionList.get(i).endElection("inconclusive");
                                }
                            } else {
                                electionList.get(i).endElection("n/a");
                            }

                        }
                    }


                    PrintWriter pw = new PrintWriter("electionDetails.txt");
                    pw.close();
                    electionList.add(new Election(NENameBox.getText()));
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
                    return true;


                }
                else{
                    NEErrorLabel.setText("Please use a unique election name");
                }
            }
            else{
                NEErrorLabel.setText("Please enter a name");
            }
        }
        return false;
    }
    public boolean createElection(String NENameBox) throws FileNotFoundException {//version of method without error label functionality(Used only for unit Tests)
        List<Candidate> candidateList = new ArrayList<>();
        List<String> candidate;

        List<Voter> voterList = new ArrayList<>();
        List<String> voter;

        List<String> electionDetailsList = new ArrayList<>();
        List<Election> electionList = new ArrayList<>();
        Election election;
        File CSVFile = new File("electionDetails.txt");
        String CurrentLine;
        Scanner CSVReader = new Scanner(CSVFile);
        if (!CSVReader.hasNextLine()) {
            election = new Election(NENameBox);
            election.Save();
        } else {
            while (CSVReader.hasNextLine()) {
                CurrentLine = CSVReader.nextLine();
                electionDetailsList = asList(CurrentLine.split(","));//converts String to list of Strings
                election = new Election(electionDetailsList.get(0), electionDetailsList.get(1), Boolean.parseBoolean(electionDetailsList.get(2)));
                electionList.add(election);//Populates list with disks both game and music
            }
            boolean uniqueName = true;
            for (int i = 0; i < electionList.size(); i++) {
                if (electionList.get(i).getElectionName().equals(NENameBox)) {
                    uniqueName = false;

                }
            }
            if (!NENameBox.equals("")) {
                if (uniqueName) {
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
                            if (!(candidateList.size() < 1)) {
                                Candidate winner = candidateList.get(0);

                                for (int j = 1; j < candidateList.size(); j++) {
                                    if (winner.compare(candidateList.get(j)) && candidateList.get(j).getNumVotes() > tieVotes) {
                                        winner = candidateList.get(j);
                                        tie = false;
                                    } else if (winner.getNumVotes() == candidateList.get(j).getNumVotes()) {
                                        tie = true;
                                    }

                                }
                                if (!tie) {
                                    electionList.get(i).endElection(winner.getName());
                                } else {
                                    electionList.get(i).endElection("inconclusive");
                                }
                            } else {
                                electionList.get(i).endElection("n/a");
                            }

                        }
                    }


                    PrintWriter pw = new PrintWriter("electionDetails.txt");
                    pw.close();
                    electionList.add(new Election(NENameBox));
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
                    return true;


                }
            }
        }
        return false;
    }


    public boolean candidateCreationLogic(String ACNameBox,String ACDescriptionBox,String ACIDBox, Label ACErrorLabel){
        boolean uniqueCandidateID = true;
        List<Candidate> candidateList = new ArrayList<>();
        List<String> candidate;

        if(!ACNameBox.equals("") && !ACDescriptionBox.equals("") && !ACIDBox.equals("")) {
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
                if(candidateList.get(i).getCandidateID().equals(ACIDBox)){
                    uniqueCandidateID = false;
                    ACErrorLabel.setText("ID is not unique");
                }


            }
            if(uniqueCandidateID) {
                Candidate currentCandidate = new Candidate(ACNameBox, ACDescriptionBox, ACIDBox, 0);
                currentCandidate.Save();
                return true;


            }
        } else{ACErrorLabel.setText("Please ensure all fields are entered");}
        return false;

    }
    public boolean candidateCreationLogic(String ACNameBox,String ACDescriptionBox,String ACIDBox){//version of method without error label functionality(Used only for unit Tests)
        boolean uniqueCandidateID = true;
        List<Candidate> candidateList = new ArrayList<>();
        List<String> candidate;

        if(!ACNameBox.equals("") && !ACDescriptionBox.equals("") && !ACIDBox.equals("")) {
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
                if(candidateList.get(i).getCandidateID().equals(ACIDBox)){
                    uniqueCandidateID = false;
                }


            }
            if(uniqueCandidateID) {
                Candidate currentCandidate = new Candidate(ACNameBox, ACDescriptionBox, ACIDBox, 0);
                currentCandidate.Save();
                return true;


            }
        }
        return false;

    }
}
