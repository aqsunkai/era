package com.sun.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加载yaml配置文件的方法
 * Created by sun on 2017-1-15.
 * spring-boot更新到1.5.2版本后locations属性无法使用
 * @PropertySource注解只可以加载proprties文件,无法加载yaml文件
 * 故现在把数据放到application.yml文件中,spring-boot启动时会加载
 */
@Component
//@ConfigurationProperties(locations = {"classpath:config/myProps.yml"},prefix = "myProps")
@ConfigurationProperties(prefix = "myYml")
public class YmlConfig {

    String simpleProp;
    private String[] arrayProps;
    private List<Map<String, String>> listProp1 = new ArrayList<>(); //接收prop1里面的属性值
    private List<String> listProp2 = new ArrayList<>(); //接收prop2里面的属性值
    private Map<String, String> mapProps = new HashMap<>(); //接收prop1里面的属性值

    public String getSimpleProp() {
        return simpleProp;
    }

    //String类型的一定需要setter来接收属性值；maps, collections, 和 arrays 不需要
    public void setSimpleProp(String simpleProp) {
        this.simpleProp = simpleProp;
    }

    public String[] getArrayProps() {
        return arrayProps;
    }

    public void setArrayProps(String[] arrayProps) {
        this.arrayProps = arrayProps;
    }

    public List<Map<String, String>> getListProp1() {
        return listProp1;
    }

    public void setListProp1(List<Map<String, String>> listProp1) {
        this.listProp1 = listProp1;
    }

    public List<String> getListProp2() {
        return listProp2;
    }

    public void setListProp2(List<String> listProp2) {
        this.listProp2 = listProp2;
    }

    public Map<String, String> getMapProps() {
        return mapProps;
    }

    public void setMapProps(Map<String, String> mapProps) {
        this.mapProps = mapProps;
    }
}
