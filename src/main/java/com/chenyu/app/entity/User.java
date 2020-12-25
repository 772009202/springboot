package com.chenyu.app.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author chenyu
 * @date 2020-08-31
 */
@Entity
@Table(name = "user")
@Api(value = "用户表")
public class User implements Serializable {

  private static final long serialVersionUID = 0L;

  @Id
  @GeneratedValue
  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "账号")
  private String username;

  @ApiModelProperty(value = "密码")
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + '}';
  }
}
