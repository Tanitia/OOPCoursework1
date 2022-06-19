package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Voter extends User {
    private String VoterID;
    private String Password;
    private Boolean hasVoted;

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
}
