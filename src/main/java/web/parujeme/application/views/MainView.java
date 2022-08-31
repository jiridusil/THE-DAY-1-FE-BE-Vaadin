package web.parujeme.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import web.parujeme.application.backend.entity.Contact;
import web.parujeme.application.backend.service.ContactService;
import web.parujeme.application.dto.UserData;

/**
 * @author jdusil
 * @date 2022-08-03 3:24 PM
 */

@Route(value = "")
@PageTitle("Login | Parujeme.cz")
public class MainView extends VerticalLayout {

    private final LoginI18n i18 = LoginI18n.createDefault();
//    @Autowired
//    Contact contact;

    public MainView(ContactService contactService, Contact contact, UserData userData) {
        LoginI18n.Form i18Form = i18.getForm();
        i18Form.setTitle("Vítejte");
        i18Form.setUsername("Email");
        i18Form.setPassword("Heslo");
        i18Form.setForgotPassword("Zapomněli jste heslo?");
        i18Form.setSubmit("Přihlásit");
        i18.setForm(i18Form);
//        this.contact = contact;

        LoginForm login = new LoginForm();
        login.setI18n(i18);

        addClassName("login");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Button addUser = new Button("Registrace");
        addUser.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        addUser.addClickListener(event -> {
            UI.getCurrent().navigate("add-user");
        });

        login.addLoginListener(loginEvent -> {
            if (loginEvent.getUsername().equals("admin")) {
                UI.getCurrent().navigate("admin-view");
            } else {
                String email = contactService.searchEmail(loginEvent.getUsername());
                if (email != null) {
                    Contact contactData = contactService.searchContactByEmail(loginEvent.getUsername());
                    contact.setFirstName(contactData.getFirstName());
                    contact.setLastName(contactData.getLastName());
                    contactData.setFirstName(contactData.getFirstName());
                    userData.firstName = contactData.getFirstName();
                    UI.getCurrent().navigate("profile");
                } else {
                    Notification notification = Notification.show("Wrong credentials!");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        });

        add(login, addUser);
    }
}
