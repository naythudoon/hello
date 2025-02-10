package org.example.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.RoleDao;
import org.example.bookshop.dao.UserDao;
import org.example.bookshop.ds.CardItem;
import org.example.bookshop.entity.Product;
import org.example.bookshop.event.ProductEventPublisher;
import org.example.bookshop.security.Role;
import org.example.bookshop.security.User;
import org.example.bookshop.service.CartService;
import org.example.bookshop.service.ProductService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final ProductEventPublisher productEventPublisher;
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("/product")
    public String listLoggedInUserProduct(Model model) {
        model.addAttribute("products", productService.listAllProductsByLoggedInUser());
        return "product";
    }



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }
    @PostMapping("/register")
    public String saveRegister(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        Role userRole = roleDao.findByRoleName("ROLE_USER").get();
        user.addRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        productEventPublisher.orderBook(cartService.getAllCardItems(),user.getUsername());
        this.user=user;
        productEventPublisher.orderBook(cartService.getAllCardItems(),user.getUsername());
        this.cardItems= cartService.getAllCardItems();
        return "redirect:/auth/checkout";
    }
    Set<CardItem> cardItems;
    User user;

    @GetMapping("/checkout")
    public String checkoutView(Model model) {

        model.addAttribute("products",productService
                .listAllProductsByRegisterUser(user.getUsername()));
        model.addAttribute("registerUser",user);
        cartService.claerCart();
        return "checkout";
    }


    @ModelAttribute("totalPrice")
    public double totalPrice() {
        return cartService.getAllCardItems()
                .stream().map(c->c.getPrice()).mapToDouble(Double::doubleValue).sum();
    }

}
