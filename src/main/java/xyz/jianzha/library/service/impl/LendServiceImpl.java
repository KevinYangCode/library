package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.LendDao;
import xyz.jianzha.library.entity.Lend;
import xyz.jianzha.library.service.LendService;
import org.springframework.stereotype.Service;

/**
 * 借阅信息表(Lend)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("lendService")
public class LendServiceImpl extends ServiceImpl<LendDao, Lend> implements LendService {

}