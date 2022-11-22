import java.io.IOException;
import java.util.*;
import java.io.*;

public class Dictionary {

    static TreeMap<String, Set<String>> dict = new TreeMap<String, Set<String>>();

    static TreeMap<String, Set<String>> searchHistory = new TreeMap<String, Set<String>>();

    static TreeMap<String, Set<String>> subTreeMap = new TreeMap<String, Set<String>>();

    static Scanner sc = new Scanner(System.in);

    Dictionary() {
        if (dict.size() != 0) {
            dict.clear();
        }
        searchHistory.clear();
        Set<String> mn = new HashSet<String>();
        mn.add("Meaning");
        dict.put("Slag", mn);
    }

    Dictionary(String _slag, Set<String> _meaning) {
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

            Set<String> meanList = new HashSet<String>();

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
            Set<String> meaning = dict.get(key);
            List<String> listMeaning = new ArrayList<String>();
            listMeaning.addAll(meaning);
            bw.write(listMeaning.get(0));
            if (meaning.size() > 1) {
                for (int i = 1; i < meaning.size(); i++) {
                    bw.write("| ");
                    bw.write(listMeaning.get(i));
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static TreeMap<String, Set<String>> search_BySlangWord(String keyword) {
        subTreeMap = new TreeMap<String, Set<String>>();
        for (String key : dict.keySet()) {
            // System.out.println("--------------");
            if (key.contains(keyword)) {
                System.out.println(key + dict.get(key));
                searchHistory.put(key, dict.get(key)); // add vao lich su tim kiem
                subTreeMap.put(key, dict.get(key));
            }
        }
        return subTreeMap;
    }

    private static TreeMap<String, Set<String>> search_ByDefinition(String keyword) {
        subTreeMap = new TreeMap<String, Set<String>>();
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
                subTreeMap.put(key, dict.get(key));
            }
        }
        return subTreeMap;
    }

    static public boolean checkExist(String slangWord) {
        System.out.println("\n\n\n");
        for (String key : dict.keySet()) {
            System.out.println(key + "\n");
            if (key.equals(slangWord)) {
                return true;
            }
        }
        return false;
    }

    private static void add() throws IOException {
        String _keyInput, _meaningInput;
        System.out.print("Enter slang word: ");
        _keyInput = sc.nextLine();
        System.out.print("Enter difinition: ");
        _meaningInput = sc.nextLine();
        Set<String> meaning = new HashSet<String>();

        boolean exist = false;
        int choice;
        for (String key : dict.keySet()) {
            if (key == _meaningInput) {
                exist = true;
                System.out.print("Slang word already exists!\t1. Overwrite\t2. Duplicate\n");
                choice = sc.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Invalid value");
                }
                if (choice == 1) {
                    meaning.add(_meaningInput);
                    dict.put(_keyInput, meaning);
                } else {
                    dict.get(key).add(_meaningInput);
                }
            }

        }
        if (exist == false) {
            meaning.add(_meaningInput);
            dict.put(_keyInput, meaning);
        }
        saveFile("slangAfter.txt");
    }

    private static void edit(String slangWord) {
        String _keyInput, _meaningInput;
        System.out.println("Enter new slang word: ");
        _keyInput = sc.nextLine();
        System.out.println("Enter new definition: ");
        _meaningInput = sc.nextLine();
        for (String key : dict.keySet()) {
            if (key.equals(slangWord)) {

            }
        }

    }

    private static void delete(String SlangWord) throws IOException {
        if (checkExist(SlangWord)) {
            int choice = 0;
            System.out.println("Remove " + SlangWord + dict.get(SlangWord) + "?\t0. No\t1. Yes");
            choice = sc.nextInt();
            if (choice == 1) {
                dict.remove(SlangWord);
            }
        }
        saveFile("SlangAfterRemove.txt");
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
        readFile("slang_test.txt");

        saveFile("output_test.txt");

        search_BySlangWord("HOO");

        System.out.print("\n\n");

        search_ByDefinition("Leave");

        // add();

        add();
        if (checkExist("THAOQUYEN")) {
            System.out.println("Co ton tai. Add thanh cong!");
        } else {
            System.out.println("Khong ton tai. Add khong thanh cong");
        }

        delete("POST");
        if (checkExist("POST")) {
            System.out.println("Co ton tai. delete ko thanh cong!");
        } else {
            System.out.println("Khong ton tai. delete thanh cong");
        }

    }
}