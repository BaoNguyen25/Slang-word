import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SlangWordList {
    private TreeMap<String, ArrayList<String>> dict;
    private HandleFiles hf = new HandleFiles();

    public SlangWordList() {
        dict = hf.readFile();
    }

    public void showList() {
        for(Map.Entry<String, ArrayList<String>> entry : dict.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public ArrayList<String> searchBySlang(String s) {
        if(dict.containsKey(s)) {
            System.out.println(dict.get(s));
        }
        return null;
    }

    public void searchByDefinition(String s) {
        for(Map.Entry<String, ArrayList<String>> entry : dict.entrySet()) {
            ArrayList<String> list = entry.getValue();
            for(String str : list ) {
                str = str.toLowerCase();
                if(str.contains(s.toLowerCase())) {
                    System.out.println(entry);
                }
            }
        }
    }
    public void addSlang(String word, String definition, boolean isDuplicate) {
        if(!dict.containsKey(word)) {
            dict.put(word, new ArrayList<>());
            dict.get(word).add(definition);
        }
        else {
            if(isDuplicate) {
                dict.get(word).add(definition);
            }
            else {
                dict.remove(word);
                dict.put(word, new ArrayList<>());
                dict.get(word).add(definition);
            }
        }
    }

    public void editSlang(String word, String definition) {
    }

    public void deleteSlang(String word) {
        dict.remove(word);
    }

}
