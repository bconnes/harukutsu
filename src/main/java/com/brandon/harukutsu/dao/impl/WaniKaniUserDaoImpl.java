package com.brandon.harukutsu.dao.impl;

import com.brandon.harukutsu.dao.WaniKaniUserDao;
import com.brandon.harukutsu.model.WaniKaniUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/21/2019
 */
@Repository("waniKaniUser")
public class WaniKaniUserDaoImpl implements WaniKaniUserDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WaniKaniUserDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<WaniKaniUser> selectWaniKaniUser(UUID id)
    {
        final String sql = "SELECT id, api_token, name FROM wani_kani_user where id = ?";

        WaniKaniUser waniKaniUser = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID userId = UUID.fromString(resultSet.getString("id"));
            String apiToken = resultSet.getString("api_token");
            String name = resultSet.getString("name");

            return new WaniKaniUser(userId, apiToken, name);
        });

        return Optional.of(waniKaniUser);
    }

    @Override
    public List<WaniKaniUser> selectWaniKaniUsers()
    {
        final String sql = "SELECT id, api_token, name FROM wani_kani_user";

        List<WaniKaniUser> waniKaniUsers = jdbcTemplate.queryForObject(sql, new Object[]{}, (resultSet, i)  -> {
            List<WaniKaniUser> users = new ArrayList<>();
            while(resultSet.next())
            {
                UUID userId = UUID.fromString(resultSet.getString("id"));
                String apiToken = resultSet.getString("api_token");
                String name = resultSet.getString("name");

                WaniKaniUser user = new WaniKaniUser(userId, apiToken, name);
                users.add(user);
            }
            return users;
        });

        return waniKaniUsers;
    }

    @Override
    public int insertWaniKaniUser(UUID id, WaniKaniUser user)
    {
        final String sql = "INSERT INTO wani_kani_user(id, api_token, name) VALUES (?, ?, ?)";

        Object[] params = new Object[]{id, user.getApiToken(), user.getName()};
        return jdbcTemplate.update(sql, params);
    }
}
