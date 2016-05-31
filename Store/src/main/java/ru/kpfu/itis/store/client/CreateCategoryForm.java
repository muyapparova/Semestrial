package ru.kpfu.itis.store.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class CreateCategoryForm {

    public FormPanel getCreateCategoryForm() {
        VerticalPanel panel = new VerticalPanel();
        final FormPanel form = new FormPanel();
        form.setAction("/admin/add-category");
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        final Label status = new Label("");
        panel.add(status);

        //Category Name input
        Label productNameLabel = new Label("Название категории");
        final TextBox nameForm = new TextBox();
        nameForm.getElement().setAttribute("type", "text");
        nameForm.setName("name");
        panel.add(productNameLabel);
        panel.add(nameForm);
        //Category product name input

        Button uploadButton = new Button("Создать");
        panel.add(uploadButton);

        uploadButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (nameForm.getValue().length() == 0) {
                    status.setText("Заполните поле!");
                    status.getElement().setClassName("error");
                } else {
                    form.submit();
                }
            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                status.setText("Категория успешно добавлена!");
                status.getElement().setClassName("success");
                nameForm.setValue("");
            }
        });
        panel.setSpacing(10);

        form.add(panel);
        return form;
    }
}
