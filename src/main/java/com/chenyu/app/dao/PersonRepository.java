package com.chenyu.app.dao;

import com.chenyu.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenyu
 * @create 2020/8/28
 */
public interface PersonRepository extends JpaRepository<Person, Long> {}
