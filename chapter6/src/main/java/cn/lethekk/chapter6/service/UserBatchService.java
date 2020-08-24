package cn.lethekk.chapter6.service;

import cn.lethekk.chapter6.pojo.User;

import java.util.List;

public interface UserBatchService {
    public int insertUser(List<User> userList);
}
