package org.example.bookshop.event;

import org.example.bookshop.ds.CardItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductEventPublisher {
    @Autowired
    private ApplicationEventPublisher eventPublisher; // all the events are void

    public void orderBook(Set<CardItem>cardItems,String registeredUser){
        eventPublisher.
                publishEvent(new ProductEvent(this, cardItems, registeredUser));
    }
}
