package util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter {
    private java.io.FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private String location;

    public FileWriter() {
        this.location = "output.txt";
    }

    public FileWriter(String location) {
        this.location = location;
    }

    public void writeToFile(String data) throws SearchException {
        try {
            this.fileWriter = new java.io.FileWriter(location);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            this.bufferedWriter.write(data.trim());
        } catch (FileNotFoundException e) {
            throw new SearchException(e.getMessage(), e);
        } catch (IOException e) {
            throw new SearchException(e.getMessage(), e);
        } finally {
            try {
                this.bufferedWriter.close();
            } catch (IOException e) {
                throw new SearchException(e.getMessage(), e);
            }
        }

    }
}