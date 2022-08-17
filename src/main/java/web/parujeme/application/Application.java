package web.parujeme.application;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import web.parujeme.application.dto.ChatMessage;
import web.parujeme.application.sec.views.LoginView;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = "parujeme")
@PWA(name = "Parujeme.cz", shortName = "Parujeme.cz", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Push
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    VaadinWebSecurityConfigurerAdapter vaadinSecAdapter = new VaadinWebSecurityConfigurerAdapter() {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            setLoginView(http, LoginView.class);
        }
        @Override
        @Bean
        public UserDetailsService userDetailsServiceBean() throws Exception {
            return new InMemoryUserDetailsManager(User.withUsername("Jiri")
                    .password("Jiri")
                    .roles("ADMIN")
                    .build());
        }
    };

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    UnicastProcessor<ChatMessage> publisher() {
        return UnicastProcessor.create();
    }

    @Bean
    Flux<ChatMessage> messages(UnicastProcessor<ChatMessage> publisher) {
        return publisher.replay(30).autoConnect();
    }


}
