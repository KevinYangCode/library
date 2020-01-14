package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.entity.UserInfo;
import xyz.jianzha.library.mapper.BookMapper;
import xyz.jianzha.library.mapper.LendMapper;
import xyz.jianzha.library.entity.Lend;
import xyz.jianzha.library.mapper.UserInfoMapper;
import xyz.jianzha.library.service.LendService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 借阅信息表(Lend)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("lendService")
public class LendServiceImpl extends ServiceImpl<LendMapper, Lend> implements LendService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public void idToName(List<Lend> records) {
        for (Lend lend : records) {
            // 人名
            QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
            userWrapper.eq("userUUID", lend.getReaderId());
            UserInfo userInfo = this.userInfoMapper.selectOne(userWrapper);

            QueryWrapper<Book> bookWrapper = new QueryWrapper<>();
            bookWrapper.eq("book_id", lend.getBookId());
            Book book = this.bookMapper.selectOne(bookWrapper);

            lend.setReaderName(userInfo.getName());
            lend.setBookName(book.getName());
        }
    }
}