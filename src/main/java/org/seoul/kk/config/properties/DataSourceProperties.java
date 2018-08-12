package org.seoul.kk.config.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "bbkk.datasource")
@PropertySource(value = { "classpath:/db/db.properties" })
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int initialSize;
    private int maxTotal;
    private int maxIdle;
    private int minIdle;
    private long maxWait;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private String validateQuery;
    private long evictTimeBetweenEvictionRunsMillis;
    private int evictNumTestsPerEvictionRun;
    private long evictMinEvictableIdleTimeMillis;

}
