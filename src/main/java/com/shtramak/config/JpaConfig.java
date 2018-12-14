package com.shtramak.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories("com.shtramak.repository")
@PropertySource("classpath:application.properties")
public class JpaConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${db.showSql}")
    private boolean showSql;
    @Value("${db.ddl}")
    private boolean generateDdl;
    @Value("${db.driver}")
    private String driverClassName;
    @Value("${db.maxPoolSize}")
    private Integer maxPoolSize;

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean("dataSource")
    @Profile("prod")
    public DataSource pooledDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setPoolName("springHikariCP");
        dataSource.setConnectionTestQuery("SELECT 1");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(showSql);
        vendorAdapter.setGenerateDdl(generateDdl);
        return vendorAdapter;
    }

    @Bean("entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.shtramak.entity");
        return entityManagerFactoryBean;
    }
}

// todo Can I somehow set up sql_format property to HibernateJpaVendorAdapter?

// todo I haven't set mapped by and that produces additional table like for many to many relation... When I fix this and run application again - the database tables was not changed... Is there an option to provide smth like create-drop for jpaVendorAdapter?
