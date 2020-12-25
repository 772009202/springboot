package com.chenyu.app.dao;

import com.chenyu.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenyu
 * @create 2020/8/31
 */
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsername(String userName);
}
