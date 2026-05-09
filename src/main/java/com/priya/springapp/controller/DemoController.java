package com.priya.springapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1")
public class DemoController {

	private final JdbcTemplate jdbcTemplate;
	
	public DemoController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@GetMapping("/hello")
	public String test() {
		
		return "hii! the server is running, and we are testing!";
	}
	
	@GetMapping("/getallstudents")
	public List<Student> all() {
		
		String sql = "SELECT id, name, age FROM students ORDER BY id";
			return jdbcTemplate.query(sql, (resultSet, rowNum) -> new  Student(
					resultSet.getLong("id"),
					resultSet.getString("name"),
					resultSet.getInt("age")));
	}
	
	@PostMapping("/createstudents")
	public ResponseEntity<String> createStudent(@RequestBody Student student){
		String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
		int rows = jdbcTemplate.update(sql, student.name(), student.age());
		
		if (rows>0) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Student record entered successfully");
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student record entry failed");
	}
	
	public record Student(Long id, String name, Integer age) {
		
	}
	
}
