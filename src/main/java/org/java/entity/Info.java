package org.java.entity;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

/**
 * Created by jh on 2017/8/2.
 */
public class Info {
    @Max(value = 3) // 最大值为3
    private int age;
    @Length(max=1,message="{maxlength}") // 字符串长度最大为1,hibernate 扩展的
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
