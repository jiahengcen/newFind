package com.pwc.searchview.bean;


import java.util.ArrayList;

/**
 * Created by lhuang126 on 1/13/2018.
 */
public class CompanyTitleListBean {
    public ArrayList<CompanyTitleSubBean> result;

    public static class CompanyTitleSubBean {

//        "companyCode": "suiyuejiezi",
//        "fullname": "\u5317\u4eac\u5c81\u6708\u6854\u5b50\u79d1\u6280\u6709\u9650\u516c\u53f8",
//        "location": "\u5317\u4eac",
//        "logo": "https://www.xiniudata.com/file/59390aa5f04e153266cb752a",
//        "name": "IT\u6854\u5b50"

        //public String companyCode;
        public String fullName;
        public String location;
        public String logo;
        public String name;
        public String round;
        public String establishDate;
        public Boolean starred;
    }

}

