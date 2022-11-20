import java.io.IOException;
import java.util.*;
import java.io.*;

public class source {

    static TreeMap<String, List<String>> dict = new TreeMap<String, List<String>>();

    static HashMap<String, List<String>> searchHistory = new HashMap<String, List<String>>();

    static Scanner sc = new Scanner(System.in);

    source() {
        if (dict.size() != 0) {
            dict.clear();
        }
        searchHistory.clear();
        List<String> mn = new ArrayList<String>();
        mn.add("Meaning");
        dict.put("Slag", mn);
    }

    source(String _slag, List<String> _meaning) {
        if (dict.size() != 0) {
            dict.clear();
        }
        searchHistory.clear();
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
            // System.out.println(slang);
            String meaning = str[1];
            // System.out.println(meaning);

            List<String> meanList = new ArrayList<String>();

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
        for (String key : dict.keySet()) {
            bw.write(key);
            bw.write("`");
            List<String> meaning = dict.get(key);
            bw.write(meaning.get(0));
            if (meaning.size() > 1) {
                for (int i = 1; i < meaning.size(); i++) {
                    bw.write("| ");
                    bw.write(meaning.get(i));
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static void search_BySlangWord(String keyword) {
        for (String key : dict.keySet()) {
            // System.out.println("--------------");
            if (key.contains(keyword)) {
                System.out.println(key + dict.get(key));
                searchHistory.put(key, dict.get(key)); // add vao lich su tim kiem
            }
        }
    }

    private static void search_ByDefinition(String keyword) {
        for (String key : dict.keySet()) {
            boolean flag = false;
            for (String str : dict.get(key)) {
                if (str.contains(keyword)) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println(key + dict.get(key));
                searchHistory.put(key, dict.get(key)); // add vao lich su tim kiem
            }
        }
    }

    static private boolean checkExist(String slangWord) {
        for (String key : dict.keySet()) {
            if (key == slangWord) {
                return true;
            }
        }
        return false;
    }

    private static void edit() {

    }

    private static void delete() {

    }

    private static void reset() throws IOException {
        readFile("slang.txt");
    }

    private static void random() {

    }

    private static void funQuestionSlangWord() {

    }

    private static void funQuestionDefinition() {

    }

    public static void main(String[] args) throws IOException {
        readFile("slang.txt");

        saveFile("output.txt");

        search_BySlangWord("HOO");

        System.out.print("\n\n");

        search_ByDefinition("Leave");

        // add();

    }

}