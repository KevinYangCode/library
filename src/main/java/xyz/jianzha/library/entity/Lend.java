package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 借阅信息表(Lend)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Lend extends Model<Lend> {
    /**
     * 借阅记录ID
     */
    @TableId(type = IdType.AUTO)
    private Integer lendId;

    /**
     * 图书ID
     */
    private Integer bookId;
    /**
     * 图书ID
     */
    @TableField(exist = false)
    private String bookName;


    /**
     * 借阅者ID
     */
    private String readerId;

    /**
     * 借阅者名字
     */
    @TableField(exist = false)
    private String readerName;

    /**
     * 借出日期
     */
    private Date lendDate;

    /**
     * 归还日期
     */
    private Date backDate;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.lendId;
    }
}