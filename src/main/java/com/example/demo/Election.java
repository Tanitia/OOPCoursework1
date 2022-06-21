package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

public class Election {
    private String electionName;

    private String electionWinner;

    private boolean electionStatus;

    public Election(String electionName){
        this.electionName = electionName;
        this.electionWinner = "null";
        this.electionStatus = false;
    }

    public Election(String electionName, String electionWinner, boolean electionStatus){
        this.electionName = electionName;
        this.electionWinner = electionWinner;
        this.electionStatus = electionStatus;
    }

    public void endElection(String electionWinner){
        this.electionWinner = electionWinner;
        this.electionStatus= true;
    }

    public String getElectionName(){
        return this.electionName;
    }

    public String getElectionWinner(){
        return this.electionWinner;
    }

    public boolean getElectionStatus(){
        return this.electionStatus;
    }

    public boolean Save() {
        try {
            FileWriter myWriter = new FileWriter("electionDetails.txt", true);
            myWriter.write(this.electionName + "," + this.electionWinner + "," + this.electionStatus);
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
}
