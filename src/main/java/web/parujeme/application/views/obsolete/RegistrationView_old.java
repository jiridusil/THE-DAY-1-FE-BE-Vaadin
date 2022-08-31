package web.parujeme.application.views.obsolete;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author jdusil
 * @date 2022-08-03 8:46 PM
 */
@Route("registration")
public class RegistrationView_old extends Composite<VerticalLayout> {

//    public RegistrationViewOld(UserData userData) {
//        VerticalLayout layout = getContent();
//
//        userData.firstName.setLabel("Celé jméno");
//        userData.firstName.setRequiredIndicatorVisible(true);
//        userData.firstName.setRequired(true);
//        userData.firstName.setErrorMessage("Toto pole je povinné");
//
//        userData.email.setLabel("Email");
//        userData.email.setRequiredIndicatorVisible(true);
//        userData.email.setErrorMessage("Toto pole je povinné");
//
//        userData.date.setLabel("Datum narozeni");
//
//        Button registrationButton = new Button("Vytvořit účet");
//        registrationButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
//        registrationButton.addClickListener(event -> {
//            UI.getCurrent().navigate("user-new");
//        });
//
//        layout.add(new H1("Pojďme na to..."),
//                userData.firstName, userData.email,
//                userData.date, registrationButton);
//        layout.setSizeFull();
//        layout.setAlignItems(FlexLayout.Alignment.CENTER);
//        layout.setJustifyContentMode(FlexLayout.JustifyContentMode.CENTER);
//    }
}
