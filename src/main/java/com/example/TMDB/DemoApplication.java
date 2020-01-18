package com.example.TMDB;

import java.io.BufferedReader;
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
    public String greetingForm(Model model) {
      model.addAttribute("tmdb", new tmdb());
      return "tmdb";
    }
    @PostMapping("/tmdb")
    public String tmdbSubmit(@ModelAttribute tmdb tmdb) {
      System.out.println("id: " + tmdb.getId() + ", content: " + tmdb.getContent());
      return "result";
    }

	
	
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Hello World!";
    }
	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);

		
		//URL url = new URL("http://api.themoviedb.org/3/movie/550?api_key=f1ee1ec938670ab34a48c865b0700437");
		URL url = new URL("http://api.themoviedb.org/3/discover/movie?api_key=f1ee1ec938670ab34a48c865b0700437&sort_by=popularity.desc");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));

        String output = br.readLine();
            //System.out.println(output);
            
            JSONObject jsonobject = new JSONObject(output);
            
            //int budget = jsonobject.getInt("results");
            
            JSONArray jsonarray = jsonobject.getJSONArray("results");
            //System.out.println(jsonarray.length());
            
            
            for (int i = 0; i < jsonarray.length(); i++) {
            	jsonobject = jsonarray.getJSONObject(i);
            	
            	String titel = jsonobject.getString("original_title");
            	double avg = jsonobject.getDouble("vote_average");

            	System.out.print("Titel: "+titel+", ");
            	System.out.println("Betyg: "+avg+", ");

        }
    }        
}