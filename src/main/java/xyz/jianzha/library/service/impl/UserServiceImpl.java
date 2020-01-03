package xyz.jianzha.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.jianzha.library.dao.UserDao;
import xyz.jianzha.library.entity.User;
import xyz.jianzha.library.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户登录表(User)表服务实现类
 *
 * @author Y_Kevin
 * @date 2020-01-03 19:18
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}