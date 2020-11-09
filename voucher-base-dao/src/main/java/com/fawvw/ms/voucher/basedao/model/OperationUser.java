package com.fawvw.ms.voucher.basedao.model;

import java.util.Date;

public class OperationUser {
    private Integer id;

    private String refId;

    private String username;

    private String email;

    private String mobile;

    private String identityTag;

    private Integer level;

    private Date createTime;

    private Date updateTime;

    private Byte active;

    private String signature;

    private String passwordDigest;

    private Long fkAvatarImageId;

    private Byte roleType;

    private String code;

    private Byte gender;

    public OperationUser(Integer id, String refId, String username, String email, String mobile, String identityTag, Integer level, Date createTime, Date updateTime, Byte active, String signature, String passwordDigest, Long fkAvatarImageId, Byte roleType, String code, Byte gender) {
        this.id = id;
        this.refId = refId;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.identityTag = identityTag;
        this.level = level;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.active = active;
        this.signature = signature;
        this.passwordDigest = passwordDigest;
        this.fkAvatarImageId = fkAvatarImageId;
        this.roleType = roleType;
        this.code = code;
        this.gender = gender;
    }

    public OperationUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getIdentityTag() {
        return identityTag;
    }

    public void setIdentityTag(String identityTag) {
        this.identityTag = identityTag == null ? null : identityTag.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest == null ? null : passwordDigest.trim();
    }

    public Long getFkAvatarImageId() {
        return fkAvatarImageId;
    }

    public void setFkAvatarImageId(Long fkAvatarImageId) {
        this.fkAvatarImageId = fkAvatarImageId;
    }

    public Byte getRoleType() {
        return roleType;
    }

    public void setRoleType(Byte roleType) {
        this.roleType = roleType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }
}