package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Lend;
import xyz.jianzha.library.service.LendService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 借阅信息表(Lend)表控制层
 *
 * @author Y_Kevin
 * @date 2020-01-03 20:03
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("lend")
public class LendController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private LendService lendService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param lend 查询实体
     * @return 所有数据
     */
    @GetMapping
    public ResponseData selectAll(Page<Lend> page, Lend lend) {
        QueryWrapper<Lend> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(lend.getBookId() != null && !"".equals(lend.getBookId()), "book_id", lend.getBookId());
        queryWrapper.eq(lend.getReaderId() != null && !"".equals(lend.getReaderId()), "reader_id", lend.getReaderId());
        Page<Lend> lendPage = this.lendService.page(page, queryWrapper);
        lendService.idToName(lendPage.getRecords());
        return ResponseData.success(lendPage.getRecords(), lendPage.getTotal(), "执行成功！");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseData selectOne(@PathVariable Serializable id) {
        return ResponseData.success(this.lendService.getById(id), "执行成功！");
    }

    /**
     * 新增数据
     *
     * @param lend 实体对象
     * @return 新增结果
     */
    @PostMapping
    public ResponseData insert(@RequestBody Lend lend) {
        lend.setReaderId(AuthUtils.authInfo().getUseruuid());
        lend.setLendDate(new Date());
        return ResponseData.success(this.lendService.save(lend), "执行成功！");
    }

    /**
     * 修改数据
     *
     * @param lend 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResponseData update(@RequestBody Lend lend) {
        if (lend.getReaderId().equals(AuthUtils.authInfo().getUseruuid())) {
            return ResponseData.success(this.lendService.updateById(lend), "执行成功！");
        } else {
            return ResponseData.fail("不是该借阅人");
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
            return ResponseData.success(this.lendService.removeByIds(idList), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}