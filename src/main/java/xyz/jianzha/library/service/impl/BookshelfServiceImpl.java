package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.mapper.BookMapper;
import xyz.jianzha.library.mapper.BookshelfMapper;
import xyz.jianzha.library.entity.Bookshelf;
import xyz.jianzha.library.service.BookshelfService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 书架表(Bookshelf)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("bookshelfService")
public class BookshelfServiceImpl extends ServiceImpl<BookshelfMapper, Bookshelf> implements BookshelfService {
    @Autowired
    BookMapper bookMapper;

    /**
     * 判断该书架绑定了书籍
     *
     * @param idList 书架id集合
     * @return 可以删为true
     */
    @Override
    public boolean canDelete(List<Long> idList) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Integer count = 0;
        for (Long bsId : idList) {
            queryWrapper.eq("location", bsId);
            count += bookMapper.selectCount(queryWrapper);
        }
        return count == 0;
    }
}