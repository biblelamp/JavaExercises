package cz.parser;

/**
 * Item - the command-line element
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Apr 13, 2018
 */
public class Item<E> {
    private String name;
    private boolean mandatory;
    private String description;
    private String[] aliases;
    private E byDefault;
    private E value;

    public Item(String name, boolean mandatory, String description, String[] aliases) {
        this.name = name;
        this.mandatory = mandatory;
        this.description = description;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public E getValue() {
        return value;
    }
}