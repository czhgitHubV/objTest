package com.czh;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author:chenzhihua
 * @Date: 2020/11/30 11:01
 * @Deacription:
 **/
public class ArrayListDemo {
    public static void main(String[] args) {
//        ArrayList<String> list=new ArrayList<String>();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//
//        System.out.println(list);
//        list.add(1, "111");
//        System.out.println(list);
//
//        boolean aaa = list.remove("aaa");
//        System.out.println(aaa);
//        String remove = list.remove(0);
//        System.out.println(remove);
//        list.set(0, "111");
//        System.out.println(list);
//        int indexOf = list.indexOf("222");
//        int indexOf1 = list.indexOf("111");
//        System.out.println(indexOf);
//        System.out.println(indexOf1);
/*

//        方法 1：使用Arrays.asList进行初始化
        ArrayList<String> list=new ArrayList<String>(Arrays.asList("Pratap", "Peter", "Harsh"));
        System.out.println(list);

//        方法 2：初始化ArrayList的匿名内部类方法
        ArrayList<String> list1=new ArrayList<String>(){
            {
                add("111");
                add("222");
                add("333");
            }
        };

        System.out.println(list1);

//        方法 4：使用Collections.ncopies
        ArrayList<String> strlist=new ArrayList<String>(Collections.nCopies(5, "czh"));
        System.out.println(strlist);
*/

        //遍历
        /*ArrayList<Integer> bli=new ArrayList<Integer>(Arrays.asList(1,2323,2,343,5645,34));
        Enumeration<Integer> enumeration = Collections.enumeration(bli);
        while (enumeration.hasMoreElements()){
            Integer integer = enumeration.nextElement();
            System.out.println(integer);
        }
        Collections.sort(bli);
        System.out.println(bli);*/
        fun2();
    }

    static void fun2(){
        ArrayList<String> a1=new ArrayList<String>();
        a1.add("Steve");
        a1.add("Justin");
        a1.add("Ajeet");
        a1.add("John");
        a1.add("Arnold");
        a1.add("Chaitanya");
        ArrayList<String> a2=new ArrayList<String>(a1.subList(1,4));
        System.out.println(a2);
        List<String> list = a1.subList(1, 3);
        System.out.println(list);


    }

    static void fun(){
        ArrayList<String> users=new ArrayList<String>(
                Arrays.asList("1,张三,98","2,李四,56","3,王五,78","4,张三,69","5,李四,99","6,张三,15"));
        Enumeration<String> enumeration = Collections.enumeration(users);
        while (enumeration.hasMoreElements()){
            String s = enumeration.nextElement();
            System.out.println(s);
        }

        List<Person> personList=new ArrayList<Person>();
        for (String user:users) {
            String[] split = user.split(",");
            Integer id=Integer.parseInt(split[0]);
            String name=split[1];
            Integer score=Integer.parseInt(split[2]);

            Person p=new Person(id,name,score);
            personList.add(p);
        }

        Map<String, List<Person>> listMap = personList.stream().collect(Collectors.groupingBy(Person::getName));
        System.out.println(listMap);
        List<Map<String, String>> qtyList = new ArrayList<Map<String, String>>();
        listMap.forEach((key,klist)->{
            IntSummaryStatistics collect = klist.stream().collect(Collectors.summarizingInt(e -> e.score));
            Map<String, String> qtyMap = new HashMap<String, String>();
            qtyMap.put("avg", String.format("%.2f",collect.getAverage()));
            qtyMap.put("id", key);
            qtyList.add(qtyMap);
        });
        System.out.println(qtyList);

        //不使用Lambda表达式的基本排序
        /*Collections.sort(qtyList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                return o2.get("avg").compareTo(o1.get("avg"));
            }
        });*/

//        qtyList.sort((Map p1,Map p2)->p1.get("avg").toString().compareTo(p2.get("avg").toString()));
        qtyList.sort(Comparator.comparing(m->m.get("avg")));

        for (Map m:qtyList) {
            System.out.println(m.get("id")+"-"+m.get("avg"));
        }

    }
}

class Person{
    Integer id;
    String name;
    Integer score;

    public Person(Integer id,String name,Integer score){
        this.id=id;
        this.name=name;
        this.score=score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
