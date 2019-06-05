package com.lucky.controller;

import com.lucky.dao.CourseDAO;
import com.lucky.entity.Course;
import com.lucky.entity.CourseList;
import com.lucky.entity.CourseMap;
import com.lucky.entity.CourseSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator.
 */
@Controller
public class DataBindController {

    @Autowired
    private CourseDAO courseDAO;

    //基本数据类型
    @RequestMapping(value = "/baseType")
    //添加@ResponseBody会直接将业务方法的返回值响应给客户端，不从jsp页面跳转到/baseType
    //http://localhost:8080/baseType?id=1
    @ResponseBody
    //@RequestParam会将前端请求中id的值取出绑定在int id里面
    //具体业务简单的返回id的值，不做service层的处理
    public String baseType(@RequestParam(value = "id") int id){
        return "id: "+id;
    }

    //包装类
    @RequestMapping(value = "/packageType")
    @ResponseBody
    public String packageType(@RequestParam(value = "id") Integer id){
        return "id: "+id;
    }

    //数组类型
    //测试：http://localhost:8080/arrayType?name=zhangsan&name=lisi
    @RequestMapping(value = "/arrayType")
    @ResponseBody
    public String arrayType(String[] name){
        StringBuffer sbf = new StringBuffer();
        for (String item:name){
            sbf.append(item).append(" ");
        }
        return sbf.toString();
    }

    //对象类型
    @RequestMapping(value = "/pojoType")
    public ModelAndView pojoType(Course course){
        courseDAO.add(course);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    //List类型：不能绑定到List集合本身，需要一个包装类CourseList
    @RequestMapping(value = "/listType")
    public ModelAndView listType(CourseList courseList){
        for(Course course:courseList.getCourses()){
            courseDAO.add(course);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    //Map类型：不能绑定到Map集合本身，需要一个包装类CourseMap
    @RequestMapping(value = "/mapType")
    public ModelAndView mapType(CourseMap courseMap){
        for(String key:courseMap.getCourses().keySet()){
            Course course = courseMap.getCourses().get(key);
            courseDAO.add(course);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    //Set类型：不能绑定到Set集合本身，需要一个包装类CourseSet
    @RequestMapping(value = "/setType")
    public ModelAndView setType(CourseSet courseSet){
        for (Course course:courseSet.getCourses()){
            courseDAO.add(course);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    //Json类型
    @RequestMapping(value = "/jsonType")
    @ResponseBody
    //@RequestBody将json格式的数据绑定到Course中
    public  Course jsonType(@RequestBody  Course course){
        course.setPrice(course.getPrice()+100);
        return course;
    }
}
