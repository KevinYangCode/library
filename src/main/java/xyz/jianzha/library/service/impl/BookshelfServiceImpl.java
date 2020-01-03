package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.BookshelfDao;
import xyz.jianzha.library.entity.Bookshelf;
import xyz.jianzha.library.service.BookshelfService;
import org.springframework.stereotype.Service;

/**
 * 书架表(Bookshelf)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("bookshelfService")
public class BookshelfServiceImpl extends ServiceImpl<BookshelfDao, Bookshelf> implements BookshelfService {

}