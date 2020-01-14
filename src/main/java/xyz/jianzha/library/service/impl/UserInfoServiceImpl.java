package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.mapper.UserInfoMapper;
import xyz.jianzha.library.entity.UserInfo;
import xyz.jianzha.library.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户详情信息表(UserInfo)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-14 13:14
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}