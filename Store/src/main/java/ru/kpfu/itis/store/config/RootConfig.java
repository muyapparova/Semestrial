package ru.kpfu.itis.store.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.kpfu.itis.store.repositories")
@PropertySource("classpath:app.properties")
public class RootConfig {

  @Resource
  private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
    dataSource.setUrl(env.getRequiredProperty("db.url"));
    dataSource.setUsername(env.getRequiredProperty("db.username"));
    dataSource.setPassword(env.getRequiredProperty("db.password"));

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty("entitymanager.packages.to.scan"));

    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(true);
    entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

    return entityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }


  @Bean
  public ru.kpfu.itis.store.utils.PropertyPath userHome(){
    ru.kpfu.itis.store.utils.PropertyPath path = new ru.kpfu.itis.store.utils.PropertyPath();
    path.setPath(env.getRequiredProperty("img.dir"));
    return path;
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
    properties.put("javax.persistence.validation.mode", env.getRequiredProperty("javax.persistence.validation.mode"));
    //properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
    properties.put("spring.jpa.hibernate.ddl-auto", env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));

    return properties;
  }

}
