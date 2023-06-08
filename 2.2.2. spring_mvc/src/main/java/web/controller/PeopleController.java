package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.DAO;
import web.dao.HibernateDAO;
import web.model.User;

@Controller
@RequestMapping("/")
public class PeopleController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @GetMapping("/")
    public String allPeople(Model model){
        model.addAttribute("people",hibernateDAO.allPeople());
        return "/allPeople";
    }

    @GetMapping("/{id}")
    public String personById(@PathVariable("id") int id, Model model){
        model.addAttribute("person", hibernateDAO.personById(id));
        return "/personById";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user){
        hibernateDAO.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") int id){
        model.addAttribute("user", hibernateDAO.personById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id){
        hibernateDAO.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
      hibernateDAO.delete(id);
      return "redirect:/";

    }
}
