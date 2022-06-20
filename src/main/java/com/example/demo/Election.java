package com.example.demo;

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
}
