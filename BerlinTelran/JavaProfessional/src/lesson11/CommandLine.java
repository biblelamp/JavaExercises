package lesson11;

import java.util.Scanner;

public class CommandLine {
    public void exec() {
        Database db = new Database(DbInit.init());
        Scanner scanner = new Scanner(System.in);
        System.out.println("CRUD application v11");
        while (true) {
            System.out.print("# ");
            String cmd = scanner.next();
            switch (cmd.toLowerCase().charAt(0)) {
                case 'c':
                    db.create();
                    break;
                case 'r':
                    db.read();
                    break;
                case 'u':
                    db.update();
                    break;
                case 'd':
                    db.delete();
                    break;
                case 'f':
                    db.find();
                    break;
                case 'x':
                    System.out.println("Exit of command line.");
                    return;
                default:
                    System.out.println("List of command: c[reate], r[ead], u[pdate], d[elete], e[x]it");
            }
        }
    }
}
