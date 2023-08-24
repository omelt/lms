package com.lc.lms;

import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lc.lms.Service.AdminService;
import com.lc.lms.Service.BookService;
import com.lc.lms.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LmsApplicationTests {


	@Autowired
	private BookService bookService;

	@Autowired
	private AdminService adminService;

	@Value("${lms.borrow-day}")
	String borrowDay;

	@Test
	void contextLoads() {
		System.out.println(adminService.list());
		System.out.println(adminService.loadUserByUsername("roo1t"));
		System.out.println(1);
	}

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		String before = "123";
		String after=passwordEncoder.encode(before);
		System.out.println(passwordEncoder.matches(before,"$2a$10$AQol1A.LkxoJ5dEzS5o5E.QG9jD.hncoeCGdVaMQZaiYZ98V/JyRq"));
		System.out.println(after);
		System.out.println(after.length());
	}

}
