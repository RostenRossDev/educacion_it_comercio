package educacionit.comercio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "educacionITUnoEntityManagerFactory",
        transactionManagerRef = "educacionITUnoTransactionManager", basePackages = {"educacionit.comercio.app.repositories.baseuno"})
public class EducacionITUno {

    @Autowired
    private Environment env;

    @Bean(name = "baseUnoDataSource")
    public DataSource baseUnoDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("educacionituno.datasource.url"));
        dataSource.setUsername(env.getProperty("educacionituno.datasource.username"));
        dataSource.setPassword(env.getProperty("educacionituno.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("educacionituno.datasource.driver-class-name"));

        return dataSource;
    }


    @Primary
    @Bean(name = "educacionITUnoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(baseUnoDatasource());
        entityManager.setPackagesToScan("educacionit.comercio.app.entities.baseuno");
        HibernateJpaVendorAdapter ventorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(ventorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hinerate.hbm2ddl.auto", env.getProperty("educacionituno.jpa.hibernate.ddl-auto"));
        properties.put("hinerate.show-sql", env.getProperty("educacionituno.jpa.show-sql"));
        properties.put("hinerate.dialect", env.getProperty("educacionituno.jpa.properties.hibernate.dialect"));

        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Primary
    @Bean(name ="educacionITUnoTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

}
