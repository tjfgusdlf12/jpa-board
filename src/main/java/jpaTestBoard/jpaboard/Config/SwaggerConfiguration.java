package jpaTestBoard.jpaboard.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

    /**Docket => Swagger 설정의 핵심으로 문서 객체임 API에 대한 내용 및 스펙은 컨트롤러에서 적용**/
    @Bean
    public Docket swaggerApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("jpaTestBoard.jpaboard.Controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("JPA API TEST")
                .description("웹 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                .license("").licenseUrl("").version("1").build();
    }

    /**apiInfo => 제목 설명 등 문서 정보를 위해 호출**/

    /**apis => api에 대한 스펙이 작성되어 있는 패키지를 지정
     *      => 컨트롤러가 존재하는 패키지를 backpackage 로 지정하여 , RequestMapping에 설정된 API 문서화**/

    /**useDefaultResponseMessages는 swagger에서 제공해주는 응답코드에 대한 기본 메세지 사용 여부**/

    /**여기까지 했음 Application을 실행해  https://locahost:8080/swagger-ui.html에 접속해보자.
     * 그러면 로그인 창이 뜨고 Spring Security의 기본설정이 되어있을거임
     * gradle에 springSecurity 부분을 주석 처리하고 실행해보자*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
