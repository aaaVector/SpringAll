package com.example.mybatis.plus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;


import java.io.Serializable;

/**
 * (Students)表实体类
 *
 * @author makejava
 * @since 2021-04-24 15:52:42
 */
@SuppressWarnings("serial")
public class Students extends Model<Students> {

    private Integer id;

    private Integer classId;

    private String name;

    private String gender;

    private Date createTime;

    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
