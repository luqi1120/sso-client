package com.ohaotian.ssoclientrest.dao;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created: luQi
 * Date:2018-5-8 9:56
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {

    private String name;
    private Integer age;
    private Boolean boss;
}
