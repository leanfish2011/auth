package com.tim.auth.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public RoleExample() {
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

    public Criteria andRemarkIsNull() {
      addCriterion("remark is null");
      return (Criteria) this;
    }

    public Criteria andRemarkIsNotNull() {
      addCriterion("remark is not null");
      return (Criteria) this;
    }

    public Criteria andRemarkEqualTo(String value) {
      addCriterion("remark =", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotEqualTo(String value) {
      addCriterion("remark <>", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThan(String value) {
      addCriterion("remark >", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkGreaterThanOrEqualTo(String value) {
      addCriterion("remark >=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThan(String value) {
      addCriterion("remark <", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLessThanOrEqualTo(String value) {
      addCriterion("remark <=", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkLike(String value) {
      addCriterion("remark like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotLike(String value) {
      addCriterion("remark not like", value, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkIn(List<String> values) {
      addCriterion("remark in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotIn(List<String> values) {
      addCriterion("remark not in", values, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkBetween(String value1, String value2) {
      addCriterion("remark between", value1, value2, "remark");
      return (Criteria) this;
    }

    public Criteria andRemarkNotBetween(String value1, String value2) {
      addCriterion("remark not between", value1, value2, "remark");
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