package com.chenyu.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyu.app.entity.Person;
import com.chenyu.app.mapper.PersonMapper;
import com.chenyu.app.service.IPersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author jobob
 * @since 2020-09-14
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void testTransactional(int num) {
    // 数据库操作
    Person person = new Person();
    person.setName("陈雨的事务控制" + num);
    person.setAge(26);
    person.setId((long) num);
    save(person);

    // 可能出现异常出现异常
    int a = 10 / num;
  }
}
