# lms
一个简单的图书管理系统
按照老式图书室逻辑，一切由管理员主导，需要找管理员成为用户。然后需要找到管理员借书还书。
管理员简单分为两种，root和admin。
## 
开发使用openjdk8，利用springboot 快速启动，使用spring security+jwt作为 用户token。
ehcache作为缓存保存token。运行时会自动配置h2database，使用spring自带的日志系统。

## 运行 
下载jar 包后
java -Dfile.encoding=GBK -jar 目录/lms-0.0.1-SNAPSHOT.jar

## 说明
并没完善好前端展示页面，所以要靠http://localhost:8080/swagger-ui.html查看接口
但一开始有计划做所以保留了security默认的登录界面，登录后可以得到token，
配合http://localhost:8080/h2-console/查看数据库，查看数据变化
连接数据库的url=jdbc:h2:mem:lms 账号sa 密码sa 
token有效期是3min，设置在jwtUtil中。

## 需要改进
目前想到的有下面几个
1.最好完成一下前端 
2.token是无状态的，所以设计一个注销问题
3.把分页加上
