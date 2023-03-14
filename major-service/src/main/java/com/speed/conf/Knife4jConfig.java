package com.speed.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 在线文档配置
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    private static Boolean ENABLE = true;
    private static String BASE_PACKAGE="com.speed";

    @Bean
    public Docket apiOrg() {
        return buildDocket("组织架构","/org/**");
    }
    @Bean
    public Docket apiSys() {
        return buildDocket("系统配置","/sys/**");
    }
//    @Bean
//    public Docket apiAuth() {
//        return buildDocket("授权管理","/auth/**");
//    }
//    @Bean
//    public Docket apiFlowable() {
//        return buildDocket("流程管理","/flowable/**");
//    }
    @Bean
    public Docket apiPrj() {
        return buildDocket("项目管理","/prj/**");
    }



    @Bean
    public Docket apiAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(ENABLE)
                .apiInfo(apiInfo())
                .groupName("全部")
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        Contact c = new Contact("Joey","url","1039192237@qq.com");
        return new ApiInfoBuilder()
                .title("服务接口文档")
                .contact(c)
                .version("1.0")
                .build();
    }
    private Docket buildDocket(String groupName,String path){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(ENABLE)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant(path))
                .build();
    }
}
