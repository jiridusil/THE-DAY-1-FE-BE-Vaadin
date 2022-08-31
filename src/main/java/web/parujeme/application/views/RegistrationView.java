package web.parujeme.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import web.parujeme.application.backend.entity.Contact;
import web.parujeme.application.backend.service.ContactService;
import web.parujeme.application.dto.UserData;

/**
 * @author jdusil
 * @date 2022-08-05 7:48 PM
 */
@Route("add-user")
public class RegistrationView extends VerticalLayout {
    private ContactService contactService;
    private Contact contact;
    private UserData userData;

    private TextField filterText = new TextField();
    private RegistrationLayout registrationLayout;

    public RegistrationView(ContactService contactService, Contact contact, UserData userData) {
        this.contactService = contactService;
        this.contact = contact;
        this.userData = userData;
        addClassName("list-view");
        setSizeFull();

        registrationLayout = new RegistrationLayout();
        registrationLayout.setContact(new Contact());
        registrationLayout.addListener(RegistrationLayout.SaveEvent.class, this::saveContact);

        add(registrationLayout);
    }

    private void saveContact(RegistrationLayout.SaveEvent event) {
        userData.firstName = event.getContact().getFirstName();
        contact.setFirstName(event.getContact().getFirstName());
        contact.setLastName(event.getContact().getLastName());
        contactService.save(event.getContact());
        Notification notification = Notification.show("Application submitted!");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        UI.getCurrent().navigate("profile");
    }
}