package com.fawvw.ms.voucher.basedao.config;


import com.fawvw.ms.oa.core.rwsplitting.MsRWSplittingMybatisConfiguration;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;


/**
 * @ClassName DataSourceConfiguration
 * @Description DataSourceConfiguration
 * @Author seanli
 * @Date 2019-07-01 15:11
 * @Version 1.0
 **/
@Configuration
@Slf4j
@MapperScan("com.fawvw.ms.voucher.**.mapper")
public class DataSourceConfiguration extends MsRWSplittingMybatisConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    public DataSourceConfiguration(MybatisProperties properties,
        ObjectProvider<Interceptor[]> interceptorsProvider,
        ResourceLoader resourceLoader,
        ObjectProvider<DatabaseIdProvider> databaseIdProvider,
        ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }


    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {
        log.info("-------------------- writeDataSource init ---------------------");
        DataSource writeDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        super.setWriteDataSource(writeDataSource);
        return writeDataSource;
    }

    /**
     * 有多少个从库就要配置多少个
     *
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.slave1")
    public DataSource readDataSourceOne() {
        log.info("-------------------- readDataSourceOne init ---------------------");
        DataSource readDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        super.addReadDataSource(readDataSource);
        return readDataSource;
    }



}
