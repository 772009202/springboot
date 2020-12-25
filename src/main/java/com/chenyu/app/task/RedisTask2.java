package com.chenyu.app.task;

import com.chenyu.app.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author chenyu
 * @date 2020-09-15
 */
@Configuration
@EnableScheduling
public class RedisTask2 {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private StringRedisTemplate redisTemplate;

  private String LockKey = "redisLock";

  @Autowired private IUserService userService;

  @Value("${taskOff}")
  public boolean taskOff;

  @Scheduled(cron = "10 * * * * ?")
  private void redisTask2() {
    if (taskOff) {
      return;
    }
    try {
      long start = System.currentTimeMillis();
      for (; ; ) {
        Boolean success = redisTemplate.opsForValue().setIfAbsent(LockKey, "lock");
        logger.info("\n【定时任务2】 是否获取锁{}", success);
        if (success) {
          redisTemplate.expire(LockKey, 10000L, TimeUnit.MILLISECONDS);
        } else {
          //  尝试5秒还没拿到锁就可以结束了 没超过就再次尝试拿锁
          long now = System.currentTimeMillis();
          if (now - start > 3000) {
            logger.info("\n【定时任务2】 我准备退出了");
            return;
          }
          continue;
        }

        // 业务代码
        long workStart = System.currentTimeMillis();
        // 业务代码
        Thread.sleep(5);

        logger.info("\n【定时任务2】 业务消耗时间{}", System.currentTimeMillis() - workStart);
        // 业务完成释放锁
        redisTemplate.delete(LockKey);
        break;
      }
    } catch (Exception e) {
      // 异常释放锁
      redisTemplate.delete(LockKey);
    }
  }
}
