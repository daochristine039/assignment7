package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class cheaters {
    //private Map<String, Scanner> files = new LinkedHashMap<>();
    private Map<String, Integer> comparisons = new LinkedHashMap<>();


    public static void main(String[] args) {

    }

    public static Map makeFiles() {
        Collection<Scanner> all = new ArrayList<Scanner>();
        Map<String, StringBuilder> files = new LinkedHashMap<>();
        File dir = new File("C:/Users/hvu/Desktop/Project7/sm_doc_set");
        StringBuilder temp = new StringBuilder();

        for (File file : dir.listFiles()) {
            if(file.isFile()) {
                try {
                    Scanner s = new Scanner(file);
                    all.add(s);
//                    s.close();

                    while(s.hasNext()){
                        temp.append(s.next().toLowerCase());
                    }

                    
                } catch (FileNotFoundException e) {
                    //DO SOMETHING?
                }
            }
        }


    }
}
