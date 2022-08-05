package web.parujeme.application.components;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

/**
 * @author jdusil
 * @date 2022-08-04 9:23 AM
 */
@UIScope
@Component
public class RegisterComponents {

    public String userName;
    public TextField name = new TextField();
    public EmailField email = new EmailField();
    public DatePicker date = new DatePicker();

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }
}
