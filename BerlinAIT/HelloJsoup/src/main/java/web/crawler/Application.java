package web.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("C:/temp/e-kladensko/06-10-23.html"));
        Elements news = doc.getElementsByClass("wrapperk2");
        for (Element element : news) {
            // find date
            Elements datehit = element.getElementsByClass("datehit");
            String dateText = datehit.text();
            List<String> items = List.of(dateText.split(" "));
            System.out.println(items.get(0));

            // find title
            Elements url = element.getElementsByClass("latestItemTitle");
            System.out.println(url.text());
            break;

            // find url
        }
    }
}
