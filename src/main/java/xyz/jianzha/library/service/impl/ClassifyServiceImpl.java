package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.ClassifyDao;
import xyz.jianzha.library.entity.Classify;
import xyz.jianzha.library.service.ClassifyService;
import org.springframework.stereotype.Service;

/**
 * 图书分类表(Classify)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("classifyService")
public class ClassifyServiceImpl extends ServiceImpl<ClassifyDao, Classify> implements ClassifyService {

}