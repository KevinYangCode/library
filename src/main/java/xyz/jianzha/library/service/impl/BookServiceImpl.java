package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.mapper.BookMapper;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.service.BookService;
import org.springframework.stereotype.Service;

/**
 * 图书书目表(Book)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}