package per.web.config.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * BasicConfig
 *
 * @author Wilson
 * @date 18-7-11
 */
@Configuration
public class CommonBaseConfig {

    /**
     * 配置参数校验器
     * @param messageSource
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(
        ReloadableResourceBundleMessageSource messageSource) {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);
        factoryBean.setProviderClass(HibernateValidator.class);
        return factoryBean;
    }


    /**
     * 配置校验返回信息
     * @return
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:ValidationMessages");
        source.setUseCodeAsDefaultMessage(false);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
