package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.mapper.UserMapper;
import xyz.jianzha.library.entity.User;
import xyz.jianzha.library.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户登录表(User)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 21:17
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}