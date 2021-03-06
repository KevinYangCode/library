# 图书管理

项目入口：http://localhost:8080/

账号：20170404430140

密码：123456

druid监控面板：http://localhost:8080/druid/index.html

swaggerAPI：http://localhost:8080/swagger-ui.html

## 技术

| 描述         | 框架                | 版本          |
| ------------ | -------------- | ---------- |
| 后台框架   | SpringBoot| 2.2.2.RELEASE |
| mybatis工具 | mybatisplus| 3.3.0 |
|数据库连接池|druid|1.1.20|
|认证权限管理|shiro|1.4.2|
|验证码工具|hutool|5.0.7|
|API管理工具|swagger|2.9.2|
| 前端框架  | Layui  | v2.5.5 |

**环境准备：**

1. JDK1.8+
2. MySQL5.X+
3. Maven3.X+


## 1、图书管理

* 全部图书
* 借出记录

## 2、个人

* 我的借阅
* 我的图书

## 3、系统管理（管理员）
* 图书管理
* 分类管理
* 书架管理
* 用户管理

# 数据库(以sql文件为准)

### 1、图书书目表t_book

| 名           | 类型     | 长度 | 小数点 | NULL | 用途                  | 键   |
| ------------ | -------- | ---- | ------ | ---- | --------------------- | ---- |
| book_id      | int      | 11   | 0      | 否   | 图书ID                | ✔    |
| name         | varchar  | 35   | 0      | 否   | 书名                  |      |
| author       | varchar  | 35   | 0      | 是   | 作者                  |      |
| publish      | varchar  | 35   | 0      | 是   | 出版社                |      |
| isbn         | varchar  | 15   | 0      | 是   | 标准书号              |      |
| introduction | text     | 0    | 0      | 是   | 简介                  |      |
| price        | decimal  | 10   | 2      | 是   | 价格                  |      |
| pub_date     | date     | 0    | 0      | 是   | 出版时间              |      |
| class_id     | int      | 11   | 0      | 否   | 分类号                |      |
| pic          | longtext | 0    | 0      | 是   | 封面图片              |      |
| location     | int      | 11   | 0      | 否   | 位置                  |      |
| status       | int      | 2    | 0      | 否   | 图书状态(1在架/0借出) |      |
| owner        | varchar  | 32   | 0      | 否   | 拥有者(0/1)           |      |

### 2、用户登录表t_user

| 名       | 类型    | 长度 | 小数点 | NULL | 用途                      | 键   |
| -------- | ------- | ---- | ------ | ---- | ------------------------- | ---- |
| userUUID | varchar | 32   | 0      | 否   | 成员ID                    | ✔    |
| stuID    | varchar | 32   | 0      | 否   | 成员学号                  |      |
| password | varchar | 32   | 0      | 否   | 登录密码                  |      |
| role     | int     | 2    | 0      | 否   | 1为超管,2为普管,3没有权限 |      |

### 3、图书分类表t_classify

| 名         | 类型    | 长度 | 小数点 | NULL | 用途       | 键   |
| ---------- | ------- | ---- | ------ | ---- | ---------- | ---- |
| class_id   | int     | 11   | 0      | 否   | 图书类别号 | ✔    |
| class_name | varchar | 32   | 0      | 否   | 图书类别名 |      |

### 4、借阅信息表t_lend

| 名        | 类型    | 长度 | 小数点 | NULL | 用途       | 键   |
| --------- | ------- | ---- | ------ | ---- | ---------- | ---- |
| lend_id   | int     | 11   | 0      | 否   | 借阅记录ID | ✔    |
| book_id   | int     | 11   | 0      | 否   | 图书ID     |      |
| reader_id | varchar | 32   | 0      | 否   | 读者ID     |      |
| lend_date | date    | 0    | 0      | 否   | 借出日期   |      |
| back_date | date    | 0    | 0      | 是   | 归还日期   |      |

### 5、新书采购意向表t_intention
| 名        | 类型     | 长度 | 小数点 | NULL | 用途           | 键   |
| --------- | -------- | ---- | ------ | ---- | -------------- | ---- |
| inte_id   | int      | 11   | 0      | 否   | 新书采购意向ID | ✔    |
| name      | varchar  | 35   | 0      | 否   | 图书名称       |      |
| submitter | varchar  | 32   | 0      | 否   | 提交者         |      |
| ISBN      | varchar  | 15   | 0      | 否   | 标准书号       |      |
| pic       | longtext | 0    | 0      | 是   | 图书图片       |      |

### 6、书架表t_bookshelf
| 名    | 类型    | 长度 | 小数点 | NULL | 用途   | 键   |
| ----- | ------- | ---- | ------ | ---- | ------ | ---- |
| bs_id | int     | 11   | 0      | 否   | 书架ID | ✔    |
| name  | varchar | 35   | 0      | 否   | 名称   |      |

## 没做
- 用户管理
- 还书时弹窗显示应放那一层书架
- 扫码录入书籍（小程序调用微信中的扫一扫功能）([已完成](https://github.com/KevinYangCode/isbnGetInfo))
- ？？处理t_book表的拥有者：工作室/未知/成员
- 页面用户的名字显示


## 已知bug：
   1. 列表删除，第二页删除当页全部数据时，会直接显示无数据，无法跳到第一页去。
   2. 卡片式显示那块，点击详情后进行借阅，强行写的逻辑，因为有些数据不会获取。
   3. tab与tab之间点击，没有刷新数据。


[前端引用大佬的开源项目](https://github.com/zhongshaofa/layuimini)

