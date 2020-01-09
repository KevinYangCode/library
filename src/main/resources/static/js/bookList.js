// 搞个值，用于修改表单的赋值
var popForm;
layui.use(['form', 'table', 'layer', 'jquery', 'laydate'], function () {
    var $ = layui.jquery
        , layer = layui.layer
        , form = layui.form
        , laydate = layui.laydate
        , table = layui.table;
    popForm = layui.form;

    // 初始化，表单里的时间组件
    laydate.render({
        elem: '#pubDate',
        type: 'date'
    });

    // 创建数据表格
    var tableIns = table.render({
        elem: '#currentTableId'
        , url: '/book'
        , limits: [8, 16, 20, 25, 50, 100]
        , limit: 8
        , page: true
        , cols: [[
            {type: "checkbox", width: 40, fixed: "left"},
            {field: 'bookId', width: 50, title: 'ID', align: 'center'},
            {field: 'name', title: '书名', align: 'center'},
            {field: 'author', title: '作者', align: 'center'},
            {field: 'publish', title: '出版社', align: 'center'},
            {field: 'isbn', width: 135, title: '标准书号', align: 'center'},
            // {field: 'introduction', title: '简介', minWidth: 150, align: 'center'},
            {field: 'price', width: 75, title: '价格', sort: true, align: 'center'},
            {field: 'pubDate', title: '出版时间', align: 'center'},
            // {field: 'classId', title: '分类号', align: 'center'},
            {field: 'className', title: '分类名称', align: 'center'},
            // {field: 'pic', title: '封面图片', align: 'center'},
            // {field: 'location', title: '位置', align: 'center'},
            {field: 'locationName', title: '位置', align: 'center'},
            // {field: 'status', title: '图书状态', align: 'center'},
            // {field: 'owner', title: '拥有者', align: 'center'},
            {title: '操作', minWidth: 50, templet: '#currentTableBar', fixed: "right", align: "center"}
        ]]
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
    });

    //模糊查询
    form.on("submit(doSerach)", function (data) {
        tableIns.reload({
            url: '/book'
            , where: data.field
            , page: {
                curr: 1
            }
        });
        //阻止表单跳转
        return false;
    });

    // 监听列工具操作
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            editFormIndex = layer.open({
                btn: ['提交', '重置', '取消']
                , title: '编辑用户'
                , area: ['700px', '520px']
                , type: 1
                , id: (new Date()).valueOf()
                , resize: false
                , content: $("#edit-tpl")
                , success: function () {
                }
                , yes: function (index, layero) {
                    $('form[lay-filter="form-edit"]').find('button[lay-submit]').click();
                }
                , btn2: function (index, layero) {
                    $('form[lay-filter="form-edit"]').find('button[type="reset"]').click();
                    // 禁止点击该按钮关闭
                    return false;
                }
                , end: function () {
                    //关闭弹窗，清空表单数据
                    $("#dataForm")[0].reset();
                }
            });
            //调用方法，动态向编辑表单赋值
            setFormValue(data);
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                data = JSON.stringify(data);
                var bookIds = JSON.parse(data)["bookId"];
                // 调用方法进行删除
                deleteById(index, bookIds)
            });
        }
    });

    // 添加页面弹出层索引
    var addFormIndex;
    // 修改页面弹出层索引
    var editFormIndex;

    // 打开添加页面
    $(".data-add-btn").on("click", function () {
        addFormIndex = layer.open({
            btn: ['添加', '重置', '取消']
            , title: '添加图书'
            , area: ['700px', '520px']
            , type: 1
            , resize: false
            , content: $("#edit-tpl")
            , success: function () {
                form.render(null, 'form-edit');
            }
            , yes: function (index, layero) {
                $('form[lay-filter="form-edit"]').find('button[lay-submit]').click();
            }
            , btn2: function (index, layero) {
                $('form[lay-filter="form-edit"]').find('button[type="reset"]').click();
                return false; // 禁止点击该按钮关闭
            }
        });
    });

    // 监听弹出层中的确认事件
    form.on("submit(doSubmit)", function (data) {
        // 修改开关的值on或""
        // 我们要保存1/0而已；
        if (data.field.status === "on") {
            data.field.status = "1";
        } else {
            data.field.status = "0";
        }
        data = JSON.stringify(data.field);
        var bookIds = JSON.parse(data)["bookId"];
        // bookId不为空就执行修改操作
        if (bookIds != null && bookIds !== "") {
            $.ajax({
                url: "/book"
                , type: "PUT"
                , data: data
                , dataType: "json"
                , contentType: "application/json; charset=utf-8"
                , success: function (data) {
                    layer.msg(data.msg, {icon: 6});
                    // 重载表格
                    tableIns.reload();
                    // 关闭修改弹窗
                    layer.close(editFormIndex);
                }
            });
        } else {
            // bookId为空就执行添加操作
            $.ajax({
                url: "/book"
                , type: "POST"
                , data: data
                , dataType: "json"
                , contentType: "application/json; charset=utf-8"
                , success: function (data) {
                    layer.msg(data.msg, {icon: 6});
                    if (data.code === 0) {
                        layer.confirm('是否继续录入？', {
                            btn: ['继续', '取消'] //按钮
                        }, function (index) {
                            $("#dataForm")[0].reset();
                            layer.close(index);
                        }, function () {
                            layer.close(addFormIndex);
                        });
                    }
                    // 重载表格
                    tableIns.reload();
                    $("#dataForm")[0].reset();
                }
            });
        }
        //阻止表单跳转
        return false;
    });

    // 监听多项删除操作
    $(".data-delete-btn").on("click", function () {
        var checkStatus = table.checkStatus('currentTableId');
        var data = checkStatus.data;
        var idList = "";
        // 拼接id呈List
        if (data.length > 0) {
            for (var i = 0; i < data.length; i++) {
                idList += data[i].bookId + ",";// id集
            }
        }
        layer.confirm('真的删除行么', function (index) {
            // 调用方法进行删除
            deleteById(index, idList);
        });
    });

    /**
     * 单行删除和多行删除
     * @param index 确认删除询问框的索引
     * @param idList id集合
     * @param obj
     */
    function deleteById(index, idList) {
        $.ajax({
            url: "/book",
            type: "DELETE",
            data: {'idList': idList},
            dataType: "json",
            success: function (data) {
                if (data == null) {
                    layer.close(index);
                    layer.msg("删除失败", {icon: 5});
                } else {
                    // 重载表格
                    tableIns.reload();
                    //关闭弹框
                    layer.close(index);
                    layer.msg(data.msg, {icon: 6});
                }
            },
            error: function () {
                layer.msg("删除失败", {icon: 5});
            },
        });
    }

    // 调用方法初始化各个下拉列表的数据
    setFormSelect();

    /**
     * 初始化下拉列表的数据
     */
    function setFormSelect() {
        $.get("/classify", function (objs) {
            var edit_classid = $("#edit_classid");
            var search_classid = $("#search_classid");
            $.each(objs.data, function (index, item) {
                edit_classid.append("<option value=" + item.classId + ">" + item.className + "</option>");
                search_classid.append("<option value=" + item.classId + ">" + item.className + "</option>");
            });
            form.render("select");
        });
        $.get("/bookshelf", function (objs) {
            var edit_location = $("#edit_location");
            $.each(objs.data, function (index, item) {
                edit_location.append("<option value=" + item.bsId + ">" + item.name + "</option>");
            });
            form.render("select");
        });
        $.get("/user", function (objs) {
            var edit_ownerid = $("#edit_ownerid");
            $.each(objs.data, function (index, item) {
                edit_ownerid.append("<option value=" + item.useruuid + ">" + item.stuid + "</option>");
            });
            form.render("select");
        });
    }

    /**
     * 动态给修改页面赋值
     * @param data 当前行数据
     */
    function setFormValue(data) {
        if (data.status !== 1) {
            data.status = "";
        }
        popForm.val("form-edit", {
            "bookId": data.bookId
            , "name": data.name
            , "author": data.author
            , "publish": data.publish
            , "isbn": data.isbn
            , "price": data.price
            , "pubDate": data.pubDate
            , "classId": data.classId
            , "location": data.location
            , "status": data.status
            , "owner": data.owner
            , "introduction": data.introduction
            , "pic": data.pic
        });
        popForm.render(null, 'form-edit')
    }
});