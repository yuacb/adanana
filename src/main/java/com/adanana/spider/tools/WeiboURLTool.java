package com.adanana.spider.tools;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WeiboURLTool {
    //这里存的是 用户关系  父 子 关系 感觉会..爆表...
    private static ConcurrentMap curMap = new ConcurrentHashMap<String,String>();

    public static void mapAdd(String k,String v){
        curMap.put(k,v);

    }
    public static boolean containsKey(String k){
       return curMap.containsKey(k);
    }


    /**
     * 这个是转码 unicode的 也放这吧
     * @param s
     * @return
     */
    public static String UnicodeToUtf8(String s){
        StringBuilder resultSB = new StringBuilder();
        char[] cArry = s.toCharArray();
        int l =cArry.length;
        for(int n = 0;n<l;)
        {
            //16进制
            StringBuilder sb16 = new StringBuilder();
            if(cArry[n] == '\\'){
                int i= 1;
                if((n+i)<l && cArry[n+i]=='u')
                {   //往下读四位
                    i++;
                    while (i <= 5) {
                        if((n+i)>=l){
                            break;
                        }
                        sb16.append(cArry[n + i]);
                        i++;
                    }
                }
            }
            //是不是 十六进制 就不验证了...微博没那么坑吧.....
            if(sb16.length()==4){
                char nc =(char)Integer.parseInt(sb16.toString(), 16);
                resultSB.append(nc);
                n+=6;
            }else{
                resultSB.append(cArry[n]);
                n++;
            }
        }
        return resultSB.toString();
    }
    /**
     * 获取评论的URL
     * @param mid 这是是微博的ID
     */
    public static String getRepeatURL(String mid){
        String repeatUrl = "https://weibo.com/aj/v6/comment/small?"
                +"ajwvr=6&act=list&"
                +"mid=%s&"
                +"uid=%s&"
                +"isMain=true&"
                +"ouid=%s&"
                +"location=page_100505_home&"
                +"comment_type=0&"
                +"_t=0&"
                +"__rnd=%s";
        return String.format(repeatUrl,mid,"7395746118","7395746118",System.currentTimeMillis());

    }
    /**
     * 翻页 URL
     * {"预览":{"HTML_PREVIEW":{"responseContent":{"content":{"mimeType":"text/html; charset=utf-8","text":"<script>parent.FM.view({\"ns\":\"
     * pl.content.homeFeed.index\",\"domid\":\"Pl_Official_MyProfileFeed__20\",......................
     * @param userId 这个是 目标的ID
     */
    public static String getPageURL(String baseUrl,String userId,int page){
        String pageUrl ="%s%s?pids=Pl_Official_MyProfileFeed__20&"
                + "is_search=0&"
                + "visible=0&"
                + "is_all=1&"
                + "is_tag=0&"
                + "profile_ftype=1&"
                + "page=%s&"
                + "ajaxpagelet=1&"
                + "ajaxpagelet_v6=1";
        return  String.format(pageUrl,pageUrl,baseUrl,userId,page);
    }
    /**
     * 获取滚轮 Url
     * @param  pagebar 这个是滚轮的次数  每页最多两次  0 1
     */
    public static String getBarURL(String userId,int pagebar,int page){
        String barUrl ="https://weibo.com/p/aj/v6/mblog/mbloglist?"
                +"ajwvr=6&domain=100505&"
                +"is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&"
                +"page=%s&"
                +"pagebar=%s&"
                +"pl_name=Pl_Official_MyProfileFeed__20&"
                +"id=100505%s&"
                +"script_uri=/u/%s&"
                +"feed_type=0&"
                +"pre_page=%s&"
                +"domain_op=100505&"
                +"__rnd=%s";
        return String.format(barUrl,page,pagebar,userId,userId,page,System.currentTimeMillis());
    }
    //cookie 要拼时间戳
    public static String buildCookie(Long time){
        String cookie ="UOR=ent.ifeng.com,widget.weibo.com,login.sina.com.cn; SINAGLOBAL=6230222730885.48.1560012855202; ULV=1582514470742:9:4:1:4306858346535.7715.1582514470740:1582191061715; SUHB=010GcjT9retXYy; webim_unReadCount=%7B%22time%22%3A"+time+"%2C%22dm_pub_total%22%3A1%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A29%2C%22msgbox%22%3A0%7D; SCF=AohfTVxWDK4GkWhnzs-2sU6u0NhkZZ8VpWU6KboEZpM80m2VxqwJ1zo9HzfKS85s4cA3hxuCOVbq8bRTy9WYwdQ.; un=17398058890; wvr=6; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFQCNsWIRPqXDTOvvfy0oE25JpX5KzhUgL.FoM01K-NShqpeKn2dJLoIEXLxKqL1KMLBK5LxK-LBo5LB.BLxKML1-2L1hBLxKML1-eLB.2LxKqL1-eL1-zt; wb_view_log_7395746118=1536*8641.25; YF-Page-G0=ae24d9a5389d566d388790f1c25a266b|1582514469|1582514448; SUB=_2A25zVzFODeRhGeFN4lcW9CjNyjSIHXVQJSWGrDV8PUNbmtAfLW3SkW9NQ6ZqFQQhgLXFvXNVg7mtSWALCiw7bAMK; ALF=1614050455; SSOLoginState=1582514462; _s_tentry=login.sina.com.cn; Apache=4306858346535.7715.1582514470740; YF-V5-G0=8c1ea32ec7cf68ca3a1513783c276b8c";
                //固定值
//                "UOR=ent.ifeng.com,widget.weibo.com,login.sina.com.cn;" +
//                        " SUB=_2A25zVByNDeRhGeFN4lcW9CjNyjSIHXVQIAlFrDV8PUJbmtAfLUGgkW9NQ6ZqFW1VHd61DCuHGgynVdTePLMc11ce;" +
//                        " SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFQCNsWIRPqXDTOvvfy0oE25JpX5K2hUgL.FoM01K-NShqpeKn2dJLoIEXLxKqL1KMLBK5LxK-LBo5LB.BLxKML1-2L1hBLxKML1-eLB.2LxKqL1-eL1-zt;" +
//                        //固定值
//                        " SINAGLOBAL=6230222730885.48.1560012855202;" +
//                        " ULV=1582191061715:8:3:2:406497514369.90234.1582191061426:1582107391785;" +
//                        " SUHB=0gHirsvu-JdKce;" +
//                        " webim_unReadCount=%7B%22time%22%3A"+time+"%2C%22dm_pub_total%22%3A0%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A16%2C%22msgbox%22%3A0%7D;" +
//                        " wb_view_log_7395746118=1536*8641.25;" +
//                        " YF-Page-G0=451b3eb7a5a4008f8b81de1fcc8cf90e|1582335729|1582335610;" +
//                        " _s_tentry=-;" +
//                        " Apache=406497514369.90234.1582191061426;" +
//                        " YF-V5-G0=3751b8b40efecee990eab49e8d3b3354;" +
//                        " Ugrow-G0=1ac418838b431e81ff2d99457147068c;" +
//                        " WBtopGlobal_register_version=307744aa77dd5677;" +
//                        " login_sid_t=522627d77aea1279f459458b83309138;" +
//                        " cross_origin_proto=SSL;" +
//                        " wb_view_log=1536*8641.25;" +
//                        " SCF=AohfTVxWDK4GkWhnzs-2sU6u0NhkZZ8VpWU6KboEZpM8ZZnm9iRlsE1zFpbGocp5MAnDSCEdOdS_V0yfaJx_EWU.;" +
//                        " SSOLoginState=1582202750;" +
//                        " un=17398058890";
        return cookie;
    }
}
