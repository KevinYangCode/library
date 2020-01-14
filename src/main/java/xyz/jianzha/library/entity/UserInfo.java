package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户详情信息表(UserInfo)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-14 13:14
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends Model<UserInfo> {
    /**
     * 成员信息ID
     */
    private String userinfouuid;

    /**
     * 成员ID
     */
    private String useruuid;

    /**
     * 成员名字
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 教务系统密码
     */
    private String eduPassword;

    /**
     * 方向
     */
    private Integer orientation;

    /**
     * 性别. 1:男	0:女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String icon;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 简介
     */
    private String info;

    /**
     * 特长
     */
    private String speciality;

    /**
     * 1老师/2毕业生/3在校4考核
     */
    private Integer note;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.userinfouuid;
    }
}