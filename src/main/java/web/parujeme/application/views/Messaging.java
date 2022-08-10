package web.parujeme.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import web.parujeme.application.dto.UserData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jdusil
 * @date 2022-08-10 4:07 PM
 */
@Route("messaging")
public class Messaging extends Div {

    UserData userData;

    public Messaging(UserData userData) {
        this.userData = userData;
//        MessageInput input = new MessageInput();
//        input.addSubmitListener(submitEvent -> {
//            Notification.show("Message received: " + submitEvent.getValue(),
//                    3000, Notification.Position.MIDDLE);
//        });
//        add(input);
        MessageList list = new MessageList();
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            MessageListItem newMessage = new MessageListItem(
                    submitEvent.getValue(), Instant.now(), userData.firstNameString);
            newMessage.setUserColorIndex(3);
            List<MessageListItem> items = new ArrayList<>(list.getItems());
            items.add(newMessage);
            list.setItems(items);
        });

//        Person person = DataService.getPeople(1).get(0);
//        MessageListItem message1 = new MessageListItem(
//                "Nature does not hurry, yet everything gets accomplished.",
//                LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC),
//                "Matt Mambo");
//        message1.setUserColorIndex(1);
//        MessageListItem message2 = new MessageListItem(
//                "Using your talent, hobby or profession in a way that makes you contribute with something good to this world is truly the way to go.",
//                LocalDateTime.now().minusMinutes(55).toInstant(ZoneOffset.UTC),
//                "Linsey Listy");
//        message2.setUserColorIndex(2);
//        list.setItems(message1, message2);

        VerticalLayout chatLayout = new VerticalLayout(list, input);
        chatLayout.setHeight("100%");
        chatLayout.setWidth("100%");
        chatLayout.expand(list);
        add(chatLayout);
    }

}
