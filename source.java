import java.util.*;

public class source {

    TreeMap<String, SortedSet<String>> dict = new TreeMap<String, SortedSet<String>>();

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

}