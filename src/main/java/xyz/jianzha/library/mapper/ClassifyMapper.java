package xyz.jianzha.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.jianzha.library.entity.Classify;

import java.io.Serializable;

/**
 * 图书分类表(Classify)表数据库访问层
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface ClassifyMapper extends BaseMapper<Classify> {
    /**
     * 为切面做切入点
     *
     * @param id
     * @return
     */
    @Override
    Classify selectById(Serializable id);
}