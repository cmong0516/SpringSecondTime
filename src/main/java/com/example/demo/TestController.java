package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // hello-mvc 주소로 접속시
    @GetMapping("hello-mvc")
    // name 속성의 값을 name 으로 저장하고
    // 이 name 값을 model에 담아 hello-template 를 리턴
   public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
    // hello-string 으로 접속시
    @GetMapping("hello-string")
    // body에 return 값을 그대로 보여준다.
    @ResponseBody
    // hello-string?name={}    의 값을 String name 에 담고 출력.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // hello-api 주소로 접속시
    @GetMapping("hello-api")
    // 리턴값을 바디에 전송
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // 아래에 만든 Hello 객체 생성
        Hello hello = new Hello();
        // @RequestParam 으로 받은 name 값을 Hello 객체에 set 메서드를 이용해 저장.
        hello.setName(name);
        // hello 객체를 리턴 => @Responsebody 에 의해 객체를 return 했으므로 json 반환.
        return hello;
    }


    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

