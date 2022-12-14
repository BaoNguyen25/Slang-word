import java.io.FileNotFoundException;
import java.util.*;

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

    public String[][] searchBySlang(String s) {
        if(dict.containsKey(s)) {
            ArrayList<String> cur = dict.get(s);
            String[][] array = new String[cur.size()][3];
            for(int i = 0; i < cur.size(); i++) {
                array[i][0] = String.valueOf(i);
                array[i][1] = s;
                array[i][2] = cur.get(i);
            }
            return array;
        }
        return null;
    }

    public String[][] searchByDefinition(String s) {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        int idx = 0;
        for(Map.Entry<String, ArrayList<String>> entry : dict.entrySet()) {
            ArrayList<String> list = entry.getValue();
            for(String str : list)
                if(str.toLowerCase().startsWith(s.toLowerCase())) {
                    ArrayList<String> cur = new ArrayList<>();
                    cur.add(String.valueOf(idx++));
                    cur.add(entry.getKey());
                    cur.add(str);
                    res.add(cur);
                }
            }
            String[][] array = new String[res.size()][];
            for (int i = 0; i < res.size(); i++) {
                ArrayList<String> row = res.get(i);
                array[i] = row.toArray(new String[row.size()]);
            }
            return res.size() == 0 ? null : array;
    }

    public boolean checkSlangExists(String word) {
        return dict.containsKey(word);
    }

    public void addSlang(String word, String definition) {
        if(!dict.containsKey(word)) {
            dict.put(word, new ArrayList<>());
            dict.get(word).add(definition);
        }
        else {
            dict.get(word).add(definition);
        }
        hf.writeToFile(dict);
    }

    public void overwriteSlang(String word, String definition, String newDefinition) {
        ArrayList<String> meanings = dict.get(word);
        int idx = meanings.indexOf(definition);
        meanings.set(idx, newDefinition);
        dict.put(word, meanings);
        hf.writeToFile(dict);
    }

    public void editSlang(String word, String definition, String newWord) {
        if(word == newWord)
            return;

        if(dict.containsKey(newWord)) {
            ArrayList<String> meaningList = dict.get(newWord);
            if(!meaningList.contains(definition)) {
                meaningList.add(definition);
            }
        } else{
            ArrayList<String> meaningList = new ArrayList<>();
            meaningList.add(definition);
            dict.put(newWord, meaningList);
        }

        ArrayList<String> oldList = dict.get(word);
        if(oldList != null) {
            int index = oldList.indexOf(definition);
            if(oldList.size() == 1) {
                dict.remove(word);
            } else{
                oldList.remove(index);
                dict.put(word, oldList);
            }
        }
        hf.writeToFile(dict);
    }

    public void editDefinition(String word, String definition, String newDefinition) {
        if(definition == newDefinition)
            return;

        ArrayList<String> meaning = dict.get(word);
        if(meaning != null) {
            int index = meaning.indexOf(definition);
            if(index != -1)
                meaning.set(index, newDefinition);
        }
        hf.writeToFile(dict);
    }

    public void deleteSlang(String word, String definition) {
        ArrayList<String> meaningList = dict.get(word);
        int idx = meaningList.indexOf(definition);
        if(meaningList.size() == 1) {
            dict.remove(word);
        } else{
            meaningList.remove(idx);
            dict.put(word, meaningList);
        }
        hf.writeToFile(dict);
    }

    public void resetOriginalDict() throws FileNotFoundException {
        hf.cleanFile("slang.txt");
        dict = hf.readOriginFile();
        hf.writeToFile(dict);
    }

    public String[] randomSlang() {
        Object[] crunchifyKey = dict.keySet().toArray();
        Object key = crunchifyKey[new Random().nextInt(crunchifyKey.length)];
        String[] slang = new String[2];
        slang[0] = (String) key;
        slang[1] = String.valueOf(dict.get((String)key));
        slang[1] = slang[1].substring(1, slang[1].length()-1);
        return slang;
    }
}
