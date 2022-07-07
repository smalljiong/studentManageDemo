package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student implements Comparable<Student>,Serializable {

    private static final long serialVersionUID = 1668668426706800192L;

    private String num;//学号
    private String name;//姓名
    private String age;//年龄
    private String sClass;//班级

//    private List<Course> course =new ArrayList<Course>();//课程
//    private List<Score> score =new ArrayList<Score>();//成绩

    public Student() {
    }

//    public void setCourses() {
//        ArrayList<Course> c1=new ArrayList<Course>();
//        ArrayList<Score> s1=new ArrayList<Score>();
//        Scanner sc = new Scanner(System.in);
//        Scanner sc1 =new Scanner(System.in);
//        System.out.println("请输入课程的数量：");
//        int num = sc.nextInt();
//        for (int i = 0;i<num;i++){
//            System.out.println("请输入第"+i+"个课程的名称和成绩：");
//            String cname=sc.next();
//            double cscore=sc1.nextDouble();
//            c1.add(i,new Course(cname));
//            s1.add(i,new Score(cscore));
//        }
//        // 方法中实例化的course类传给student的course属性
//        this.course = c1;
//        // 方法中实例化的score类传给student的score属性
//        this.score = s1;
//    }

//    public void getCourses(String num){
//        System.out.println("以下是"+num+"号学生"+this.name+"的课程：");
//        List<Course> list=new ArrayList<Course>();
//        list=this.course;
//        for (int i=0;i<list.size();i++){
//            if
//            System.out.println("课程名为："+list.get(i).getCname());
//        }
//    }
//
//    public void getScores(){
//        System.out.println("以下是"+this.num+"号学生"+this.name+"的成绩：");
//        List<Score> list=new ArrayList<Score>();
//        list=this.score;
//        for (int i=0;i<list.size();i++){
//            System.out.println("成绩为："+list.get(i).getSscore());
//        }
//    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public int compareTo(Student o) {
        return this.num.compareTo(o.num);
    }



    @Override
    public String toString() {
        return "Student{" +
                "num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sClass='" + sClass + '\'' +
//                ", course=" + course +
                '}';
    }
}