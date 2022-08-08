package web.parujeme.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
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
 * @date 2022-08-05 3:27 PM
 */
@Route("user-list")
@CssImport("./styles/shared-styles.css")
public class UserListView extends VerticalLayout {
    private ContactService contactService;

    private Grid<Contact> grid = new Grid<>(Contact.class);
    private TextField filterText = new TextField();
    private ContactForm form;

    public UserListView(ContactService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        form = new ContactForm();
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
//        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void saveContact(ContactForm.SaveEvent event) {
        contactService.save(event.getContact());
//        updateList();
//        closeEditor();
    }
    private void deleteContact(ContactForm.DeleteEvent event) {
        contactService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
//        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email"
//                , "status"
        );
//        grid.addColumn(contact -> {
//            Company company = contact.getCompany();
//            return company == null ? "-" : company.getName();
//        }).setHeader("Company");

        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));
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

    private void updateList() {
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());
        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton
        );
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    void addContact() {
        //pri opakovanem kliknuti form zmizi
//        grid.asSingleSelect().clear();
        editContact(new Contact());
    }
}