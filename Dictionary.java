import java.io.IOException;
import java.util.*;
import java.util.zip.CheckedOutputStream;
import java.io.*;
import java.lang.Math.*;

public class Dictionary {
    public static Scanner scanner = new Scanner(System.in);

    static TreeMap<String, Set<String>> dict;
    // static TreeMap<String, Set<String>> searchHistory;
    static TreeMap<String, Set<String>> subTreeMap;

    static Scanner sc = new Scanner(System.in);

    Dictionary() throws IOException {
        dict = new TreeMap<String, Set<String>>();
        readFile("slang.txt");

    }

    Dictionary(String _slag, Set<String> _meaning) {
        if (dict.size() != 0) {
            dict.clear();
        }
        dict.put(_slag, _meaning);
    }

    public static void readFile(String filename) throws IOException {
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

    private void saveFile(String filename) throws IOException {
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

    public TreeMap<String, Set<String>> search_BySlangWord(String keyword) {
        subTreeMap = new TreeMap<String, Set<String>>();
        for (String key : dict.keySet()) {
            // System.out.println("--------------");
            if (key.indexOf(keyword) == 0) {
                System.out.println(key + dict.get(key));
                // searchHistory.put(key, dict.get(key)); // add vao lich su tim kiem
                subTreeMap.put(key, dict.get(key));
            }
        }
        return subTreeMap;
    }

    public TreeMap<String, Set<String>> search_ByDefinition(String keyword) {
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
                // searchHistory.put(key, dict.get(key)); // add vao lich su tim kiem
                subTreeMap.put(key, dict.get(key));
            }
        }
        return subTreeMap;
    }

    public boolean checkExist(String slangWord) {
        System.out.println("\n\n\n");
        for (String key : dict.keySet()) {
            System.out.println(key + "\n");
            if (key.equals(slangWord)) {
                return true;
            }
        }
        return false;
    }

    public void add() throws IOException {
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

    public static void edit(String slangWord) {
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

    public void delete(String SlangWord) throws IOException {
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

    public static void reset() throws IOException {
        readFile("slang.txt");
    }

    public static TreeMap<String, Set<String>> random() {
        subTreeMap = new TreeMap<String, Set<String>>();
        Random r = new Random();

        int randomIndex = r.nextInt(dict.size());
        Set<String> keys = dict.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        String randomKey = keyList.get(randomIndex);
        Set<String> randomMeaning = dict.get(randomKey);

        subTreeMap.put(randomKey, randomMeaning);

        // System.out.println("random: " + randomKey);
        // for (String e : randomMeaning) {
        // System.out.print(e + "| ");
        // }

        return subTreeMap;

    }

    private static String randomKey() {
        Random r = new Random();

        int randomIndex = r.nextInt(dict.size());
        Set<String> keys = dict.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        String randomKey = keyList.get(randomIndex);
        return randomKey;
    }

    private static String randomMeaning() {
        String randomKey = randomKey();
        Set<String> meanings = dict.get(randomKey);
        List<String> meaningList = new ArrayList<>();
        meaningList.addAll(meanings);

        Random r = new Random();
        int randomIndex = r.nextInt(meaningList.size());

        String randMeaning = meaningList.get(randomIndex);
        return randMeaning;
    }

    private static boolean checkOptionOfFunQuestion(List<String> option) {
        String a = option.get(0);
        String b = option.get(1);
        String c = option.get(2);
        String d = option.get(3);
        if (!a.equals(b) && (!a.equals(c)) && (!a.equals(d)) && (!b.equals(c)) && (!b.equals(d)) && (!c.equals(d))) {
            return true;
        }
        return false;
    }

    public static String randomItemOfSet(Set<String> set) {
        List<String> list = new ArrayList<>();
        list.addAll(set);
        Random r = new Random();
        int randomNumber = r.nextInt(list.size());
        String result = list.get(randomNumber);
        return result;
    }

    public static void funQuestionSlangWord() {
        subTreeMap = new TreeMap<String, Set<String>>();
        subTreeMap = random();
        String randomQues = subTreeMap.firstKey();
        Set<String> randomResult = subTreeMap.get(randomQues);
        String result = randomItemOfSet(randomResult);

        List<String> options = new ArrayList<>();
        Random r = new Random();
        int randomNumber;

        do {
            randomNumber = r.nextInt(4);
            System.out.println("-------" + randomNumber + "------");
            for (int i = 0; i < 4; i++) {
                if (i == randomNumber) {
                    options.add(result);
                } else {
                    options.add(randomMeaning());
                }
            }
        } while (!checkOptionOfFunQuestion(options));

        System.out.println("\nFunQues_slangWord: " + randomQues + "\n");
        for (String e : options) {
            System.out.print(e + "\t");
        }

        int choice;
        choice = scanner.nextInt();

        if (choice == randomNumber) {
            System.out.println("Bingo");
        } else {
            System.out.print("Wrong");
        }

    }

    public void funQuestionDefinition() {
        subTreeMap = new TreeMap<String, Set<String>>();
        subTreeMap = random();
        String result = subTreeMap.firstKey();
        Set<String> randomQues = subTreeMap.get(result);

        List<String> options = new ArrayList<>();

        Random r = new Random();
        int randomNumber;

        do {
            randomNumber = r.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == randomNumber) {
                    options.add(result);
                } else {
                    options.add(randomKey());
                }
            }
        } while (!checkOptionOfFunQuestion(options));

        System.out.println("Fun question _ definition: " + randomQues);
        for (String e : options) {
            System.out.print(e + "\t");
        }

        int choice = sc.nextInt();

        if (choice == randomNumber) {
            System.out.println("Bingo");
        } else {
            System.out.println("Wrong");
        }
    }

    // public static void main(String[] args) throws IOException {
    // readFile("slang.txt");

    // saveFile("output_test.txt");

    // search_BySlangWord("HOO");

    // System.out.print("\n\n");

    // search_ByDefinition("Leave");

    // // add();

    // // add();
    // // if (checkExist("THAOQUYEN")) {
    // // System.out.println("Co ton tai. Add thanh cong!");
    // // } else {
    // // System.out.println("Khong ton tai. Add khong thanh cong");
    // // }

    // // delete("THAOQUYEN");
    // // if (checkExist("THAOQUYEN")) {
    // // System.out.println("Co ton tai. delete ko thanh cong!");
    // // } else {
    // // System.out.println("Khong ton tai. delete thanh cong");
    // // }

    // // random();
    // funQuestionSlangWord();
    // funQuestionDefinition();

    // }
    public static void main() {

    }
}