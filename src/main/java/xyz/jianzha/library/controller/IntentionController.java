package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.service.IntentionService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;
import xyz.jianzha.library.utils.Tools;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 新书采购意向表(Intention)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("intention")
public class IntentionController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private IntentionService intentionService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param intention 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(Page<Intention> page, Intention intention) {
        // MybatisPlus条件构造器
        QueryWrapper<Intention> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Tools.isNotEmpty(intention.getName()), "name", intention.getName());
        queryWrapper.eq(Tools.isNotEmpty(intention.getSubmitter()), "submitter", intention.getSubmitter());

        Page<Intention> intentionPage = this.intentionService.page(page, queryWrapper);
        // 获取建议者的名字
        intentionService.idToName(intentionPage.getRecords());
        return ResponseData.success(intentionPage.getRecords(), intentionPage.getTotal(), "执行成功！");
    }

    /**
     * 新增数据
     *
     * @param intention 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody Intention intention) {
        return ResponseData.success(this.intentionService.save(intention), "执行成功！");
    }

    /**
     * 修改数据
     *
     * @param intention 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody Intention intention) {
        return ResponseData.success(this.intentionService.updateById(intention), "执行成功！");
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
            return ResponseData.success(this.intentionService.removeByIds(idList), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}