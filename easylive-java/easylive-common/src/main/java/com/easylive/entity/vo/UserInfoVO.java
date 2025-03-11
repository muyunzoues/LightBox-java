package com.easylive.entity.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
    private String userId;
    private String nickName;
    private String avatar;
    private Integer sex;
    private String personIntroduction;
    /**
     * 空间公告
     */
    private String noticeInfo;
    /**
     * 等级
     */
    private Integer grade;
    private String birthday;
    private String school;

    private Integer fansCount;
    private Integer focusCount;
    private Integer likeCount;
    private Integer playCount;
    private Boolean havaFocus;
    private Integer theme;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPersonIntroduction() {
        return personIntroduction;
    }

    public void setPersonIntroduction(String personIntroduction) {
        this.personIntroduction = personIntroduction;
    }

    public String getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(String noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(Integer focusCount) {
        this.focusCount = focusCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Boolean getHavaFocus() {
        return havaFocus;
    }

    public void setHavaFocus(Boolean havaFocus) {
        this.havaFocus = havaFocus;
    }

    public Integer getTheme() {
        return theme;
    }

    public void setTheme(Integer theme) {
        this.theme = theme;
    }
}
