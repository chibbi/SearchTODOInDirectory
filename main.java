import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class main {

    public static void main(String[] args) {
        String[] Files = getFiles(args[0]);
        for (String one : Files) {
            String[] Lines = ReadFile(new File(one));
            int i = 0;
            for (String two : Lines) {
                if(two != "" && two != null && i != 0) {
                    if(two.contains("TODO:")) {
                        System.out.print("\u001B[31m");
                        System.out.println(two.strip());
                        System.out.print("\u001B[0m");
                    }
                } else if(i == 0) {
                    System.out.println(two);
                }
                i++;
            }
        }
    }

    public static String[] getFiles(String Path) {
        String[] pathnames = null;
        File f = new File(Path);
        pathnames = f.list();
        return pathnames;
    }

    public static String StringOutOfArray(String[] Array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Array.length; i++) {
            sb.append("[" + Array[i] + "] ");
        }
        String str = sb.toString();
        return str;
    }

    public static String[] ReadFile(File fi) {
        List<String> Lines = new ArrayList<String>();
        Scanner myReader;
        Lines.add("\u001B[35m" + fi.getName() + "\u001B[0m");
        try {
            myReader = new Scanner(fi);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Lines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] str = {"Some","Array"};
        return Lines.toArray(str);
    }

}
