package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 书架表(Bookshelf)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Bookshelf extends Model<Bookshelf> {
    /**
     * 书架ID
     */
    @TableId(type = IdType.AUTO)
    private Integer bsId;
    
    /**
     * 书架名称
     */    
    private String name;
    
    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.bsId;
    }
    }