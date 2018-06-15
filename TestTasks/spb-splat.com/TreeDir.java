/* Recursive crawl of folders
 */
import java.io.*;

class TreeDir {

    public static void main(String[] args) {
        try {
            showDirectoryes(new File("e:\\"));
            //showDirectoryes(new File("e:\\Java\\JavaExercises"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    static void showDirectoryes(File f) throws Exception  {

        if (f.isDirectory()) { 
            System.out.println(f.getCanonicalFile());
            File[] child = f.listFiles();
            if (child != null)
                for (int i = 0; i < child.length; i++)
                    showDirectoryes(child[i]);
        }
    }
}