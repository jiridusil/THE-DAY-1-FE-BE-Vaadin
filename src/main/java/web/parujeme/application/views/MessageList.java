package web.parujeme.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

/**
 * @author jdusil
 * @date 2022-08-09 5:22 PM
 */

public class MessageList extends Div {
    public MessageList() {
        addClassName("message-list");
    }

    @Override
    public void add(Component... components) {
        super.add(components);
        components[components.length - 1].getElement().callFunction("scrollIntoView");
    }
}
