package xyz.jianzha.library.entity;

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
 * @date 2020-01-03 19:18
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
    private Integer lendId;

    /**
     * 图书ID
     */
    private Integer bookId;

    /**
     * 读者ID
     */
    private String readerId;

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