package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.jianzha.library.entity.*;
import xyz.jianzha.library.mapper.IntentionMapper;
import xyz.jianzha.library.mapper.UserInfoMapper;
import xyz.jianzha.library.mapper.UserMapper;
import xyz.jianzha.library.service.IntentionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新书采购意向表(Intention)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("intentionService")
public class IntentionServiceImpl extends ServiceImpl<IntentionMapper, Intention> implements IntentionService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public void idToName(List<Intention> records) {
        for (Intention intention : records) {
            // 人名
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userUUID", intention.getSubmitter());
            UserInfo userInfo = this.userInfoMapper.selectOne(queryWrapper);
            intention.setSubmitterName(userInfo.getName());
        }
    }
}