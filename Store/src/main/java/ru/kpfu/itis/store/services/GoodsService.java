package ru.kpfu.itis.store.services;

import ru.kpfu.itis.store.models.Good;
import ru.kpfu.itis.store.models.forms.GoodForm;
import ru.kpfu.itis.store.models.forms.GoodFormUpdate;

import java.util.List;

/**
 * Created by perfect on 5/6/16.
 */
public interface GoodsService {

    void saveProduct(GoodForm goodForm);

    List<Good> findByBrandId(Long brandId);

    void updateProduct(GoodFormUpdate goodFormUpdate);
}
