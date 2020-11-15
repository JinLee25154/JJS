package com.example.mobileprogramming_teamproject_leejin;

// 작성자: 김수연
// 11/6 Feed 클래스 작성. Feed 클래스의 여러 생성자 및 필드의 get(), set() 메서드 작성.
// 11/11 Feed 클래스에 ArrayList<Bitmap> 타입 필드 arrListBM추가, 새로운 생성자의 인자로도 추가. 인자에서 체형정보를 삭제.
// 11/12 Feed 클래스에 Bitmap[] 타입 필드 arrBM 추가, Bitmap 타입 profile 추가. 각각 get(), set() 메서드 작성, 두 필드를 인자로 포함하는 생성자 추가.
// 11/14 댓글작성자 닉네임인 commentNickname 삭제, get(), set() 메서드 삭제. 글쓴이 체형 정보인 menu_body_info 삭제 및 get(), set() 메서드 삭제.
// 본문 내용인 description 추가, 새로운 생성자의 인자에 추가
// Feed의 실제 Image 개수 반환하는 getImageCount() 메서드 추가
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

// Feed 클래스
public class Feed {
    // nickname, category, target, menu_person_info, bm, arrBM은 각각
    // 글쓴이 닉네임, 피드 카테고리, 피드 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지, 코디를 보여주기 위한 Bitmap 이미지 배열을 의미함.
    String nickname, category, target;
    String menu_person_info;
    Bitmap bm, profile;
    ArrayList<Bitmap> arrListBM;
    Bitmap arrBM[] = new Bitmap[8];
    //ArrayList<Bitmap> arrBM = ArrayList<Bitmap>();
    String description;
    String clothComment[] = new String[8];

    // Feed 생성자1. 닉네임, 카테고리, 타겟, 글쓴이 개인정보를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        Log.d("Feed", "1");
    }

    // Feed 생성자2. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, Bitmap bm) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.bm = bm;
        Log.d("Feed", "2");
    }

    // Feed 생성자3. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지 ArrayList를 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, ArrayList<Bitmap> arrListBM) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.arrListBM = arrListBM;
        Log.d("Feed", "3");
    }
    // Feed 생성자4. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지 배열을 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, Bitmap[] arrBM) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.arrBM = arrBM;
        Log.d("Feed", "4");
    }
    // Feed 생성자5. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 코디를 보여주기 위한 Bitmap 이미지 배열, 프로필을 나타내는 Bitmap 이미지을 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, Bitmap[] arrBM, Bitmap profile) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.arrBM = arrBM;
        this.profile = profile;
        Log.d("Feed", "5");
    }

    // Feed 생성자6. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 본문 내용, 코디를 보여주기 위한 Bitmap 이미지 배열, 프로필을 나타내는 Bitmap 이미지을 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, String description, Bitmap[] arrBM, Bitmap profile) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.description = description;
        this.arrBM = arrBM;
        this.profile = profile;
        Log.d("Feed", "6");
    }

    // Feed 생성자7. 닉네임, 카테고리, 타겟, 글쓴이 개인정보, 본문 내용, 코디를 보여주기 위한 Bitmap 이미지 배열, 프로필을 나타내는 Bitmap 이미지을 인자로 가짐.
    public Feed(String nickname, String category, String target, String menu_person_info, String description, String clothComment[], Bitmap[] arrBM, Bitmap profile) {
        this.nickname = nickname;
        this.category = category;
        this.target = target;
        this.menu_person_info = menu_person_info;
        this.description = description;
        this.clothComment = clothComment;
        this.arrBM = arrBM;
        this.profile = profile;
        Log.d("Feed", "6");
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

    public ArrayList<Bitmap> getArrListBM() {
        return arrListBM;
    }

    public void setArrListBM(ArrayList<Bitmap> arrListBM) {
        this.arrListBM = arrListBM;
    }

    public Bitmap[] getArrBM() {
        return arrBM;
    }

    public void setArrBM(Bitmap[] arrBM) {
        this.arrBM = arrBM;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getClothComment() {
        return clothComment;
    }

    public void setClothComment(String clothComment[]) {
        this.clothComment = clothComment;
    }

    public int getImageCount() {
        int i = 0;
        while (this.getArrBM()[i] != null && i < 8) {
            i++;
        }
        return i;
    }
}
