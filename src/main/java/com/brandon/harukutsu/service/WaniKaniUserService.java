package com.brandon.harukutsu.service;

import com.brandon.harukutsu.dao.WaniKaniUserDao;
import com.brandon.harukutsu.model.WaniKaniUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/21/2019
 */
@Service
public class WaniKaniUserService
{
    private final WaniKaniUserDao waniKaniUserDao;

    @Autowired
    public WaniKaniUserService(@Qualifier("waniKaniUser") WaniKaniUserDao waniKaniUserDao)
    {
        this.waniKaniUserDao = waniKaniUserDao;
    }

    public int addWaniKaniUser(WaniKaniUser waniKaniUser)
    {
        return waniKaniUserDao.insertWaniKaniUser(waniKaniUser);
    }

    public Optional<WaniKaniUser> getWaniKaniUserById(UUID id)
    {
        return waniKaniUserDao.selectWaniKaniUser(id);
    }

    public List<WaniKaniUser> getWaniKaniUsers()
    {
        return waniKaniUserDao.selectWaniKaniUsers();
    }
}
