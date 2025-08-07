package com.by.test;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Collections;

public class test {
    public static void main(String[] args) {
        Boy boy1=Boy.builder().id(1).age(23).name("张1-23").build();
        Boy boy2=Boy.builder().id(2).age(48).name("张2-48").build();
        Boy boy3=Boy.builder().id(3).age(35).name("张3-35").build();
        ArrayList<Boy> boyList= CollUtil.newArrayList(boy1,boy2,boy3);
        Collections.sort(boyList, (o1, o2) -> o1.getAge()-o2.getAge());


        String a="";
    }
}
