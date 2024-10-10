package com.readyent.readyx.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.readyent.readyx.mapper") // 매퍼가 위치한 패키지 경로 설정
public class MybatisDbConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://database-dev.chqweg66ns7c.ap-northeast-2.rds.amazonaws.com:3306/readyx?serverTimezone=Asia/Seoul");
        dataSource.setUsername("root");
        dataSource.setPassword("Tg8#Lm9$Xp1&Nv2!");

//        dataSource.setUrl("jdbc:mysql://localhost:3306/readyx?serverTimezone=Asia/Seoul");
//        dataSource.setUsername("root");
//        dataSource.setPassword("Android4.03");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // MyBatis Mapper XML 경로 설정
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

        // MyBatis 설정 추가
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        // 언더스코어를 카멜 케이스로 자동 변환
        mybatisConfig.setMapUnderscoreToCamelCase(true);
        // MyBatis에 log-impl 설정 (LOG4J2 사용)
        mybatisConfig.setLogImpl(org.apache.ibatis.logging.log4j2.Log4j2Impl.class);

        sessionFactory.setConfiguration(mybatisConfig);

        return sessionFactory.getObject();
    }
}
