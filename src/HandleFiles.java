import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HandleFiles {

    final String WORKING_SLANG_PATH = "slang.txt";
    final String ORIGIN_SLANG_PATH = "origin-slang.txt";

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
            System.out.println(e);
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
                list.put(slang[0], meanings);
            }
            fr.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanFile() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("slang.txt");
        pw.close();
    }
}
