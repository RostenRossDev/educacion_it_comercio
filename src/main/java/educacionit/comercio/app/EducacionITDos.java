package educacionit.comercio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "educacionITDosEntityManagerFactory",
transactionManagerRef = "educacionITDosTransactionManager", basePackages = {"educacionit.comercio.app.repositories.basedos"})
public class EducacionITDos {

    @Autowired
    private Environment env;

    @Bean(name = "baseDosDataSource")
    public DataSource baseUnoDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("educacionitdos.datasource.url"));
        dataSource.setUsername(env.getProperty("educacionitdos.datasource.username"));
        dataSource.setPassword(env.getProperty("educacionitdos.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("educacionitdos.datasource.driver-class-name"));

        return dataSource;
    }

    @Bean("educacionITDosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(baseUnoDatasource());
        entityManager.setPackagesToScan("educacionit.comercio.app.entities.basedos");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hinerate.hbm2ddl.auto", env.getProperty("educacionitdos.jpa.hibernate.ddl-auto"));
        properties.put("hinerate.show-sql", env.getProperty("educacionitdos.jpa.show-sql"));
        properties.put("hinerate.dialect", env.getProperty("educacionitdos.jpa.properties.hibernate.dialect"));

        entityManager.setJpaPropertyMap(properties);

        return entityManager;
    }

    @Bean(name = "educacionITDosTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}