package com.brandon.harukutsu;



import com.brandon.harukutsu.model.Article;
import com.brandon.harukutsu.model.KanjiPhrase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/13/2019
 */

public class Kijiyomi
{
    private static final Logger log = LoggerFactory.getLogger(Kijiyomi.class);

    public static final String ARTICLE = "article";
    public static final String PARAGRAPHS = "paragraphs";
    public static final String TIME = "time";
    public static final String HEADLINE = "headline";
    public static final String URL = "url";

    public static final String BODY_TAG = "body";
    public static final String RUBY_TAG = "ruby";
    public static final String RT_TAG = "rt";
    public static final String HTML_TAG = "html";

    public static final String NHK_EASY_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss xxxx '(JST)'";
    public static final String FILE_TO_PARSE = "run_results.json";


    public static void main(String[] args) throws Exception
    {
        Kijiyomi kj = new Kijiyomi();
        File file = kj.getFileFromResources(FILE_TO_PARSE);

        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;

        kj.readArticles(jo);
    }

    private List<Article>  readArticles(JSONObject jo)
    {
        JSONArray articles = (JSONArray) jo.get(ARTICLE);

        List<Article> articleList = new ArrayList<>();
        for ( Object articleJson : articles)
        {
            Map articleMap = ((Map) articleJson);
            articleList.add(extractArticle(articleMap));
        }

        return articleList;
    }

    private Article extractArticle(Map articleMap)
    {
        log.debug("Extracting article: {}", articleMap);

        LocalDateTime time = null;
        List<KanjiPhrase> articleKanjiPhrases = null;
        List<KanjiPhrase> headlineKanjiPhrases = null;
        String paragraphText = null;
        String headlineText = null;
        String url = null;

        for (Map.Entry pair : (Iterable<Map.Entry>) (articleMap.entrySet())) // todo ew
        {
            String key = (String) pair.getKey();
            switch((String) pair.getKey())
            {
                case PARAGRAPHS:
                    Document doc = getDocumentFromHtml((JSONArray) pair.getValue());

                    articleKanjiPhrases = extractKanjiPhrases(doc);
                    paragraphText = extractDocumentText(doc);
                    break;
                case TIME:
                    String timeText = (String) pair.getValue();
                    time = LocalDateTime.from(DateTimeFormatter.ofPattern(NHK_EASY_TIME_FORMAT).parse(timeText));
                    break;
                case HEADLINE:
                    doc = getDocumentFromHtml((String) pair.getValue());

                    headlineKanjiPhrases = extractKanjiPhrases(doc);
                    headlineText = extractDocumentText(doc);
                    break;
                case URL:
                    url = (String) pair.getValue();
                    break;
                default:
                    log.debug("Unhandled key: {}", key);
            }
        }

        Article article = new Article(
            UUID.randomUUID(),
            time,
            url,
            headlineText,
            paragraphText,
            articleKanjiPhrases,
            headlineKanjiPhrases);

        log.info("New article created: {}", article);
        return article;
    }

    private Document getDocumentFromHtml(String htmlString)
    {
        return Jsoup.parse(htmlString);
    }

    private Document getDocumentFromHtml(JSONArray htmlJsonArray)
    {
        String html = extractHtml(htmlJsonArray);
        return getDocumentFromHtml(html);
    }

    private String extractDocumentText(Document doc)
    {
        StringBuilder sb = new StringBuilder();

        doc.select(RUBY_TAG).unwrap();
        Elements paragraphs = doc.select(BODY_TAG);
        for(Element paragraphText : paragraphs)
        {
            sb.append(paragraphText.ownText());
        }

        return sb.toString();
    }

    private List<KanjiPhrase> extractKanjiPhrases(Document doc)
    {
        List<KanjiPhrase> phrases = new ArrayList<>();

        Elements pairings = doc.select(RUBY_TAG);
        for(Element kanjiPairing : pairings)
        {
            String kanji = kanjiPairing.textNodes().get(0).getWholeText();
            String reading = kanjiPairing.select(RT_TAG).text();

            KanjiPhrase kanjiPhrase = new KanjiPhrase(kanji, reading);
            phrases.add(kanjiPhrase);
        }

        return phrases;
    }

    private String extractHtml(JSONArray pHtmlJsonArray)
    {
        StringBuilder paragraph = new StringBuilder();
        for (Object p : pHtmlJsonArray)
        {
            Object html = ((JSONObject) p).get(HTML_TAG);
            paragraph.append(html);
        }

        return paragraph.toString();
    }

    private File getFileFromResources(String fileName)
    {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null)
        {
            throw new IllegalArgumentException("file is not found!");
        } else
        {
            return new File(resource.getFile());
        }

    }
}
