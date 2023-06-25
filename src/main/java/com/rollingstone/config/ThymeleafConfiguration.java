package com.rollingstone.config;

//import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
// import org.thymeleaf.spring6.SpringTemplateEngine;

@Configuration
public class ThymeleafConfiguration {
   /* @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    } */

    @Bean
    public IDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }
}
