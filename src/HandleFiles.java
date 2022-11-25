import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class HandleFiles {

    private final String WORKING_SLANG_PATH = "slang.txt";
    private final String ORIGIN_SLANG_PATH = "origin-slang.txt";
    private final String HISTORY_PATH = "history.txt";
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date date;

    public HandleFiles() {
    }

    public TreeMap<String, ArrayList<String>> readFile() {
        TreeMap<String, ArrayList<String>> list = new TreeMap<>();
        try {
            FileReader fr = new FileReader(WORKING_SLANG_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
               ArrayList<String> meanings = new ArrayList<>();
                String[] slang = line.split("`");
                if(slang.length > 1) {
                    String[] definitions = slang[1].split("\\|");
                    for(int i = 0; i < definitions.length; i++) {
                        definitions[i] = definitions[i].trim();
                    }
                    for(String s : definitions) {
                        meanings.add(s);
                    }
                }
                slang[0] = slang[0].trim();
                list.put(slang[0], meanings);
            }
            fr.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void writeToFile(TreeMap<String, ArrayList<String>> dict) {
        try {
            FileWriter fw = new FileWriter("slang.txt");
            for(Map.Entry<String, ArrayList<String>> entry : dict.entrySet()) {
                fw.write(entry.getKey() + "`");
                for(int i = 0; i < entry.getValue().size(); i++) {
                    fw.write(entry.getValue().get(i));
                    if(i != entry.getValue().size()-1)
                        fw.write("|");
                }
                fw.write("\n");
            }
            fw.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public TreeMap<String, ArrayList<String>> readOriginFile() {
        TreeMap<String, ArrayList<String>> list = new TreeMap<>();
        try {
            FileReader fr = new FileReader(ORIGIN_SLANG_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> meanings = new ArrayList<>();
                String[] slang = line.split("`");
                if(slang.length > 1) {
                    String[] definitions = slang[1].split("\\|");
                    for(int i = 0; i < definitions.length; i++) {
                        definitions[i] = definitions[i].trim();
                    }
                    for(String s : definitions) {
                        meanings.add(s);
                    }
                }
                slang[0] = slang[0].trim();
                list.put(slang[0], meanings);
            }
            fr.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveHistory(String slag, String meaning){
        try {
            File f = new File(HISTORY_PATH);
            FileWriter fr = new FileWriter(f, true);
            date = new Date();
            fr.write(slag + "`" + meaning + "`" + formatter.format(date) + "\n");
            fr.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[][] readHistory() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        try {
            FileReader fr = new FileReader(HISTORY_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> list = new ArrayList<>();
                String[] slang = line.split("`");
                list.add(slang[0]);
                list.add(slang[1]);
                list.add(slang[2]);
                res.add(list);
            }
            fr.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        String[][] array = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ArrayList<String> row = res.get(i);
            array[i] = row.toArray(new String[row.size()]);
        }
        return array;
    }

    public void cleanFile(String filename) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(filename);
        pw.close();
    }
}
