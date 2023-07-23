package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    //정적 컨텐츠
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "열공하자~!!!");
        return "hello";
    }

    //MVC와 템플릿
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String hihi, Model model) {
        model.addAttribute("name", hihi);
        return "hello-templates";
    }

    //API
    @GetMapping("hello-string")
    public String helloStrign(@RequestParam("name") String name) {
        return "helle" + name;
    }
}
