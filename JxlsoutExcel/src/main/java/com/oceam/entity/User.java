package com.oceam.entity;




import java.io.Serializable;

/**
 * @author zhuzhe
 * @date 2018/5/20 17:13
 * @email 1529949535@qq.com
 */

public class User implements Serializable {

    /**
     * 用户实体类模型
     *
     * @author JustryDeng
     * @date 2018年7月24日 下午1:55:59
     */

        /** 姓名 */
        private String name;

        /** 年龄 */
        private Integer age;

        /** 性别 */
        private String gender;

        /** 座右铭 */
        private String motto;

        public User() {
        }

        public User(String name, Integer age, String gender, String motto) {
            super();
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.motto = motto;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getMotto() {
            return motto;
        }

        public void setMotto(String motto) {
            this.motto = motto;
        }

    }

