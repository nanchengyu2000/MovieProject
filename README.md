# 项目改造

## 后端代码迭代

### 技术栈的修改

- 后端原来使用的技术栈是SSM，现在将使用SpringBoot+mybatis+OAuth2.0+springsecurity(SSO)+Redis

### 其中功能的修改

- 管理员登录没有存储啥，刷新后数据丢失【token】
- 做好密码加密存储
- 找到存储用户头像和电影信息的文件夹
- 将电影存储在项目位置，不要使用绝对路径了
- 为了减少存储空间，需要在切片后将源文件mp4进行一个删除
- 用户界面中的头像部分改成今天的
- 如果将接片设置为一个异步，那么需要将切片是否成功的消息需要传给管理员
- 并且要设计一个切片队列

### 其中功能的增加

- OAuth2.0授权
- springSecurity实现接口权限控制和单点登录
- Redis实现电影排行榜和用户是否在线



在代码修改的过程当中根据场景实现设计模式，将代码设计为可扩展性

## 前端代码迭代

- 更改用户页面中的加载
- 前端的后端接口可以采用环境变量来存储
- 做好导航守卫
- 大文件上传的优化

## 移动端代码迭代

- 将移动端的代码完善