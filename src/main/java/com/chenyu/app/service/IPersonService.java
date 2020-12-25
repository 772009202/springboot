package com.chenyu.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyu.app.entity.Person;

/**
 * 服务类
 *
 * @author jobob
 * @since 2020-09-14
 */
public interface IPersonService extends IService<Person> {

  /**
   * 测试事务
   *
   * @param num
   */
  void testTransactional(int num);
}
