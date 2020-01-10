package xyz.jianzha.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.jianzha.library.entity.Intention;

import java.util.List;

/**
 * 新书采购意向表(Intention)表服务接口
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
public interface IntentionService extends IService<Intention> {

    void idToName(List<Intention> records);
}