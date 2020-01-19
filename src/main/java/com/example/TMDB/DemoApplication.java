package com.example.TMDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.ui.Model;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@SpringBootApplication
public class DemoApplication {
	
	@GetMapping("/tmdb")
    public String tmdbForm(Model model) {
      model.addAttribute("tmdb", new tmdb());
      return "tmdb";
    }
    @PostMapping("/tmdb")
    public String tmdbSubmit(@ModelAttribute tmdb tmdb) throws IOException {
      return "result";
    }
	
	
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

    }        
}