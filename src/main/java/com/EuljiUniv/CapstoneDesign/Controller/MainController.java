package com.EuljiUniv.CapstoneDesign.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    //private final {ServiceName} {ServiceMethod}

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /*@GetMapping("/{survey}/{survey}")
      public String getSurvey() {
        return "survey";
      }
     */

    /*@ResponseBody
    @PostMapping("/answer")
    Response에 따른 반응형 설계 자리
    */
}

