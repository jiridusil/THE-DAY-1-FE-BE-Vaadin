package web.parujeme.application.views;

import web.parujeme.application.components.RegisterComponents;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author jdusil
 * @date 2022-08-03 4:31 PM
 */
@Route("user-new")
@Scope(SCOPE_PROTOTYPE)
public class WelcomeNewUserView extends VerticalLayout {

    public WelcomeNewUserView(RegisterComponents sharedComponents) {
        add(new H1("Vytvořen nový profil pro: " + sharedComponents.name.getValue()));
    }
}
