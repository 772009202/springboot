package com.chenyu.app.service;

import com.chenyu.app.entity.Person;

/**
 * @author chenyu
 * @create 2021/1/12
 */
public interface PersonJdbcService {

  Person queryPersonById(Long id);
}
