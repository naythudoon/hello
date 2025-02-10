package org.example.bookshop.service;

import org.example.bookshop.dao.BookDao;
import org.example.bookshop.ds.CardItem;
import org.example.bookshop.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@SessionScope
@Service
public class CartService {
    private final BookDao bookDao;
    private Set<CardItem> cardItems=new TreeSet<>();

    public void removeFromCart(int id) {
       this.cardItems= this.cardItems.stream()
                .filter(item->item.getCardId()!=id)
                .collect(Collectors.toSet());
    }

    public void claerCart() {
        cardItems.clear();
    }

    public CartService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void decreaseCartItemQuantity(int id){
        double price= bookDao.getPriceByBookId(id);
        this.cardItems.stream()
                .map(item ->{
                   if ( item.getCardId() == id && item.getQuantity()>1 ){
                        item.setQuantity(item.getQuantity()-1);
                        item.setPrice(item.getPrice()-price);
                   }
                   return item;
                }) .collect(Collectors.toSet());
    }

    public void increaseCartItemQuantity(int id){
        double price = bookDao.getPriceByBookId(id);
        this.cardItems.stream().filter(item ->item.getCardId()==id)
                .map(item->{
                    if(item.getCardId()==id) {
                        item.setQuantity(item.getQuantity() + 1);
                        item.setPrice(price * item.getQuantity());
                    }
                    return item;
                })
                .collect(Collectors.toSet());
    }

    public Set<CardItem> getAllCardItems() {
        return cardItems;
    }

    public void addToCard(CardItem cardItem){
        cardItems.add(cardItem);
    }

    public int cardSize(){
        return cardItems.size();
    }

}
