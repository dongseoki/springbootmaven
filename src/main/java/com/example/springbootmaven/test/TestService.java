package com.example.springbootmaven.test;

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
public class TestService {
    @Autowired
    ApplicationContext applicationContext;
    private final Map<String, String> testDataMap = new HashMap<>();
    private final Map<String, User> testUserMap = new HashMap<>();

    private final Map<String, String> strToStrMap = new HashMap<>();

    private final Map<Integer, Integer> intTointMap = new HashMap<>();

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

        for (int i = 1; i < 15; i++) {
            strToStrMap.put(Long.toString(i),Long.toString(i) + "data");
        }
        for (int i = 1; i < 15; i++) {
            intTointMap.put(i,i);
        }
    }


    public String getTestData(String testKey){
        return testDataMap.get(testKey);

    }

    public void setTestData(String testKey, String testData){
        testDataMap.put(testKey, testData);
    }



    public String getString(long id){
        return strToStrMap.get(""+id);
    }

    public int getInteger(long id){
        Long value = id;
        return intTointMap.get(value.intValue());
    }

    // guide 시작.
    // 1) APP_USER:case1#20220101
    public User getCase1(long id) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return testUserMap.get(Long.toString(id));
    }

    // 5) APP:user.UserService.case5@ARG0=<id=1,name=arcus>,ARG1=<*=1>
    public User getCase5(User user, int userId) throws InterruptedException {
        Thread.sleep(2000);
        return testUserMap.get(user.getId()+" "+user.getName());
    }

    // 6) APP:user.UserService.case6@ARG0=<*=[1,2,3]>
    public List<User> getCase6(int[] userIds) {
        List<User> li = new ArrayList();
        for (int userId :
                userIds) {
            li.add(testUserMap.get(userId + " u"+userId));
        }
        return li;
    }



//     10) APP:user.UserService.case10@ARG0=<id=1,name=arcus>
    public User getCase10(Map<String, Object> user) {
        return testUserMap.get(""+user.get("id").toString() + " "+user.get("name").toString());
    }

}
