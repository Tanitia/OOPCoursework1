package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;

public class Candidate {
    private String candidateName;

    private String candidateDescription;

    private String candidateID;

    private Integer numVotes;

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
public String getName(){
        return this.candidateName;
};
}

