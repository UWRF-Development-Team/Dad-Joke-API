package org.falcon.jokegenerator;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

// From theoliverlear's Utilities library
public class FileLineRetriever {
    int targetLineIndex;
    String filePath;
    String data;
    /**
     * This constructor takes a target line index and a file path as arguments.
     * @param targetLineIndex
     * @param filePath
     */
    public FileLineRetriever(int targetLineIndex, String filePath) {
        this.targetLineIndex = targetLineIndex;
        this.filePath = filePath;
        this.data = "";
        this.fetchData();
    }
    /**
     * This method retrieves line data from a file.
     */
    public void fetchData() {
        String line = "";
        try {
            int lineCounter = 0;
            InputStream fileStream = new ClassPathResource(this.filePath).getInputStream();
            Scanner fileReader = new Scanner(fileStream);
            while (fileReader.hasNext()) {
                line = fileReader.nextLine();
                if (lineCounter == this.targetLineIndex) {
                    this.data = line;
                }
                lineCounter++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
