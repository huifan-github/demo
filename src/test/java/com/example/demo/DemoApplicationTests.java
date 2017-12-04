package com.example.demo;

import com.example.demo.controller.HelloWorldController;
import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Test
	public void contextLoads() {
	}
	/*简单的http请求来测试*/
	private MockMvc mvc;

	// 在测试开始前初始化工作
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}
	@Test
	public void getHello() throws Exception {

		/*
	get测试方法:
		*  MvcResult result = mockMvc.perform(post("/page").param("pageNo", "1").param("pageSize", "2"))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());

    post测试方法:
         Map<String, Object> map = new HashMap<>();
        map.put("address", "合肥");
        MvcResult result = mockMvc.perform(post("/q1?address=合肥").content(JSONObject.toJSONString(map)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());
		* */
		MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON_UTF8))
				// 模拟向testRest发送get请求
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();// 返回执行请求的结果
		System.err.println(result.getResponse().getContentAsString());
	}

	@Autowired
	private UserRepository userRepository;

	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
	String formattedDate = dateFormat.format(date);
	@Test
	public void test() throws Exception {
		userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
		userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
		userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));
		//Assert.assertEquals(9, userRepository.findAll().size());
		//Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
		//userRepository.delete(userRepository.findByUserName("aa1"));
	}
	@Test
	public void testBaseQuery() throws Exception {
		User user=new User();
		user.setUserName("admin");
		user.setPassWord("123");
		user.setEmail("123@qq.com");
		user.setNickName("dudu");
		user.setRegTime(formattedDate);
		System.err.println(userRepository.findAll());;
		System.err.println(userRepository.findOne(1l));;
		//userRepository.save(user);
		//userRepository.delete(user);
		System.err.println(userRepository.count());;
		System.err.println(userRepository.exists(1l));;
	}
	@Test
	public void userNameBaseQuery() throws Exception {
		System.err.println(userRepository.findByUserNameOrEmail("admin","asdad"));
		System.err.println(userRepository.findByPassWord("aa@126.com"));
		System.err.println(userRepository.countByUserName("aa1"));
		System.err.println(userRepository.findByEmailLike("%@%"));
		System.err.println(userRepository.findByUserNameIgnoreCase("AA1"));
		System.err.println(userRepository.findByUserNameOrderByEmailDesc("aa1"));
		System.err.println(userRepository.findByUserNameAndEmail("aa1","bb"));
		System.err.println(userRepository.findByNickNameIsNull());
		System.err.println(userRepository.findAll());
		int page=1,size=2;
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page, size, sort);
		System.err.println(userRepository.findAll(pageable));
		//userRepository.deleteByUserId(5l);
	}

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("13201692120@163.com");
		//给自己发送一份,可以解决被当成垃圾邮件的问题.
		message.setTo("13201692120@163.com");
        //message.setTo("3025463144@qq.com");
        message.setTo("huifan@hollycrm.com");
		message.setSubject("新建的邮箱");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}
}
