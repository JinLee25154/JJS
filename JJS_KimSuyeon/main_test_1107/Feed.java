package com.example.main_test_1107;

import android.graphics.Bitmap;

// 작성자: 김수연, 11/6
public class Feed {
    String nickname, category, target;
    String menu_body_info, menu_person_info;
    Bitmap bm;
    String commentNickname;

    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
    }

    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info, Bitmap bm) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
        this.bm = bm;
    }

    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info, Bitmap bm, String commentNickname) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
        this.bm = bm;
        this.commentNickname = commentNickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMenu_body_info() {
        return menu_body_info;
    }

    public void setMenu_body_info(String menu_body_info) {
        this.menu_body_info = menu_body_info;
    }

    public String getMenu_person_info() {
        return menu_person_info;
    }

    public void setMenu_person_info(String menu_person_info) {
        this.menu_person_info = menu_person_info;
    }

    public Bitmap getBitmap() {
        return bm;
    }

    public void setBitmap(Bitmap bm) {
        this.bm = bm;
    }
    public String getCommentNickname() {
        return commentNickname;
    }

    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }
}
