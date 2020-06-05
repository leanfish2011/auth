package com.tim.auth.service;

import com.tim.auth.vo.UserAdd;
import com.tim.auth.vo.UserUpdate;
import java.util.List;
import com.tim.auth.vo.UserSearchReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.auth.po.User;

public interface UserService {

  List<UserSearchResp> search(UserSearchReq userSearchReq);

  Boolean isExist(String userCode);

  Boolean add(UserAdd userAdd);

  Boolean update(UserUpdate userUpdate);

  UserSearchResp select(String id);

  Boolean delete(String id);

  List<UserSearchResp> roleUser(String roleId);

  User findOne(String userCode);
}
