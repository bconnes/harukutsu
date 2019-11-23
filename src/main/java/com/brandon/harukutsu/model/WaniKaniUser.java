package com.brandon.harukutsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/21/2019
 */

@Entity
@Data
@AllArgsConstructor
public class WaniKaniUser extends EntityWithUUID
{
    private final UUID id;
    private final String apiToken;
    private final String name;
}
