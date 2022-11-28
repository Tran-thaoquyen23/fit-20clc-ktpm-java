import java.io.IOException;
import java.util.*;

import java.io.*;

public class Dictionary {
    public static Scanner scanner = new Scanner(System.in);

    private TreeMap<String, Set<String>> dict;
    TreeMap<String, Set<String>> searchHistory;
    static TreeMap<String, Set<String>> subTreeMap;

    static Scanner sc = new Scanner(System.in);

    Dictionary() throws IOException {
        dict = new TreeMap<String, Set<String>>();
        readFile("slangAfter.txt");
    }

    Dictionary(String _slag, Set<String> _meaning) {
        if (dict.size() != 0) {
            dict.clear();
        }
        dict.put(_slag, _meaning);
    }

    public void readFile(String filename) throws IOException {
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
            String meaning = str[1];

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
            if (key.toUpperCase().indexOf(keyword.toUpperCase()) == 0) {
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
                if (str.toUpperCase().contains(keyword.toUpperCase())) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println(key + dict.get(key));
                subTreeMap.put(key, dict.get(key));
            }
        }
        return subTreeMap;
    }

    public String checkExist(String slangWord) {
        for (String key : dict.keySet()) {
            if (key.toUpperCase().equals(slangWord.toUpperCase())) {
                return key;
            }
        }
        return null;
    }

    public boolean checkPairExit(String slangWord, Set<String> meaning) {
        if (checkExist(slangWord) != null && dict.get(slangWord).equals(meaning)) {
            return true;
        }
        return false;
    }

    public void add(String _keyInput, Set<String> _meaningInput) {
        Set<String> meaning = new HashSet<String>();
        meaning = _meaningInput;
        dict.put(_keyInput, meaning);
        try {
            saveFile("slangAfter.txt");
        } catch (IOException exc) {
            System.out.println("Error save file");
        }
    }

    public void add_overwrite(String _keyInput, Set<String> _meaningInput) {
        dict.replace(_keyInput, _meaningInput);
        try {
            saveFile("slangAfter.txt");
        } catch (IOException exc) {
            System.out.println("Error save file");
        }
    }

    public void add_duplicate(String _keyInput, String _meaningInput) {
        dict.get(_keyInput).add(_meaningInput);
        try {
            saveFile("slangAfter.txt");
        } catch (IOException exc) {
            System.out.println("Error save file slangAfter.txt");
        }
    }

    public boolean edit(String slangWord, Set<String> meaning) {

        add_overwrite(slangWord, meaning);

        for (String e : dict.keySet()) {
            if (e.toUpperCase().equals(slangWord.toUpperCase()) && dict.get(e) == meaning) {
                return true;
            }
        }
        return false;

    }

    public void delete(String slangWord) {
        dict.remove(slangWord);
        try {
            saveFile("slangAfter.txt");
        } catch (IOException exc) {
            System.out.println("Error save file slangAfter.txt");
        }
    }

    public void reset() {
        dict.clear();
        try {
            readFile("slang.txt");
        } catch (IOException exc) {
            System.out.println("Error read file slang.txt");
        }
        try {
            saveFile("slangAfter.txt");
        } catch (IOException exc) {
            System.out.println("Error write file slangAfter.txt");
        }
    }

    public TreeMap<String, Set<String>> random() {
        subTreeMap = new TreeMap<String, Set<String>>();
        Random r = new Random();

        int randomIndex = r.nextInt(dict.size());
        Set<String> keys = dict.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        String randomKey = keyList.get(randomIndex);
        Set<String> randomMeaning = dict.get(randomKey);

        subTreeMap.put(randomKey, randomMeaning);
        return subTreeMap;

    }

    private String randomKey() {
        Random r = new Random();

        int randomIndex = r.nextInt(dict.size());
        Set<String> keys = dict.keySet();
        List<String> keyList = new ArrayList<String>();
        keyList.addAll(keys);
        String randomKey = keyList.get(randomIndex);
        return randomKey;
    }

    private String randomMeaning() {
        String randomKey = randomKey();
        Set<String> meanings = dict.get(randomKey);
        List<String> meaningList = new ArrayList<>();
        meaningList.addAll(meanings);

        Random r = new Random();
        int randomIndex = r.nextInt(meaningList.size());

        String randMeaning = meaningList.get(randomIndex);
        return randMeaning;
    }

    private boolean checkOptionOfFunQuestion(List<String> option) {
        String a = option.get(0);
        String b = option.get(1);
        String c = option.get(2);
        String d = option.get(3);
        if (!a.equals(b) && (!a.equals(c)) && (!a.equals(d)) && (!b.equals(c)) && (!b.equals(d)) && (!c.equals(d))) {
            return true;
        }
        return false;
    }

    public String randomItemOfSet(Set<String> set) {
        List<String> list = new ArrayList<>();
        list.addAll(set);
        Random r = new Random();
        int randomNumber = r.nextInt(list.size());
        String result = list.get(randomNumber);
        return result;
    }

    public List<String> funQuestionSlangWord() {
        List<String> quiz = new ArrayList<>();

        String randomKey = randomKey();
        String result = randomItemOfSet(dict.get(randomKey));

        quiz.add(randomKey);

        Random r = new Random();
        int randomNumber;

        do {
            randomNumber = r.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == randomNumber) {
                    quiz.add(result);
                } else {
                    quiz.add(randomMeaning());
                }
            }
        } while (!checkOptionOfFunQuestion(quiz));

        quiz.add(result);
        System.out.println("trong dict " + quiz);
        return quiz;
    }

    public List<String> funQuestionDefinition() {
        List<String> quiz = new ArrayList<>();

        subTreeMap = new TreeMap<String, Set<String>>();
        subTreeMap = random();
        String result = subTreeMap.firstKey();
        Set<String> questionList = subTreeMap.get(result);

        int length = questionList.size();
        int count = 0;
        String randomQues = "";
        for (String item : questionList) {
            randomQues = randomQues.concat(item);
            count++;
            if (count < length) {
                randomQues = randomQues.concat(",  ");
            }
        }

        quiz.add(randomQues);
        Random r = new Random();
        int randomNumber;

        do {
            randomNumber = r.nextInt(4);
            for (int i = 0; i < 4; i++) {
                if (i == randomNumber) {
                    quiz.add(result);
                } else {
                    quiz.add(randomKey());
                }
            }
        } while (!checkOptionOfFunQuestion(quiz));
        quiz.add(result);

        return quiz;
    }

}