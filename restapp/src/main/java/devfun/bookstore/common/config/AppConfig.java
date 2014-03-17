package devfun.bookstore.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableTransactionManagement
@MapperScan("devfun.bookstore.common.mapper")
@Configuration
@ComponentScan(basePackages = {"devfun.bookstore.common.service"}, 
useDefaultFilters = false, includeFilters = {@Filter(Service.class)})
public class AppConfig implements TransactionManagementConfigurer{

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setName("testDB")
			.setType(EmbeddedDatabaseType.HSQL)
			.addScript("schema.sql")
			.addScript("data.sql")
			.build();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}

	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager(); // reference the existing @Bean method
										// above
	}

}
