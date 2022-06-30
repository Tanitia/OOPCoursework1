package com.example.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class VotingScreenControllerTest {
/*
@Test
    public void Election() throws FileNotFoundException {
    Voter tea =new Voter("tea","tea","tea","tea",false);
    Staff staff = new Staff();


    assertTrue(staff.createElection("sssadafrgrethssss"));//can create election
    assertTrue(staff.candidateCreationLogic("12345","123","123"));//can create candidate
    assertTrue(staff.candidateCreationLogic("123","123","1234"));//can create candidate
    assertTrue(tea.Vote("123"));//can user Vote
    assertTrue(tea.Vote("123"));//can user Vote
    assertFalse(staff.candidateCreationLogic("123s","123s","123"));//cannot create candidate with an already used ID
    assertTrue(staff.endElection());//can end election


    List<Election> electionList = new ArrayList<>();
    List<String> election;

    File CSVFile = new File("electiondetails.txt");
    Scanner CSVReader = null;
    String CurrentLine;
    try {
        CSVReader = new Scanner(CSVFile);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    while (CSVReader.hasNextLine()) {
        CurrentLine = CSVReader.nextLine();
        election = asList(CurrentLine.split(","));//converts String to list of Strings
        electionList.add(new Election(election.get(0), election.get(1), Boolean.parseBoolean(election.get(2))));
    }
    System.out.println(electionList.get(electionList.size()-1).getElectionWinner());
    assertTrue(electionList.get(electionList.size()-1).getElectionWinner().equals("12345"));//has the correct candidate won
    assertFalse(staff.endElection());//cannot end an election if non is currently running
}*/
@Test
    public void electionWithMultipleVoters() throws FileNotFoundException {
    Staff staff = new Staff();
    List<Voter> voterList = new ArrayList<>();
    List<String> voter;
    File CSVFile = new File("userdetails.txt");
    String CurrentLine;
    Scanner CSVReader = null;
    int numOfVoters = 5;

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

    assertTrue(staff.createElection("asddsafsssssadassesdasdwer"));//can create election
    assertTrue(staff.candidateCreationLogic("123","123","123"));//can create candidate
    assertTrue(staff.candidateCreationLogic("123w","123","234"));//can create candidate
    assertTrue(staff.candidateCreationLogic("123ww3","123","345"));//can create candidate
    List<Voter> voters = new ArrayList<>();
    try{
        for (int i = 0; i < numOfVoters; i++) {
            voters.add(voterList.get(i));
        }
    }
    catch (IndexOutOfBoundsException e){
        voters = new ArrayList<>();
        for (int i = 0; i < numOfVoters; i++) {

            voters.add(new Voter("Name","Address",String.valueOf(i),"Password"));
        }
    }

    for (int i = 0; i < numOfVoters; i++) {
        voters.get(i).Vote("345");
    }
    //assertTrue(staff.endElection());//can end election

    List<Election> electionList = new ArrayList<>();
    List<String> election;

    CSVFile = new File("electiondetails.txt");
    try {
        CSVReader = new Scanner(CSVFile);
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }
    while (CSVReader.hasNextLine()) {
        CurrentLine = CSVReader.nextLine();
        election = asList(CurrentLine.split(","));//converts String to list of Strings
        electionList.add(new Election(election.get(0), election.get(1), Boolean.parseBoolean(election.get(2))));
    }

    assertTrue(electionList.get(electionList.size()-1).getElectionWinner().equals("123ww3"));//has the correct candidate won


}
}