package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.User;
import xyz.jianzha.library.service.UserService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.ResponseData;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 用户登录表(User)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("user")
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 查询所有数据
     *
     * @param user 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(User user) {
        return ResponseData.success(this.userService.list(new QueryWrapper<>(user)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseData selectOne(@PathVariable Serializable id) {
        return ResponseData.success(this.userService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody User user) {
        return ResponseData.success(this.userService.save(user));
    }

    /**
     * 修改数据
     *
     * @param user 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody User user) {
        return ResponseData.success(this.userService.updateById(user));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseData delete(@RequestParam("idList") List<Long> idList) {
        return ResponseData.success(this.userService.removeByIds(idList));
    }

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stuID", user.getStuid())
                .eq("password", user.getPassword());
        user = this.userService.getOne(queryWrapper);
        if (!Objects.isNull(user)) {
            return success(user);
        } else {
            return failed("请输入正确的账号和密码");
        }
    }
}