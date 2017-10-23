package com.katsman.solanteq.test;

import com.katsman.solanteq.aspect.LoggingAnnotatedMethodsAspect;
import com.katsman.solanteq.test.bean.LoggedClassTestBean;
import com.katsman.solanteq.test.bean.LoggedMethodsTestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Alexey Katsman
 * @since 23.10.17
 */

@Configuration
@EnableAspectJAutoProxy
public class ContextConfiguration {

    @Bean
    public LoggedClassTestBean loggedClassTestBean() {
        return new LoggedClassTestBean();
    }

    @Bean LoggedMethodsTestBean loggedMethodsTestBean() {
        return new LoggedMethodsTestBean();
    }

    @Bean
    public LoggingAnnotatedMethodsAspect aspect() {
        return new LoggingAnnotatedMethodsAspect();
    }
}
