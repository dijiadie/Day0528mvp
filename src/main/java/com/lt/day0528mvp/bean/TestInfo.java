package com.lt.day0528mvp.bean;

import java.util.List;

/**
 * Created by 任小龙 on 2020/5/19.
 */
public class TestInfo {
    public String status;
    public List<DataInfo> datas;
    public class DataInfo{
        public String thumbnail;
        public String title;
        public String author;
        public String description;
    }
}
