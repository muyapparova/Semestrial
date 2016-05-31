package ru.kpfu.itis.store.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import ru.kpfu.itis.store.client.service.GoodService;
import ru.kpfu.itis.store.client.service.GoodServiceAsync;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Good;

import java.util.List;

public class ProductList extends DialogBox {


    private GoodServiceAsync goodServiceAsyncService = GWT.create(GoodService.class);

    private AsyncCallback<List<Brand>> callback;

    private AsyncCallback<List<Good>> callbackProduct;

    public FormPanel getEditProductForm(Good product, final MyDialog widgets) {
        final ListBox list = new ListBox();

        callback = new AsyncCallback<List<Brand>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error");
            }

            @Override
            public void onSuccess(List<Brand> categories) {
                for (int i = 0; i < list.getItemCount(); i++) {
                    list.removeItem(i);
                }
                for (Brand category : categories) {
                    list.addItem(category.getName(), String.valueOf(category.getId()));
                }
            }

        };
        goodServiceAsyncService.getAllCategories(callback);
        VerticalPanel panel = new VerticalPanel();
        final FormPanel form = new FormPanel();
        form.setAction("/admin/update-product");
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        final Label status = new Label("");
        panel.add(status);

        final TextBox textBox = new TextBox();
        textBox.setName("id");
        textBox.setValue(product.getId().toString());
        textBox.setVisible(false);
        panel.add(textBox);

        //Product Name input
        Label productNameLabel = new Label("Название продукта");
        final TextBox nameForm;
        nameForm = new TextBox();
        nameForm.getElement().setAttribute("type", "text");
        nameForm.setName("name");
        nameForm.setValue(product.getName());
        panel.add(productNameLabel);
        panel.add(nameForm);
        //End product name input

        //Product description input
        Label productDescriptionLabel = new Label("Описание продукта");
        final TextBox descriptionForm = new TextBox();
        descriptionForm.getElement().setAttribute("type", "text");
        descriptionForm.setName("description");
        descriptionForm.setValue(product.getDescription());
        panel.add(productDescriptionLabel);
        panel.add(descriptionForm);
        //End product name input

        //Product price input
        Label priceProductLabel = new Label("Цена продукта");
        final TextBox priceProductForm = new TextBox();
        priceProductForm.setName("price");
        priceProductForm.getElement().setAttribute("type", "number");
        priceProductForm.setValue(String.valueOf(product.getPrice()));
        panel.add(priceProductLabel);
        panel.add(priceProductForm);
        //End product price input

        //Product category select
        Label categoryProductLabel = new Label("Категория:");
        list.setVisibleItemCount(0);
        list.setName("categoryId");
        panel.add(categoryProductLabel);
        panel.add(list);
        //end product category
        final Image image = new Image();

        //set image source
        image.setUrl(product.getImg());
        panel.add(image);
        //FileUpload input
        Label fileUploadLabel = new Label("Выберете новую картинку:");
        final FileUpload fileUpload = new FileUpload();
        fileUpload.setName("img");
        fileUpload.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                image.setSize("0", "0");
            }
        });
        panel.add(fileUploadLabel);
        panel.add(fileUpload);
        //end FileUpload input

        Button uploadButton = new Button("Обновить");
        panel.add(uploadButton);

        uploadButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String filename = fileUpload.getFilename();
                if (false == true) {

                    status.getElement().setClassName("error");
                } else {
                    form.submit();
                }
            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                widgets.hide();
            }
        });
        Button cancel = new Button("Отменить");
        cancel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                widgets.hide();
            }
        });
        panel.add(cancel);
        panel.setSpacing(10);

        form.add(panel);
        return form;
    }

    public VerticalPanel getAddProductForm() {

        final VerticalPanel panel = new VerticalPanel();


        callbackProduct = new AsyncCallback<List<Good>>() {
            @Override
            public void onFailure(Throwable throwable) {
                Window.alert("ERROR!");
            }

            @Override
            public void onSuccess(final List<Good> products) {
                final ListBox lb = new ListBox();
                lb.setWidth("300");
                for (final Good product : products) {
                    lb.addItem(product.getName(), product.getId().toString());
                }
                lb.addChangeListener(new ChangeListener() {
                    @Override
                    public void onChange(Widget widget) {
                        int itemSelected = lb.getSelectedIndex();
                        final MyDialog myDialog = new MyDialog(products.get(itemSelected));
                        myDialog.show();
                    }
                });


                lb.setVisibleItemCount(products.size());
                panel.add(lb);
            }
        };
        goodServiceAsyncService.getProducts(callbackProduct);
        return panel;
    }

    private static class MyDialog extends DialogBox {

        public MyDialog(Good product) {
            setText("My First Dialog");
            setAnimationEnabled(true);
            setGlassEnabled(true);
            setWidget(new ProductList().getEditProductForm(product, this));
        }
    }
}
