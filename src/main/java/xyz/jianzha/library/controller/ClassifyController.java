package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import xyz.jianzha.library.entity.Classify;
import xyz.jianzha.library.service.ClassifyService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书分类表(Classify)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("classify")
public class ClassifyController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ClassifyService classifyService;

    /**
     * 查询所有数据
     *
     * @param classify 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(Classify classify) {
        return ResponseData.success(this.classifyService.list(new QueryWrapper<>(classify)), "执行成功！");
    }

    /**
     * 新增数据
     *
     * @param classify 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody Classify classify) {
        // 判断是不是管理员
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.classifyService.save(classify), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }

    /**
     * 修改数据
     *
     * @param classify 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody Classify classify) {
        // 判断是不是管理员
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.classifyService.updateById(classify), "执行成功！");
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
        // 判断是不是管理员
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            // 如果绑定这个id就不允许删除
            boolean b = classifyService.canDelete(idList);
            if (b) {
                return ResponseData.success(this.classifyService.removeByIds(idList), "执行成功！");
            } else {
                return ResponseData.fail("删除错误，请逐个删除，或者其他内容使用到该分类");
            }
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}