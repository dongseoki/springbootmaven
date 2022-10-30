package com.example.springbootmaven;

import com.example.springbootmaven.test.User;
import com.jam2in.arcus.app.common.aop.ArcusCache;
import com.jam2in.arcus.app.common.aop.ArcusCacheKey;
import com.jam2in.arcus.app.common.aop.ArcusCacheKeyParameter;
import com.jam2in.arcus.app.common.key.ArcusCacheKeyDate;
import com.jam2in.arcus.app.common.recaching.ArcusRecachingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService2 {
    @Autowired
    ApplicationContext applicationContext;
    private final Map<String, String> testDataMap = new HashMap<>();
    private final Map<String, User> testUserMap = new HashMap<>();

    @PostConstruct
    public void postMethod(){
//        System.out.println("hello");
//
//        String[] beans = applicationContext.getBeanDefinitionNames();
//
//        for (String bean : beans) {
//            System.out.println("bean : " + bean);
//        }
        for (int i = 1; i < 15; i++) {
            testUserMap.put(Long.toString(i),new User(i,"u"+i));
        }

        //for case6
        for (int i = 1; i < 6; i++) {
            testUserMap.put(""+ i +" u"+i ,new User(i,"u"+i));
        }
    }



    // guide 시작.
    public User getCaseStrange(long id) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return testUserMap.get(Long.toString(id));
    }
}
