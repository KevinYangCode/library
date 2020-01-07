package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.jianzha.library.entity.Bookshelf;
import xyz.jianzha.library.entity.Classify;
import xyz.jianzha.library.mapper.BookMapper;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.mapper.BookshelfMapper;
import xyz.jianzha.library.mapper.ClassifyMapper;
import xyz.jianzha.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书书目表(Book)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Autowired
    ClassifyMapper classifyMapper;

    @Autowired
    BookshelfMapper bookshelfMapper;

    /**
     * 根据ID查找对应的名称
     *
     * @param records 包含id的数据
     */
    @Override
    public void idToName(List<Book> records) {
        for (Book book : records) {
            // 分类名
            Classify classify = this.classifyMapper.selectById(book.getClassId());
            book.setClassName(classify.getClassName());
            // 书架位置名
            Bookshelf bookshelf = this.bookshelfMapper.selectById(book.getLocation());
            book.setLocationName(bookshelf.getName());
        }
    }
}