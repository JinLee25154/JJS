package com.example.main_test_1107;

import android.graphics.Bitmap;

// 작성자: 김수연, 11/6

// Feed 클래스
public class Feed {
    // nickname, category, target, menu_body_info, menu_person_info, bm, commentNickname은 각각
    // 글쓴이 닉네임, 피드 카테고리, 피드 타겟, 글쓴이 체형정보, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지, 댓글 작성자 닉네임을 의미함.
    String nickname, category, target;
    String menu_body_info, menu_person_info;
    Bitmap bm;
    String commentNickname;

    // Feed 생성자1. 닉네임, 카테고리, 타겟, 글쓴이 체형정보, 글쓴이 개인정보를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
    }

    // Feed 생성자2. 닉네임, 카테고리, 타겟, 글쓴이 체형정보, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info, Bitmap bm) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
        this.bm = bm;
    }

    // Feed 생성자3. 닉네임, 카테고리, 타겟, 글쓴이 체형정보, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지, 댓글 작성자 닉네임을 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_body_info, String menu_person_info, Bitmap bm, String commentNickname) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_body_info = menu_body_info;
        this.menu_person_info = menu_person_info;
        this.bm = bm;
        this.commentNickname = commentNickname;
    }

    // nickname을 반환하는 함수
    public String getNickname() {
        return nickname;
    }

    // nickname을 설정하는 함수
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // category를 반환하는 함수
    public String getCategory() {
        return category;
    }

    // category를 설정하는 홤수
    public void setCategory(String category) {
        this.category = category;
    }

    // target을 반환하는 함수
    public String getTarget() {
        return target;
    }

    // target을 설정하는 함수
    public void setTarget(String target) {
        this.target = target;
    }

    // menu_body_info를 반환하는 함수
    public String getMenu_body_info() {
        return menu_body_info;
    }

    // menu_body_info를 설정하는 함수
    public void setMenu_body_info(String menu_body_info) {
        this.menu_body_info = menu_body_info;
    }

    // menu_person_info를 반환하는 함수
    public String getMenu_person_info() {
        return menu_person_info;
    }

    // menu_person_info를 설정하는 함수
    public void setMenu_person_info(String menu_person_info) {
        this.menu_person_info = menu_person_info;
    }

    // bm을 반환하는 함수
    public Bitmap getBitmap() {
        return bm;
    }

    // bm을 설정하는 함수
    public void setBitmap(Bitmap bm) {
        this.bm = bm;
    }

    // commentNickname을 반환하는 함수
    public String getCommentNickname() {
        return commentNickname;
    }

    // commentNickname을 설정하는 함수
    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }
}
