import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class scraper {
        public static Document getHTML (String url) throws IOException {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (" +
                    "Windows NT 10.0; Win64; x64) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/107.0.0.0 Safari/537.36").get();
            try {
                return document;
            } catch(Exception e) {
                System.out.println("Something went wrong...");
            }
            return document;
        }

        public static ArrayList<String> getHeaders(Document document){
            Elements elements = document.select("div.blog-header a h1, h2, h3, h4, h5, h6");

            ArrayList<String> blogHeadings = new ArrayList<>();

            for (Element element:elements) {
                blogHeadings.add(element.text());
            }
            return blogHeadings;
        }

        public static List<String> getLinks(Document document){
            Elements links = document.select("a");
            return links.eachAttr("abs:href");
        }



        public static void main(String[] args) throws IOException {
            Document scrape = getHTML("https://en.wikipedia.org/wiki/Prime_number");
            ArrayList<String> headings = getHeaders(scrape);
//            for (String s : headings) {
//                System.out.println(s);
//            }

            List<String> links = getLinks(scrape);
            for (String l: links) {
                System.out.println(l);
            }
        }


}
