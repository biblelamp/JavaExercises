package cz.parser;

/**
 * Item - the command-line element
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Apr 10, 2018
 */
public class Item {
    private String name;
    private boolean mandatory;
    private String description;
    private String[] aliases;

    public Item() {
        //
    }
}