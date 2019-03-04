package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.dao"}, sqlSessionTemplateRef = "userSqlSessionTemplate")
public class TestConfig {
    @Bean(name="dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name="userSqlSessionFactory")
    @Primary
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try{
            bean.setMapperLocations(resolver.getResources("classpath*:com/example/demo/dao/mapping/*.xml"));
            return bean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    @Primary
    public SqlSessionTemplate userSqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }

}
