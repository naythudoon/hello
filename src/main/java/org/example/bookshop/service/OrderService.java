package org.example.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.ProductDao;
import org.example.bookshop.ds.CardItem;
import org.example.bookshop.entity.Product;
import org.example.bookshop.event.ProductEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductDao productDao;

    @EventListener
    public void bookOrderListener(ProductEvent productEvent) {
     productEvent.getCardItems().stream().map(c->{
         Product product =new Product(c.getTitle(),c.getQuantity(),c.getPrice(),productEvent.getRegisteredUser(),LocalDate.now());
         return product;
     }).forEach(productDao::save);
    }


}
