import java.text.Collator;
import java.util.Locale;

class CollatorExercise {
    public static void main(String[] args) {

        // compare two strings in the default locale
        Collator myCollator = Collator.getInstance();
        if (myCollator.compare("abc", "ABC") < 0) {
            System.out.println("abc is less than ABC");
        } else {
            System.out.println("abc is greater than or equal to ABC");
        }

        System.out.println(myCollator.compare("321", "123"));

        Collator czCollator = Collator.getInstance(Locale.forLanguageTag("cs"));
        //czCollator.setStrength(Collator.PRIMARY);
        if (czCollator.compare("křižovatka", "krizovatka") == 0) {
            System.out.println("Strings are equivalent");
        }
    }
}