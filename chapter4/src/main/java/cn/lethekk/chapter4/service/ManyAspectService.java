package cn.lethekk.chapter4.service;

import org.springframework.stereotype.Service;

@Service
public class ManyAspectService {
    public void manyAspects(){
        System.out.println("测试多个切面顺序");
    }

}
