package com.example.TMDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class tmdb {
    private String content;
    public String getContent() {
      return content;
    }
    public void setContent(String content) throws IOException { 
      URL url = new URL("http://api.themoviedb.org/3/discover/movie?api_key=f1ee1ec938670ab34a48c865b0700437&primary_release_year="+content+"&sort_by=popularity.desc");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setDoOutput(true);
      con.setRequestMethod("GET");
      con.setRequestProperty("Content-Type", "application/json");

      BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
      String output = br.readLine();
          
          JSONObject jsonobject = new JSONObject(output);
          JSONArray jsonarray = jsonobject.getJSONArray("results");
          
          for (int i = jsonarray.length()-1; i >=0; i--) {
          	jsonobject = jsonarray.getJSONObject(i);
          	
          	String titel = jsonobject.getString("original_title");
          	System.out.println("i="+i);

          	content=i+1+". "+titel+"\r\n"+content;
          	this.content = content;

          	System.out.println("s"+this.content);
          }
 
    }
    
}