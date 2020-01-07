package xyz.jianzha.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.jianzha.library.entity.Bookshelf;

import java.io.Serializable;

/**
 * 书架表(Bookshelf)表数据库访问层
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface BookshelfMapper extends BaseMapper<Bookshelf> {
    /**
     * 为切面做切入点
     *
     * @param id
     * @return
     */
    @Override
    Bookshelf selectById(Serializable id);

}