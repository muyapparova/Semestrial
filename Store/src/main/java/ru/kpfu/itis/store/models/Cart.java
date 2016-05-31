package ru.kpfu.itis.store.models;

/**
 * Created by perfect on 4/29/16.
 */

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "good_id")
    private Good good;

    private int goodAmount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGoodAmount() {
        return goodAmount;
    }

    public void setGoodAmount(int goodID) {
        this.goodAmount = goodID;
    }
}
