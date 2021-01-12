package com.chenyu.app.jdbc;

import com.chenyu.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author chenyu
 * @create 2021/1/12
 */
@Repository
public class PersonDao {

  @Autowired private JdbcTemplate jdbcTemplate;

  public Person getPerson(Long id) {
    String sql = "select * from person where id = ?";
    return jdbcTemplate.queryForObject(
        sql,
        (res, index) -> {
          Person person = new Person();
          person.setId(res.getLong("id"));
          person.setAge(res.getInt("age"));
          person.setName(res.getString("name"));
          return person;
        },
        id);
  }
}
