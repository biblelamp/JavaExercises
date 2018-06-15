/**
 * Class MainApp
 * The main class providing a dialog with the user in console
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 06, 2017
 */
class MainApp {

    public static void main(String[] args) {
        Product product;

        // check numbers of arguments
        if (args.length < 3) {
            System.out.println(
                "Use: MainApp -[s|a|p] [apple|wine] <quantity>|<price>");
            return;
        }

        // define the product
        if (args[1].equals("apple") || args[1].equals("wine"))
            product = new Product(args[1]);
        else
            return;

        // get the value
        int value = Integer.parseInt(args[2]);

        // action according to choice
        switch (args[0]) {
            case "-s":
                product.setForSale(value);
                product.sell();
                break;
            case "-a":
                product.add(value);
                break;
            case "-p":
                product.setPrice(value);
        }
    }
}