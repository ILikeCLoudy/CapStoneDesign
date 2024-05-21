package com.EuljiUniv.CapstoneDesign.Controller;

import com.EuljiUniv.CapstoneDesign.Entity.ResponseEntity;
import com.EuljiUniv.CapstoneDesign.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final ResponseService responseService;
    //private final {ServiceName} {ServiceMethod}

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/survey")
      public String getSurvey() {
        return "survey";
      }

    @ResponseBody
    @PostMapping("/answer")
    public HashMap<String, String> getAnswer(@RequestBody ResponseEntity responseEntity) {
        responseEntity.setResult(0000);
        responseService.insertdata(responseEntity);
        HashMap<String, String> result = new HashMap<>();
        try {
            String[] command = new String[] {"C:\\Users\\LICL\\Desktop\\졸업작품\\testmodel2"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("\nForceExit by ErrorCode = "+exitCode); //파이썬 연산종료후 DB에서 결과값 수신

            ResponseEntity responseEntitysec = responseService.response(responseEntity.getID()); //DB 호출

            log.info(String.valueOf(responseEntity.getID()));

            result.put("Person", responseEntitysec.getPerson());
            result.put("None", "INT"); //초기값 지정

            //result.put("type", PageSet.Page_SET.get(String.format("%d",responseEntitysec.getResult())));
            //PageSet은 초기미구현 상태 배치 + 향후 GPT 및 ML 연동 활성화시 고려
        }
        catch (Exception e) {
            System.out.println("\n ForceExit by ErrorCode = "+e.getMessage());
        }
        finally {
            return result;
        }

    }
}

