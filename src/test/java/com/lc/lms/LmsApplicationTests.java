package com.lc.lms;

import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lc.lms.Service.AdminService;
import com.lc.lms.Service.BookService;
import com.lc.lms.Service.BorrowService;
import com.lc.lms.Service.UserService;
import com.lc.lms.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class LmsApplicationTests {


	@Autowired
	private BookService bookService;

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private BorrowService borrowService;

	@Value("${lms.borrow-day}")
	String borrowDay;

	@Test
	void contextLoads() {
		System.out.println(adminService.list());
		System.out.println(bookService.list());
		System.out.println(borrowService.list());
		System.out.println(userService.list());
		System.out.println(1);
	}

	public static void main(String[] args) {

		Date now = new Date();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 30);

		System.out.println(sf.format(c.getTime()));
		System.out.println(sf.format(now));

		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		String before = "123";
		String after=passwordEncoder.encode(before);
		System.out.println(passwordEncoder.matches(before,"$2a$10$AQol1A.LkxoJ5dEzS5o5E.QG9jD.hncoeCGdVaMQZaiYZ98V/JyRq"));
		System.out.println(after);
		System.out.println(after.length());
	}

}
