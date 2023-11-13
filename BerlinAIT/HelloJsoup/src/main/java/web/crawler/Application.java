package web.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
 * Parsing HTML in Java with Jsoup https://www.baeldung.com/java-with-jsoup
 */
public class Application {

    private static final String URL_CLASS = "Link-module-root"; // Link-module-isInBlockTitle";
    private static final String TITLE_CLASS = "BlockTitle-module-first";
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("C:/temp/meduza.io/index-10-11-2023.html"));
        Elements news = doc.getElementsByClass(URL_CLASS);
        for (Element element : news) {
            // find url
            String url = element.select("a").attr("href");
            System.out.println(url);

            // find title
            Elements title = element.getElementsByClass(TITLE_CLASS);
            String dateText = title.text();
            System.out.println(dateText);
        }
    }
}
