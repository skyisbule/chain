package com.github.skyisbule.chain;

import cn.hutool.setting.dialect.Props;
import com.github.skyisbule.chain.exception.ExceptionMessage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.github.skyisbule.chain.*")
@SpringBootApplication
public class SkyApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {

    Props props = new Props("errorMessage.properties");
    props.forEach((key, value) -> {
        ExceptionMessage.message.put(key.toString(),value.toString());
    });

    SpringApplication.run(SkyApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SkyApplication.class);
  }

}
