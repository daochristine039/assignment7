package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class cheaters {
    private static Map<String, ArrayList<String>> sequences = new LinkedHashMap<>();
    //private static Map<String, String> toCompare = new LinkedHashMap<>();
    private static Map<String[], Integer> comparisons = new LinkedHashMap<>();
    public static int wordSequenceLength ;   //number words looking for common
    public static int minNumSameSequence;   //number of sequences in common we are looking for
    public static String filePath;


    public static void main(String[] args) {
        //long startTime = System.nanoTime();
        makeFiles();
        compareFiles();
        printComparisons();
        //long endTime = System.nanoTime();
        //System.out.println("Took "+(endTime - startTime) + " ns");

    }

    public static void parseArgs(String[] args) {
        if(args.length != 3) {
            System.err.println("incorrect arguments");
        } else {
            filePath = args[0];
            wordSequenceLength = Integer.parseInt(args[1]);
            minNumSameSequence = Integer.parseInt(args[2]);
        }
    }

    public static void makeFiles() {
        Map<String, String[]> files = new LinkedHashMap<>();
        File dir = new File("C:\\Users\\hvu\\Desktop\\Project7\\med_doc_set");


        //File[] listFiles = dir.listFiles();

        for (File file : dir.listFiles()) {
            String temp = "";                   //To get array of words
//            String temp1 = "";                  //To get a string without spaces and punctuation
            StringBuilder result = new StringBuilder();
//            StringBuilder result1 = new StringBuilder();
            if(file.isFile()) {
                try {
                    temp = new String(Files.readAllBytes(Paths.get(file.toString())));
                    temp = temp.toLowerCase();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                char previous = ' ';
                for(int i = 0; i < temp.length(); i++){
                    if((temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') || temp.charAt(i) == ' ' || temp.charAt(i) == '\n' || temp.charAt(i) == '\t' || temp.charAt(i) == '\r' || (temp.charAt(i) >= '0' && temp.charAt(i) <= '9')){
                        if((previous == '\n' || previous == '\t' || previous == '\r' || previous == ' ') && (temp.charAt(i) == '\n' || temp.charAt(i) == '\t' || temp.charAt(i) == '\r' || temp.charAt(i) == ' ')){
                            continue;
                        } else if(temp.charAt(i) == '\n' || temp.charAt(i) == '\t' || temp.charAt(i) == '\r' || temp.charAt(i) == ' '){
                            result.append('\n');
                        } else {
                            result.append(temp.charAt(i));
                        }
                    }
                    previous = temp.charAt(i);
                }

                temp = "";
                temp = temp + result;
                String[] wordsArray = temp.split("\n");
                files.put(file.getName(), wordsArray);
            }
        }

        if(files.isEmpty()){
            return;
        }

        Iterator iterator = files.keySet().iterator();
        String key = "";
        String temp = "";

        while(iterator.hasNext()){
            ArrayList<String> tempArr = new ArrayList<>();
            key = (String) iterator.next();
            String[] currentValue = files.get(key);
            int count = 0;

            loop1: for(; ; count++) {
                temp = "";
                for (int i = 0, j = count; i < wordSequenceLength; i++, j++) {   //i = checks for 6-word-sequence
                    if (j == currentValue.length) {
                        break loop1;
                    }
                    temp += currentValue[j];
                }

                tempArr.add(temp);
            }

            sequences.put(key, tempArr);
        }
    }

    public static void compareFiles(){
        ArrayList<String> current = new ArrayList<>();
        ArrayList<String> toCmp = new ArrayList<>();

        Iterator iterator = sequences.keySet().iterator();
        String key = "";
        String key1 = "";
        int numOfRep = 0;

        while(iterator.hasNext()){
            key = (String) iterator.next();
            current = sequences.get(key);

            Iterator cmpIterator = sequences.keySet().iterator();

            while(cmpIterator.hasNext()){
                numOfRep = 0;
                key1 = (String) cmpIterator.next();
                if(key1.equals(key)){
                    continue;
                }
                toCmp = sequences.get(key1);

                for(String sixWords : current){
                    if(toCmp.contains(sixWords)){
                        numOfRep++;
                    }
                }

                ArrayList keyList = new ArrayList<>(comparisons.keySet());
                if(numOfRep > minNumSameSequence){
                    String[] temp1 = {key, key1};
                    String[] check = {key1, key};
                    Boolean contained = false;

                    for(int k = 0; k < keyList.size(); k++){
                        String[] stringArr = (String[]) keyList.get(k);
                        if(Arrays.equals(check, stringArr)){
                            contained = true;
                            break;
                        }
                    }

                    if(!contained) {
                        comparisons.put(temp1, numOfRep);
                    }
                }
            }
        }
    }

    public static void printComparisons(){
        List<Map.Entry<String[], Integer>> list = new ArrayList<>(comparisons.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String[], Integer> result = new LinkedHashMap<>();
        for(Map.Entry<String[], Integer> entry : list){
            result.put(entry.getKey(), entry.getValue());
        }

        Iterator iterator = result.keySet().iterator();
        ArrayList keyList = new ArrayList<>(result.keySet());
        for(int i = result.size() - 1; i >= 0; i--){
            String[] key = (String[]) keyList.get(i);
            int value = result.get(key);
            System.out.print(value + ": ");

            for(int j = 0; j < key.length; j++){
                if(j == key.length - 1){
                    System.out.println(key[j]);
                } else {
                    System.out.print(key[j] + ", ");
                }
            }

        }

    }
}

//    public static void compareFiles(Map<String, String[]> files){
//        Iterator iterator = files.keySet().iterator();
//        String key = "";            //File name
//        String key1 = "";
//
//        while(iterator.hasNext()){
//            String newValue = "";       //current 6-word-sequence
//
//            Iterator fileToCmp = toCompare.keySet().iterator();
//            String strToCmp = "";
//
//            key = (String) iterator.next();
//            String[] currentValue = files.get(key);     //File content
//
//            while(fileToCmp.hasNext()) {
//                int count = 0;  //Keeps track of which word to start with
//                int numOfRep = 0;
//                key1 = (String) fileToCmp.next();
//                if(key1.equals(key)){
//                    continue;
//                }
//                strToCmp = toCompare.get(key1);
//
//                loop1:
//                for (; ; count++) {
//                    newValue = "";
//                    for (int i = 0, j = count; i < 6; i++, j++) {   //i = checks for 6-word-sequence
//                        if (j == currentValue.length) {
//                            break loop1;
//                        }
//                        newValue += currentValue[j];
//                    }
//
//                    for (int i = 0, j = newValue.length(); ; i++, j++) {
//                        if (j == strToCmp.length() + 1) {
//                            break;
//                        }
//                        String temp = strToCmp.substring(i, j);
//
//                        if (temp.equals(newValue)) {
//                            numOfRep++;
//                        }
//                    }
//                }
//
//                if(numOfRep >= 200){                    //FIX 200 TO ANY NUMBER !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                    comparisons.put(numOfRep, key);
//                    comparisons.put(numOfRep, key1);
//                }
//            }
//        }
//    }