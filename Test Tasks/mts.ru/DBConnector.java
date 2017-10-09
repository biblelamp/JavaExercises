/**
 * Class DBConnector
 * The class class provides working with the database
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Oct 09, 2017
 */
class DBConnector {

    public static int getPrice(String name) { // get price by name
        return DBase.getIntField(name, "price");
    }

    public static void sell(String name, int quantity) { // sell by name
        Integer price = getPrice(name);
        Integer balance = DBase.getIntField(name, "amount");
        Integer sales = DBase.getIntField(name, "sales");
        Integer batch = DBase.getIntField(name, "batch");
        Integer change = DBase.getIntField(name, "change");
        if (balance != null && balance >= quantity) {
            DBase.setIntField(name, "amount", balance - quantity);
            DBase.setIntField(name, "sales", sales + quantity);
            DBase.writeLog(
                name, "sell " + Integer.toString(quantity), "success");
            // change price if it need
            int rest = sales % batch; // get the rest of saled batches
            if ((rest + quantity) / batch > 0)
                setPrice(name, price + (rest + quantity) / batch * change);
        } else
            DBase.writeLog(
                name, "sell " + quantity, "failed");
    }

    public static void add(String name, int quantity) { // add quantity by name
        int amount = DBase.getIntField(name, "amount");
        DBase.setIntField(name, "amount", amount + quantity);
        DBase.writeLog(
            name, "add " + quantity, "success");
    }

    public static void setPrice(String name, int price) { // set price by name
        DBase.setIntField(name, "price", price);
        DBase.writeLog(
            name, "set price " + price, "success");
    }
}