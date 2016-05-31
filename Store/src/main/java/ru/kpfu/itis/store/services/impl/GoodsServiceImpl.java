package ru.kpfu.itis.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Good;
import ru.kpfu.itis.store.models.forms.GoodForm;
import ru.kpfu.itis.store.models.forms.GoodFormUpdate;
import ru.kpfu.itis.store.repositories.BrandRepository;
import ru.kpfu.itis.store.repositories.GoodRepository;
import ru.kpfu.itis.store.services.GoodsService;
import ru.kpfu.itis.store.utils.PropertyPath;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by perfect on 5/6/16.
 */
@Service("goodService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    GoodRepository goodRepository;

    @Override
    public List<Good> findByBrandId(Long brandId) {
        Brand brand = brandRepository.findOne(brandId);
        return goodRepository.findByBrand(brand);

    }

    @Override
    @Transactional
    public void saveProduct(GoodForm goodForm) {
        Good good = new Good();
        good.setBrand(brandRepository.findOne(goodForm.getCategoryId()));
        good.setDescription(goodForm.getDescription());
        good.setPrice(goodForm.getPrice());
        String newFileName = null;
        MultipartFile file = goodForm.getImg();
        File dir = null;
        //Загрузка картинки
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                dir = new File(PropertyPath.getPath() + File.separator + "product_images");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //генерируем имя картинке
                newFileName = UUID.randomUUID().toString() + "."
                        + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + newFileName);
                //сохраняем картинку
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        good.setImg("/images/" + newFileName);
        good.setName(goodForm.getName());
        goodRepository.save(good);
    }

    @Override
    @Transactional
    public void updateProduct(GoodFormUpdate goodForm) {
        Good product = goodRepository.findOne(goodForm.getId());
        product.setBrand(brandRepository.findOne(goodForm.getCategoryId()));
        product.setDescription(goodForm.getDescription());
        product.setPrice(goodForm.getPrice());

        String newFileName = null;
        if (goodForm.getImg() != null) {
            MultipartFile file = goodForm.getImg();
            File dir = null;
            //Загрузка картинки
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    dir = new File(PropertyPath.getPath() + File.separator + "product_images");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    //генерируем имя картинке
                    newFileName = UUID.randomUUID().toString() + "."
                            + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + newFileName);
                    //сохраняем картинку
                    try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                        stream.write(bytes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            product.setImg("/images/" + newFileName);
        }

        product.setName(goodForm.getName());
        goodRepository.save(product);
    }

}
