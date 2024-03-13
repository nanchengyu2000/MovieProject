package entity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AdministratorExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public AdministratorExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
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

        public Criteria andAdministratoridIsNull() {
            addCriterion("administratorid is null");
            return (Criteria) this;
        }

        public Criteria andAdministratoridIsNotNull() {
            addCriterion("administratorid is not null");
            return (Criteria) this;
        }

        public Criteria andAdministratoridEqualTo(Integer value) {
            addCriterion("administratorid =", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridNotEqualTo(Integer value) {
            addCriterion("administratorid <>", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridGreaterThan(Integer value) {
            addCriterion("administratorid >", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridGreaterThanOrEqualTo(Integer value) {
            addCriterion("administratorid >=", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridLessThan(Integer value) {
            addCriterion("administratorid <", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridLessThanOrEqualTo(Integer value) {
            addCriterion("administratorid <=", value, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridIn(List<Integer> values) {
            addCriterion("administratorid in", values, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridNotIn(List<Integer> values) {
            addCriterion("administratorid not in", values, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridBetween(Integer value1, Integer value2) {
            addCriterion("administratorid between", value1, value2, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratoridNotBetween(Integer value1, Integer value2) {
            addCriterion("administratorid not between", value1, value2, "administratorid");
            return (Criteria) this;
        }

        public Criteria andAdministratornameIsNull() {
            addCriterion("administratorname is null");
            return (Criteria) this;
        }

        public Criteria andAdministratornameIsNotNull() {
            addCriterion("administratorname is not null");
            return (Criteria) this;
        }

        public Criteria andAdministratornameEqualTo(String value) {
            addCriterion("administratorname =", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameNotEqualTo(String value) {
            addCriterion("administratorname <>", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameGreaterThan(String value) {
            addCriterion("administratorname >", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameGreaterThanOrEqualTo(String value) {
            addCriterion("administratorname >=", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameLessThan(String value) {
            addCriterion("administratorname <", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameLessThanOrEqualTo(String value) {
            addCriterion("administratorname <=", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameLike(String value) {
            addCriterion("administratorname like", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameNotLike(String value) {
            addCriterion("administratorname not like", value, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameIn(List<String> values) {
            addCriterion("administratorname in", values, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameNotIn(List<String> values) {
            addCriterion("administratorname not in", values, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameBetween(String value1, String value2) {
            addCriterion("administratorname between", value1, value2, "administratorname");
            return (Criteria) this;
        }

        public Criteria andAdministratornameNotBetween(String value1, String value2) {
            addCriterion("administratorname not between", value1, value2, "administratorname");
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

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table administrator
     *
     * @mbggenerated do_not_delete_during_merge Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table administrator
     *
     * @mbggenerated Wed Dec 07 21:33:23 GMT+08:00 2022
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

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

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}