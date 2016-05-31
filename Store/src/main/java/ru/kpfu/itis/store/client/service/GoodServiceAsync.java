package ru.kpfu.itis.store.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Good;

import java.util.List;

public interface GoodServiceAsync {

    void getAllCategories(AsyncCallback<List<Brand>> asyncCallback);

    void getProduct(Long id, AsyncCallback<Good> productAsyncCallback);

    void getProducts(AsyncCallback<List<Good>> asyncCallback);
}
