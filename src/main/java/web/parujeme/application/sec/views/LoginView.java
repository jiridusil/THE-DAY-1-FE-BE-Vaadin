package web.parujeme.application.sec.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;

/**
 * author: jiri dusil 8/16/2022
 */
@Route("secure-login")
public class LoginView extends Composite<LoginOverlay> {
    public LoginView() {
        getContent().setOpened(true);
        getContent().setAction("login");
    }
}
