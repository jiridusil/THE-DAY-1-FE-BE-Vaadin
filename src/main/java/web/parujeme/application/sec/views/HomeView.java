package web.parujeme.application.sec.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * @author jdusil
 * @date 2022-08-16 4:50 PM
 */
@Route("home")
@AnonymousAllowed
public class HomeView extends VerticalLayout {

    public HomeView() {
        add(new H1 ("Home View"));
    }

}
