package ru.kpfu.itis.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ru.kpfu.itis.store.models.UserAuthority;
import ru.kpfu.itis.store.utils.StringToEntityConverter;

import javax.validation.Validator;
import java.util.List;

@Configuration
@ComponentScan("ru.kpfu.itis.store.controllers")
@PropertySource("classpath:app.properties")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public UrlBasedViewResolver setupViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
    resolver.setDefaultEncoding("utf-8");
    resolver.setMaxUploadSize(1000000);
    return resolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/assets/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/assets/css/");
    registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
    registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/assets/img/");
    registry.addResourceHandler("/images/**").addResourceLocations("file:${img.dir}");
    registry.addResourceHandler("/resources.hellogwt/**").addResourceLocations("/resources.hellogwt/").setCachePeriod(31556926);
  }



  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/403").setViewName("error/access_denied");
    registry.addViewController("/about").setViewName("static/about");
  }
  
  @Bean
  public Validator validator(){
    return new LocalValidatorFactoryBean();
  }

  @Override
  public void addFormatters(FormatterRegistry formatterRegistry) {
    //formatterRegistry.addConverter(publishingHouseGenericConverter());
    formatterRegistry.addConverter(userAuthorityGenericConverter());
  }
/*
  @Bean
  public StringToEntityConverter publishingHouseGenericConverter(){
    return new StringToEntityConverter(PublishingHouse.class);
  }*/
  @Bean
  public StringToEntityConverter userAuthorityGenericConverter(){
    return new StringToEntityConverter(UserAuthority.class);
  }
}
