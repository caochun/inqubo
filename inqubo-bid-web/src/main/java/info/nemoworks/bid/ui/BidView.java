package info.nemoworks.bid.ui;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Route;
import info.nemoworks.bid.model.Bid;

import java.lang.reflect.Field;

@Route("")
public class BidView extends VerticalLayout {

    private BeanValidationBinder<Bid> binder =  new BeanValidationBinder<Bid>(Bid.class);


    public BidView() {
        FormLayout formLayout = new FormLayout();

        Field[] fields = Bid.class.getDeclaredFields();

        for (Field field:
             fields) {
            if (field.getType().getName().startsWith("java")|| field.getType().getName().equals("boolean")){
                TextField textField = new TextField(field.getName());
                binder.forField(textField).bind(field.getName());
                formLayout.add(textField);
            }else{

                for (Field f:field.getDeclaringClass().getDeclaredFields()
                     ) {
                    TextField textField = new TextField(f.getName());
                    binder.forField(textField).bind(f.getName());
                    formLayout.add(textField);
                } ;

            }


        }

        // Restrict maximum width and center on page
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");

        // Allow the form layout to be responsive. On device widths 0-490px we have one
        // column, then we have two. Field labels are always on top of the fields.
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        add(formLayout);

    }
}
