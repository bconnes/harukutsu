package com.brandon.harukutsu.dao;

import com.brandon.harukutsu.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/9/2019
 */
@Repository("postgres")
public class PersonDataAccessService implements PersonDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person)
    {
        final String sql = "INSERT INTO Person (id, name) VALUES (?, ?)";

        Object[] params = new Object[]{id, person.getName()};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Person> selectAllPeople()
    {
        final String sql = "SELECT id, name FROM person";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }

    @Override
    public Optional<Person> selectPersonById(UUID id)
    {
        final String sql = "SELECT id, name FROM person WHERE id = ?";

        Person person = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId, name);
        });

        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id)
    {
        final String sql = "DELETE FROM person WHERE id = ?";

        Object[] params = new Object[]{id};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int updatePersonById(UUID id, Person person)
    {
        final String sql = "UPDATE Person SET name = ? WHERE id = ?";

        Object[] params = new Object[]{person.getName(), id};
        return jdbcTemplate.update(sql, params);
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }
}
