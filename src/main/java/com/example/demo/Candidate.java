package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

//encapsulation for security
//priv attributes accesssed through public self contained methods
public class Candidate {
    private String candidateName;

    private String candidateDescription;

    private String candidateID;

    private Integer numVotes;

    //constructor
    public Candidate(String candidateName, String candidateDescription, String candidateID, Integer numVotes) {
        this.candidateName = candidateName;
        this.candidateDescription = candidateDescription;
        this.candidateID = candidateID;
        this.numVotes = numVotes;

}
public void Increment(){
        this.numVotes++;
}
    public boolean Save() {
        try {
            FileWriter myWriter = new FileWriter("candidatedetails.txt", true);
            myWriter.write(this.candidateName + "," + this.candidateID + "," + this.candidateDescription + "," +
                    this.numVotes);
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

    //as above - public getters for encapsulation
public String getName(){
        return this.candidateName;
};

    public String getCandidateID(){
        System.out.println(this.candidateID);
        return this.candidateID;

    };

    public int getNumVotes(){
        return this.numVotes;
    }

    //message passing - compare method takes
    //other Candidate object as parameter
    //objects take actions based on each other
    public Boolean compare(Candidate nextCandidate){
        return this.numVotes< nextCandidate.getNumVotes();
    }
}

