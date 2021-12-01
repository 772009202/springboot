package com.chenyu.app.mongoService;

import com.mongodb.BasicDBList;

/**
 * @author chenyu
 * @date 2021-11-18
 */
public class BasicDBListOperation {

  public static BasicDBList build(Object... list) {
    if (list == null || list.length < 1) {
      throw new RuntimeException("参数为空");
    }
    BasicDBList basicDBList = new BasicDBList();
    for (Object o : list) {
      basicDBList.add(o);
    }

    return basicDBList;
  }
}
