package com.chenyu.app.web;

import com.chenyu.app.entity.User;
import com.chenyu.app.service.IUserService;
import com.chenyu.app.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private IUserService userService;

    @RequestMapping
    public Response login() {
        return Response.success("成功");
    }
    @RequestMapping("user")
    public Response<User> getUser(@RequestParam String id){
        User user = userService.getById(id);
        return Response.success("成功", user);
    }
}
