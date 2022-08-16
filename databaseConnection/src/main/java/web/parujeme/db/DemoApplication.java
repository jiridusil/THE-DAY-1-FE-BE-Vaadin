package web.parujeme.db;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        setLoginView(http, LoginView.class);
//    }
//
//    @Override
//    @Bean
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new InMemoryUserDetailsManager(User.withUsername("Jiri").password("Jiri").roles("ADMIN").build()) ;
//    }

}
