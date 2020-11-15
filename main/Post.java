package com.example.mobileprogramming_teamproject_leejin;

import java.util.HashMap;
import java.util.Map;

//@IgnoreExtraProperties
public class Post {

    public String uemail;
    public String unickname;
    public String uage;
    public String ugender;

    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    //유저 이메일, 닉네임, 나이, 성별
    public Post(String uemail, String unickname, String uage, String ugender) {
        this.uemail = uemail;
        this.unickname = unickname;
        this.uage = uage;
        this.ugender = ugender;
    }

    //유저 이메일, 닉네임, 나이, 성별 PUT
    //@Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uemail", uemail);
        result.put("unickname", unickname);
        result.put("uage", uage);
        result.put("ugender", ugender);

        return result;
    }

}
