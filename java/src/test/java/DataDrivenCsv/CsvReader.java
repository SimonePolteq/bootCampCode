package DataDrivenCsv;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {
//code van trainers staat helemaal onderaan class
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

/*
code from Trainers

package dataDriven;

import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testcases.TestShopScenarioBrowFact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVDrivenTest extends TestShopScenarioBrowFact {

    @Test(dataProvider = "test")
    public void checkCSV(String subject, String email, String orderID, String message){
        HomePage homePage = new HomePage(driver);
        homePage.clickContactUS();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillInContactForm(subject, email , orderID, message);

        Assertions.assertThat(contactUsPage.validationMessage()).as("Validate succesfull contactform submit")
                .isEqualToIgnoringCase("your message has been successfully sent to our team.");
    }

    @DataProvider(name = "test")
    public Iterator<Object[]> Test(){
        List<Object []> testCases = new ArrayList<>();
        String csvFile = System.getProperty("user.dir")+"/src/test/java/dataDriven/testdata.csv";
        BufferedReader br = null;
        String[] data = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                data = line.split(cvsSplitBy);
                testCases.add(data);
                System.out.println(data[0] + data[1] + data[2]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return testCases.iterator();
    }
}

 */