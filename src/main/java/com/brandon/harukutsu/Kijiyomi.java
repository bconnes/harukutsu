package com.brandon.harukutsu;



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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author bconnes
 * @since 11/13/2019
 */

public class Kijiyomi
{
    private static final Logger log = LoggerFactory.getLogger(Kijiyomi.class);

    public static final String ARTICLE = "article";
    public static final String PARAGRAPHS = "paragraphs";
    public static final String RUBY = "ruby";
    public static final String RT = "rt";
    public static final String HTML = "html";

    public static void main(String[] args) throws Exception
    {
        Kijiyomi kj = new Kijiyomi();
        File file = kj.getFileFromResources("run_results.json");

        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jo = (JSONObject) obj;

        kj.readArticles(jo);
    }

    private void readArticles(JSONObject jo)
    {
        JSONArray articles = (JSONArray) jo.get(ARTICLE);
        Iterator articleIter = articles.iterator();

        while(articleIter.hasNext())
        {
            Iterator<Map.Entry> fieldIterator = ((Map) articleIter.next()).entrySet().iterator(); // todo ew
            while (fieldIterator.hasNext())
            {
                Map.Entry pair = fieldIterator.next();
                if ((pair.getKey()).equals(PARAGRAPHS))
                {
                    String paragraphs = extractParagraphs((JSONArray) pair.getValue());
                    parseParagraphs(paragraphs);
                }
            }
        }
    }

    private void parseParagraphs(String paragraphs)
    {
        log.info("Starting new paragraph.");
        Document doc = Jsoup.parse(paragraphs);
        List<KanjiPhrase> kanjiPhrases = extractKanjiPhrases(doc);

        log.info("Phrases: {}", kanjiPhrases);
    }

    private List<KanjiPhrase> extractKanjiPhrases(Document doc)
    {
        List<KanjiPhrase> phrases = new ArrayList<>();

        Elements pairings = doc.select(RUBY);
        for(Element kanjiPairing : pairings)
        {
            String kanji = kanjiPairing.textNodes().get(0).getWholeText();
            String reading = kanjiPairing.select(RT).text();

            KanjiPhrase kanjiPhrase = new KanjiPhrase(kanji, reading);
            phrases.add(kanjiPhrase);
        }

        return phrases;
    }

    private String extractParagraphs(JSONArray paragraphs)
    {
        StringBuilder paragraph = new StringBuilder();
        for (Object paragraph1 : paragraphs)
        {
            Object html = ((JSONObject) paragraph1).get(HTML);
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
