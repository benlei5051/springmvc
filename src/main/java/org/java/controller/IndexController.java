package org.java.controller;

import org.java.entity.PagModel;
import org.java.entity.RequestDto;
import org.java.entity.User;
import org.java.entity.Users;
import org.java.entity.WsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jh on 2017/6/21.
 */
@Controller
@RequestMapping("/home")
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/index")
    public String index() {
        return "yes";
    }

    @RequestMapping(value = "/jsonformat1")
    @ResponseBody
    public User jsonController1() {
        return new User(12, "林浩", "1982-12-23");
    }

    @RequestMapping(value = "/jsonformat3", method = RequestMethod.POST)
    @ResponseBody
    public Users jsonController2(@RequestBody Users user) {
        return user;
    }

    @RequestMapping(value = "/stringformat")
    @ResponseBody
    public String stringController() {
        LOG.info("进入此方法{}","-----");
        return "林浩";
    }

    @RequestMapping(value = "/jsonformat2", method = RequestMethod.POST)
    @ResponseBody
    public String stringController(@RequestParam(value = "name") String name) {
        return name;
    }

    // url:   http://localhost:8080/springmvc/home/dateTest?date1=2018-01-01
    @RequestMapping(value = "/dateTest", method = RequestMethod.GET)
    @ResponseBody
    public String dateTest(Date date1) {
        return date1.toString();
    }
    //局部转化
//    @InitBinder("date1")
    @InitBinder  //所有的方法只要是date类型，都会转换，变量date1和date2都起作用
    public void initDate1(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true));
    }
    //全局转化，在配置文件中配置
    @RequestMapping(value = "date2",method = RequestMethod.GET)
    @ResponseBody
    public User date2(Date date2) {
        return new User(12,"林浩", date2.toString());
    }

    @RequestMapping(value = "converter")
    @ResponseBody
    public String converter(Boolean bool){
        return bool.toString();
    }
    @RequestMapping(value = "responseEntity")
    public ResponseEntity<User> testResponseEntity(){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        User user=new User(12, "林浩", "1982-12-23");
        return new ResponseEntity<>(user,headers, HttpStatus.OK);
    }
    @RequestMapping(value = "testRemoteCall")
    public ResponseEntity<WsResponse<PagModel>> testHttpClient(){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","4");
        RequestDto dto=new RequestDto();
        dto.setCurrentPage(1);
        dto.setNumPerPage(10);
        dto.setMessageType("system");
        HttpEntity<RequestDto> httpEntity=new HttpEntity<>(dto,headers);
        ParameterizedTypeReference<WsResponse<PagModel>> responseType=new ParameterizedTypeReference<WsResponse<PagModel>>() {};
        ResponseEntity<WsResponse<PagModel>> rest=restTemplate.exchange("http://localhost:8080/api-service/services/systemMessage/messageListForPortal", HttpMethod.POST, httpEntity,responseType );
        return rest;
    }
}

