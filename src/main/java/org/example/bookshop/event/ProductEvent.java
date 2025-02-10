package org.example.bookshop.event;

import lombok.Getter;
import org.example.bookshop.ds.CardItem;
import org.springframework.context.ApplicationEvent;

import java.util.Set;
@Getter
public class ProductEvent extends ApplicationEvent {

    private Set<CardItem>cardItems;
    private String registeredUser;

    public ProductEvent(Object source, Set<CardItem> cardItems, String registeredUser) {
        super(source);
        this.cardItems = cardItems;
        this.registeredUser = registeredUser;
    }

    public Set<CardItem> getCardItems() {
        return cardItems;
    }

    public String getRegisteredUser() {
        return registeredUser;
    }
}
