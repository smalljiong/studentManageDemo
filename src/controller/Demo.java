package controller;

import domain.Course;
import domain.Score;
import domain.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Demo {

    // 设置用户名和密码
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "123";
    private static ArrayList<Student> studentList = new ArrayList<Student>();//静态studentList集合存储学生信息
    private static ArrayList<Course> courseList = new ArrayList<Course>();//静态courseList集合存储课程信息
    private static ArrayList<Score> scoreList = new ArrayList<Score>();//静态scoreList集合存储成绩信息

    public static void main(String[] args) throws IOException {

        //先从文件中读入原来保存过的学生数据
        readInformationFromFile("1");
        //先从文件中读入原来保存过的课程数据
        readInformationFromFile("2");

        readInformationFromFile("3");

        int n = 3;
        for (int i = 0; i < n; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.print("请输入用户名:");
            String sUsername = sc.nextLine();
            // 获取输入的用户名
            System.out.print("请输入密码:");
            // 获取输入的密码
            String sPassword = sc.nextLine();
            // 将输入的用户名和密码进行对比
            if (USERNAME.equals(sUsername) && PASSWORD.equals(sPassword)) {
                // 如果相同就直接显示登陆成功，退出循环
                System.out.println("登陆成功！");
                break;
            } else {
                if (i == 2){
                    // 第三次输入的用户名和密码对不上直接输出达到次数上限，结束循环
                    System.out.println("登录次数已经达到上限。");
                    System.exit(0);
                } else {
                    System.out.println("用户名或者密码错误，还有"+(2-i)+"次机会!!!");
                }
            }
        }

        Scanner temp = new Scanner(System.in);

        while (true) {
            System.out.println("请选择操作");
            System.out.println("1 查看所有学生");
            System.out.println("2 添加学生信息");
            System.out.println("3 查看所有课程");
            System.out.println("4 添加课程信息");
            System.out.println("5 修改课程信息");
            System.out.println("6 删除课程信息");
            System.out.println("7 添加学生课程分数");
            System.out.println("8 修改学生课程分数");
            System.out.println("9 删除学生课程分数");
            System.out.println("10 查看学生分数信息");
            System.out.println("11 退出系统");
            //输入选项
            String choice = temp.nextLine();
            //判断
            switch (choice) {
                case "1":
                    show(studentList);
                    System.out.println("系统中共存有"+ studentList.size()+"个学生");
                    break;
                case "2":
                    add(studentList);
                    break;
                case "3":
                    showCourses(courseList);
                    System.out.println("系统中共存有"+ courseList.size()+"条课程信息");
                    break;
                case "4":
                    addCourses(courseList);
                    break;
                case "5":
                    modifyCourses(courseList);
                    //修改数据之后立即更新硬盘文件
                    writeInformationToFile(courseList,"2");
                    break;
                case "6":
                    deleteCourses(courseList);
                    //修改数据之后立即更新硬盘文件
                    writeInformationToFile(courseList,"2");
                    writeInformationToFile(scoreList,"3");
                    break;
                case "7":
                    addScores(scoreList);
                    break;
                case "8":
                    modifyScores(scoreList);
                    //修改数据之后立即更新硬盘文件
                    writeInformationToFile(scoreList,"3");
                    break;
                case "9":
                    deleteScores(scoreList);
                    //修改数据之后立即更新硬盘文件
                    writeInformationToFile(scoreList,"3");
                    break;
                case "10":
                    showScores(studentList,courseList,scoreList);
                    System.out.println("系统中共存有"+ scoreList.size()+"条分数信息");
                    break;
                case "11":
                    System.out.println("系统已成功退出");
                    System.exit(0);
                default:
                    System.out.println("您的输入有误，请重新选择！");
            }
        }
    }

    //查询学生信息方法
    private static void show(ArrayList<Student> array){
        System.out.println("共储存了"+array.size()+"名学生信息");
        if(array.size() == 0){
            System.out.println("暂时未存储任何学生信息，请重新选择。");
            return;
        }
        for(int i=0;i<array.size();i++){
            Student s = array.get(i);
            System.out.println("第"+(i+1)+"位同学的学号为"+s.getNum()+"，姓名为"+s.getName()+"，年龄为"+s.getAge()+"，班级为"+s.getsClass());
        }
    }

    //添加学生信息方法
    private static void add(ArrayList<Student> array){
        Scanner temp = new Scanner(System.in);
        String num;
        while(true){
            System.out.println("请输入新同学学号：");
            num = temp.nextLine();
            //判断是否重复学号
            boolean flag = false;//默认未重复
            for(int i=0;i<array.size();i++){
                Student s = array.get(i);
                if(s.getNum().equals(num)){
                    System.out.println("该学号已经存在，请重新输入！");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                break;
            }
        }
        System.out.println("请输入新同学姓名：");
        String name = temp.nextLine();
        System.out.println("请输入新同学年龄：");
        String age = temp.nextLine();
        System.out.println("请输入新同学所在班级：");
        String city = temp.nextLine();
        Student s = new Student();
        s.setNum(num);
        s.setName(name);
        s.setAge(age);
        s.setsClass(city);
        array.add(s);
        System.out.println("添加学生信息成功！");

        writeInformationToFile(array,"1");

    }

    //删除学生信息方法
    private static void delete(ArrayList<Student> array){
        Scanner temp = new Scanner(System.in);
        System.out.println("请输入需要删除的学生学号：");
        String num = temp.nextLine();
        for(int i=0;i<array.size();i++){
            Student s = array.get(i);
            if(s.getNum().equals(num)){
                System.out.println("请确认删除信息(Y/N)：");
                  System.out.println("第"+(i+1)+"位同学的学号为"+s.getNum()+"，姓名为"+s.getName()+"，年龄为"+s.getAge()+"，班级为"+s.getsClass());
//                s.getCourses();
                String confirm = temp.nextLine();
                //判断确认删除
                if(confirm.equals("y") || confirm.equals("Y")){
                    if(array.remove(s)){
                        System.out.println("删除成功！");
                        return;
                    }
                }else{
                    System.out.println("已取消删除！");
                    return;
                }
            }
        }
        System.out.println("未找到所对应学生学号，请确认学号信息是否有误!");
    }

    //查询课程信息方法
    private static void showCourses(ArrayList<Course> array){
        System.out.println("共储存了"+array.size()+"条课程信息");
        if(array.size() == 0){
            System.out.println("暂时未存储任何课程信息，请重新选择。");
            return;
        }
        for(int i=0;i<array.size();i++){
            Course c = array.get(i);
            System.out.println("第"+(i+1)+"条课程的编号为"+c.getId()+"，课程名称为"+c.getCname());
        }
    }

    //添加课程信息方法
    private static void addCourses(ArrayList<Course> array){
        Scanner sc = new Scanner(System.in);
        String num;
        while(true){
            System.out.println("请输入新课程编号：");
            num = sc.nextLine();
            //判断是否重复课程编号
            boolean flag = false;//默认未重复
            for(int i=0;i<array.size();i++){
                Course c = array.get(i);
                if(c.getId().equals(num)){
                    System.out.println("该课程编号已经存在，请重新输入！");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                break;
            }
        }
        System.out.println("请输入课程名称：");
        String cname = sc.nextLine();
        Course c = new Course();
        c.setId(num);
        c.setCname(cname);
        array.add(c);
        System.out.println("添加课程信息成功！");

        writeInformationToFile(array,"2");

    }

    //修改课程信息方法
    private static void modifyCourses(ArrayList<Course> array){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要修改的课程编号：");
        String num = sc.nextLine();
        for(int i=0;i<array.size();i++){
            Course c = array.get(i);
            if(c.getId().equals(num)){
                System.out.println("请输入修改后的课程名称：");
                String cname = sc.nextLine();
                System.out.println("请确认修改信息(Y/N)：");
                System.out.println("修改后课程的编号为"+c.getId()+"的课程名称修改为"+cname);
                String confirm = sc.nextLine();
                //判断确认修改
                if(confirm.equals("y") || confirm.equals("Y")){
//                    if(array.remove(c)){
                    c.setCname(cname);
                    array.set(i,c);
                    System.out.println("修改成功！");
                    return;
//                    }
                }else{
                    System.out.println("已取消修改！");
                    return;
                }
            }
        }
        System.out.println("未找到所对应课程编号，请确认课程信息是否有误!");
    }

    //删除课程信息方法
    private static void deleteCourses(ArrayList<Course> array){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的课程编号：");
        String num = sc.nextLine();
        for(int i=0;i<array.size();i++){
            Course c = array.get(i);
            if(c.getId().equals(num)){
                System.out.println("请确认删除信息(Y/N)：");
                System.out.println("第"+(i+1)+"条课程的编号为"+c.getId()+"，课程名称为"+c.getCname());
                String confirm = sc.nextLine();
                //判断确认删除
                if(confirm.equals("y") || confirm.equals("Y")){
                    if(array.remove(c)){
                        for (int j = 0; j < scoreList.size(); j++) {
                            if (scoreList.get(j).getCid().equals(c.getId())){
                                scoreList.remove(j);
                            }
                        }
                        System.out.println("删除成功！");
                        return;
                    }
                }else{
                    System.out.println("已取消删除！");
                    return;
                }
            }
        }
        System.out.println("未找到所对应课程编号，请确认课程信息是否有误!");
    }

    //添加分数信息方法
    private static void addScores(ArrayList<Score> array){
        Scanner sc = new Scanner(System.in);
        String sid;
        String cid;
        while(true){
            System.out.println("请输入要添加分数的学生编号：");
            sid = sc.nextLine();
            System.out.println("请输入要添加分数的课程编号：");
            cid = sc.nextLine();
            //判断是否重复添加分数
            boolean flag = false;
            //确认不存在及重复的意外情况
            for(int i=0;i<array.size();i++){
                Score s = array.get(i);
                if(s.getSid().equals(sid) && s.getCid().equals(cid)){
                    System.out.println("该学生此课程分数已经存在，请重新输入！");
                    flag = true;
                    break;
                }
            }
            if(!flag){
                break;
            }
        }
        System.out.println("请输入分数：");
        String score = sc.nextLine();
        if (score.equals("") || score == null){
            System.out.println("您没有输入分数请重新输入分数：");
            score = sc.nextLine();
        }
        Score s = new Score();
        s.setSid(sid);
        s.setCid(cid);
        s.setSscore(Double.parseDouble(score));
        array.add(s);
        System.out.println("添加课程分数成功！");

        writeInformationToFile(array,"3");
    }

    //修改分数信息方法
    private static void modifyScores(ArrayList<Score> array){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要修改的学生编号：");
        String sid = sc.nextLine();
        System.out.println("请输入需要修改的课程编号：");
        String cid = sc.nextLine();
        for(int i=0;i<array.size();i++){
            Score s = array.get(i);
            if(s.getSid().equals(sid) && s.getCid().equals(cid)){
                System.out.println("请输入修改后的课程分数：");
                String score = sc.nextLine();
                System.out.println("请确认修改信息(Y/N)：");
                System.out.println("修改后学生的编号为"+s.getSid()+"的课程编号为"+s.getCid()+"的分数为"+score);
                String confirm = sc.nextLine();
                //判断确认修改
                if(confirm.equals("y") || confirm.equals("Y")){
                    s.setSscore(Double.parseDouble(score));
                    array.set(i,s);
                    System.out.println("修改成功！");
                    return;
                }else{
                    System.out.println("已取消修改！");
                    return;
                }
            }
        }
        System.out.println("未找到所对应此学生此门课程的分数，请确认信息是否有误!");
    }

    //删除分数信息方法
    private static void deleteScores(ArrayList<Score> array){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的学生编号：");
        String sid = sc.nextLine();
        System.out.println("请输入需要删除的课程编号：");
        String cid = sc.nextLine();
        for(int i=0;i<array.size();i++){
            Score s = array.get(i);
            if(s.getSid().equals(sid) && s.getCid().equals(cid)){
                System.out.println("请确认删除信息(Y/N)：");
                System.out.println("要删除的学生编号为"+s.getSid()+"，要删除的课程的编号为"+s.getCid()+"，要删除的课程的分数为"+s.getSscore());
                String confirm = sc.nextLine();
                //判断确认删除
                if(confirm.equals("y") || confirm.equals("Y")){
                    if(array.remove(s)){
                        System.out.println("删除成功！");
                        return;
                    }
                }else{
                    System.out.println("已取消删除！");
                    return;
                }
            }
        }
        System.out.println("未找到该学生编号该课程的成绩，请确认信息是否有误!");
    }

    //查询分数信息方法
    private static void showScores(ArrayList<Student> arrayStudent,ArrayList<Course> arrayCourse,ArrayList<Score> arrayScore){
        Map<String,Student> mapStudent = new HashMap<>();
        for (Student student:arrayStudent) {
            mapStudent.put(student.getNum(),student);
        }
        Map<String,Course> mapCourse = new HashMap<>();
        for (Course course:arrayCourse) {
            mapCourse.put(course.getId(),course);
        }
        for (int i = 0; i < arrayScore.size(); i++) {
            Score s = arrayScore.get(i);
            Student student = mapStudent.get(s.getSid());
            Course course = mapCourse.get(s.getCid());
            System.out.println("第"+(i+1)+"条:学生编号为"+student.getNum()+"，学生姓名为"+student.getName()+"，课程名称为"+course.getCname()+"，课程分数为"+s.getSscore()+"分");
        }
    }

    //将信息写入文件
    private static void writeInformationToFile(ArrayList array,String flag){
        File file = new File("");
        switch (flag) {
            case "1" :
                file = new File("d://javaDemo//students.txt");
                break;
            case "2":
                file = new File("D://javaDemo//courses.txt");
                break;
            case "3":
                file = new File("d://javaDemo//scores.txt");
                break;
        }

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        ObjectOutputStream oOut=null;
        try {
            oOut=new ObjectOutputStream(new FileOutputStream(file));
            oOut.writeObject(array);//将add到array的score对象写入
            oOut.close();
            System.out.println("写入成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //从文件读取信息
    private static void readInformationFromFile(String flag){
        File file= new File("");
        switch (flag) {
            case "1" :
                file= new File("D://javaDemo//students.txt");
                break;
            case "2" :
                file= new File("D://javaDemo//courses.txt");
                break;
            case "3" :
                file= new File("D://javaDemo//scores.txt");
                break;
        }

        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("初次启动初始化完成！");
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            switch (flag) {
                case "1" :
                    System.out.println("初次启动学生信息初始化完成！");
                    break;
                case "2" :
                    System.out.println("初次启动课程信息初始化完成！");
                    break;
                case "3" :
                    System.out.println("初次启动分数信息初始化完成！");
                    break;
            }
        }
        ObjectInputStream in=null;
        try {
            switch (flag) {
                case "1" :
                    in=new ObjectInputStream(new FileInputStream("D://javaDemo//students.txt"));
                    break;
                case "2" :
                    in=new ObjectInputStream(new FileInputStream("D://javaDemo//courses.txt"));
                    break;
                case "3" :
                    in=new ObjectInputStream(new FileInputStream("D://javaDemo//scores.txt"));
                    break;
            }
            Object object=in.readObject();
            switch (flag) {
                case "1" :
                    studentList=(ArrayList<Student>)object;//读入的是Object类型，需要强制类型转换
                    if (studentList.size()==0) {
                        System.out.println("未读取到学生数据");
                    } else {
                        studentList =(ArrayList<Student>)studentList;
                    }
                    System.out.println("从指定文件读取学生数据完毕");
                    break;
                case "2" :
                    courseList=(ArrayList<Course>)object;//读入的是Object类型，需要强制类型转换
                    if (courseList.size()==0) {
                        System.out.println("未读取到课程数据");
                    } else {
                        courseList =(ArrayList<Course>)courseList;
                    }
                    System.out.println("从指定文件读取课程数据完毕");
                    break;
                case "3" :
                    scoreList=(ArrayList<Score>)object;//读入的是Object类型，需要强制类型转换
                    if (scoreList.size()==0) {
                        System.out.println("未读取到学生数据");
                    } else {
                        scoreList =(ArrayList<Score>)scoreList;
                    }
                    System.out.println("从指定文件读取分数数据完毕");
                    break;
            }
        } catch (EOFException e){
            switch (flag) {
                case "1" :
                    System.out.println("学生数据读入完毕，没有数据可读了");
                    break;
                case "2" :
                    System.out.println("课程数据读入完毕，没有数据可读了");
                    break;
                case "3" :
                    System.out.println("成绩数据读入完毕，没有数据可读了");
                    break;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
