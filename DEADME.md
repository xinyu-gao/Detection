## 大学生创新创业项目

智能垃圾清运回收系统。

### 1. springboot 开发



### 2. idea 插件推荐

1. #### Free MyBatis plugin

   mybatis 插件

2. #### Key promoter X

   快捷键提醒

3. #### IDEA Mind Map

   思维导图

4. #### CodeGlance

   代码编辑区迷你缩放插

5. #### Translation

   翻译插件

6. #### Alibaba Java Coding Guidelines

   一款阿里巴巴公司试行的开发设计规范~

7. #### Lombok

   Lombok能通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString方法。

8. #### GsonFormat

   json 格式的字符串转换成实体类参数

9. #### SequenceDiagram

   时序图生成

10. #### Rainbow Brackets

    括号匹配颜色

11. #### Iedis

    redis 可视化工具

### 3. 环境遇到的问题

1. #### Doker 安装

   ##### 安装教程：

   ###### https://yeasy.gitbook.io/docker_practice/install/ubuntu

   ##### 安装容器加速:

   阿里云镜像加速地址获取： https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

   ```bash
   sudo mkdir -p /etc/docker
   sudo tee /etc/docker/daemon.json <<-'EOF'
   {
     "registry-mirrors": ["https://zoza94a9.mirror.aliyuncs.com"]
   }
   EOF
   sudo systemctl daemon-reload
   sudo systemctl restart docker
   ```

   

2. #### redis 安装

   ##### 安装教程：

   https://www.runoob.com/docker/docker-install-redis.html

   

3. #### gitlab 搭建

   需要 2 GB 以上内存。可以增加服务器的 swap 空间：

   ```
   dd if=/dev/zero of=/var/swap bs=1024 count=2048000 #分区大小设为2g
   ls -lh /var/swap  # 验证
   mkswap /var/swap #启动分区
   swapon /var/swap #激活
   ```

   注意防火墙端口号。

### 4. 其他问题

1. jpa 构建数据库时，字段顺序混乱：

   https://blog.csdn.net/xqnode/article/details/102470380