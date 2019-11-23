package com.brandon.harukutsu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author bconnes
 * @since 11/14/2019
 */
@Entity
@Data
@AllArgsConstructor
public class KanjiPhrase extends EntityWithUUID
{
    private String kanji;
    private String reading;

    @Override
    public String toString()
    {
        return "KanjiPhrase{" +
            "kanji='" + kanji + '\'' +
            ", reading='" + reading + '\'' +
            '}';
    }
}
