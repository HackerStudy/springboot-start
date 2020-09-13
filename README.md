# springboot学习研究
## 项目的目录结构
```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─hackerstudy
│  │  │          └─adminclient
│  │  │              ├─aspect
│  │  │              ├─common
│  │  │              ├─component
│  │  │              ├─config
│  │  │              ├─controller
│  │  │              ├─dao
│  │  │              ├─enums
│  │  │              ├─exception
│  │  │              ├─HandlerInterceptor
│  │  │              ├─pojo
│  │  │              ├─service
│  │  │              │  └─impl
│  │  │              ├─tasks
│  │  │              ├─utils
│  │  │              ├─vo
│  │  │              └─websocket
│  │  └─resources
│  │      ├─i18n
│  │      ├─mapper
│  │      ├─sql
│  │      ├─static
│  │      └─templates
│  │        
│  └─test
```
## 项目的功能介绍
+ springboot基本配置
+ 热部署
+ jackson的基本演绎法
+ 自定义json响应结构
+ server服务端和server-tomcat的相关配置
+ 读取资源文件
+ 整合FreeMarker
+ 整合Thymeleaf
+ 常用的Thymeleaf标签
+ 统一异常处理
+ 统一错误码页面处理
+ mybatis逆向工程（generatorConfig）
+ mybatis的crud和mybatis的example的使用
+ springboot的事务管理
+ Springboot集成redis（jedis）- 基于RedisTemplate<String,Object>的RedisConfig的redis配置类
+ 基于StringRedisTemplate的redis工具类
+ springboot整合定时任务+异步任务
+ SpringbootAOP
+ Springboot拦截器
+ springboot的自定义配置类
+ yml语法和properties语法
+ Springboot集成websocket实现即时通讯
+ 不同级别的日志输出
+ 定时任务的不同实现
+ logback日志输出
+ 国际化
+ 集成swagger2
+ 多线程文件下载 
+ springboot整合webservice 
+ springboot修改图标
+ 集成logback
+ 升级成2.0
+ 修改统一配置的yml
+ 多文件打包配置，完美打包
+ 打包脚本和启动脚本设置
+ springboot整合security
+ springboot整合mail
+ springboot整合springbootAdmin