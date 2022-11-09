package com.example.springbootmaven.test;

import com.example.springbootmaven.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class TestRestController {
    @Autowired
    ApplicationContext applicationContext;
    private final TestService testService;
    private final TestService2 testService2;

    public TestRestController(TestService testService, TestService2 testService2) {
        this.testService = testService;
        this.testService2 = testService2;
    }

    @GetMapping("/hello")
    public String hello(Model model){
        return "hello success";
    }


    @GetMapping("/test-rest/arcus-common-module-test/set-test")
    public String arcusCommonModuleSetTest(@RequestParam(value = "testKey") String testKey, @RequestParam(value="testData") String testData){
        testService.setTestData(testKey, testData);
        return "success";
    }

    @GetMapping("/test-rest/arcus-common-module-test/get-test")
    public String arcusCommonModuleGetTest(@RequestParam(value = "testKey") String testKey){
        String result = testService.getTestData(testKey);
        return result;
    }

    @GetMapping("/user/{id}")
    public String arcusUserTest(@PathVariable long id) {
        testService.getCase1(id);
        return "end";
    }

    @GetMapping("/test-rest/arcus-common-module-test/poc")
    public String poc(@RequestParam(value = "id", required = false) Integer id){
        String result = "";
        Random randomV = new Random();
        result = ""+randomV.nextInt(100);
        return result;
    }

    @GetMapping(value = {"/tv/main/main-info",
                            "/tv/main/broad-goods-info",
                            "/tv/display/tv-vod-list",
                            "/cloud/main/main-info"})
    public String skstoademo() throws InterruptedException {
        String result = "end";
        int id = 2;
        String name = "u2";

        testService.getCase5(new User(id, name), id);
        return result;
    }

    @GetMapping("/test-rest/arcus-common-module-test/get-user")
    public String arcusCommonModuleGetUser(@RequestParam(value = "caseType") String caseType,
                                           @RequestParam(value = "id", required = false) Integer id,
                                           @RequestParam(value="name", required = false)String name,
                                           @RequestParam(value="data", required = false)String data) throws InterruptedException {
        String result = "end";
//        String[] names = applicationContext.getBeanDefinitionNames();
//        for (String value :
//                names) {
//            System.out.println("value = " + value);
//        }
        switch (caseType){
            case "1":
                testService.getCase1(id);
                break;
            case "5":
                testService.getCase5(new User(id, name), id);
                break;
            case "6":
                int[] dataArr = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
                testService.getCase6(dataArr);
                break;
            case "10":
                Map<String, Object> tempMap = new HashMap();
                tempMap.put("id",id);
                tempMap.put("name",name);
                testService.getCase10(tempMap);
                break;
            case "97":
                result = "int value : "+ testService.getInteger(id);
                break;
            case "98":
                result = testService.getString(id);
                break;
            case "99":
                testService2.getCaseStrange(id);
                break;
        }
        return result;
    }
}
