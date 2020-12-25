package com.chenyu.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyu.app.entity.User;
import com.chenyu.app.mapper.UserMapper;
import com.chenyu.app.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author jobob
 * @since 2020-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {}
