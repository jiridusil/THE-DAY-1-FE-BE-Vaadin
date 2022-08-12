package web.parujeme.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import web.parujeme.application.dto.ChatMessage;
import web.parujeme.application.dto.UserData;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author jdusil
 * @date 2022-08-03 4:31 PM
 */
@Route("user-new")
@Scope(SCOPE_PROTOTYPE)
@CssImport("./styles/shared-styles.css")
public class WelcomeNewUserView extends VerticalLayout {

    UserData userData;
    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;

    public WelcomeNewUserView(UserData userData, UnicastProcessor<ChatMessage> publisher,
                              Flux<ChatMessage> messages) {
        this.publisher = publisher;
        this.messages = messages;
        this.userData = userData;
        addClassName("list-view");
        setSizeFull();
        Button backToLogin = new Button("Logout");
        backToLogin.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        backToLogin.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("");
        });

        add(backToLogin);
        goToChat(userData);
    }

    private void goToChat(UserData userData) {
        VerticalLayout layout = new VerticalLayout();
//        Button startChat = new Button("Začít chatovat");
        Icon startChat = new Icon(VaadinIcon.CHAT);
        startChat.addClickListener(click -> {
            remove(layout);
            showChat(userData);
        });

        layout.add(new H1("Vytvořen nový profil pro uživatele: "
                + userData.firstNameString + " "
                + userData.lastNameString), startChat);

        add(layout);
    }

    private void showChat(UserData userData) {
        MessageListek messageListek = new MessageListek();

        add(messageListek, createInputLayout(userData));
        expand(messageListek);

        messages.subscribe(message -> {
            getUI().ifPresent(ui -> ui.access(() ->
                    messageListek.add(new Paragraph(
                            //todo: change userData.firstNameString to the name from ChatMessage
                            userData.firstNameString + " " + message.getTime().getHour() + ":"+ message.getTime().getMinute() + ": " + message.getMessage()))
            ));
        });
    }

    private Component createInputLayout(UserData userData) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        TextField messageField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        sendButton.addClickShortcut(Key.ENTER);

        layout.add(messageField, sendButton);
        layout.expand(messageField);

        sendButton.addClickListener(click -> {
            //todo: change userData.userName to the name from ChatMessage
            publisher.onNext(new ChatMessage(userData.userName, messageField.getValue()));
            messageField.clear();
            messageField.focus();
        });
        messageField.focus();

        return layout;
    }
}
