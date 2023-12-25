package org.falcon.jokegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to retrieve data from a file.
 */
public class FileDataRetriever {
    ArrayList<String> lines;
    String filePath;
    String data;
    public FileDataRetriever(String filePath) {
        this.filePath = filePath;
        this.data = "";
        this.fetchData();
    }
    /**
     * This method retrieves data from a file.
     */
    public void fetchData() {
        String line = "";
        try {
            int lineCounter = 0;
            Scanner fileReader = new Scanner(new File(this.filePath));
            while (fileReader.hasNext()) {
                line = fileReader.nextLine();
                this.data += line + "\n";
                this.lines.add(line);
                lineCounter++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * This method returns the data retrieved from the file.
     * @return String
     */
    public String getData() {
        return this.data;
    }
}

