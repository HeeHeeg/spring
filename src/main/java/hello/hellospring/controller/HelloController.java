package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //API - http에 정보를 그대로 보내주는 형식
    @GetMapping("hello-string")
    @ResponseBody
    public String helloStrign(@RequestParam("name") String name) {
        return "helle" + name;
    }

    //이런걸 API방식이라고 얘기한다. - JSON방식으로 나온다. 키-벨류형식으로 나온다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("hohoho") String hihi) {
        Hello hello = new Hello(); // 객체 생성.
        hello.setHahaha(hihi); // 파라미터로 넘어온 name을 넣어줌.
        return hello; // 위에 하던것과 다른 점 -> 문자가 아닌 객체를 리턴해준다.
    }

    //객체 먼저 생성. static으로 하면 이 안에서 class를 만들 수 있음.
    static class Hello { // HelloController.Hello로 쓸 수 있다.
        public String hahaha;

        public String getHahaha() {
            return hahaha;
        }

        public void setHahaha(String hahaha) {
            this.hahaha = hahaha;
        }
    }

}
