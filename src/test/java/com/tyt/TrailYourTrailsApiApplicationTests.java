package com.tyt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tyt.TrailYourTrailsApiApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TrailYourTrailsApiApplication.class)
@WebAppConfiguration
public class TrailYourTrailsApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
