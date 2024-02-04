package com.aiguigu.main;

import com.aiguigu.pojo.Star;
import com.aiguigu.pojo.impl.BigStar;
import com.aiguigu.proxy.ProxyUtil;

public class TestProxy {
    public static void main(String[] args) {
      ProxyUtil proxyUtil = new ProxyUtil(new BigStar());
      Star bigStar = (Star) proxyUtil.getProxy();
      bigStar.dance();
    }
}
