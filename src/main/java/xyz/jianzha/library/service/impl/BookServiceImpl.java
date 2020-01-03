package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.BookDao;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.service.BookService;
import org.springframework.stereotype.Service;

/**
 * 图书书目表(Book)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {

}