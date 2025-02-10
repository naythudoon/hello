package org.example.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.BookDao;
import org.example.bookshop.ds.CardItem;
import org.example.bookshop.entity.Book;
import org.example.bookshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final CartService cardService;
    private final BookDao bookDao;
    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("id")int cartId) {
        cardService.removeFromCart(cartId);
        return "redirect:/view-cart";
    }
    @GetMapping("/clear-cart")
    public String clearCart() {
        cardService.claerCart();
        return "redirect:/view-cart";
    }

    @GetMapping("/increase/item/{id}")
    public String increaseCartItemQuantity(@PathVariable("id")int id){
        cardService.increaseCartItemQuantity(id);
        return "redirect:/view-cart";
    }

    @GetMapping("/decrease/item/{id}")
    public String decreaseCartItemQuantity(@PathVariable("id")int id){
        cardService.decreaseCartItemQuantity(id);
        return "redirect:/view-cart";
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems",cardService.getAllCardItems());
        return "cart";
    }


    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "home";
    }

    @ModelAttribute("cartSize") //work before handlers(getMapping,postMapping)
    public int cartSize(){
        return cardService.cardSize();
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int bookId){
        Book book =bookDao.findById(bookId).get();
        cardService.addToCard(toCartItem(book));
        return "redirect:/";
    }

    private CardItem toCartItem(Book book){
        CardItem cardItem = new CardItem();
        cardItem.setCardId(book.getId());
        cardItem.setTitle(book.getTitle());
        cardItem.setPrice(book.getPrice());
        cardItem.setQuantity(1);
        return cardItem;
    }

}
