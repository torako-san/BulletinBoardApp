package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Report;
import com.example.demo.Service.TopService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TopController {
	
	@Autowired
	TopService topService;
	
	@GetMapping
	public ModelAndView top( ) {
		ModelAndView mav = new ModelAndView();
		// 投稿を全件取得
		List<Report> contentData = topService.findAllReport();
		//画面遷移先を指定
		mav.setViewName("/top");
		//投稿データオブジェクトを保管
		mav.addObject("contents", contentData);
		
		return mav;
	
	}
	
	@PostMapping("/add")
	@ResponseBody
	public String addContent(@RequestParam String content) {		
		Report report = new Report();	
		
		report.setContent(content);
		// 投稿をテーブルに格納
		topService.saveReport(report);
		
		// コメントを取得
		Report LatestPort = topService.findSelectedPost();
		
		return GetJson(LatestPort);
			
	}
	
	@GetMapping("/Edit/{id}")
	@ResponseBody
	public String editContent(@PathVariable Integer id) {
		Report report = topService.GetReport(id);
		return getEditJson(report);
	}
	
	private String GetJson(Report LatestPost) {
		String retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(LatestPost);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}
	
	@PutMapping("/Updata/{id}")
	@ResponseBody
	public String updateContent (@PathVariable Integer id, @RequestParam String content) {
		
		Report report = new Report();
		
		report.setContent(content);
		report.setId(id);
		
		topService.saveReport(report);
		
		Report UserPost = topService.GetReport(id);
		
		return UpdateJson(UsersPost);
	}
	
	private String UpdateJson(Report UsersPost) {
		String retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(UsersPost);
		} catch (JsonProcessingException e) {
			System.err.println(e);
		}
		return retVal;
	}
}