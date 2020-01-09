package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户登录表(User)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {
    /**
     * 成员ID
     */
    private String useruuid;

    /**
     * 成员学号/老师可用手机号
     */
    private String stuid;

    /**
     * s
     * 登录密码
     */
    private String password;

    /**
     * 1为超管,2为普管，3没有权限
     */
    private Integer role;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.useruuid;
    }
}