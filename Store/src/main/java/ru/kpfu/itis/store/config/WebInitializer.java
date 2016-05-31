package ru.kpfu.itis.store.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Dao and other root components. Parent container that can't get components
   * from child.
   *
   * @return
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{RootConfig.class, SecurityConfig.class};
  }

  /**
   * Controllers and other web components. Child container that can get 
   * components from parent.
   *
   * @return
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebConfig.class};
  }

  /**
   * Register paths to handle.
   *
   * @return
   */
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }



}
