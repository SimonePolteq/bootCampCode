package DataDrivenCsv;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
//todo zoe de code die we nog krijgen
    @Test
    public static void readCsv() {

        String csvFile = "c://Users/bludwig-laptop/datadriven.csv"; // staat notmaal in de resource in project
        String line = "";
        String cvsSplitBy = ","; // use comma as separator
        String email="";
        String password="";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                email=data[0];
                password = data[1];
                System.out.println(email + " , " + password);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}