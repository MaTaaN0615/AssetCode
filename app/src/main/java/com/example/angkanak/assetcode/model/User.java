package com.example.angkanak.assetcode.model;

public class User {

  private int _id;
  private String username;
  private  String password;
  private String empcode;
  private String fullname;


  public User(int _id, String username, String password, String empcode, String fullname) {
    this.username = username;
    this.password = password;
    this.empcode = empcode;
    this.fullname = fullname;
  }


  public int get_id() {
    return _id;
  }

  public void set_id(int _id) {
    this._id = _id;
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

  public String getEmpcode() {
    return empcode;
  }

  public void setEmpcode(String empcode) {
    this.empcode = empcode;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

}
