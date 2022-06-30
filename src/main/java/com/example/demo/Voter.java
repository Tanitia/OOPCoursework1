package com.example.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

//voter inherits from user
//inherits reusable attributes
public class Voter extends User {

    //security through encapsulation
    //# and = attributes
    //accessed through public getter methods
    private String VoterID;
    private String Password;
    private Boolean hasVoted;

    //polymorphism through multiple constructors
    //adds adaptability

    public Voter(String Name, String Address, String VoterID, String Password) {
        this.Name = Name;
        this.Address = Address;
        this.VoterID = VoterID;
        this.Password = Password;
        this.hasVoted = false;
    }
    public Voter(String Name, String Address, String VoterID, String Password, Boolean hasVoted) {
        this.Name = Name;
        this.Address = Address;
        this.VoterID = VoterID;
        this.Password = Password;
        this.hasVoted = hasVoted;
    }

    //encapsulation - getters

    public boolean gethasVoted(){
        return this.hasVoted;
    }

    public void sethasVoted(){
        this.hasVoted = true;
    }

    public void sethasVotedFalse(){
        this.hasVoted = false;
    }

    public String getVoterUsername(){
        return this.Name;
    }

    public String getVoterID(){return this.VoterID;}

    public boolean Save() {
        try {
            FileWriter myWriter = new FileWriter("userdetails.txt", true);
            myWriter.write(this.Name + "," + this.Address + "," + this.VoterID +
                    "," + this.Password + "," + this.hasVoted);
            myWriter.write("\n");
            myWriter.close();
            System.out.println("File has been written to");
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }
    }
    public boolean Vote(String candidateID) throws FileNotFoundException {
        List<Candidate> candidateList = new ArrayList<>();
        List<String> candidate;

        List<Voter> voterList = new ArrayList<>();
        List<String> voter;
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
            candidateList.add(new Candidate(candidate.get(0), candidate.get(1), candidate.get(2), Integer.valueOf(candidate.get(3))));
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


        if (!gethasVoted()) {
            for (int x = 0; x < candidateList.size(); x++) {
                if (candidateID.equals(candidateList.get(x).getCandidateID())) {
                    candidateList.get(x).Increment();
                }
            }
            sethasVoted();
            PrintWriter pw = new PrintWriter("candidatedetails.txt");
            pw.close();
            for (int x = 0; x < candidateList.size(); x++) {
                candidateList.get(x).Save();

                }
                pw = new PrintWriter("userdetails.txt");
                pw.close();
                for (int x = 0; x < voterList.size(); x++) {
                    if(voterList.get(x).getVoterID().equals(this.getVoterID())){
                        Save();
                    }
                    else {
                        voterList.get(x).Save();
                    }
                }


        }



        return true;
    }
}
