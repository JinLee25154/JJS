package com.example.main_test_1111;

// 작성자: 김수연
// 11/6 Feed 클래스 작성. Feed 클래스의 여러 생성자 및 필드의 get(), set() 메서드 작성.
// 11/11 Feed 클래스에 ArrayList<BM> 타입 필드 추가, 새로운 생성자인 Feed 생성자4의 인자로도 추가. Feed 생성자4 인자에서 체형정보를 삭제.

import android.graphics.Bitmap;

import java.util.ArrayList;

// Feed 클래스
public class Feed {
    // nickname, category, target, menu_body_info, menu_person_info, bm, commentNickname은 각각
    // 글쓴이 닉네임, 피드 카테고리, 피드 타겟, 글쓴이 체형정보, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지, 댓글 작성자 닉네임을 의미함.
    String nickname, category, target;
    String menu_body_info, menu_person_info;
    Bitmap bm;
    ArrayList<Bitmap> arrBM;
    //ArrayList<Bitmap> arrBM = ArrayList<Bitmap>();
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

    // Feed 생성자4. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지 ArrayList를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, ArrayList<Bitmap> arrBM) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.arrBM = arrBM;
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

    public ArrayList<Bitmap> getArrBM() {
        return arrBM;
    }

    public void setArrBM(ArrayList<Bitmap> arrBM) {
        this.arrBM = arrBM;
    }

    public String getCommentNickname() {
        return commentNickname;
    }

    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }

}
