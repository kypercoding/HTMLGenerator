/**
 * App.java
 * 
 * Author: kypercoding
 * Description: code written to automate the task of inserting information into
 * HTML files. 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        // default file paths and pattern
        String inputProperties = "input/input.properties";
        String inputHTML = "input/input.html";
        String outputHTML = "input/output.html";
        String format = "{{{%s}}}";

        try {
            Scanner input = new Scanner(System.in);

            // asks user for .properties file
            System.out.println("Please enter the path of your .properties file, or press ENTER for default.");
            
            String response = input.nextLine();
            
            if (response != "") {
                inputProperties = response;
            }
            
            // asks user for .html input file
            System.out.println("Please enter the path of your .html file, or press ENTER for default.");
            
            response = input.nextLine();
            
            if (response != "") {
                inputHTML = response;
            }

            // asks user for .html output path
            System.out.println("Please enter the path of your desired output .html file, or press ENTER for default.");

            response = input.nextLine();
            
            if (response != "") {
                outputHTML = response;
            }

            // asks user for format of HTML variables
            System.out.println("Please enter the desired format of the HTML variables, or press ENTER for default");

            response = input.nextLine();
            
            if (response != "") {
                format = response;
            }

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // file input stream and properties files
        FileInputStream fis = null;
        Properties prop = null;

        try {
            // read input properties file
            fis = new FileInputStream(inputProperties);
            
            // new properties file
            prop = new Properties();

            // load in properties file from input stream
            prop.load(fis);

            // close the input stream
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // read input HTML file line by line
        StringBuilder html = null;

        try {
            // input html file
            File file = new File(inputHTML);
            Scanner scanner = new Scanner(file);

            // initializes StringBuilder object
            html = new StringBuilder();

            // reads all the html file's lines
            while (scanner.hasNextLine()) {
                html.append(scanner.nextLine());
                html.append("\n");
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String contents = html.toString();

        // replaces each variable in the HTML with
        // the keys in properties
        Enumeration<Object> em = prop.keys();

        while(em.hasMoreElements()) {
            // gets key and value from the properties
            String key = (String) em.nextElement();
            String value = (String) prop.get(key);

            // replaces key in HTML with value
            contents = contents.replace(String.format(format, key), value);
        }

        // write new file containing new HTML
        try {
            File file = new File(outputHTML);
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            writer.write(contents);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
