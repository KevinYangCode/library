package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.UserInfo;
import xyz.jianzha.library.service.UserInfoService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户详情信息表(UserInfo)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-14 13:14
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("userInfo")
public class UserInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param userInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(Page<UserInfo> page, UserInfo userInfo) {
        return ResponseData.success(this.userInfoService.list(new QueryWrapper<>(userInfo)), "执行成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseData selectOne(@PathVariable Serializable id) {
        return ResponseData.success(this.userInfoService.getById(id), "执行成功！");
    }

    /**
     * 新增数据
     *
     * @param userInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody UserInfo userInfo) {
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.userInfoService.save(userInfo), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }

    /**
     * 修改数据
     *
     * @param userInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody UserInfo userInfo) {
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.userInfoService.updateById(userInfo), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public ResponseData delete(@RequestParam("idList") List<Long> idList) {
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.userInfoService.removeByIds(idList), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}