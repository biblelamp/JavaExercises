class FileNameDecode {

    // after (in Angular):
    //  btoa(encodeURI(file.name))

    public static void main(String[] args) throws java.io.UnsupportedEncodingException {

        String encodeURI = "Ahoj%20jak%20se%20m%C3%A1%C5%A1";
        String btoa = "QWhvaiUyMGphayUyMHNlJTIwbSVDMyVBMSVDNSVBMQ==";

        System.out.println(java.net.URLDecoder.decode(encodeURI, "UTF-8"));
        System.out.println(
            java.net.URLDecoder.decode(
                new String(java.util.Base64.getDecoder().decode(btoa)), "UTF-8"));
    }
}
