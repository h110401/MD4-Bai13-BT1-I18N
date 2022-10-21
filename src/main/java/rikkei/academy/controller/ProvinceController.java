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
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;


    @GetMapping
    public String showList(Model model) {
        Iterable<Province> provinces = provinceService.findAll();
        model.addAttribute("provinces", provinces);
        return "/province/list";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("provinceForm", new Province());
        return "/province/create";
    }

    @PostMapping("/create")
    public String create(Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Create success!");
        return "redirect:/province/create";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Optional<Province> province, Model model) {
        if (!province.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("provinceEdit", province.get());
        return "/province/edit";
    }

    @PostMapping("/update")
    public String update(Province province) {
        provinceService.save(province);
        return "redirect:/province";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Optional<Province> province, Model model) {
        if (!province.isPresent()) {
            return "/error.404";
        }
        model.addAttribute("provinceDelete", province.get());
        return "/province/delete";
    }

    @PostMapping("/remove")
    public String remove(Province province) {
        provinceService.remove(province.getId());
        return "redirect:/province";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewForm(@PathVariable("id") Optional<Province> province, Pageable pageable) {
        if (!province.isPresent()) {
            return new ModelAndView("/error.404");
        }
//        Iterable<Customer> customers = customerService.findAllByProvince(province.get());

        ModelAndView modelAndView = new ModelAndView("/province/view");

        Page<Customer> customers = customerService.findAllByProvince(province.get(), pageable);
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("province", province.get());
        return modelAndView;
    }
}
