package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 图书分类表(Classify)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Classify extends Model<Classify> {
    /**
     * 图书类别号
     */
    private Integer classId;

    /**
     * 图书类别名
     */
    private String className;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.classId;
    }
}