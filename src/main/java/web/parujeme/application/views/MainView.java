package web.parujeme.application.views;

import web.parujeme.application.components.RegisterComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * @author jdusil
 * @date 2022-08-03 3:24 PM
 */

@Route(value = "")
@PageTitle("Login | Jiri")
public class MainView extends VerticalLayout {

    private final LoginI18n i18 = LoginI18n.createDefault();

    public MainView(RegisterComponents sharedComponents) {
        LoginI18n.Form i18Form = i18.getForm();
        i18Form.setTitle("Vítejte");
        i18Form.setUsername("Uživatelské jméno");
        i18Form.setPassword("Heslo");
        i18Form.setForgotPassword("Zapomněli jste heslo?");
        i18Form.setSubmit("Přihlásit");
        i18.setForm(i18Form);

        LoginForm login = new LoginForm();
        login.setI18n(i18);

        addClassName("login");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Button registrationButton = new Button("Založit účet");
        registrationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        registrationButton.addClickListener(event -> {
            UI.getCurrent().navigate("registration");
        });

        Button userList = new Button("Seznam uživatelů");
        userList.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        userList.addClickListener(event -> {
            UI.getCurrent().navigate("user-list");
        });

        Button addUser = new Button("Přidej nového uživatele");
        addUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        addUser.addClickListener(event -> {
            UI.getCurrent().navigate("add-user");
        });

        login.addLoginListener(loginEvent -> {
            sharedComponents.userName = loginEvent.getUsername();
            UI.getCurrent().navigate("user-logged");
        });

        add(login, registrationButton, userList, addUser);
    }
}
