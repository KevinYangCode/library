package xyz.jianzha.library.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.jianzha.library.entity.Book;


/**
 * @author Y_Kevin
 * @date 2020-01-06 00:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookVo extends Book {
    private static final long serialVersionUID = 1L;
    private Integer current;
    private Integer size;
}
