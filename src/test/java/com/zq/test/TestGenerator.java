package com.zq.test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.zq.maven.Genetator;



public class TestGenerator {
	@Test
	public  void main() throws Exception {
		System.out.println("jjj");
		Genetator g = new Genetator();
		g.create();
	}
}
