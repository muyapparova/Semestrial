package ru.kpfu.itis.store.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Good;

import java.util.List;

@RemoteServiceRelativePath("springGwtServices/goodService")
public interface GoodService extends RemoteService {

    List<Brand> getAllCategories();

    Good getProduct(Long id);

    List<Good> getProducts();

}
