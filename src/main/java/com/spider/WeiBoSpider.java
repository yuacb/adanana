package com.spider;

import com.spider.tools.WeiBoEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WeiBoSpider {
    private static final String TAG_SCRIPT = "script";
    private static final String BASE_URL = "https://weibo.com/u/";
    private static final String ROOT_UESR_ID = "7395746118";
    //翻页
//    @Autowired
//    private RedisTemplate redisTemplate;

    public static void main(String[] args) {
        WeiBoSpider w = new WeiBoSpider();
        w.getInfo("3920678482");
    }

    public void getInfo(String userId){
        if(null == userId){
            userId = "3920678482";
        }
        String weiboUrl = BASE_URL+userId+"?profile_ftype=1&is_all=1";
        try {
            //这个是首页的  html
            String HTMLString = getConnectionResponse(weiboUrl,null,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            //开始循环..好像有死循环的 风险
            boolean next = true;
            int page = 1;
            int pagebar = 0;
            String loopHTMLString;
            String pageHtmlUrl;
            String barHtmlUrl;
            while (next)
            {
                String feedListHTml = getHtmlDoc(HTMLString);
                next = getFeedListContent(feedListHTml,userId);
                //两次滚轮
                //滚轮 的URL
                while(pagebar<=1)
                {
                    barHtmlUrl = getBarURL(userId, pagebar, page);
                    loopHTMLString = getConnectionResponse(barHtmlUrl,"application/x-www-form-urlencoded","*/*");
                    //去掉没用的东西
                    loopHTMLString = loopHTMLString.substring(loopHTMLString.indexOf("<div"),loopHTMLString.length()-3).replace("<\\","<").replace("\\\"","\"");
                    next = getFeedListContent(loopHTMLString,userId);
                    pagebar++;
                    if(!next){
                        pagebar=0;
                        break;
                    }
                }
                page++;
                pageHtmlUrl = getPageURL(BASE_URL,userId,page);
                HTMLString = getConnectionResponse(pageHtmlUrl,null,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // https://weibo.com/u/3920678482

    /**
     * 微博列表返回
     * @param accept
     * @return "text/plain;charset=UTF-8"
     * @throws IOException
     */
    public String getConnectionResponse(String urlStr,String contentType,String accept) throws IOException {
        URL url = new URL(urlStr);
        System.out.println("访问地址"+urlStr);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept",accept);
        //connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
        connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        connection.setRequestProperty("Cache-Control","no-cache");
        connection.setRequestProperty("Connection","keep-alive");
        if(null!=contentType){
            connection.setRequestProperty("Content-Type",contentType);
        }

        connection.setRequestProperty("Host","weibo.com");

        connection.setRequestProperty("Upgrade-Insecure-Requests","1");
        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:73.0) Gecko/20100101 Firefox/73.0");
        connection.setRequestProperty("Cookie",buildCookie(System.currentTimeMillis()));
        connection.setInstanceFollowRedirects(true);
        connection.setConnectTimeout(3000);
        connection.setUseCaches(false);

        int code = connection.getResponseCode();
        System.out.println("返回码" + code );
        System.out.println("编码" + connection.getContentType());

        StringBuilder sbHTML = new StringBuilder();
        String HTMLString = "";
        String temp = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf8"));
        if(200 == code)
        {   //e..我不如一开是 字节转字符 转 unicode...再说吧
            while (null != (temp = reader.readLine())) {
                sbHTML.append(temp);
            }
            HTMLString = sbHTML.toString();
            if(null != contentType){
                HTMLString = UnicodeToUtf8(sbHTML.toString());
            }
        }
        connection.disconnect();
        return HTMLString;
    }

    public String UnicodeToUtf8(String s){
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
     * 获取 微博 消息
     * 将内容直接放到 REDIS里 ...
     * @throws IOException
     */

    public String getHtmlDoc(String HTMLString){
        Document doc = Jsoup.parse(HTMLString);
        Elements elementsScript = doc.getElementsByTag(TAG_SCRIPT);
        String feedListHTml = null;
        for(Element o:elementsScript){
            if(o.html().contains("\"domid\":\"Pl_Official_MyProfileFeed__20\""))
            {
                int startIndex = o.html().indexOf("\"html\":\"");
                feedListHTml = o.html().substring(startIndex+19, o.html().length()-3).replace("<\\","<").replace("\\\"","\"");

                break;
            }
        }
        return feedListHTml;
    }

    /**
     * 返回是否有下一页
     * @param feedListHTml
     * @return
     * @throws IOException
     */
    public boolean getFeedListContent(String feedListHTml,String userId) throws IOException {
        //这个是 一条微博的ID
        String weiboFeedId="";
        Map<String,Object> map = new HashMap<>();
        //这个用来计数的 评论 点赞 等的数量
        Integer count = 0;
        Document feedListDoc = Jsoup.parse(feedListHTml);
        //获取 包含了
        Elements elementDetails = feedListDoc.select("div.WB_cardwrap.WB_feed_type");
        if(elementDetails.size() == 0)
        {
            return false;
        }
        for(Element o:elementDetails){
            //那么...这条 是她给别人点赞的  先跳过吧
            if(!o.attr("tbinfo").equals("ouid="+userId)){
                continue;
            }
            //获取 赞 评论 转发 收藏
            for(WeiBoEnum oEnum:WeiBoEnum.values()){
                Elements element = o.select("em."+oEnum.getCode()).next();
                Element elementForSelf;
                //这里 有转发的情况
                //取第二个 自己的 评论 数量
                if(element.size()>1)
                {
                    elementForSelf = element.get(1);
                }else{
                    elementForSelf = element.get(0);
                }
                //如果是中文 就是0
                if(elementForSelf.text().equals(oEnum.getValue())){
                    count=Integer.valueOf(0);
                }else{
                    count=Integer.valueOf(elementForSelf.text());
                }
                //fixme 看看的写好了删除
                for(Map.Entry<String,Object> entry : map.entrySet())
                {
                    System.out.print(entry.getValue()+"  ");
                }
                //column
                map.put(oEnum.getColumnName(),count);
                //这个是用来获取 评论的
                //element.parents()[4];
                //将评论的人 放入待 查询的队列 = =虽然 我还没想好用什么队列
            }
            if(((Integer)map.get(WeiBoEnum.WEBO_REPEAT.getColumnName()))>0 ){
                //这里有评论ID
                String sudaUatrack = o.select("a[action-data='fl_comment']").get(0).attr("suda-uatrack");
                weiboFeedId =sudaUatrack.substring(sudaUatrack.indexOf("comment:")+7,sudaUatrack.length()-1);
                //TODO
                /**
                 * 获取评论里的人 继续下一步 爬数据..
                 * 要去重复的人 set
                 * 评论的内容也记录吧
                 * 就不直接 接下去了  写个事件 或者 线程 另外跑吧
                 */
            }
            //column 微博的ID
            map.put("weiboFeedId",weiboFeedId);
            //column 昵称
            map.put("weiboName",o.select("div[node-type='feed_list_content']").get(0).attr("nick-name"));
            //column 这里是一条微博 内容
            map.put("weiboContent",o.select("div[node-type='feed_list_content']").get(0).text());
            //column 发布时间 node-type="feed_list_item_date"
            map.put("feedTime",o.select("a[node-type='feed_list_item_date']").get(0).attr("title"));

            System.out.println("微博内容"+o.select("div[node-type='feed_list_content']").get(0).text());
        }
        return true;
    }
    /**
     * 获取评论的URL
     */
    public String repeatURL(String mid){
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
        return String.format(repeatUrl,mid,ROOT_UESR_ID,ROOT_UESR_ID,System.currentTimeMillis());

    }
    /**
     * 翻页 URL
     * {"预览":{"HTML_PREVIEW":{"responseContent":{"content":{"mimeType":"text/html; charset=utf-8","text":"<script>parent.FM.view({\"ns\":\"
     * pl.content.homeFeed.index\",\"domid\":\"Pl_Official_MyProfileFeed__20\",......................
     */
    public String getPageURL(String baseUrl,String userId,int page){
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
     *
     */
    public String getBarURL(String userId,int pagebar,int page){
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
    public String buildCookie(Long time){
        String cookie =
                //固定值
                "UOR=ent.ifeng.com,widget.weibo.com,login.sina.com.cn;" +
                " SUB=_2A25zVByNDeRhGeFN4lcW9CjNyjSIHXVQIAlFrDV8PUJbmtAfLUGgkW9NQ6ZqFW1VHd61DCuHGgynVdTePLMc11ce;" +
                " SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFQCNsWIRPqXDTOvvfy0oE25JpX5K2hUgL.FoM01K-NShqpeKn2dJLoIEXLxKqL1KMLBK5LxK-LBo5LB.BLxKML1-2L1hBLxKML1-eLB.2LxKqL1-eL1-zt;" +
                //固定值
                " SINAGLOBAL=6230222730885.48.1560012855202;" +
                " ULV=1582191061715:8:3:2:406497514369.90234.1582191061426:1582107391785;" +
                " SUHB=0gHirsvu-JdKce;" +
                " webim_unReadCount=%7B%22time%22%3A"+time+"%2C%22dm_pub_total%22%3A0%2C%22chat_group_client%22%3A0%2C%22allcountNum%22%3A16%2C%22msgbox%22%3A0%7D;" +
                " wb_view_log_7395746118=1536*8641.25;" +
                " YF-Page-G0=451b3eb7a5a4008f8b81de1fcc8cf90e|1582335729|1582335610;" +
                " _s_tentry=-;" +
                " Apache=406497514369.90234.1582191061426;" +
                " YF-V5-G0=3751b8b40efecee990eab49e8d3b3354;" +
                " Ugrow-G0=1ac418838b431e81ff2d99457147068c;" +
                " WBtopGlobal_register_version=307744aa77dd5677;" +
                " login_sid_t=522627d77aea1279f459458b83309138;" +
                " cross_origin_proto=SSL;" +
                " wb_view_log=1536*8641.25;" +
                " SCF=AohfTVxWDK4GkWhnzs-2sU6u0NhkZZ8VpWU6KboEZpM8ZZnm9iRlsE1zFpbGocp5MAnDSCEdOdS_V0yfaJx_EWU.;" +
                " SSOLoginState=1582202750;" +
                " un=17398058890";
        return cookie;
    }
}
