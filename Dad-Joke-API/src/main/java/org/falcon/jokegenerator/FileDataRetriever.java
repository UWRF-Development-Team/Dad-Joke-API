package org.falcon.jokegenerator;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
        this.lines = new ArrayList<>();
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
            InputStream fileStream = new ClassPathResource(this.filePath).getInputStream();
            Scanner fileReader = new Scanner(fileStream);
            while (fileReader.hasNext()) {
                line = fileReader.nextLine();
                this.data += line + "\n";
                this.lines.add(line);
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

