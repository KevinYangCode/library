package xyz.jianzha.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xyz.jianzha.library.entity.Lend;
import xyz.jianzha.library.service.LendService;
import org.springframework.web.bind.annotation.*;
import xyz.jianzha.library.utils.AuthUtils;
import xyz.jianzha.library.utils.ResponseData;
import xyz.jianzha.library.utils.Tools;

import javax.annotation.Resource;
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
        return getResponseData(page, lend, lend.getReaderId());
    }

    /**
     * 分页查询一个用户所有数据
     *
     * @param page 分页对象
     * @param lend 查询实体
     * @return 所有数据
     */
    @GetMapping("/One")
    public ResponseData selectOne(Page<Lend> page, Lend lend) {
        // 获取当前用户
        String userUuid = AuthUtils.authInfo().getUseruuid();
        return getResponseData(page, lend, userUuid);
    }

    /**
     * 查询分离出代码块
     *
     * @param page     分页条件
     * @param lend     模糊查询条件
     * @param userUuid 模糊查询条件或者当前用户
     * @return
     */
    private ResponseData getResponseData(Page<Lend> page, Lend lend, String userUuid) {
        // MybatisPlus条件构造器
        QueryWrapper<Lend> queryWrapper = new QueryWrapper<>();
        // 不为空，则是模糊查询
        if (Tools.isNotEmpty(lend.getBookName())) {
            // 调用方法：通过图书名称获取
            List<Object> idList = lendService.nameToId(lend.getBookName());
            queryWrapper.in("book_id", idList);
        }
        queryWrapper.eq(Tools.isNotEmpty(userUuid), "reader_id", userUuid);

        Page<Lend> lendPage = this.lendService.page(page, queryWrapper);
        // 获取借阅人的名字和书名
        lendService.idToName(lendPage.getRecords());
        return ResponseData.success(lendPage.getRecords(), lendPage.getTotal(), "执行成功！");
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
        // 判断当前用户是不是对应借阅者
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
        // 判断是不是管理员
        if (AuthUtils.authInfo().getRole() == 1 || AuthUtils.authInfo().getRole() == 2) {
            return ResponseData.success(this.lendService.removeByIds(idList), "执行成功！");
        } else {
            return ResponseData.fail("没有权限！");
        }
    }
}