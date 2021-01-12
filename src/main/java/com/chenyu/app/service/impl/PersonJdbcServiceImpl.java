package com.chenyu.app.service.impl;

import com.chenyu.app.entity.Person;
import com.chenyu.app.jdbc.PersonDao;
import com.chenyu.app.service.PersonJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyu
 * @date 2021-01-12
 */
@Service
public class PersonJdbcServiceImpl implements PersonJdbcService {

  @Autowired PersonDao personDao;

  @Override
  public Person queryPersonById(Long id) {
    return personDao.getPerson(id);
  }
}
