package org.falcon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class JokeRepository {
    File fileRepository;
    String repositoryText;
    //----------------------------Constructors--------------------------------
    public JokeRepository(File fileRepository) {
        this.fileRepository = fileRepository;
        this.fetchRepositoryText();
    }
    //--------------------------Fetch-File-Text-------------------------------
    public void fetchRepositoryText() {
        this.repositoryText = "";
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(this.fileRepository);
            while (fileReader.hasNextLine()) {
                this.repositoryText += fileReader.nextLine() + "\n";
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (fileReader != null) {
            fileReader.close();
        } else {
            System.out.println("Error: file reader is null.");
        }
    }
    //---------------------------Contains-Joke--------------------------------
    public boolean containsJoke(Joke joke) {
        this.fetchRepositoryText();
        if (!this.repositoryText.isEmpty()) {
            return this.repositoryText.contains(joke.getJoke());
        } else {
            return false;
        }
    }
    //-----------------------------Save-Joke----------------------------------
    public void saveJoke(Joke joke) {
        // This performs the process of saving to the file.
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(this.fileRepository);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (fileWriter != null) {
            String separator = "-".repeat(78);
            fileWriter.print(this.repositoryText);
            fileWriter.println(separator);
            fileWriter.println(joke.getJoke());
            fileWriter.println(joke.getPunchline());
            fileWriter.close();
        } else {
            System.out.println("Error: file writer is null.");
        }
    }
    //------------------------------Add-Joke----------------------------------
    public boolean addJoke(Joke joke) {
        // This performs the action of attempting and reporting on the status
        // of adding a joke to the file.
        if (!this.containsJoke(joke)) {
            this.saveJoke(joke);
            return true;
        } else {
            System.out.println("---Joke already exists in repository---");
            return false;
        }
    }
}
