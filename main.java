import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class main {

    public static void main(String[] args) {
        String[] Files = getAllFiles(new File(args[0]), "-");
        ParseFile(Files);
    }

    public static String[] getFiles(File f, String Recs) {
        String[] pathnames = null;
        pathnames = f.list();
        int i = 0;
        for (String two : pathnames) {
            pathnames[i] = f + "/" + two;
            i++;
        }
        return pathnames;
    }

    public static String[] getAllFiles(File f, String Recs) {
        String[] pathnames = null;
        pathnames = f.list();
        int i = 0;
        for (String two : pathnames) {
            File fi = new File(f + "/" + two);
            if (fi.isDirectory()) {
                pathnames[i] = StringOutOfArray(getAllFiles(fi, Recs + "-"));
            } else {
                pathnames[i] = f + "/" + two;
                // System.out.print(Recs);
                // System.out.println(f + "/" + two + "/");
            }
            i++;
        }
        return pathnames;
    }

    public static String StringOutOfArray(String[] fis) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fis.length; i++) {
            sb.append("[" + fis[i] + "] ");
        }
        String str = sb.toString();
        return str;
    }

    public static String[] ReadFile(File fi) {
        List<String> Lines = new ArrayList<String>();
        Scanner myReader;
        Lines.add(fi.getName());
        try {
            myReader = new Scanner(fi);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Lines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // System.err.println(e.getMessage());
        }
        String[] str = { "Some", "Array" };
        return Lines.toArray(str);
    }

    public static void ParseFile(String[] Files) {
        for (int i=0;i<Files.length;i++) {
            if (new File(Files[i]).isDirectory()) {
                //System.out.println("1");
                continue;
            }
            String[] Fis = Files[i].split(" ");
            if(Fis.length < 2) {
                Files[i] = Files[i].replace("[", "").replace("]", "");
                if (Files[i].contains("node_modules")) {
                    //System.out.println("2");
                    continue;
                } else {
                    String[] Lines = ReadFile(new File(Files[i]));
                    for (String two : Lines) {
                        if (two != "" && two != null) {
                            if (two.contains("TODO:")) {
                                System.out.print("\u001B[35m");
                                System.out.println(Files[i]);
                                System.out.print("\u001B[0m");
                                System.out.print("\u001B[31m");
                                System.out.println(two.strip());
                                System.out.print("\u001B[0m");
                            }
                        }
                    }
                }
            } else if (Files[i].contains("[") && Files[i].contains("]") && Fis.length >= 2) {
                //System.out.println("3");
                ParseFile(Fis);
            }
        }
}}
