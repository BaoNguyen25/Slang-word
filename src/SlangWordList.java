import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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

    public String[][] getList() {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        int idx = 0;

        for(Map.Entry<String, ArrayList<String>> entry : dict.entrySet()) {
            ArrayList<String> values = entry.getValue();
            for(int i = 0; i < values.size(); i++) {
                ArrayList<String> cur = new ArrayList<>();
                cur.add(String.valueOf(idx++));
                cur.add(entry.getKey());
                cur.add(values.get(i));
                list.add(cur);
            }

        }
        String[][] array = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> row = list.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        return array;
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

    public void resetOriginalDict() throws FileNotFoundException {
        hf.cleanFile();
        dict = hf.readOriginFile();
        hf.writeToFile(dict);
    }

    public void randomSlang() {
        Object[] crunchifyKey = dict.keySet().toArray();
        Object key = crunchifyKey[new Random().nextInt(crunchifyKey.length)];
        System.out.println(key + " :: " + dict.get(key));
    }
}
