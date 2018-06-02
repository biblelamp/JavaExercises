/**
 * Test task Bookshelf from merck.com
 *
 * @author Sergey Iryupin
 * @version dated Jun 02, 2018
 */

import tools.Service;

public class BookShelf {

    public static void main(String[] args) {
        new Service().load(".\\..\\data.txt");
        System.exit(0);
    }
}