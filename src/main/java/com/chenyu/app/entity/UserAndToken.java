package com.chenyu.app.entity;

/**
 * token和用户信息的集合，用于存在redis
 *
 * @author chenyu
 * @date 2020-12-30
 */
public class UserAndToken {

  private String accessToken;
  private User user;

  public UserAndToken() {}

  public UserAndToken(String accessToken, User user) {
    this.accessToken = accessToken;
    this.user = user;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
