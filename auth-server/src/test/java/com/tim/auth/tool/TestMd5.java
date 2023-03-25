package com.tim.auth.tool;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author：tim
 * @date：2022-12-18 6:14 PM
 * @description：
 */
public class TestMd5 {

  @Test
  public void getMd5() {
    String password = "1234567a";
    String md5Password = DigestUtils.md5Hex(password);
    Assert.assertEquals("", "fe008700f25cb28940ca8ed91b23b354", md5Password);
  }
}
