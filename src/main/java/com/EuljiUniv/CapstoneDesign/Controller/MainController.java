package com.EuljiUniv.CapstoneDesign.Controller;

import com.EuljiUniv.CapstoneDesign.DTO.AddPersonalRequest;
import com.EuljiUniv.CapstoneDesign.DTO.AnswerRequest;
import com.EuljiUniv.CapstoneDesign.DTO.ResultRequest;
import com.EuljiUniv.CapstoneDesign.Entity.PersonalEntity;
import com.EuljiUniv.CapstoneDesign.Entity.SurveyEntity;
import com.EuljiUniv.CapstoneDesign.Service.PersonalService;
import com.EuljiUniv.CapstoneDesign.Service.SurveyService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class MainController {

    private final SurveyService surveyService;
    private final PersonalService personalService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/survey")
    public boolean addSurvey(@RequestBody AddPersonalRequest request) {
        try {
            PersonalEntity savedm = personalService.save(request.toPersonalEntity());
        } catch (Exception e) {
            PersonalEntity savedm = personalService.findPersonalEntityByStudentid(request.getStudentid()).orElseThrow(() -> new RuntimeException("회원정보가 존재하지 않습니다."));
        }

        SurveyEntity saveds = surveyService.save(request.toSurveyEntity());
        return true;
    }

    @PutMapping("/survey/update")
    public ResponseEntity<SurveyEntity> updateSurvey(@RequestBody ResultRequest request) {
        SurveyEntity updateSurvey = surveyService.updateResult(request);
        return ResponseEntity.ok()
                .body(updateSurvey);
    }

    @PostMapping("/survey/ver2")
    public ResponseEntity<String> submitSurvey(@RequestBody AddPersonalRequest request) {

        SurveyEntity saveds = surveyService.save(request.toSurveyEntity());
        try {
            PersonalEntity savedm = personalService.save(request.toPersonalEntity());
        } catch (Exception e) {
            PersonalEntity savedm = personalService.findPersonalEntityByStudentid(request.getStudentid()).orElseThrow(() -> new RuntimeException("회원정보가 존재하지 않습니다."));
        }
        String surveyJson = convertToJson(saveds);

        // python과 통신할때 json 형태로 보내려면 APPLICATION_JSON headers가 필요하다
        //이게 없으면 415 error가 뜨면서 python이 데이터를 받아들이지 못한다
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> jsonInput = new HttpEntity<String>(surveyJson, headers);


        //파이썬 코드 작업공간 5/30 오후 작업할것
        String pythonServerUrl = "http://localhost:5000/test/pyflask";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(pythonServerUrl, jsonInput, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
            String result = responseEntity.getBody();
            ResultRequest resultRequest = new ResultRequest(saveds.getStudentid(), Integer.parseInt(result));
            surveyService.updateResult(resultRequest);
            return ResponseEntity.ok("Survey submitted successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to submit survey.");
        }
    }

    private String convertToJson(SurveyEntity surveyForm) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(surveyForm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // JSON 변환 실패 시 null 반환
        }
    }

    @PostMapping("/survey/ver3")
    public AnswerRequest test(@RequestBody AddPersonalRequest request) {
        SurveyEntity saveds = surveyService.save(request.toSurveyEntity());
        try {
            PersonalEntity savedm = personalService.save(request.toPersonalEntity());
        } catch (Exception e) {
            PersonalEntity dm = personalService.findPersonalEntityByStudentid(request.getStudentid()).orElseThrow(() -> new RuntimeException("회원정보가 존재하지 않습니다."));
        }
        String surveyJson = convertToJson(saveds);

        String pythonScriptPath = "C:\\Users\\LICL\\Desktop\\testmodel4\\testRunCode.py";

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInput = null;
        try {
            jsonInput = objectMapper.writeValueAsString(surveyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] command = {"python", pythonScriptPath, jsonInput};
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int processedValue;

            while ((line = reader.readLine()) != null) {
                // 출력값을 읽어와서 필요한 작업 수행
                // 여기서는 정수로 변환하여 저장
                processedValue = Integer.parseInt(line);
                System.out.println("Processed value: " + processedValue);

                SurveyEntity saveur = surveyService.updateResult(new ResultRequest(request.getStudentid(), processedValue));

            }
            int exitCode = process.waitFor();

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println("Error : " + line);
            }

            System.out.println("Exited with Code" + exitCode);
            ResultRequest resultRequest = new ResultRequest(request.getStudentid(), 0);
            return sendAnswer(resultRequest);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @PostMapping("/answer")
    //    public AnswerRequest sendAnswer(@RequestBody SurveyEntity surveyEntity ) {
    public AnswerRequest sendAnswer(@RequestBody ResultRequest a) {

        //SurveyEntity as = surveyService.findnewByStudentid(surveyEntity.getStudentid());
        SurveyEntity as = surveyService.findnewByStudentid(a.getStudentid());
//        String pe = personalService.findPersonalEntityByStudentid(surveyEntity.getStudentid()).orElseThrow().getPerson();
        String pe = personalService.findPersonalEntityByStudentid(a.getStudentid()).orElseThrow().getName();

        //dohi
        AnswerRequest ar =AnswerRequest.builder()
                .name(pe)
                .studentid(as.getStudentid())
                .result(as.getResult())
                .build();
        return ar;
    }

}
