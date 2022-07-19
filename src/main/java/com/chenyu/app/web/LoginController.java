package com.chenyu.app.web;

import com.chenyu.app.config.redis.RedisConstant;
import com.chenyu.app.config.shiro.JwtUtil;
import com.chenyu.app.dao.UserRepository;
import com.chenyu.app.entity.User;
import com.chenyu.app.entity.UserAndToken;
import com.chenyu.app.util.RedisUtils;
import com.chenyu.app.util.Response;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 1.redis 怎么和mysql同步 canal（监听binlog文件）和消息队列<br>
 * 2.会发现token永远是固定值 怎么办 -- 可以不校验姓名 直接判断redis是否有值
 *
 * @author chenyu
 * @date 2020-09-01
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

  @Autowired private UserRepository userRepository;

  @Autowired private RedisUtils redisUtils;

  @RequestMapping
  public Response login(@RequestBody User user, HttpServletResponse res) {

    if (user == null
        || StringUtils.isEmpty(user.getUsername())
        || StringUtils.isEmpty(user.getPassword())) {
      return Response.fail("缺失参数");
    }

    User sysUser = userRepository.findByUsername(user.getUsername());
    if (sysUser == null || !user.getPassword().equals(sysUser.getPassword())) {
      return Response.fail("账号或者密码不正确");
    }

    String token = JwtUtil.sign(user.getUsername(), user.getPassword() + new Date().toString());
    // 把身份信息序列化存在redis里 这样每个节点只要通过redis就可以获取到身份 而不是数据库
    UserAndToken uat = new UserAndToken(token, sysUser);
    String redisKey = RedisConstant.PREFIX_TOKEN + user.getUsername();
    redisUtils.setCacheObject(redisKey, uat, 5 * 60 * 1000, TimeUnit.SECONDS);
    res.setHeader("access_token", token);
    return Response.success("成功");
  }

  @RequestMapping("/getUserInfo")
  public Response login() {
    User data = (User) SecurityUtils.getSubject().getPrincipal();
    return Response.success(data);
  }

  @RequestMapping("/getUserInfo2")
  @ResponseBody
  public Response login2() {
    User obj = (User) SecurityUtils.getSubject().getPrincipal();
    return Response.success("succscc");
  }
}
