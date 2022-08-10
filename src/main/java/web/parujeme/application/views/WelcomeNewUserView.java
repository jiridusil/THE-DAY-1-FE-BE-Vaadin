package web.parujeme.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import web.parujeme.application.dto.UserData;
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

    public WelcomeNewUserView(UserData userData) {
        Button backToLogin = new Button("Logout");
        backToLogin.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backToLogin.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("");
        });
        add(backToLogin, new H1("Vytvořen nový profil pro uživatele: "
                + userData.firstNameString + " "
                + userData.lastNameString));
    }
}
