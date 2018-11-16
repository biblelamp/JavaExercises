import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

class FileSZSplitter {
    static final String PATH = "/home/lamp/Downloads/";
    static final String FILE_NAME = "/home/lamp/Downloads/Slova_zivota_2018_11-12.txt";
    static final String[] DAYS = {"pondělí", "úterý", "středa", "čtvrtek", "pátek", "sobota", "neděle"};
    static final String[] MONTHS = {"ledna", "února", "března", "dubna", "května", "června",
        "července", "srpna", "září", "října", "listopadu", "prosince"};

    public static void main(String[] args) throws IOException {
        int numMonth = -1, numDay = -1;
        StringBuffer sb = new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);

        for(String line: lines) {
            for (String day : DAYS) {
                if (line.startsWith(day)) {
                    System.out.println(line);
                    for (String month : MONTHS) {
                        if (line.indexOf(month) > -1) {
                            String[] fields = line.split(" ");
                            //System.out.println(Arrays.toString(fields));
                            if (numMonth > -1) {
                                FileWriter fw = new FileWriter(PATH + Integer.toString(numMonth) + "/" + Integer.toString(numDay) + ".txt");
                                sb.delete(0, sb.indexOf("\n\n") + 2);
                                fw.write(sb.toString());
                                fw.close();
                                sb.setLength(0);
                            }
                            numDay = (int) Float.parseFloat(fields[2]);
                            numMonth = Arrays.asList(MONTHS).indexOf(month);
                            File folder = new File(PATH + Integer.toString(numMonth));
                            folder.mkdirs();
                        }
                    }
                }
            }
            sb.append(line + "\n");
        }
    }
}
