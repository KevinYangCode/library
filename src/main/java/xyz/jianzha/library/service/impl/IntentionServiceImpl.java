package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.IntentionDao;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.service.IntentionService;
import org.springframework.stereotype.Service;

/**
 * 新书采购意向表(Intention)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("intentionService")
public class IntentionServiceImpl extends ServiceImpl<IntentionDao, Intention> implements IntentionService {

}