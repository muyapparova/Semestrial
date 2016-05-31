package ru.kpfu.itis.store.models.forms;

import ru.kpfu.itis.store.models.Cart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perfect on 5/8/16.
 */
public class CartListWrapper {
        private List<Cart> cartList;

        public CartListWrapper() {
            this.cartList = new ArrayList<Cart>();
        }

        public List<Cart> getCartList() {
            return cartList;
        }

        public void setCartList(List<Cart> fooList) {
            this.cartList = fooList;
        }

        public void add(Cart cart) {
            this.cartList.add(cart);
        }
}
