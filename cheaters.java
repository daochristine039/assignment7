package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class cheaters {
    //private Map<String, Scanner> files = new LinkedHashMap<>();
    private Map<String, Integer> comparisons = new LinkedHashMap<>();


    public static void main(String[] args) {
        Map<String, StringBuilder> files = makeFiles();


    }

    public static Map<String, StringBuilder> makeFiles() {
        Map<String, StringBuilder> files = new LinkedHashMap<>();
        File dir = new File("C:\\Users\\hvu\\Desktop\\Project7\\sm_doc_set");
        String temp = "";

        //File[] listFiles = dir.listFiles();

        for (File file : dir.listFiles()) {
            StringBuilder result = new StringBuilder();
            if(file.isFile()) {
                try {
                    temp = new String(Files.readAllBytes(Paths.get(file.toString())));
                    temp = temp.toLowerCase();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for(int i = 0; i < temp.length(); i++){
                    if((temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') || temp.charAt(i) == ' '){
                        result.append(temp.charAt(i));
                    }
                }

                files.put(file.getName(), result);
                temp = "";
            }
        }

        if(files.isEmpty()){
            return null;
        }

        return files;

    }

    public static Map<Integer, StringBuilder[]> compareFiles(Map<Integer, StringBuilder[]> comparisons, Map<String, StringBuilder> files){
        Iterator iterator = files.keySet().iterator();
        String key = "";

        while(iterator.hasNext()){
            StringBuilder newValue = new StringBuilder();
            key = (String) iterator.next();
            StringBuilder currentValue = files.get(key);
            int count = 0;  //Keeps track of which word to start with

            for(int i = 0, j = 0; i < currentValue.length(); i++){  //6-word sequence without
                if(j == 6){
                    count++;
                    break;
                }

                
                if(currentValue.charAt(i) >= 'a' && currentValue.charAt(i) <= 'z'){
                    newValue.append(currentValue.charAt(i));
                } else if(currentValue.charAt(i) == ' '){
                    j++;
                }
            }


        }

        return comparisons;
    }
}
