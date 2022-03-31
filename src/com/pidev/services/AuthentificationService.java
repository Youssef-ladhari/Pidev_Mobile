package com.pidev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.pidev.entities.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AuthentificationService {

    public User login(String login, String password) {
        var url = "http://localhost:8000/api/v1/loginJson";
        HttpURLConnection con = null;
        User user = null;
        try {
            JSONParser js = new JSONParser();
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Agent", "mobile");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            String jsonInputString = "{\"username\": \"" + login + "\", \"password\": \"" + password + "\"}";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                Map<String, Object> map = js.parseJSON(new CharArrayReader(response.toString().toCharArray()));
                user = new User();
                user.setId((int) Math.round((Double) map.get("id")));
                user.setUsername((String) map.get("username"));
                user.setEmail((String) map.get("email"));
                user.setTelephone((String) map.get("Telephone"));
                user.setAddress((String) map.get("address"));
                System.out.println(user.toString());
//                Map<String,Object> mapUser=js.parseJSON(new CharArrayReader(map.get("user").toString().toCharArray()));
//                System.out.println(mapUser);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return user;
        }
    }
    public Boolean signUp(User user) {
        JSONParser js = new JSONParser();
        var url = "http://localhost:8000/api/v1/signupJson";
        HttpURLConnection con = null;
        try {
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Agent", "mobile");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = user.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
