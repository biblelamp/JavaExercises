import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearchInTree {

    private String fileNameToSearch;
    private List<String> result = new ArrayList<String>();

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }

    public void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    public List<String> getResult() {
        return result;
    }

    public static void main(String[] args) {

        FileSearch fileSearch = new FileSearch();
        fileSearch.searchDirectory(new File("C:/Users/lamp/eclipse-workspace/Trombon-core/src/main/java"), "java");

        int count = fileSearch.getResult().size();
        if (count == 0) {
            System.out.println("\nNo result found!");
        } else {
            System.out.println("\nFound " + count + " result!\n");
            for (String matched : fileSearch.getResult()) {
                System.out.println("Found : " + matched);
            }
        }
    }

    public void searchDirectory(File directory, String fileNameToSearch) {

        setFileNameToSearch(fileNameToSearch);

        if (directory.isDirectory()) {
            search(directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(File file) {

        if (file.isDirectory()) {
            System.out.println("Searching directory ... " + file.getAbsoluteFile());

            if (file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                        search(temp);
                    } else {
                        if (temp.getName().toLowerCase().endsWith(".java")) {
                            result.add(temp.getAbsoluteFile().toString());
                        }
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }
    }
}