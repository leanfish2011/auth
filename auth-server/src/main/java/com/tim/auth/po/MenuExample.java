package com.tim.auth.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public MenuExample() {
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

    public Criteria andUrlIsNull() {
      addCriterion("url is null");
      return (Criteria) this;
    }

    public Criteria andUrlIsNotNull() {
      addCriterion("url is not null");
      return (Criteria) this;
    }

    public Criteria andUrlEqualTo(String value) {
      addCriterion("url =", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotEqualTo(String value) {
      addCriterion("url <>", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThan(String value) {
      addCriterion("url >", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThanOrEqualTo(String value) {
      addCriterion("url >=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThan(String value) {
      addCriterion("url <", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThanOrEqualTo(String value) {
      addCriterion("url <=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLike(String value) {
      addCriterion("url like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotLike(String value) {
      addCriterion("url not like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlIn(List<String> values) {
      addCriterion("url in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotIn(List<String> values) {
      addCriterion("url not in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlBetween(String value1, String value2) {
      addCriterion("url between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotBetween(String value1, String value2) {
      addCriterion("url not between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andRequestPathIsNull() {
      addCriterion("request_path is null");
      return (Criteria) this;
    }

    public Criteria andRequestPathIsNotNull() {
      addCriterion("request_path is not null");
      return (Criteria) this;
    }

    public Criteria andRequestPathEqualTo(String value) {
      addCriterion("request_path =", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathNotEqualTo(String value) {
      addCriterion("request_path <>", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathGreaterThan(String value) {
      addCriterion("request_path >", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathGreaterThanOrEqualTo(String value) {
      addCriterion("request_path >=", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathLessThan(String value) {
      addCriterion("request_path <", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathLessThanOrEqualTo(String value) {
      addCriterion("request_path <=", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathLike(String value) {
      addCriterion("request_path like", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathNotLike(String value) {
      addCriterion("request_path not like", value, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathIn(List<String> values) {
      addCriterion("request_path in", values, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathNotIn(List<String> values) {
      addCriterion("request_path not in", values, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathBetween(String value1, String value2) {
      addCriterion("request_path between", value1, value2, "requestPath");
      return (Criteria) this;
    }

    public Criteria andRequestPathNotBetween(String value1, String value2) {
      addCriterion("request_path not between", value1, value2, "requestPath");
      return (Criteria) this;
    }

    public Criteria andParentIdIsNull() {
      addCriterion("parent_id is null");
      return (Criteria) this;
    }

    public Criteria andParentIdIsNotNull() {
      addCriterion("parent_id is not null");
      return (Criteria) this;
    }

    public Criteria andParentIdEqualTo(String value) {
      addCriterion("parent_id =", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotEqualTo(String value) {
      addCriterion("parent_id <>", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdGreaterThan(String value) {
      addCriterion("parent_id >", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdGreaterThanOrEqualTo(String value) {
      addCriterion("parent_id >=", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLessThan(String value) {
      addCriterion("parent_id <", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLessThanOrEqualTo(String value) {
      addCriterion("parent_id <=", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdLike(String value) {
      addCriterion("parent_id like", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotLike(String value) {
      addCriterion("parent_id not like", value, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdIn(List<String> values) {
      addCriterion("parent_id in", values, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotIn(List<String> values) {
      addCriterion("parent_id not in", values, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdBetween(String value1, String value2) {
      addCriterion("parent_id between", value1, value2, "parentId");
      return (Criteria) this;
    }

    public Criteria andParentIdNotBetween(String value1, String value2) {
      addCriterion("parent_id not between", value1, value2, "parentId");
      return (Criteria) this;
    }

    public Criteria andSortNumIsNull() {
      addCriterion("sort_num is null");
      return (Criteria) this;
    }

    public Criteria andSortNumIsNotNull() {
      addCriterion("sort_num is not null");
      return (Criteria) this;
    }

    public Criteria andSortNumEqualTo(Integer value) {
      addCriterion("sort_num =", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumNotEqualTo(Integer value) {
      addCriterion("sort_num <>", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumGreaterThan(Integer value) {
      addCriterion("sort_num >", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumGreaterThanOrEqualTo(Integer value) {
      addCriterion("sort_num >=", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumLessThan(Integer value) {
      addCriterion("sort_num <", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumLessThanOrEqualTo(Integer value) {
      addCriterion("sort_num <=", value, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumIn(List<Integer> values) {
      addCriterion("sort_num in", values, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumNotIn(List<Integer> values) {
      addCriterion("sort_num not in", values, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumBetween(Integer value1, Integer value2) {
      addCriterion("sort_num between", value1, value2, "sortNum");
      return (Criteria) this;
    }

    public Criteria andSortNumNotBetween(Integer value1, Integer value2) {
      addCriterion("sort_num not between", value1, value2, "sortNum");
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