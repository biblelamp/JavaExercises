class ReplaceBadCharsInURL {
    public static void main(String[] args) {
        String s = new String("3\\07-91 - Kontroln akce 07-91 - Athna\\komponenty\\E28B91E4-00F0-48B1-976C-2B75AF6B056A\\3_El_dokum_legislativa[1].pdf");
        System.out.println(s);
        String s1 = s.replaceAll("\\[", "%5B");
        System.out.println(s1);
    }
}