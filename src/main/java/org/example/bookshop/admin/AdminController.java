package org.example.bookshop.admin;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    @GetMapping
    public String index() {
        return "admin/admin-index";
    }
    @GetMapping("/list-category")
    public String listAllCategory(Model model) {
        model.addAttribute("categories", adminService.findAllCategories());
        model.addAttribute("category", new Category());
        return "admin/categories";
    }
    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/categories";
        }
            adminService.saveCategory(category);
        return "redirect:/admin/list-category";
    }

}
