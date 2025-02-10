package org.example.bookshop.ds;

import java.util.Objects;

public class CardItem implements Comparable<CardItem>{
    @Override
    public int compareTo(CardItem obj) {
        return cardId-obj.cardId;
    }

    private int cardId;
    private String title;
    private int quantity;
    private double price;

    @Override
    public String toString() {
        return "CardItem{" +
                "cardId=" + cardId +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CardItem cardItem = (CardItem) o;
        return cardId == cardItem.cardId && quantity == cardItem.quantity && Double.compare(price, cardItem.price) == 0 && Objects.equals(title, cardItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, title, quantity, price);
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
