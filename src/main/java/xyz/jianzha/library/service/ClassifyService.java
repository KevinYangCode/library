package xyz.jianzha.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jianzha.library.entity.Classify;

import java.util.List;

/**
 * 图书分类表(Classify)表服务接口
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface ClassifyService extends IService<Classify> {

    boolean canDelete(List<Long> idList);

}