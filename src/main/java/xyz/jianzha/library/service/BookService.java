package xyz.jianzha.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jianzha.library.entity.Book;

import java.util.List;

/**
 * 图书书目表(Book)表服务接口
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface BookService extends IService<Book> {
    void idToName(List<Book> records);

}