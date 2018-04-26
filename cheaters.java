package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class cheaters {
    //private Map<String, Scanner> files = new LinkedHashMap<>();
    private Map<Integer, String[]> comparisons = new LinkedHashMap<>();


    public static void main(String[] args) {
        Map<String, String[]> files = makeFiles();
        compareFiles(files);


    }

    public static Map<String, String[]> makeFiles() {
        Map<String, String[]> files = new LinkedHashMap<>();
        File dir = new File("C:\\Users\\hvu\\Desktop\\Project7\\sm_doc_set");


        //File[] listFiles = dir.listFiles();

        for (File file : dir.listFiles()) {
            String temp = "";
            StringBuilder result = new StringBuilder();
            if(file.isFile()) {
                try {
                    temp = new String(Files.readAllBytes(Paths.get(file.toString())));
                    temp = temp.toLowerCase();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for(int i = 0; i < temp.length(); i++){
                    if(((temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') || temp.charAt(i) == ' ') || (temp.charAt(i) >= '0' && temp.charAt(i) <= '9')){
                        result.append(temp.charAt(i));
                    }
                }

                temp = "";
                temp = temp + result;
                String[] wordsArray = temp.split(" ");
                files.put(file.getName(), wordsArray);
            }
        }

        if(files.isEmpty()){
            return null;
        }

        return files;

    }

    public static void compareFiles(Map<String, String[]> files){
        Iterator iterator = files.keySet().iterator();
        String key = "";

        while(iterator.hasNext()){
            String newValue = "";
            key = (String) iterator.next();
            String[] currentValue = files.get(key);
            int count = 0;  //Keeps track of which word to start with

            for(int i = 0, j = count; ; i++, j++){
                newValue += currentValue[j];
            }


        }
    }