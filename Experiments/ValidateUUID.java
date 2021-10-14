import java.util.regex.Pattern;

class ValidateUUID {
    static Pattern UUID_REGEX_PATTERN =
        Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

    static boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }

    public static void main(String[] args) {
        System.out.println(isValidUUID(null));
        System.out.println(isValidUUID("009692ee-f930-4a74-bbd0-63b8baa5a927"));
        System.out.println(isValidUUID("test-ss-ss-ss-s"));
        System.out.println(isValidUUID("1-1-1-1"));
    }
}
