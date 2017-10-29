package com.katsman.solanteq;

import com.katsman.solanteq.aspect.LoggingAnnotatedMethodsAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Alexey Katsman
 * @since 29.10.17
 */

@ContextConfiguration
@EnableAspectJAutoProxy
public class TestConfiguration {

    @Bean
    public LoggedClassTestBean loggedClassTestBean() {
        return new LoggedClassTestBean();
    }

    @Bean
    public LoggedMethodsTestBean loggedMethodsTestBean() {
        return new LoggedMethodsTestBean();
    }

    @Bean
    public LoggingAnnotatedMethodsAspect aspect() {
        return new LoggingAnnotatedMethodsAspect();
    }
}
