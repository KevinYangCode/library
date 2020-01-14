package xyz.jianzha.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.entity.Lend;

import java.util.List;

/**
 * 借阅信息表(Lend)表服务接口
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface LendService extends IService<Lend> {
    void idToName(List<Lend> records);
}