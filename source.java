import java.io.IOException;
import java.util.*;
import java.io.*;

public class source {

    static TreeMap<String, SortedSet<String>> dict = new TreeMap<String, SortedSet<String>>();

    source() {
        if (dict.size() != 0) {
            dict.clear();
        }
        SortedSet<String> mn = new TreeSet<String>();
        mn.add("Meaning");
        dict.put("Slag", mn);
    }

    source(String _slag, SortedSet<String> _meaning) {
        if (dict.size() != 0) {
            dict.clear();
        }
        dict.put(_slag, _meaning);
    }

    private static void readFile(String filename) throws IOException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (IOException exc) {
            System.out.println("Error opening " + filename);
            return;
        }

        String line = "";
        String slitBy = "`";

        while ((line = br.readLine()) != null) {
            String[] str = line.split(slitBy);
            String slang = str[0];
            // System.out.println(slag);
            String meaning = str[1];
            // System.out.println(meaning);

            SortedSet<String> meanList = new TreeSet<String>();

            if (meaning.contains("| ") == true) {
                meaning = meaning.replace("| ", "@");
                String[] multiMeaning = meaning.split("@");
                for (int i = 0; i < multiMeaning.length; i++) {
                    meanList.add(multiMeaning[i]);
                }

            } else {
                meanList.add(meaning);
            }
            dict.put(slang, meanList);
        }

        // System.out.println("Treemap: " + dict);

        br.close();

    }

    private static void saveFile(String filename) throws IOException {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));

        } catch (IOException exc) {
            System.out.println("Error opening " + filename);
            return;
        }
        for (Map.Entry m : dict.entrySet()) {
            // String key = ;
            // bw.write(m.getKey());
            // bw.write("` ");

        }

        bw.flush();
        bw.close();
    }

    private static void search() {

    }

    private static void insert() {

    }

    public static void main(String[] args) throws IOException {
        readFile("slang.txt");

    }

}