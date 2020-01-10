package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 新书采购意向表(Intention)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Intention extends Model<Intention> {
    /**
     * 新书采购意向ID
     */
    @TableId(type = IdType.AUTO)
    private Integer inteId;

    /**
     * 图书名称
     */
    private String name;

    /**
     * 提交者
     */
    private String submitter;
    /**
     * 提交者
     */
    @TableField(exist = false)
    private String submitterName;

    /**
     * 标准书号
     */
    private String isbn;

    /**
     * 图书图片
     */
    private String pic;

    /**
     * 原因
     */
    private String reasons;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.inteId;
    }
}