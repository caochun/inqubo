package info.nemoworks.bid.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.model.Content;
import info.nemoworks.bid.process.BidProcess;
import info.nemoworks.inqubo.Command;
import info.nemoworks.inqubo.Task;
import org.apache.commons.scxml2.model.ModelException;
import info.nemoworks.bid.model.Addon;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Route("")
public class BidView extends VerticalLayout {

    Bid bid = new Bid();

    private BeanValidationBinder<Bid> binder =  new BeanValidationBinder<Bid>(Bid.class);

    List<String> options=new ArrayList<>();

    BidProcess bidProcess;
    Task t;
    List<Command<Bid>> commands;
    String commandStr="";

    private BeanValidationBinder<Addon> addonBinder =  new BeanValidationBinder<Addon>(Addon.class);

    List<Addon> addons=new ArrayList<>();

    private void constructField(FormLayout formLayout,Field field, String path){
        if(field.getType().getName().equals("java.lang.String")){
            TextField textField = new TextField(field.getName());
            binder.forField(textField).bind(path+field.getName());
            formLayout.add(textField);
        }else if(field.getType().getName().equals("boolean")){
            Checkbox checkbox=new Checkbox(field.getName());
            binder.forField(checkbox).bind(path+field.getName());
            formLayout.add(checkbox);
        }else if(field.getType().getName().equals("info.nemoworks.bid.model.Content")){
            Field[] fs = field.getType().getDeclaredFields();
            for (Field f:fs) {
                constructField(formLayout,f,"content.");
            }
        }else{

        }

    }

    public void constructFormlayout(FormLayout formLayout){
        formLayout.setWidth("500px");
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "10px auto");
        formLayout.getStyle().set("padding", "10px 20px");
//        formLayout.getStyle().set("border", "1px solid gray");
        formLayout.getStyle().set("border-radius", "5px");
        formLayout.getStyle().set("min-height", "50px");
        formLayout.getStyle().set("box-shadow","3px 3px 5px gray, 1px 1px 3px gray");
        // Allow the form layout to be responsive. On device widths 0-490px we have one
        // column, then we have two. Field labels are always on top of the fields.
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

    }

    public void constructSelect(FormLayout formLayout){
        formLayout.removeAll();
        Set<String> set=new HashSet<>();
        commands.forEach(e->{
            set.add(e.toString());
        });
        if(set.size()!=0){
            Select<String> select = new Select<>();
            select.setItems(set);
            select.setLabel("Commands");
            select.setEmptySelectionAllowed(true);
            select.addValueChangeListener(e->{
                commandStr=e.getValue();
//                showNotify(commandStr);
            });
            formLayout.setColspan(select,2);
            formLayout.add(select);
        }
    }

    public void constructInitFormLayout(FormLayout queryFormLayout,FormLayout commandFormLayout){
        queryFormLayout.removeAll();
        commandFormLayout.removeAll();

        TextField titleTextField = new TextField("title");
        binder.forField(titleTextField).bind("title");
        commandFormLayout.add(titleTextField);

        TextField creatorTextField = new TextField("creator");
        binder.forField(creatorTextField).bind("creator");
        commandFormLayout.add(creatorTextField);

        Button createButton=new Button("create");
        createButton.addClickListener(e->{
            try {
                binder.writeBean(bid);
                t.complete((Command) t.getExpectedCommands().get(0));
                t = bidProcess.getPendingTask();
//                showNotify(t.toString());

                constructEditingFormLayout(queryFormLayout, commandFormLayout);
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });
        commandFormLayout.add(createButton);
        commandFormLayout.setColspan(createButton,2);
    }

    public void constructEditingFormLayout(FormLayout queryFormLayout,FormLayout commandFormLayout){
        queryFormLayout.removeAll();
        commandFormLayout.removeAll();

        TextField titleTextField = new TextField("title");
        binder.forField(titleTextField).bind("title");
        queryFormLayout.add(titleTextField);

        TextField creatorTextField = new TextField("creator");
        binder.forField(creatorTextField).bind("creator");
        queryFormLayout.add(creatorTextField);

        TextField contentTextField = new TextField("content");
        binder.forField(contentTextField).bind("content.content");
        commandFormLayout.add(contentTextField);

        TextField editorTextField = new TextField("editor");
        binder.forField(editorTextField).bind("content.editor");
        commandFormLayout.add(editorTextField);

        binder.setBean(bid);
        Button saveButton=new Button("save");
        saveButton.addClickListener(e->{
            try {
                binder.writeBean(bid);
                t.complete((Command) t.getExpectedCommands().get(0));
                t = bidProcess.getPendingTask();
//                showNotify(t.toString());
                constructEditingFormLayout(queryFormLayout, commandFormLayout);
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });
        commandFormLayout.add(saveButton);
        commandFormLayout.setColspan(saveButton,2);

        Button submitButton=new Button("submit");
        submitButton.addClickListener(e->{
            try {
                binder.writeBean(bid);
                t.complete((Command) t.getExpectedCommands().get(1));
                t = bidProcess.getPendingTask();
//                showNotify(t.toString());
                constructReviewingFormLayout(queryFormLayout, commandFormLayout);
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });
        commandFormLayout.add(submitButton);
        commandFormLayout.setColspan(submitButton,2);
    }

    public void constructReviewingFormLayout(FormLayout queryFormLayout,FormLayout commandFormLayout){
        queryFormLayout.removeAll();
        commandFormLayout.removeAll();

        TextField titleTextField = new TextField("title");
        binder.forField(titleTextField).bind("title");
        queryFormLayout.add(titleTextField);

        TextField creatorTextField = new TextField("creator");
        binder.forField(creatorTextField).bind("creator");
        queryFormLayout.add(creatorTextField);

        TextField contentTextField = new TextField("content");
        binder.forField(contentTextField).bind("content.content");
        queryFormLayout.add(contentTextField);

        TextField editorTextField = new TextField("editor");
        binder.forField(editorTextField).bind("content.editor");
        queryFormLayout.add(editorTextField);

        TextField reviewerTextField = new TextField("reviewer");
        commandFormLayout.add(reviewerTextField);

        TextField commentTextField = new TextField("comment");
        commandFormLayout.add(commentTextField);

        binder.setBean(bid);
        Button disapproveButton=new Button("disapprove");
        disapproveButton.addClickListener(e->{
            t.complete((Command) t.getExpectedCommands().get(1));
            t = bidProcess.getPendingTask();
//            showNotify(t.toString());
            bid.setContent(new Content("",""));
            constructEditingFormLayout(queryFormLayout,commandFormLayout);
        });
        commandFormLayout.add(disapproveButton);
        commandFormLayout.setColspan(disapproveButton,2);

        Button approveButton=new Button("approve");
        approveButton.addClickListener(e->{
            t.complete((Command) t.getExpectedCommands().get(0));
            t = bidProcess.getPendingTask();
//            showNotify(t.toString());
            constructTrackingFormLayout(queryFormLayout, commandFormLayout);
        });
        commandFormLayout.add(approveButton);
        commandFormLayout.setColspan(approveButton,2);
    }

    public void constructTrackingFormLayout(FormLayout queryFormLayout,FormLayout commandFormLayout){
        Addon addon=new Addon("","");
        queryFormLayout.removeAll();
        commandFormLayout.removeAll();

        TextField titleTextField = new TextField("title");
        binder.forField(titleTextField).bind("title");
        queryFormLayout.add(titleTextField);

        TextField creatorTextField = new TextField("creator");
        binder.forField(creatorTextField).bind("creator");
        queryFormLayout.add(creatorTextField);

        TextField contentTextField = new TextField("content");
        binder.forField(contentTextField).bind("content.content");
        queryFormLayout.add(contentTextField);

        TextField editorTextField = new TextField("editor");
        binder.forField(editorTextField).bind("content.editor");
        queryFormLayout.add(editorTextField);


        Grid<Addon> grid = new Grid<>(Addon.class, false);
        grid.addColumn(Addon::getAuthor).setHeader("tracker");
        grid.addColumn(Addon::getMessage).setHeader("addon");

        grid.setItems(addons);
        grid.getStyle().set("margin","10px auto");
        grid.getStyle().set("border-radius","5px");
        grid.getStyle().set("max-height","200px");
        queryFormLayout.setColspan(grid,2);
        queryFormLayout.add(grid);

        TextField trackerTextField = new TextField("tracker");
        addonBinder.forField(trackerTextField).bind("author");
        commandFormLayout.add(trackerTextField);

        TextField addonTextField = new TextField("addon");
        addonBinder.forField(addonTextField).bind("message");
        commandFormLayout.add(addonTextField);

        binder.setBean(bid);
        Button finalizeButton=new Button("finalize");
        finalizeButton.addClickListener(e->{
            t.complete((Command) t.getExpectedCommands().get(1));
            queryFormLayout.removeAll();
            commandFormLayout.removeAll();
            bid=new Bid();
            bid.setContent(new Content("",""));
            bid.setAddons(new ArrayList<Addon>());
            addons=new ArrayList<>();
            binder.setBean(bid);

//            constructInitFormLayout(queryFormLayout, commandFormLayout);
        });
        commandFormLayout.add(finalizeButton);
        commandFormLayout.setColspan(finalizeButton,2);

        Button trackButton=new Button("track");
        trackButton.addClickListener(e->{
            t.complete((Command) t.getExpectedCommands().get(0));
            t = bidProcess.getPendingTask();
//            showNotify(t.toString());
            try {
                addonBinder.writeBean(addon);
                addons.add(addon);
//                showNotify(addon.toString());
//                showNotify(addons.toString());
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
            constructTrackingFormLayout(queryFormLayout, commandFormLayout);
        });
        commandFormLayout.add(trackButton);
        commandFormLayout.setColspan(trackButton,2);
    }

    public BidView() throws ModelException {
        bid.setContent(new Content("",""));
        bid.setAddons(new ArrayList<Addon>());

        FormLayout queryFormLayout=new FormLayout();
        FormLayout commandFormLayout=new FormLayout();
        constructFormlayout(queryFormLayout);
        constructFormlayout(commandFormLayout);

//        FormLayout selectFormLayout=new FormLayout();
//        constructFormlayout(selectFormLayout);

        //create 按钮
        FormLayout initFormLayout=new FormLayout();
        constructFormlayout(initFormLayout);

        Button initButton=new Button("init");
        initButton.addClickListener(buttonClickEvent ->{
            try {
                bidProcess=new BidProcess(bid);
                t=bidProcess.getPendingTask();
//                showNotify(t.toString());
//                bid=(Bid)t.getObject();
//                binder.setBean(bid);
//                commands=t.getExpectedCommands();
//                initFormLayout.removeAll();
                constructInitFormLayout(queryFormLayout,commandFormLayout);
//                constructSelect(selectFormLayout);
            } catch (ModelException e) {
                e.printStackTrace();
            }
        });
        initFormLayout.add(initButton);
        initFormLayout.setColspan(initButton,2);
        add(initFormLayout);

        add(queryFormLayout);
        add(commandFormLayout);


//        //query表单
//        FormLayout queryFormLayout = new FormLayout();
//        constructFormlayout(queryFormLayout);
//
//        Field[] fields = bid.getClass().getDeclaredFields();
//        for (Field field:
//             fields) {
//            constructField(queryFormLayout,field,"");
//        }
//        add(queryFormLayout);
//
//
//        //command表单
//        FormLayout commandFormLayout = new FormLayout();
//        constructFormlayout(commandFormLayout);
//
//        Field[] commandFields = bid.getClass().getDeclaredFields();
//        for (Field field:
//                commandFields) {
//            constructField(commandFormLayout,field,"");
//        }
//        Button submitButton=new Button("submit");
//        submitButton.addClickListener(buttonClickEvent ->{
//            try {
//                binder.writeBean(bid);
//                Command command = null;
//                for(Command c:commands){
//                    if(c.toString().equals(commandStr)){
//                        command=c;
//                    }
//                }
//                if(command!=null){
//                    t.complete((Command) command);
//                    t = bidProcess.getPendingTask();
//                    if(t==null) {
//                        selectFormLayout.removeAll();
//                    }else{
//                        showNotify(t.toString());
//                        bid=(Bid)t.getObject();
//                        binder.setBean(bid);
//                        commands=t.getExpectedCommands();
//                        constructSelect(selectFormLayout);
//                    }
//
//                }
//            } catch (ValidationException e) {
//                e.printStackTrace();
//            }
//        });
//        commandFormLayout.setColspan(submitButton,2);
//        commandFormLayout.add(submitButton);
//
//        add(commandFormLayout);
//
//        add(selectFormLayout);
    }

    private void showNotify(String notice) {
        Notification notification = Notification.show(notice);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    public void showSuccess(Bid bid){
        Notification notification = Notification.show(bid.toString());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }
}
