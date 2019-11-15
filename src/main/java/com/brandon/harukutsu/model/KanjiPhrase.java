package com.brandon.harukutsu.model;

/**
 * @author bconnes
 * @since 11/14/2019
 */
public class KanjiPhrase
{
    private String kanji;
    private String reading;

    public KanjiPhrase(String kanji, String reading)
    {

        this.kanji = kanji;
        this.reading = reading;
    }

    public String getKanji()
    {
        return kanji;
    }

    public String getReading()
    {
        return reading;
    }

    @Override
    public String toString()
    {
        return "KanjiPhrase{" +
            "kanji='" + kanji + '\'' +
            ", reading='" + reading + '\'' +
            '}';
    }
}
