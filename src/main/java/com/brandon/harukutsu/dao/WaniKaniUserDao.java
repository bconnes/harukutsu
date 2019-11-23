package com.brandon.harukutsu.dao;

import com.brandon.harukutsu.model.WaniKaniUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/21/2019
 */
public interface WaniKaniUserDao
{
    Optional<WaniKaniUser> selectWaniKaniUser(UUID id);

    default int insertWaniKaniUser(WaniKaniUser user)
    {
        UUID id = UUID.randomUUID();
        return insertWaniKaniUser(id, user);
    }

    int insertWaniKaniUser(UUID id, WaniKaniUser user);

    List<WaniKaniUser> selectWaniKaniUsers();
}
