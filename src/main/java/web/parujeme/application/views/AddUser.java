package web.parujeme.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import web.parujeme.application.backend.entity.Company;
import web.parujeme.application.backend.entity.Contact;
import web.parujeme.application.backend.service.CompanyService;
import web.parujeme.application.backend.service.ContactService;

/**
 * @author jdusil
 * @date 2022-08-05 7:48 PM
 */
@Route("add-user")
public class AddUser extends VerticalLayout {
    private ContactService contactService;
    private Contact contact;

    private TextField filterText = new TextField();
    private ContactForm form;

    public AddUser(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        form = new ContactForm();
        form.setContact(new Contact());
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
//        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
    }

    private void saveContact(ContactForm.SaveEvent event) {
        contactService.save(event.getContact());
//        updateList();
    }


    public void editContact(Contact contact) {
        if (contact == null) {
            closeEditor();
        } else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

//    private void updateList() {
//        grid.setItems(contactService.findAll(filterText.getValue()));
//    }

    private HorizontalLayout getToolbar() {
        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton
        );
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addContact() {
        editContact(new Contact());
    }
}