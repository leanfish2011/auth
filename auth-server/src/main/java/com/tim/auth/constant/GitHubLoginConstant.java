package com.tim.auth.constant;

/**
 * @author：tim
 * @date：20-4-11 下午9:22
 * @description： github登录参数
 */
public class GitHubLoginConstant {

  // 这里填写在GitHub上注册应用时候获得 CLIENT ID
  public static final String CLIENT_ID = "f2a52e951e10ea966cf9";

  //这里填写在GitHub上注册应用时候获得 CLIENT_SECRET
  public static final String CLIENT_SECRET = "21c9e61ed469c1abf87f8cdc29c9a59c8b0bdeb8";

  //获取token的url
  public static final String TOKEN_URL =
      "https://github.com/login/oauth/access_token?client_id=" + CLIENT_ID + "&client_secret="
          + CLIENT_SECRET + "&code=CODE";

  //获取用户信息的url
  public static final String
      USER_INFO_URL = "https://api.github.com/user?access_token=TOKEN";

}
