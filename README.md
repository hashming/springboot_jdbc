
> 网上好多教程都是似是而非好多也下载下来根本就运行不了，所以我摸索并创作了这个demo，此项目可以根据postman进行登录、添加、查询操作
涉及到Mybatis中分页查询的应用。

## 2.教程
### 2.1 

# 用Springboot操作数据库的小项目

## 1.  工具

- postman
- idea
- spring boot（我使用的版本是2.2.4）

## 2.  需求

> 可以用postman配合向数据库的表格中添加相应的数据，并且可以在网页上面显示相应的数据库中的数据

## 3.  步骤：

### 3.1  项目的整体结构图：

![image.png](http://img.wwery.com/hashming/699590131021337.png)

### 3.2  创建项目——具体步骤

首先创建项目：

![image.png](http://img.wwery.com/hashming/3dfa70131021815.png)

下一步：

![image.png](http://img.wwery.com/hashming/dde9c0131021904.png)

**一般只要更改artifact就可以了**

![image.png](http://img.wwery.com/hashming/c4d7d0131022018.png)

我的和网上面的不太一样，这里我把第一个勾选上就等同于，网上的勾选web，然后再勾选相应的组件。

![image.png](http://img.wwery.com/hashming/209a90131022103.png)
![image.png](http://img.wwery.com/hashming/881a00131022156.png)

然后就勾选完成了，给项目取一个名字就完事了

### 3.3  项目中的具体步骤

更改pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.4.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.duoduo.springbootjdbc</groupId>
	<artifactId>springboot_jdbc</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>springboot_jdbc</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.1.1</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<!--<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>-->
		</dependency>

		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.4</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.datatype</groupId>
			<artifactId>jackson-datatype-joda</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.module</groupId>
			<artifactId>jackson-module-parameter-names</artifactId>
		</dependency>
		<!-- 分页插件 -->
		<dependency>
			<groupId>com.github.pagehelper</groupId>
			<artifactId>pagehelper-spring-boot-starter</artifactId>
			<version>1.2.5</version>
		</dependency>
		<!-- alibaba的druid数据库连接池 -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid-spring-boot-starter</artifactId>
			<version>1.1.9</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<!--<version>1.2.4.RELEASE</version>-->
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>


```

> **这里注意springboot是要有parent进行声明的**

创建pojo层：

```java
package com.duoduo.springbootjdbc.springboot_jdbc.pojo;

public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String phone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

```

创建dao层

```java
package com.duoduo.springbootjdbc.springboot_jdbc.dao;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;

import java.util.List;

public interface UserDao {
    //插入方法
    int insert(User record);

    //查找其中的用户数
    List<User> selectUsers();
}


```

创建service层:

```java
package com.duoduo.springbootjdbc.springboot_jdbc.service;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    //插入我们想要插入的用户信息
    int addUser(User user);

    //分页插件 查找所有的用户
    PageInfo<User> findAllUser(int pageNum,int pageSize);

}


```

```java
package com.duoduo.springbootjdbc.springboot_jdbc.service;

import com.duoduo.springbootjdbc.springboot_jdbc.dao.UserDao;
import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {//实现接口

    @Autowired
    private UserDao userDao;//这里爆红但是不需要进行理会

    @Override
    public int addUser(User user) {
        return userDao.insert(user);//插入用户信息
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开始的页数，每一页的现实的数据的条数
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}


```

创建controller层

``` java
package com.duoduo.springbootjdbc.springboot_jdbc.controller;

import com.duoduo.springbootjdbc.springboot_jdbc.pojo.User;
import com.duoduo.springbootjdbc.springboot_jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired//自动配置
    private UserService userService;

    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    /*@GetMapping("/all")
    public Object findAllUser(@RequestParam(name = "pageNum",required = true,defaultValue = "1")int pageNum,@RequestParam(name = "pageSize",required = true,defaultValue = "10")int pageSize){
        return userService.findAllUser(pageNum, page-+Size);
    }*/

    @PostMapping("/all")
    public Object findAllUser(int pageNum,int pageSize){
        return userService.findAllUser(pageNum, pageSize);
    }
}


```

更改application配置文件,改成application.yml（新创建一个application.yml之前的application.properties不删除也没有关系）

```
server:
  port: 8080

spring:
    datasource:
        name: test
        url: jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
        username: root
        password: root
        driver-class-name: com.mysql.jdbc.Driver

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.duoduo.springbootjdbc.springboot_jdbc.pojo




pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params: count=countSql
  returnPageInfo: check
```

更改demoapplication.java

```java
package com.duoduo.springbootjdbc.springboot_jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.duoduo.springbootjdbc.springboot_jdbc.dao")
public class SpringbootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJdbcApplication.class, args);
	}

}

```

在resource的文件夹中增加文件夹mapper,在其下创建配置文件UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.duoduo.springbootjdbc.springboot_jdbc.dao.UserDao" >
    <sql id="BASE_TABLE">
        test
    </sql>

    <sql id="BASE_COLUMN">
        userId,userName,password,phone
    </sql>

    <insert id="insert" parameterType="com.duoduo.springbootjdbc.springboot_jdbc.pojo.User">
        INSERT INTO
        <include refid="BASE_TABLE"/>
        <trim prefix="(" suffix=")" suffixOverrides=",">
            userName,password,
            <if test="phone != null">
                phone,
            </if>
        </trim>
        <trim prefix="VALUES(" suffix=")" suffixOverrides=",">
            #{userName, jdbcType=VARCHAR},#{password, jdbcType=VARCHAR},
            <if test="phone != null">
                #{phone, jdbcType=VARCHAR},
            </if>
        </trim>
    </insert>

    <select id="selectUsers" resultType="com.duoduo.springbootjdbc.springboot_jdbc.pojo.User">
        SELECT
        <include refid="BASE_COLUMN"/>
        FROM
        <include refid="BASE_TABLE"/>
    </select>


</mapper>
```

然后是css的代码和html的代码

```css
body{
    color: red;
}
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>好久不见震哥哥</h1>
</body>
</html>
```

## 4.  项目测试

**启动Demoapplication.java**,然后在postman中输入网址localhost:8080/user/add.在其中添加相关的信息,通过post方式send给数据库完成添加的操作.

![image.png](http://img.wwery.com/hashming/6035f0131021338.png)

然后发现数据库中多出来一行数据

![image.png](http://img.wwery.com/hashming/cbc280131021339.png)

对了数据库的建库sql:

```sql
create table test(userId int primary key auto_increment,userName varchar(20),password varchar(20),phone varchar(20));
```

还有分页查询的测试：
![image.png](http://img.wwery.com/hashming/ff68c0131021338.png)

还有不清楚的欢迎留言😂希望能帮助更多的人！
