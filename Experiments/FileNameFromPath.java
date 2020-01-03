class FileNameFromPath {
    public static void main(String[] args) {
        String path = "2\\dokument_digi_vlastni\\komponenty\\priloha.pdf";
        String name = path.substring(path.lastIndexOf('\\') + 1);
        System.out.println(name);
    }
}