1、 需要模糊查询的字段注释加 like  【可选】


2、 需要逻辑删除的 在字段注释加 logic [可选]

3、 application.yml中配置：
    # 下换线转驼峰
    mybatis:
        configuration:
            mapUnderscoreToCamelCase: true
            log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

4、 如果存在多个主键，请把本表的主键放在第一个顺序

5、 分页及thymeleaf（提供复制）
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-thymeleaf</artifactId>
          </dependency>
          <dependency>
              <groupId>com.github.pagehelper</groupId>
              <artifactId>pagehelper-spring-boot-starter</artifactId>
              <version>1.2.3</version>
          </dependency>

6、如果使用逆向工程 插件
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <configurationFile>src/main/resources/generate/generatorConfig.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
            </plugin>