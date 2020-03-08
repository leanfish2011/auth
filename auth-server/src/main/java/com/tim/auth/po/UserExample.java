package com.tim.auth.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public UserExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  protected abstract static class GeneratedCriteria {

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(String value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(String value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(String value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(String value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(String value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(String value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLike(String value) {
      addCriterion("id like", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotLike(String value) {
      addCriterion("id not like", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<String> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<String> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(String value1, String value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(String value1, String value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andCreateTimeIsNull() {
      addCriterion("create_time is null");
      return (Criteria) this;
    }

    public Criteria andCreateTimeIsNotNull() {
      addCriterion("create_time is not null");
      return (Criteria) this;
    }

    public Criteria andCreateTimeEqualTo(Date value) {
      addCriterion("create_time =", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeNotEqualTo(Date value) {
      addCriterion("create_time <>", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeGreaterThan(Date value) {
      addCriterion("create_time >", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
      addCriterion("create_time >=", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeLessThan(Date value) {
      addCriterion("create_time <", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
      addCriterion("create_time <=", value, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeIn(List<Date> values) {
      addCriterion("create_time in", values, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeNotIn(List<Date> values) {
      addCriterion("create_time not in", values, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeBetween(Date value1, Date value2) {
      addCriterion("create_time between", value1, value2, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
      addCriterion("create_time not between", value1, value2, "createTime");
      return (Criteria) this;
    }

    public Criteria andCreatorIdIsNull() {
      addCriterion("creator_id is null");
      return (Criteria) this;
    }

    public Criteria andCreatorIdIsNotNull() {
      addCriterion("creator_id is not null");
      return (Criteria) this;
    }

    public Criteria andCreatorIdEqualTo(String value) {
      addCriterion("creator_id =", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdNotEqualTo(String value) {
      addCriterion("creator_id <>", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdGreaterThan(String value) {
      addCriterion("creator_id >", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
      addCriterion("creator_id >=", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdLessThan(String value) {
      addCriterion("creator_id <", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdLessThanOrEqualTo(String value) {
      addCriterion("creator_id <=", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdLike(String value) {
      addCriterion("creator_id like", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdNotLike(String value) {
      addCriterion("creator_id not like", value, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdIn(List<String> values) {
      addCriterion("creator_id in", values, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdNotIn(List<String> values) {
      addCriterion("creator_id not in", values, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdBetween(String value1, String value2) {
      addCriterion("creator_id between", value1, value2, "creatorId");
      return (Criteria) this;
    }

    public Criteria andCreatorIdNotBetween(String value1, String value2) {
      addCriterion("creator_id not between", value1, value2, "creatorId");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeIsNull() {
      addCriterion("modified_time is null");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeIsNotNull() {
      addCriterion("modified_time is not null");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeEqualTo(Date value) {
      addCriterion("modified_time =", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeNotEqualTo(Date value) {
      addCriterion("modified_time <>", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeGreaterThan(Date value) {
      addCriterion("modified_time >", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeGreaterThanOrEqualTo(Date value) {
      addCriterion("modified_time >=", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeLessThan(Date value) {
      addCriterion("modified_time <", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeLessThanOrEqualTo(Date value) {
      addCriterion("modified_time <=", value, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeIn(List<Date> values) {
      addCriterion("modified_time in", values, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeNotIn(List<Date> values) {
      addCriterion("modified_time not in", values, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeBetween(Date value1, Date value2) {
      addCriterion("modified_time between", value1, value2, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifiedTimeNotBetween(Date value1, Date value2) {
      addCriterion("modified_time not between", value1, value2, "modifiedTime");
      return (Criteria) this;
    }

    public Criteria andModifierIdIsNull() {
      addCriterion("modifier_id is null");
      return (Criteria) this;
    }

    public Criteria andModifierIdIsNotNull() {
      addCriterion("modifier_id is not null");
      return (Criteria) this;
    }

    public Criteria andModifierIdEqualTo(String value) {
      addCriterion("modifier_id =", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdNotEqualTo(String value) {
      addCriterion("modifier_id <>", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdGreaterThan(String value) {
      addCriterion("modifier_id >", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdGreaterThanOrEqualTo(String value) {
      addCriterion("modifier_id >=", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdLessThan(String value) {
      addCriterion("modifier_id <", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdLessThanOrEqualTo(String value) {
      addCriterion("modifier_id <=", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdLike(String value) {
      addCriterion("modifier_id like", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdNotLike(String value) {
      addCriterion("modifier_id not like", value, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdIn(List<String> values) {
      addCriterion("modifier_id in", values, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdNotIn(List<String> values) {
      addCriterion("modifier_id not in", values, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdBetween(String value1, String value2) {
      addCriterion("modifier_id between", value1, value2, "modifierId");
      return (Criteria) this;
    }

    public Criteria andModifierIdNotBetween(String value1, String value2) {
      addCriterion("modifier_id not between", value1, value2, "modifierId");
      return (Criteria) this;
    }

    public Criteria andUsercodeIsNull() {
      addCriterion("userCode is null");
      return (Criteria) this;
    }

    public Criteria andUsercodeIsNotNull() {
      addCriterion("userCode is not null");
      return (Criteria) this;
    }

    public Criteria andUsercodeEqualTo(String value) {
      addCriterion("userCode =", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeNotEqualTo(String value) {
      addCriterion("userCode <>", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeGreaterThan(String value) {
      addCriterion("userCode >", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeGreaterThanOrEqualTo(String value) {
      addCriterion("userCode >=", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeLessThan(String value) {
      addCriterion("userCode <", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeLessThanOrEqualTo(String value) {
      addCriterion("userCode <=", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeLike(String value) {
      addCriterion("userCode like", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeNotLike(String value) {
      addCriterion("userCode not like", value, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeIn(List<String> values) {
      addCriterion("userCode in", values, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeNotIn(List<String> values) {
      addCriterion("userCode not in", values, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeBetween(String value1, String value2) {
      addCriterion("userCode between", value1, value2, "userCode");
      return (Criteria) this;
    }

    public Criteria andUsercodeNotBetween(String value1, String value2) {
      addCriterion("userCode not between", value1, value2, "userCode");
      return (Criteria) this;
    }

    public Criteria andPasswordIsNull() {
      addCriterion("password is null");
      return (Criteria) this;
    }

    public Criteria andPasswordIsNotNull() {
      addCriterion("password is not null");
      return (Criteria) this;
    }

    public Criteria andPasswordEqualTo(String value) {
      addCriterion("password =", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotEqualTo(String value) {
      addCriterion("password <>", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordGreaterThan(String value) {
      addCriterion("password >", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordGreaterThanOrEqualTo(String value) {
      addCriterion("password >=", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLessThan(String value) {
      addCriterion("password <", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLessThanOrEqualTo(String value) {
      addCriterion("password <=", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLike(String value) {
      addCriterion("password like", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotLike(String value) {
      addCriterion("password not like", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordIn(List<String> values) {
      addCriterion("password in", values, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotIn(List<String> values) {
      addCriterion("password not in", values, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordBetween(String value1, String value2) {
      addCriterion("password between", value1, value2, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotBetween(String value1, String value2) {
      addCriterion("password not between", value1, value2, "password");
      return (Criteria) this;
    }

    public Criteria andNameIsNull() {
      addCriterion("name is null");
      return (Criteria) this;
    }

    public Criteria andNameIsNotNull() {
      addCriterion("name is not null");
      return (Criteria) this;
    }

    public Criteria andNameEqualTo(String value) {
      addCriterion("name =", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotEqualTo(String value) {
      addCriterion("name <>", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThan(String value) {
      addCriterion("name >", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThanOrEqualTo(String value) {
      addCriterion("name >=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThan(String value) {
      addCriterion("name <", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThanOrEqualTo(String value) {
      addCriterion("name <=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLike(String value) {
      addCriterion("name like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotLike(String value) {
      addCriterion("name not like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameIn(List<String> values) {
      addCriterion("name in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotIn(List<String> values) {
      addCriterion("name not in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameBetween(String value1, String value2) {
      addCriterion("name between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotBetween(String value1, String value2) {
      addCriterion("name not between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andEmailIsNull() {
      addCriterion("email is null");
      return (Criteria) this;
    }

    public Criteria andEmailIsNotNull() {
      addCriterion("email is not null");
      return (Criteria) this;
    }

    public Criteria andEmailEqualTo(String value) {
      addCriterion("email =", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotEqualTo(String value) {
      addCriterion("email <>", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailGreaterThan(String value) {
      addCriterion("email >", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailGreaterThanOrEqualTo(String value) {
      addCriterion("email >=", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLessThan(String value) {
      addCriterion("email <", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLessThanOrEqualTo(String value) {
      addCriterion("email <=", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailLike(String value) {
      addCriterion("email like", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotLike(String value) {
      addCriterion("email not like", value, "email");
      return (Criteria) this;
    }

    public Criteria andEmailIn(List<String> values) {
      addCriterion("email in", values, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotIn(List<String> values) {
      addCriterion("email not in", values, "email");
      return (Criteria) this;
    }

    public Criteria andEmailBetween(String value1, String value2) {
      addCriterion("email between", value1, value2, "email");
      return (Criteria) this;
    }

    public Criteria andEmailNotBetween(String value1, String value2) {
      addCriterion("email not between", value1, value2, "email");
      return (Criteria) this;
    }

    public Criteria andPhotourlIsNull() {
      addCriterion("photourl is null");
      return (Criteria) this;
    }

    public Criteria andPhotourlIsNotNull() {
      addCriterion("photourl is not null");
      return (Criteria) this;
    }

    public Criteria andPhotourlEqualTo(String value) {
      addCriterion("photourl =", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlNotEqualTo(String value) {
      addCriterion("photourl <>", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlGreaterThan(String value) {
      addCriterion("photourl >", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlGreaterThanOrEqualTo(String value) {
      addCriterion("photourl >=", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlLessThan(String value) {
      addCriterion("photourl <", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlLessThanOrEqualTo(String value) {
      addCriterion("photourl <=", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlLike(String value) {
      addCriterion("photourl like", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlNotLike(String value) {
      addCriterion("photourl not like", value, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlIn(List<String> values) {
      addCriterion("photourl in", values, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlNotIn(List<String> values) {
      addCriterion("photourl not in", values, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlBetween(String value1, String value2) {
      addCriterion("photourl between", value1, value2, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotourlNotBetween(String value1, String value2) {
      addCriterion("photourl not between", value1, value2, "photourl");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintIsNull() {
      addCriterion("photo_fingerprint is null");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintIsNotNull() {
      addCriterion("photo_fingerprint is not null");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintEqualTo(String value) {
      addCriterion("photo_fingerprint =", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintNotEqualTo(String value) {
      addCriterion("photo_fingerprint <>", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintGreaterThan(String value) {
      addCriterion("photo_fingerprint >", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintGreaterThanOrEqualTo(String value) {
      addCriterion("photo_fingerprint >=", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintLessThan(String value) {
      addCriterion("photo_fingerprint <", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintLessThanOrEqualTo(String value) {
      addCriterion("photo_fingerprint <=", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintLike(String value) {
      addCriterion("photo_fingerprint like", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintNotLike(String value) {
      addCriterion("photo_fingerprint not like", value, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintIn(List<String> values) {
      addCriterion("photo_fingerprint in", values, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintNotIn(List<String> values) {
      addCriterion("photo_fingerprint not in", values, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintBetween(String value1, String value2) {
      addCriterion("photo_fingerprint between", value1, value2, "photoFingerprint");
      return (Criteria) this;
    }

    public Criteria andPhotoFingerprintNotBetween(String value1, String value2) {
      addCriterion("photo_fingerprint not between", value1, value2, "photoFingerprint");
      return (Criteria) this;
    }
  }

  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}