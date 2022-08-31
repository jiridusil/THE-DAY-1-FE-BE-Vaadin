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
import web.parujeme.application.backend.entity.Contact;
import web.parujeme.application.dto.ChatMessage;
import web.parujeme.application.dto.UserData;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author jdusil
 * @date 2022-08-03 4:31 PM
 */
@Route("profile")
@Scope(SCOPE_PROTOTYPE)
@CssImport("./styles/shared-styles.css")
public class ProfileView extends VerticalLayout {

    UserData userData;
    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;
    private Contact contact;

    public ProfileView(UserData userData, UnicastProcessor<ChatMessage> publisher,
                       Flux<ChatMessage> messages, Contact contact1) {
        this.publisher = publisher;
        this.messages = messages;
        this.userData = userData;
        contact = contact1;
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
        Icon startChat = new Icon(VaadinIcon.CHAT);
        startChat.addClickListener(click -> {
            remove(layout);
            showChat(userData);
        });

        layout.add(new H1("Profil uživatele: "
                + contact.getFirstName() + " "
                + contact.getLastName()), startChat);

        add(layout);
    }

    private void showChat(UserData userData) {
        MessageList messageList = new MessageList();
        add(messageList, createInputLayout(userData));
        expand(messageList);

        messages.subscribe(message -> {
            getUI().ifPresent(ui -> ui.access(() ->
                    messageList.add(new Paragraph(
                            message.getFrom() + " " + message.getTime().getHour() + ":"+ message.getTime().getMinute() + ": " + message.getMessage()))
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
            publisher.onNext(new ChatMessage(userData.firstName, messageField.getValue()));
            messageField.clear();
            messageField.focus();
        });
        messageField.focus();

        return layout;
    }
}
