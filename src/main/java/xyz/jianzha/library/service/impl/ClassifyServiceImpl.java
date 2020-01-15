package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.jianzha.library.entity.Book;
import xyz.jianzha.library.mapper.BookMapper;
import xyz.jianzha.library.mapper.ClassifyMapper;
import xyz.jianzha.library.entity.Classify;
import xyz.jianzha.library.service.ClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书分类表(Classify)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("classifyService")
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {
    @Autowired
    BookMapper bookMapper;

    /**
     * 判断该分类是否绑定了书籍
     *
     * @param idList 分类ID集合
     * @return true可删
     */
    @Override
    public boolean canDelete(List<Long> idList) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Integer count = 0;
        for (Long classId : idList) {
            queryWrapper.eq("class_id", classId);
            count += bookMapper.selectCount(queryWrapper);
        }
        return count == 0;
    }
}