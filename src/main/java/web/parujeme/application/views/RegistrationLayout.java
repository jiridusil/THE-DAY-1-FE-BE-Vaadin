package web.parujeme.application.views;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import web.parujeme.application.backend.entity.Contact;

/**
 * @author jdusil
 * @date 2022-08-05 3:37 PM
 */
public class RegistrationLayout extends VerticalLayout {
    private Contact contact;

    Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

    TextField firstName = new TextField("Jméno");
    TextField lastName = new TextField("Příjmení");
    EmailField email = new EmailField("Email");
    Button save = new Button("Registrovat");

    public RegistrationLayout() {
        binder.bindInstanceFields(this);

        add(firstName,
                lastName,
                email,
                createButtonsLayout());
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
    private Button createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        save.addClickShortcut(Key.ENTER);
        save.addClickListener(event -> validateAndSave());

        return save;
    }

    private void validateAndSave() {
        try {
            binder.writeBean(contact);
            fireEvent(new SaveEvent(this, contact));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        binder.readBean(contact);
    }

    public static abstract class ContactFormEvent extends ComponentEvent<RegistrationLayout> {
        private Contact contact;
        protected ContactFormEvent(RegistrationLayout source, Contact contact) {
            super(source, false);
            this.contact = contact;
        }
        public Contact getContact() {
            return contact;
        }
    }
    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(RegistrationLayout source, Contact contact) {
            super(source, contact);
        }
    }
    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(RegistrationLayout source, Contact contact) {
            super(source, contact);
        }
    }
    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(RegistrationLayout source) {
            super(source, null);
        }
    }
    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
