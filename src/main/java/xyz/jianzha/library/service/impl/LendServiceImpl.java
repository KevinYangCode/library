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

    /**
     * Id获取人名书名
     *
     * @param records 借阅的所有数据
     */
    @Override
    public void idToName(List<Lend> records) {
        for (Lend lend : records) {
            // 人名
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userUUID", lend.getReaderId());
            UserInfo userInfo = this.userInfoMapper.selectOne(queryWrapper);
            // 书名
            Book book = this.bookMapper.selectById(lend.getBookId());
            // 赋值
            lend.setReaderName(userInfo.getName());
            lend.setBookName(book.getName());
        }
    }

    /**
     * 用于借阅的模糊查询，通过图书名字查出图书ID
     *
     * @param name 图书名字
     * @return 图书ID集合
     */
    @Override
    public List<Object> nameToId(String name) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return bookMapper.selectObjs(queryWrapper);
    }
}