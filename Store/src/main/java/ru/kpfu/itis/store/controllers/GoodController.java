package ru.kpfu.itis.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.store.exceptions.NotFoundException;
import ru.kpfu.itis.store.models.*;
import ru.kpfu.itis.store.models.forms.CartListWrapper;
import ru.kpfu.itis.store.repositories.*;
import ru.kpfu.itis.store.services.CartService;
import ru.kpfu.itis.store.services.UserService;
import ru.kpfu.itis.store.utils.Auth;

import java.util.List;

@Controller
public class GoodController {

  @Autowired
  private GoodRepository goodRepo;

  @Autowired
  private BillingRepository billingRepository;

  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private CartRepository cartRepository;

  @Autowired
  private CartService cartService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubscribeRepository subscribeRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String out(Pageable pageable,Model model) {
    Page<Good> goods = goodRepo.findAll(pageable);
    model.addAttribute("goods", goods);
    return "goods/main";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String out1(@RequestParam String searchTerm, Pageable pageable, Model model) {
    Page<Good> goods = goodRepo.findAll(searchTerm, pageable);
    model.addAttribute("goods", goods);
    return "goods/main";
  }

  @RequestMapping(value = "/fastaddtocart")
  @ResponseStatus(value = HttpStatus.OK)
  public String addGood(@RequestParam Long id, RedirectAttributes redirectAttributes) {
    cartService.addGood(id);
    redirectAttributes.addFlashAttribute("message", "Product has been added to cart successfully");
    redirectAttributes.addFlashAttribute("messageType", "success");
    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#out").build();
  }

  @RequestMapping("/goods/{id}")
  public String show(@PathVariable Long id, ModelMap map) {
    Good good = goodRepo.findOne(id);
    List<Brand> brand = brandRepository.findAll();
    if (good == null) {
      throw new NotFoundException("good");
    }

    map.put("good", good);
    map.put("cat", brand);
    return "goods/show";
  }

  @RequestMapping(value = "/account")
  @ResponseStatus(value = HttpStatus.OK)
  public String account(ModelMap map) {
    try {
      Billing billing = billingRepository.findOne(Auth.getCurrentUser().getBilling().getId());
      map.addAttribute("billing", billing);
    }catch (NullPointerException nullPointer) {
      //map.addAttribute("billing", new Billing());
    }

    return "security/account";
  }

  @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.OK)
  public String addToCart(@RequestParam Long id, @RequestParam Integer Amount) {
    System.out.println(id + " "+ Amount);
    cartService.addGoodCart(id, Amount);
    //return "redirect:/added";
    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#added").build();
  }

  @RequestMapping(value = "/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {
    cartService.deleteGood(id);
    redirectAttributes.addFlashAttribute("message", "Product has been deleted from cart successfully");
    redirectAttributes.addFlashAttribute("messageType", "success");

    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#getCart").build();
  }

  @RequestMapping(value = "/added")
  @ResponseStatus(value = HttpStatus.OK)
  public String added(RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("message", "Product has been added to cart successfully");
    redirectAttributes.addFlashAttribute("messageType", "success");

    return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#getCart").build();
  }

  @RequestMapping(value = "/cart", method = RequestMethod.GET)
  public String getCart(Model model) {
    List<Brand> brand = brandRepository.findAll();
    List<Cart> carts = cartRepository.findAllByUser(Auth.getCurrentUser());

    CartListWrapper cartListWrapper = new CartListWrapper();

    Integer total = 0;
    for (Cart cart : carts) {
      cartListWrapper.add(cart);
      total += (cart.getGoodAmount() * cart.getGood().getPrice());
    }

    model.addAttribute("cat", brand);
    model.addAttribute("cartListWrapper", cartListWrapper);
    model.addAttribute("total", total);
    return "goods/cart";
  }

  @RequestMapping(value = "/cart", method = RequestMethod.POST)
  public String setCart(@ModelAttribute("cartListWrapper") @Validated CartListWrapper cartListWrapper, @RequestParam String action, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    if(action.equals("Update")){
      for (Cart cart : cartListWrapper.getCartList()) {
        Integer amount = cart.getGoodAmount();
        cartService.changeGoodAmount(cart.getId(), amount);
      }

      redirectAttributes.addFlashAttribute("message", "Cart has been updated successfully");
      redirectAttributes.addFlashAttribute("messageType", "success");

      return "redirect:" + MvcUriComponentsBuilder.fromMappingName("GC#getCart").build();
    }
      return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#checkoutSave").build();

  }

}
