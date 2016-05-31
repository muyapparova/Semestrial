package ru.kpfu.itis.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.store.models.Brand;
//import ru.kpfu.itis.store.models.Order;
import ru.kpfu.itis.store.models.forms.GoodForm;
import ru.kpfu.itis.store.repositories.BrandRepository;
//import ru.kpfu.itis.store.repositories.OrderRepository;
import ru.kpfu.itis.store.repositories.GoodRepository;
import ru.kpfu.itis.store.repositories.UserRepository;
import ru.kpfu.itis.store.services.GoodsService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin")
public class GWTController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    BrandRepository brandRepository;

    //@Autowired
    //OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getAdminPage(Model model, HttpServletResponse httpServletResponse) {
        model.addAttribute("categories", brandRepository.findAll());
        httpServletResponse.setHeader("X-Frame-Options", "GOFORIT");
        return "gwt/HelloGwt";
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("categories", brandRepository.findAll());
        return "gwt/addprod";
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String newProduct(@ModelAttribute GoodForm productForm) {
        goodsService.saveProduct(productForm);
        return "redirect:/admin/add-product";
    }
/*
    @RequestMapping(value = "/update-product", method = RequestMethod.POST)
    public void updateProduct(@ModelAttribute GoodFormUpdate productFormUpdate) {
        goodsService.updateProduct(productFormUpdate);
    }
*/
    @RequestMapping(value = "/add-category", method = RequestMethod.GET)
    public String addCategory(Model model) {
        return "gwt/addcat";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String saveCategory(@RequestParam String name, Model model) {
        Brand brand = new Brand();
        brand.setName(name);
        brandRepository.save(brand);
        return "gwt/addcat";
    }
/*
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders-list-admin";
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public String getOrderDetail(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findOne(id);
        model.addAttribute("order", order);
        ShippingInfo shippingInfo = userRepository.findOne(order.getUser().getId()).getShippingInfo();
        model.addAttribute("shipping", shippingInfo);
        return "order-detail";
    }*/
}
