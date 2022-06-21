package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // creates user login details file if it doesn't already exist
        try {
            File userFile = new File("userDetails.txt");
            if (userFile.createNewFile()) {
                System.out.println("File created: " + userFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            File adminFile = new File("adminDetails.txt");
            if (adminFile.createNewFile()) {
                System.out.println("File created: " + adminFile.getName());
                FileWriter myWriter = new FileWriter("adminDetails.txt");
                myWriter.write("Admin" + "," + "Admin" + "\n");
                myWriter.close();
            } else {
                System.out.println("File already exists.");
            }
            File electionFile = new File("electionDetails.txt");
            if (electionFile.createNewFile()) {
                System.out.println("File created: " + electionFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("voting_portal_landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Voting Portal");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}