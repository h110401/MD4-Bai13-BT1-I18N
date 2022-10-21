package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rikkei.academy.model.Customer;
import rikkei.academy.model.Province;
import rikkei.academy.service.customer.ICustomerService;
import rikkei.academy.service.province.IProvinceService;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("allProvince")
    public Iterable<Province> getAllProvince() {
        return provinceService.findAll();
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/create")
    public String save(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "New customer created successfully");
        return "redirect:/customer/create";
    }

    @GetMapping
    public ModelAndView showList(@RequestParam("search") Optional<String> search, Pageable pageable) {
        Page<Customer> customers;
        if (search.isPresent()) {
            customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Optional<Customer> customer, Model model) {
        if (!customer.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("customerEdit", customer.get());
        return "/customer/edit";
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Customer updated successfully");
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Optional<Customer> customer, Model model) {
        if (!customer.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("customerDelete", customer.get());
        return "/customer/delete";

    }

    @PostMapping("/remove")
    public String remove(Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:/customer";
    }
}

