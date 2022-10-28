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

    @ArcusCache
    public String getTestData(@ArcusCacheKey String testKey){
        return testDataMap.get(testKey);

    }

    public void setTestData(String testKey, String testData){
        testDataMap.put(testKey, testData);
    }


    // guide 시작.
    // 1) APP_USER:case1#20220101
    @ArcusCache(prefix="_USER", expireTime="10",keyDate= ArcusCacheKeyDate.KEY_DATE_DAY, recachingType = ArcusRecachingType.SUS)
    public User getCase1(@ArcusCacheKey long id) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return testUserMap.get(Long.toString(id));
    }

    // 5) APP:user.UserService.case5@ARG0=<id=1,name=arcus>,ARG1=<*=1>
    @ArcusCache
    public User getCase5(
            @ArcusCacheKeyParameter({"id", "name"}) User user,@ArcusCacheKey int userId) {
        return testUserMap.get(user.getId()+" "+user.getName());
    }

    // 6) APP:user.UserService.case6@ARG0=<*=[1,2,3]>
    @ArcusCache
    public List<User> getCase6(@ArcusCacheKey int[] userIds) {
        List<User> li = new ArrayList();
        for (int userId :
                userIds) {
            li.add(testUserMap.get(userId + " u"+userId));
        }
        return li;
    }



//     10) APP:user.UserService.case10@ARG0=<id=1,name=arcus>
    @ArcusCache
    public User getCase10(@ArcusCacheKeyParameter({"id", "name"}) Map<String, Object> user
    ) {
        return testUserMap.get(""+user.get("id").toString() + " "+user.get("name").toString());
    }

}
