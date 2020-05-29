import java.util.*;
import java.util.stream.*;

class LambdaExample {

    public static void main(String[] args) {
        List<Artist> allArtists = Arrays.asList(
            new Artist("Leonid Sobinov", "Moscow"),
            new Artist("John Lennon", "London")
        );
        System.out.println(allArtists);

        List<String> london = 
            allArtists.stream()
                .filter(artist -> artist.isFrom("London"))
                .map(artist -> artist.getName())
                .collect(Collectors.toList());
        System.out.println(london);

        List<String> collected = 
            Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(collected);
    }

    static class Artist {
        String name;
        String city;

        Artist(String name, String city) {
            this.name = name;
            this.city = city;
        }

        boolean isFrom(String city) {
            return this.city.equals(city);
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("{name: %s, city: %s}", name, city);
        }
    }
}