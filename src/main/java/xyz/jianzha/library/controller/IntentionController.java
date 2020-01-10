package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Intention;
import xyz.jianzha.library.service.IntentionService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.ResponseData;

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
        QueryWrapper<Intention> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(intention.getName() != null && !"".equals(intention.getName()), "name", intention.getName());
        queryWrapper.eq(intention.getSubmitter() != null && !"".equals(intention.getSubmitter()), "submitter", intention.getSubmitter());
        Page<Intention> intentionPage = this.intentionService.page(page,queryWrapper);
        intentionService.idToName(intentionPage.getRecords());
        return ResponseData.success(intentionPage.getRecords(), intentionPage.getTotal(), "执行成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseData selectOne(@PathVariable Serializable id) {
        return ResponseData.success(this.intentionService.getById(id), "执行成功！");
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
        return ResponseData.success(this.intentionService.removeByIds(idList), "执行成功！");
    }
}