import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ListFileDir {
    public static void main(String[] args) throws IOException {
        List<Path> files;
        try (Stream<Path> stream = Files.list(Paths.get("."))) {
            files = stream.filter(f -> Files.isRegularFile(f)).collect(Collectors.toList());
        }
        System.out.println(files);
    }
}