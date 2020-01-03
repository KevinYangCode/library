package xyz.jianzha.library.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 图书书目表(Book)表实体类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Book extends Model<Book> {
    /**
     * 图书ID
     */
    private Integer bookId;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publish;

    /**
     * 标准书号
     */
    private String isbn;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 价格
     */
    private Double price;

    /**
     * 出版时间
     */
    private Date pubDate;

    /**
     * 分类号
     */
    private Integer classId;

    /**
     * 封面图片
     */
    private String pic;

    /**
     * 位置
     */
    private Integer location;

    /**
     * 图书状态(0在架/1借出)
     */
    private Integer status;

    /**
     * 拥有者(0/1)
     */
    private String owner;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.bookId;
    }
}