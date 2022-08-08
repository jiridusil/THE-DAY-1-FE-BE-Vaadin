package web.parujeme.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import web.parujeme.application.dto.UserData;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author jdusil
 * @date 2022-08-03 4:31 PM
 */
@Route("user-logged")
@Scope(SCOPE_PROTOTYPE)
public class WelcomeLoggedUserView extends VerticalLayout {

    public WelcomeLoggedUserView(UserData userData) {
        add(new H1("Přihlášený uživatel: " + userData.userName));
    }
}
