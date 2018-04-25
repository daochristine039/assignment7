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
        System.out.println(files);

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
}
