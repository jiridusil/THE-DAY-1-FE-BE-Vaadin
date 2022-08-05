package web.parujeme.application.views;

import web.parujeme.application.components.RegisterComponents;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author jdusil
 * @date 2022-08-03 8:46 PM
 */
@Route("registration")
public class RegistrationView extends Composite<VerticalLayout> {

    public RegistrationView(RegisterComponents sharedComponents) {
        VerticalLayout layout = getContent();

        sharedComponents.name.setLabel("Celé jméno");
        sharedComponents.name.setRequiredIndicatorVisible(true);
        sharedComponents.name.setRequired(true);
        sharedComponents.name.setErrorMessage("Toto pole je povinné");

        sharedComponents.email.setLabel("Email");
        sharedComponents.email.setRequiredIndicatorVisible(true);
        sharedComponents.email.setErrorMessage("Toto pole je povinné");

        sharedComponents.date.setLabel("Datum narozeni");

        Button registrationButton = new Button("Vytvořit účet");
        registrationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
        registrationButton.addClickListener(event -> {
            UI.getCurrent().navigate("user-new");
        });
        
        layout.add(new H1("Pojďme na to..."),
                sharedComponents.name, sharedComponents.email,
                sharedComponents.date, registrationButton);
        layout.setSizeFull();
        layout.setAlignItems(FlexLayout.Alignment.CENTER);
        layout.setJustifyContentMode(FlexLayout.JustifyContentMode.CENTER);
    }
}
