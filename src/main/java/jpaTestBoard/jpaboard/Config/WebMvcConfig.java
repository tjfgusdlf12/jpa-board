package jpaTestBoard.jpaboard.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver(); //sorting에 대한 설정을 수정하는 Resolver
        sortArgumentResolver.setSortParameter("sortBy");
        sortArgumentResolver.setPropertyDelimiter("-");

        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver); //Paging에 대한 설정을 수정하는 Resolver
        pageableArgumentResolver.setOneIndexedParameters(true); //page 기본값을 1로 설정한다.
        pageableArgumentResolver.setMaxPageSize(500); //paging 요청에 대해 한 번에 많은 갯수를 요청시 Control 해줌
        pageableArgumentResolver.setFallbackPageable(PageRequest.of(0, 10)); //페이징 정보를 수정할 수 있음 Defailt는 0~20
        argumentResolvers.add(pageableArgumentResolver);
    }
}
