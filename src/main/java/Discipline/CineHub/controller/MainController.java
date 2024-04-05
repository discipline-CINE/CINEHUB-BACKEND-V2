package Discipline.CineHub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")    //TODO: 확인 용도로 사용, 추후에 변경해야 함
    public String mainPage(){
        return "this is main page";
    }
}
