package xyz.jianzha.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jianzha.library.entity.Bookshelf;

import java.util.List;

/**
 * 书架表(Bookshelf)表服务接口
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface BookshelfService extends IService<Bookshelf> {

    boolean canDelete(List<Long> idList);
}