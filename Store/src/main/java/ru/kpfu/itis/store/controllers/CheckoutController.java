package ru.kpfu.itis.store.controllers;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.store.models.Billing;
import ru.kpfu.itis.store.models.Brand;
import ru.kpfu.itis.store.models.Cart;
import ru.kpfu.itis.store.models.User;
import ru.kpfu.itis.store.repositories.BillingRepository;
import ru.kpfu.itis.store.repositories.BrandRepository;
import ru.kpfu.itis.store.repositories.CartRepository;
import ru.kpfu.itis.store.repositories.UserRepository;
import ru.kpfu.itis.store.utils.Auth;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by perfect on 5/14/16.
 */
@Controller
public class CheckoutController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    BillingRepository billingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkoutSave(ModelMap map) throws NullPointerException {

        List<Cart> carts = cartRepository.findAllByUser(Auth.getCurrentUser());
        try {
            Billing billing = billingRepository.findOne(Auth.getCurrentUser().getBilling().getId());
            map.addAttribute("billing", billing);
        }catch (NullPointerException nullPointer) {
            map.addAttribute("billing", new Billing());
        }

        Integer total = 0;
        for (Cart cart : carts) {
            total += (cart.getGoodAmount() * cart.getGood().getPrice());
        }
        List<Brand> brand = brandRepository.findAll();

        map.addAttribute("carts", carts);
        map.addAttribute("cat", brand);
        map.addAttribute("total", total);
        //return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#getCart").build();
        return "goods/checkout";
    }


    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkout(@ModelAttribute @Valid Billing billing, ModelMap map, RedirectAttributes redirectAttributes) {

        if(billing.getId() == null) {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Session session = entityManager.unwrap(Session.class);

            try {
                final Transaction transaction = session.beginTransaction();
                try {
                    User user = entityManager.find(User.class, Auth.getCurrentUser().getId());
                    user.setPasswordRepeat(user.getPassword());
                    System.out.println(user.getFullName());
                    user.setBilling(billingRepository.save(billing));
                    userRepository.save(user);

                    transaction.commit();
                } catch (Exception ex) {
                    // Log the exception here
                    transaction.rollback();
                    throw ex;
                }
            } finally {
                session.close();
            }

        }
        else{
            billingRepository.save(billing);
        }

        redirectAttributes.addFlashAttribute("message", "Info has been saved successfully");
        redirectAttributes.addFlashAttribute("messageType", "success");

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#checkoutSave").build();

    }

}
