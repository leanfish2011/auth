package com.tim.auth.service;

import com.tim.auth.vo.UserAdd;
import com.tim.auth.vo.UserUpdate;
import com.tim.message.Message;
import java.util.List;
import com.tim.auth.vo.UserSearchReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.auth.po.User;

public interface UserService {

  List<UserSearchResp> search(UserSearchReq userSearchReq);

  boolean isExist(String userCode);

  boolean add(UserAdd userAdd);

  Message update(UserUpdate userUpdate);

  UserSearchResp select(String id);

  boolean delete(String id);
}
