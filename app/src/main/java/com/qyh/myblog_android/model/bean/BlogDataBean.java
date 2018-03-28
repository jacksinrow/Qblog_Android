package com.qyh.myblog_android.model.bean;

import java.io.Serializable;

/**
 * Created by adminn on 2018/3/27.
 */

public class BlogDataBean implements Serializable {

    /**
     * id : 7
     * type : 2
     * createTime : 2018-03-22 04:14:08 下午
     * title : 的点点滴滴多多顶顶顶顶
     * content : 的点点滴滴多多顶顶顶顶多多多多顶顶顶顶
     * userBean : {"userId":"91d43c10-edbc-4427-a431-6c5852ad9967","phone":null,"userName":null}
     * userId : 91d43c10-edbc-4427-a431-6c5852ad9967
     */

    private int id;
    private int type;
    private String createTime;
    private String title;
    private String content;
    private UserBeanBean userBean;
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBeanBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBeanBean userBean) {
        this.userBean = userBean;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userBean=" + userBean +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static class UserBeanBean  implements Serializable{
        /**
         * userId : 91d43c10-edbc-4427-a431-6c5852ad9967
         * phone : null
         * userName : null
         */

        private String userId;
        private String phone;
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "UserBeanBean{" +
                    "userId='" + userId + '\'' +
                    ", phone='" + phone + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}
