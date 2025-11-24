package com.priadi.web.dummy.controller;

import com.priadi.web.dummy.AppConstants;
import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.model.UserModel;
import com.priadi.web.dummy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String viewMainPage(Model model) {
        return "index";
    }

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        model.addAttribute("allUser", userService.getAllUser());
        return "home";
    }

    @GetMapping("/tambah-pengguna")
    public String viewAddUserPage(Model model) {
        model.addAttribute("userForm", new UserModel());
        return "create-user";
    }

    @PostMapping("/tambah-pengguna")
    public String processRegistration(@ModelAttribute("userForm") UserModel userForm, RedirectAttributes redirectAttributes) {
        // Process the submitted data (e.g., save to database)
        BaseResDTO<String> response = userService.addUser(userForm);

        if(response.getCode() == 200) {
            redirectAttributes.addFlashAttribute("alertMessage", AppConstants.MSG_TAMBAH_DATA_SUKSES);
            return "redirect:/home";
        }
        else {
            String errMessage = AppConstants.MSG_GENERAL_ERROR;
            if(response.getMessage() != null){
                errMessage = response.getMessage();
            }
            redirectAttributes.addFlashAttribute("errMessage", errMessage);
            return "redirect:/tambah-pengguna";
        }
    }

    @GetMapping("/edit-pengguna/{id}")
    public String viewEditUserPage(@PathVariable("id") long id, Model model) {
        UserModel user = userService.getUser(id);
        if(user != null) {
            model.addAttribute("editUser", user);
            return "edit-user";
        }
        else {
            model.addAttribute("allUser", userService.getAllUser());
            model.addAttribute("alertMessage", AppConstants.MSG_MEMUAT_DATA_GAGAL);
            return "home";
        }
    }

    @PostMapping("/edit-pengguna")
    public String viewEditUserPage(@ModelAttribute("editUser") UserModel userForm, RedirectAttributes redirectAttributes) {
        System.out.println(userForm.getId());
        System.out.println(userForm.getName());

        BaseResDTO<String> response = userService.editUser(userForm);

        if(response.getCode() == 200) {
            redirectAttributes.addFlashAttribute("alertMessage", AppConstants.MSG_TAMBAH_DATA_SUKSES);
            return "redirect:/home";
        }
        else {
            String errMessage = AppConstants.MSG_GENERAL_ERROR;
            if(response.getMessage() != null){
                errMessage = response.getMessage();
            }
            redirectAttributes.addFlashAttribute("errMessage", errMessage);
            return "redirect:/edit-pengguna";
        }
    }
}
