package com.springboot.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: kt
 * @Date: 2021/1/14 18:01
 */
@Component
public class Test1 {

  public static void main(String[] args) {
    int duration = 60, delayTime = 5;
    final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
    scheduledThreadPoolExecutor.scheduleAtFixedRate(Test1::request,
            delayTime, duration, TimeUnit.SECONDS);

  }

  private static void request() {
    String url = "https://sf2021.kuaishou.com/zw/rest/wd/sf21/wanYuanRedpack/grab?activityId=ewy_o6&grabType=1";
    String cookieStr = "POST /zw/rest/wd/sf21/wanYuanRedpack/grab?activityId=ewy_o6&grabType=1 HTTP/1.1\n" +
            "Host: sf2021.kuaishou.com\n" +
            "Connection: keep-alive\n" +
            "Content-Length: 59\n" +
            "Accept: application/json\n" +
            "Origin: https://sf2021.kuaishou.com\n" +
            "User-Agent: Mozilla/5.0 (Linux; Android 10; PACM00 Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/79.0.3999.119 KsWebView/1.5.79.137 Mobile Safari/537.36 Yoda/2.1.3-rc29 NetType/WIFI StatusHT/32 Kwai/9.0.50.18348 OS_PRO_BIT/64 MAX_PHY_MEM/5666 TitleHT/50 JSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fjs%2Fapp.95dfd0ac.js,js CSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fcss%2Fapp.8e7ff16f.css,css ISC/0 ICFO/0 ISLP/0 AZPREFIX/zw CT/0\n" +
            "Content-Type: application/json;charset=UTF-8\n" +
            "X-Requested-With: com.smile.gifmaker\n" +
            "Sec-Fetch-Site: same-origin\n" +
            "Sec-Fetch-Mode: cors\n" +
            "Referer: https://sf2021.kuaishou.com/td/eve/index.html?layoutType=4&enableWK=1&sf2021=1&loadingType=sf20211&hyId=sf21_td_vendor%2Csf21_td_preset%2Csf21_td_eve%2Csf21_td_eve_sos%2Csf21_font&sfpage=home&optMemory=1&webview_bgcolor=%23dc1824&entry_src=pendant&activityId=ec_o6\n" +
            "Accept-Encoding: gzip, deflate\n" +
            "Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7\n" +
            "Cookie: mod=OPPO%28PACM00%29; keyconfig_state=2; appver=9.0.50.18348; isp=CTCC; client_key=3c2cd3f3; language=zh-cn; kuaishou.h5_st=Cg5rdWFpc2hvdS5oNS5zdBKgAZHT0FQRDRWdoWTp5WcnvKTRku_nY9FOUTv8fgCMoGzstVUIOmhXP5rpWgKOnS9YEGGrXfdm1f6W_TxqI5dMPHLeBx6fnCChISzDJfflPChBg2huXp9pt4RQAxM3aiDvxjw8WRAtD9JpMduItjkvuu_auKWpSIW23NxZHo9STx916oyxTioK_-AHDe1edQ6ONpa6b5r-tIW7hs850Gko5w0aEqWAqa4qaXGcL-RKnK6eSUQY8iIgZjUWrNOXHOMd-NxHmdQIh8ML9IOtbV56bd5i6ICk6PMoBTAB; sys=ANDROID_10; kuaishou.api_st=Cg9rdWFpc2hvdS5hcGkuc3QSsAFWf3Q1pWLKs56yq-2_u5CQW_iInhI9AM78ssaT78JrUnDElL9WlJ5IcqJmikYNUILoIEjH8mXb7ajPTdQ2voznh-oZmprDiOhT5KpuS59bw_V-3orPy_7esV2wh-B156jsv-XI_CwWxYBM0XiAWBYkzJmauaqIisjG87wQ6xBYgNF6itKI7xFYWbGN3l_XevW73ne4DZ_RcNXrVfl-7F8UmPChBzK5S0XY_qh40iHffBoSo0KKvSypS3ylYXk_I3oEVnzAIiD_M2JCtA9Tkq88iJDVJvj5J4CG6buglzxDMz26IH7h-CgFMAE; deviceName=OPPO%28PACM00%29; token=12fe76c1c0f54f5a84a8f08052d90f1e-1393958704; max_memory=384; ud=1393958704; did_tag=0; egid=DFP17CE64C8BBCF5BEFCC504B516943296786E3D2875B8578C572D8F028B341D; oc=OPPO_KWAI; sh=2280; countryCode=CN; ddpi=480; deviceBit=0; browseType=4; power_mode=0; socName=MediaTek+MT6771V%2FC; kpf=ANDROID_PHONE; app=0; ver=UNKNOWN; c=OPPO_KWAI; sw=1080; ftt=; kpn=KUAISHOU; androidApiLevel=29; newOc=OPPO; abi=arm64; userId=1393958704; country_code=cn; totalMemory=5666; nbh=132; hotfix_ver=; did_gt=1588255224497; iuid=; rdid=ANDROID_8dbe3a0e28387e1e; sbh=96; darkMode=false; did=ANDROID_4f928d852e986886; kcv=1306; sid=4eda60be-a2ba-4ff0-ab44-7f9b2795f682; cold_launch_time_ms=1613018761780; net=WIFI";

    final HashMap<String, String> map = new HashMap<>();
    map.put("Cookie", "mod=OPPO%28PACM00%29; keyconfig_state=2; appver=9.0.50.18348; isp=CTCC; client_key=3c2cd3f3; language=zh-cn; kuaishou.h5_st=Cg5rdWFpc2hvdS5oNS5zdBKgAZHT0FQRDRWdoWTp5WcnvKTRku_nY9FOUTv8fgCMoGzstVUIOmhXP5rpWgKOnS9YEGGrXfdm1f6W_TxqI5dMPHLeBx6fnCChISzDJfflPChBg2huXp9pt4RQAxM3aiDvxjw8WRAtD9JpMduItjkvuu_auKWpSIW23NxZHo9STx916oyxTioK_-AHDe1edQ6ONpa6b5r-tIW7hs850Gko5w0aEqWAqa4qaXGcL-RKnK6eSUQY8iIgZjUWrNOXHOMd-NxHmdQIh8ML9IOtbV56bd5i6ICk6PMoBTAB; sys=ANDROID_10; kuaishou.api_st=Cg9rdWFpc2hvdS5hcGkuc3QSsAFWf3Q1pWLKs56yq-2_u5CQW_iInhI9AM78ssaT78JrUnDElL9WlJ5IcqJmikYNUILoIEjH8mXb7ajPTdQ2voznh-oZmprDiOhT5KpuS59bw_V-3orPy_7esV2wh-B156jsv-XI_CwWxYBM0XiAWBYkzJmauaqIisjG87wQ6xBYgNF6itKI7xFYWbGN3l_XevW73ne4DZ_RcNXrVfl-7F8UmPChBzK5S0XY_qh40iHffBoSo0KKvSypS3ylYXk_I3oEVnzAIiD_M2JCtA9Tkq88iJDVJvj5J4CG6buglzxDMz26IH7h-CgFMAE; deviceName=OPPO%28PACM00%29; token=12fe76c1c0f54f5a84a8f08052d90f1e-1393958704; max_memory=384; ud=1393958704; did_tag=0; egid=DFP17CE64C8BBCF5BEFCC504B516943296786E3D2875B8578C572D8F028B341D; oc=OPPO_KWAI; sh=2280; countryCode=CN; ddpi=480; deviceBit=0; browseType=4; power_mode=0; socName=MediaTek+MT6771V%2FC; kpf=ANDROID_PHONE; app=0; ver=UNKNOWN; c=OPPO_KWAI; sw=1080; ftt=; kpn=KUAISHOU; androidApiLevel=29; newOc=OPPO; abi=arm64; userId=1393958704; country_code=cn; totalMemory=5666; nbh=132; hotfix_ver=; did_gt=1588255224497; iuid=; rdid=ANDROID_8dbe3a0e28387e1e; sbh=96; darkMode=false; did=ANDROID_4f928d852e986886; kcv=1306; sid=4eda60be-a2ba-4ff0-ab44-7f9b2795f682; cold_launch_time_ms=1613018761780; net=WIFI");
    map.put("Sec-Fetch-Site", "same-origin");
    map.put("Sec-Fetch-Mode", "cors");
    map.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PACM00 Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/79.0.3999.119 KsWebView/1.5.79.137 Mobile Safari/537.36 Yoda/2.1.3-rc29 NetType/WIFI StatusHT/32 Kwai/9.0.50.18348 OS_PRO_BIT/64 MAX_PHY_MEM/5666 TitleHT/50 JSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fjs%2Fapp.95dfd0ac.js,js CSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fcss%2Fapp.8e7ff16f.css,css ISC/0 ICFO/0 ISLP/0 AZPREFIX/zw CT/0");
    map.put("Referer", "https://sf2021.kuaishou.com");
    map.put("X-Requested-With", "com.smile.gifmaker");
    map.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
    map.put("Accept-Encoding", "gzip, deflate");
    map.put("Accept", "application/json");

    final String s = WebUtil.postJson(url, "{\"activityId\":\"ewy_o6\",\"grabType\":1,\"appList\":[\"KUAISHOU\"]}", map, null);
    System.err.println("自动进行抢红包" + s);

    final Data data = JSON.parseObject(s, Data.class);
    if(data.result == 1) {
      StringBuilder sb = new StringBuilder();
      final List<Data.DataDTO.RedpackViewListDTO> redpackViewList = data.getData().getRedpackViewList();
      redpackViewList.forEach(x -> sb.append(x.jumpTaskList ? x.getPayFen() + "√ " : x.getPayFen() + " "));
      System.err.println("请求成功 本次奖项：" + sb.toString());
      final Data.DataDTO.RedpackViewListDTO redpackViewListDTO = redpackViewList.stream().filter(x -> x.jumpTaskList).findFirst().get();
      if(redpackViewListDTO.payFen > 0) {
        reqTotal();
      }
    }
  }

  private static void reqTotal() {
    String xx = "POST https://sf2021.kuaishou.com/zw/rest/wd/sf21/cnyEveLanding/walletMoney?activityId=ec_o6 HTTP/1.1\n" +
            "Host: sf2021.kuaishou.com\n" +
            "Connection: keep-alive\n" +
            "Content-Length: 22\n" +
            "Accept: application/json\n" +
            "Origin: https://sf2021.kuaishou.com\n" +
            "User-Agent: Mozilla/5.0 (Linux; Android 10; PACM00 Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/79.0.3999.119 KsWebView/1.5.79.137 Mobile Safari/537.36 Yoda/2.1.3-rc29 NetType/WIFI StatusHT/32 Kwai/9.0.50.18348 OS_PRO_BIT/64 MAX_PHY_MEM/5666 TitleHT/50 JSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fjs%2Fapp.95dfd0ac.js,js CSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fcss%2Fapp.8e7ff16f.css,css ISC/0 ICFO/0 ISLP/0 AZPREFIX/zw CT/0\n" +
            "Content-Type: application/json;charset=UTF-8\n" +
            "X-Requested-With: com.smile.gifmaker\n" +
            "Sec-Fetch-Site: same-origin\n" +
            "Sec-Fetch-Mode: cors\n" +
            "Referer: https://sf2021.kuaishou.com/td/eve/index.html?layoutType=4&enableWK=1&sf2021=1&loadingType=sf20211&hyId=sf21_td_vendor%2Csf21_td_preset%2Csf21_td_eve%2Csf21_td_eve_sos%2Csf21_font&sfpage=home&optMemory=1&webview_bgcolor=%23dc1824&entry_src=pendant&activityId=ec_o6\n" +
            "Accept-Encoding: gzip, deflate\n" +
            "Accept-Language: zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7\n" +
            "Cookie: mod=OPPO%28PACM00%29; keyconfig_state=2; appver=9.0.50.18348; isp=CTCC; client_key=3c2cd3f3; language=zh-cn; kuaishou.h5_st=Cg5rdWFpc2hvdS5oNS5zdBKgAZHT0FQRDRWdoWTp5WcnvKTRku_nY9FOUTv8fgCMoGzstVUIOmhXP5rpWgKOnS9YEGGrXfdm1f6W_TxqI5dMPHLeBx6fnCChISzDJfflPChBg2huXp9pt4RQAxM3aiDvxjw8WRAtD9JpMduItjkvuu_auKWpSIW23NxZHo9STx916oyxTioK_-AHDe1edQ6ONpa6b5r-tIW7hs850Gko5w0aEqWAqa4qaXGcL-RKnK6eSUQY8iIgZjUWrNOXHOMd-NxHmdQIh8ML9IOtbV56bd5i6ICk6PMoBTAB; sys=ANDROID_10; kuaishou.api_st=Cg9rdWFpc2hvdS5hcGkuc3QSsAFWf3Q1pWLKs56yq-2_u5CQW_iInhI9AM78ssaT78JrUnDElL9WlJ5IcqJmikYNUILoIEjH8mXb7ajPTdQ2voznh-oZmprDiOhT5KpuS59bw_V-3orPy_7esV2wh-B156jsv-XI_CwWxYBM0XiAWBYkzJmauaqIisjG87wQ6xBYgNF6itKI7xFYWbGN3l_XevW73ne4DZ_RcNXrVfl-7F8UmPChBzK5S0XY_qh40iHffBoSo0KKvSypS3ylYXk_I3oEVnzAIiD_M2JCtA9Tkq88iJDVJvj5J4CG6buglzxDMz26IH7h-CgFMAE; deviceName=OPPO%28PACM00%29; token=12fe76c1c0f54f5a84a8f08052d90f1e-1393958704; max_memory=384; ud=1393958704; did_tag=0; egid=DFP17CE64C8BBCF5BEFCC504B516943296786E3D2875B8578C572D8F028B341D; oc=OPPO_KWAI; sh=2280; countryCode=CN; ddpi=480; deviceBit=0; browseType=4; power_mode=0; socName=MediaTek+MT6771V%2FC; kpf=ANDROID_PHONE; app=0; ver=UNKNOWN; c=OPPO_KWAI; sw=1080; ftt=; kpn=KUAISHOU; androidApiLevel=29; newOc=OPPO; abi=arm64; userId=1393958704; country_code=cn; totalMemory=5666; nbh=132; hotfix_ver=; did_gt=1588255224497; iuid=; rdid=ANDROID_8dbe3a0e28387e1e; sbh=96; darkMode=false; did=ANDROID_4f928d852e986886; kcv=1306; net=WIFI; cold_launch_time_ms=1613025641859; sid=1f0d7bd4-4f8a-4c9d-bb40-296113c0c44d\n" +
            "\n" +
            "{\"activityId\":\"ec_o6\"}";
    String url = "https://sf2021.kuaishou.com/zw/rest/wd/sf21/cnyEveLanding/walletMoney?activityId=ec_o6";
    final HashMap<String, String> map = new HashMap<>();
    map.put("Cookie", "mod=OPPO%28PACM00%29; keyconfig_state=2; appver=9.0.50.18348; isp=CTCC; client_key=3c2cd3f3; language=zh-cn; kuaishou.h5_st=Cg5rdWFpc2hvdS5oNS5zdBKgAZHT0FQRDRWdoWTp5WcnvKTRku_nY9FOUTv8fgCMoGzstVUIOmhXP5rpWgKOnS9YEGGrXfdm1f6W_TxqI5dMPHLeBx6fnCChISzDJfflPChBg2huXp9pt4RQAxM3aiDvxjw8WRAtD9JpMduItjkvuu_auKWpSIW23NxZHo9STx916oyxTioK_-AHDe1edQ6ONpa6b5r-tIW7hs850Gko5w0aEqWAqa4qaXGcL-RKnK6eSUQY8iIgZjUWrNOXHOMd-NxHmdQIh8ML9IOtbV56bd5i6ICk6PMoBTAB; sys=ANDROID_10; kuaishou.api_st=Cg9rdWFpc2hvdS5hcGkuc3QSsAFWf3Q1pWLKs56yq-2_u5CQW_iInhI9AM78ssaT78JrUnDElL9WlJ5IcqJmikYNUILoIEjH8mXb7ajPTdQ2voznh-oZmprDiOhT5KpuS59bw_V-3orPy_7esV2wh-B156jsv-XI_CwWxYBM0XiAWBYkzJmauaqIisjG87wQ6xBYgNF6itKI7xFYWbGN3l_XevW73ne4DZ_RcNXrVfl-7F8UmPChBzK5S0XY_qh40iHffBoSo0KKvSypS3ylYXk_I3oEVnzAIiD_M2JCtA9Tkq88iJDVJvj5J4CG6buglzxDMz26IH7h-CgFMAE; deviceName=OPPO%28PACM00%29; token=12fe76c1c0f54f5a84a8f08052d90f1e-1393958704; max_memory=384; ud=1393958704; did_tag=0; egid=DFP17CE64C8BBCF5BEFCC504B516943296786E3D2875B8578C572D8F028B341D; oc=OPPO_KWAI; sh=2280; countryCode=CN; ddpi=480; deviceBit=0; browseType=4; power_mode=0; socName=MediaTek+MT6771V%2FC; kpf=ANDROID_PHONE; app=0; ver=UNKNOWN; c=OPPO_KWAI; sw=1080; ftt=; kpn=KUAISHOU; androidApiLevel=29; newOc=OPPO; abi=arm64; userId=1393958704; country_code=cn; totalMemory=5666; nbh=132; hotfix_ver=; did_gt=1588255224497; iuid=; rdid=ANDROID_8dbe3a0e28387e1e; sbh=96; darkMode=false; did=ANDROID_4f928d852e986886; kcv=1306; net=WIFI; cold_launch_time_ms=1613025641859; sid=1f0d7bd4-4f8a-4c9d-bb40-296113c0c44d");
    map.put("Sec-Fetch-Site", "same-origin");
    map.put("Sec-Fetch-Mode", "cors");
    map.put("User-Agent", "Mozilla/5.0 (Linux; Android 10; PACM00 Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/79.0.3999.119 KsWebView/1.5.79.137 Mobile Safari/537.36 Yoda/2.1.3-rc29 NetType/WIFI StatusHT/32 Kwai/9.0.50.18348 OS_PRO_BIT/64 MAX_PHY_MEM/5666 TitleHT/50 JSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fjs%2Fapp.95dfd0ac.js,js CSV/%2F%2Fh2.static.yximgs.com%2Fkos%2Fnlav10721%2Fwarm-up%2Fcss%2Fapp.8e7ff16f.css,css ISC/0 ICFO/0 ISLP/0 AZPREFIX/zw CT/0");
    map.put("Referer", "https://sf2021.kuaishou.com/td/eve/index.html?layoutType=4&enableWK=1&sf2021=1&loadingType=sf20211&hyId=sf21_td_vendor%2Csf21_td_preset%2Csf21_td_eve%2Csf21_td_eve_sos%2Csf21_font&sfpage=home&optMemory=1&webview_bgcolor=%23dc1824&entry_src=pendant&activityId=ec_o6");
    map.put("X-Requested-With", "com.smile.gifmaker");
    map.put("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
    map.put("Accept-Encoding", "gzip, deflate");
    map.put("Accept", "application/json");

    final String s = WebUtil.postJson(url, "{\"activityId\":\"ec_o6\"}", map, null);
    System.err.println("\n ****************************");
    System.err.println(" 红包入账：" + s);
    System.err.println(" ****************************");
  }

  public static class Data {

    /**
     * data : {"redpackViewList":[{"payFen":0,"jumpTaskList":true,"jumpTaskListText":"做任务，下轮必中现金红包","type":0},{"payFen":37,"jumpTaskList":false,"type":2},{"payFen":17,"jumpTaskList":false,"type":2}],"redpackStatus":0,"grabInfo":{"cashCount":0,"cash":[],"shareToken":"hss17AeUjUA","vipUpcomingPayFen":1238,"vip":[],"nowGrabWindow":0,"grabTime":5000,"absGrabTime":1613026413000,"nextGrabTime":60000,"grabWindow":5000,"nextGrabWindow":5000,"grabCount":0,"waitMills":60000,"intervalMills":60000},"title":{"countDownFrom":1000000,"nextCountDownFrom":1000000}}
     * message : success
     * currentTime : 1613026408987
     * result : 1
     * host : public-bjdy-kcs-node1541.idczw.hb1.kwaidc.com
     * error_msg : success
     */

    private DataDTO data;
    private String message;
    private Long currentTime;
    private Integer result;
    private String host;
    private String error_msg;

    public DataDTO getData() {
      return data;
    }

    public void setData(DataDTO data) {
      this.data = data;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public Long getCurrentTime() {
      return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
      this.currentTime = currentTime;
    }

    public Integer getResult() {
      return result;
    }

    public void setResult(Integer result) {
      this.result = result;
    }

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public String getError_msg() {
      return error_msg;
    }

    public void setError_msg(String error_msg) {
      this.error_msg = error_msg;
    }

    public static class DataDTO {
      /**
       * redpackViewList : [{"payFen":0,"jumpTaskList":true,"jumpTaskListText":"做任务，下轮必中现金红包","type":0},{"payFen":37,"jumpTaskList":false,"type":2},{"payFen":17,"jumpTaskList":false,"type":2}]
       * redpackStatus : 0
       * grabInfo : {"cashCount":0,"cash":[],"shareToken":"hss17AeUjUA","vipUpcomingPayFen":1238,"vip":[],"nowGrabWindow":0,"grabTime":5000,"absGrabTime":1613026413000,"nextGrabTime":60000,"grabWindow":5000,"nextGrabWindow":5000,"grabCount":0,"waitMills":60000,"intervalMills":60000}
       * title : {"countDownFrom":1000000,"nextCountDownFrom":1000000}
       */

      private List<RedpackViewListDTO> redpackViewList;
      private Integer redpackStatus;
      private GrabInfoDTO grabInfo;
      private TitleDTO title;

      public List<RedpackViewListDTO> getRedpackViewList() {
        return redpackViewList;
      }

      public void setRedpackViewList(List<RedpackViewListDTO> redpackViewList) {
        this.redpackViewList = redpackViewList;
      }

      public Integer getRedpackStatus() {
        return redpackStatus;
      }

      public void setRedpackStatus(Integer redpackStatus) {
        this.redpackStatus = redpackStatus;
      }

      public GrabInfoDTO getGrabInfo() {
        return grabInfo;
      }

      public void setGrabInfo(GrabInfoDTO grabInfo) {
        this.grabInfo = grabInfo;
      }

      public TitleDTO getTitle() {
        return title;
      }

      public void setTitle(TitleDTO title) {
        this.title = title;
      }

      public static class GrabInfoDTO {
        /**
         * cashCount : 0
         * cash : []
         * shareToken : hss17AeUjUA
         * vipUpcomingPayFen : 1238
         * vip : []
         * nowGrabWindow : 0
         * grabTime : 5000
         * absGrabTime : 1613026413000
         * nextGrabTime : 60000
         * grabWindow : 5000
         * nextGrabWindow : 5000
         * grabCount : 0
         * waitMills : 60000
         * intervalMills : 60000
         */

        private Integer cashCount;
        private List<?> cash;
        private String shareToken;
        private Integer vipUpcomingPayFen;
        private List<?> vip;
        private Integer nowGrabWindow;
        private Integer grabTime;
        private Long absGrabTime;
        private Integer nextGrabTime;
        private Integer grabWindow;
        private Integer nextGrabWindow;
        private Integer grabCount;
        private Integer waitMills;
        private Integer intervalMills;

        public Integer getCashCount() {
          return cashCount;
        }

        public void setCashCount(Integer cashCount) {
          this.cashCount = cashCount;
        }

        public List<?> getCash() {
          return cash;
        }

        public void setCash(List<?> cash) {
          this.cash = cash;
        }

        public String getShareToken() {
          return shareToken;
        }

        public void setShareToken(String shareToken) {
          this.shareToken = shareToken;
        }

        public Integer getVipUpcomingPayFen() {
          return vipUpcomingPayFen;
        }

        public void setVipUpcomingPayFen(Integer vipUpcomingPayFen) {
          this.vipUpcomingPayFen = vipUpcomingPayFen;
        }

        public List<?> getVip() {
          return vip;
        }

        public void setVip(List<?> vip) {
          this.vip = vip;
        }

        public Integer getNowGrabWindow() {
          return nowGrabWindow;
        }

        public void setNowGrabWindow(Integer nowGrabWindow) {
          this.nowGrabWindow = nowGrabWindow;
        }

        public Integer getGrabTime() {
          return grabTime;
        }

        public void setGrabTime(Integer grabTime) {
          this.grabTime = grabTime;
        }

        public Long getAbsGrabTime() {
          return absGrabTime;
        }

        public void setAbsGrabTime(Long absGrabTime) {
          this.absGrabTime = absGrabTime;
        }

        public Integer getNextGrabTime() {
          return nextGrabTime;
        }

        public void setNextGrabTime(Integer nextGrabTime) {
          this.nextGrabTime = nextGrabTime;
        }

        public Integer getGrabWindow() {
          return grabWindow;
        }

        public void setGrabWindow(Integer grabWindow) {
          this.grabWindow = grabWindow;
        }

        public Integer getNextGrabWindow() {
          return nextGrabWindow;
        }

        public void setNextGrabWindow(Integer nextGrabWindow) {
          this.nextGrabWindow = nextGrabWindow;
        }

        public Integer getGrabCount() {
          return grabCount;
        }

        public void setGrabCount(Integer grabCount) {
          this.grabCount = grabCount;
        }

        public Integer getWaitMills() {
          return waitMills;
        }

        public void setWaitMills(Integer waitMills) {
          this.waitMills = waitMills;
        }

        public Integer getIntervalMills() {
          return intervalMills;
        }

        public void setIntervalMills(Integer intervalMills) {
          this.intervalMills = intervalMills;
        }
      }

      public static class TitleDTO {
        /**
         * countDownFrom : 1000000
         * nextCountDownFrom : 1000000
         */

        private Integer countDownFrom;
        private Integer nextCountDownFrom;

        public Integer getCountDownFrom() {
          return countDownFrom;
        }

        public void setCountDownFrom(Integer countDownFrom) {
          this.countDownFrom = countDownFrom;
        }

        public Integer getNextCountDownFrom() {
          return nextCountDownFrom;
        }

        public void setNextCountDownFrom(Integer nextCountDownFrom) {
          this.nextCountDownFrom = nextCountDownFrom;
        }
      }

      public static class RedpackViewListDTO {
        /**
         * payFen : 0
         * jumpTaskList : true
         * jumpTaskListText : 做任务，下轮必中现金红包
         * type : 0
         */

        private Integer payFen;
        private Boolean jumpTaskList;
        private String jumpTaskListText;
        private Integer type;

        public Integer getPayFen() {
          return payFen;
        }

        public void setPayFen(Integer payFen) {
          this.payFen = payFen;
        }

        public Boolean getJumpTaskList() {
          return jumpTaskList;
        }

        public void setJumpTaskList(Boolean jumpTaskList) {
          this.jumpTaskList = jumpTaskList;
        }

        public String getJumpTaskListText() {
          return jumpTaskListText;
        }

        public void setJumpTaskListText(String jumpTaskListText) {
          this.jumpTaskListText = jumpTaskListText;
        }

        public Integer getType() {
          return type;
        }

        public void setType(Integer type) {
          this.type = type;
        }
      }
    }
  }

}
