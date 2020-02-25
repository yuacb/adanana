package com.adanana.spider;

import com.adanana.spider.model.SpiderWeboInfo;
import com.adanana.spider.tools.CandidateQueue;
import com.adanana.spider.tools.WeiBoEnum;
import com.adanana.spider.tools.WeiboURLTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.concurrent.TimeUnit;
@Component
public class WeiBoSpiderThread implements Runnable {
    @Resource
    private RedisTemplate redisTemplate;
    private static final String TAG_SCRIPT = "script";
    private static final String BASE_URL = "https://weibo.com/u/";
    @Override
    public void run() {
        String userId = CandidateQueue.poll(1l, TimeUnit.MINUTES);
        if(userId == null)
        {
            //没有就算了.....
            return;
        }
        getInfo(userId);
    }

    public void getInfo(String userId){
        System.out.println("当前线程"+Thread.currentThread().getName());
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
                    barHtmlUrl = WeiboURLTool.getBarURL(userId, pagebar, page);
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
                pageHtmlUrl =WeiboURLTool.getPageURL(BASE_URL,userId,page);
                HTMLString = getConnectionResponse(pageHtmlUrl,null,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            }

        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
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
        connection.setRequestProperty("Cookie",WeiboURLTool.buildCookie(System.currentTimeMillis()));
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
        {   //e..我不如一开始 字节转字符 转 unicode...再说吧
            while (null != (temp = reader.readLine())) {
                sbHTML.append(temp);
            }
            HTMLString = sbHTML.toString();
            if(null != contentType){
                HTMLString = WeiboURLTool.UnicodeToUtf8(sbHTML.toString());
            }
        }
        connection.disconnect();
        System.out.println(HTMLString);
        return HTMLString;
    }

    /**
     * 获取 微博 消息
     * 将内容直接放到 REDIS里 ...
     * @throws IOException
     * 由于 响应 格式的区别  取页的时候需要
     * 滚轮不需要
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
    public boolean getFeedListContent(String feedListHTml,String userId) throws IOException, NoSuchFieldException, IllegalAccessException {
        SpiderWeboInfo spiderWeboInfo = new SpiderWeboInfo();
        //这个是 一条微博的ID
        String weiboFeedId="";
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
                Field field = SpiderWeboInfo.class.getDeclaredField(oEnum.getFieldName());
                field.setAccessible(true);
                field.set(spiderWeboInfo,count);
            }
             weiboFeedId = o .attr("mid");
            //这里好像抓不完所有评论 以后再说
            if( spiderWeboInfo.getNumberRepeat() > 0 ){
                //这里有评论ID
                String repeatURL = WeiboURLTool.getRepeatURL(weiboFeedId);
                String repeatHTML = getConnectionResponse(repeatURL,"application/x-www-form-urlencoded","*/*");
                String repeatHTMLString = repeatHTML.substring(repeatHTML.indexOf("\"html\":\"")+8,repeatHTML.indexOf("\",\"count\":")).replace("\\\"","\"").replace("\\\\/","/")
                        .replace("lt;\\","<\\").replace("l&gt;",">");
                Document repeatElements = Jsoup.parse("<html><head></head> <body>"+repeatHTMLString+"</body></html>");
                Elements repeatTextElements = repeatElements.select("div[class='WB_text']");
                for(Element repeatTextElement : repeatTextElements)
                {
                    //这里没有二级评论
                    Elements usercardElemt = repeatTextElement.select("a[usercard]");
                    if(usercardElemt.size()==0){
                        continue;
                    }
                    String repeaUserId = usercardElemt.get(0).attr("usercard").substring(3);
                    //放进队列里 重复的就不要了
                    if(WeiboURLTool.containsKey(repeaUserId)){
                        WeiboURLTool.mapAdd(repeaUserId,userId);
                        CandidateQueue.offer(repeaUserId);
                    }
                }
            }
            //column 微博的ID
            spiderWeboInfo.setWeiboFeelId(weiboFeedId);
            //column 昵称
            spiderWeboInfo.setWeiboUserNickName( o.select("div[node-type='feed_list_content']").get(0).attr("nick-name"));
            //column 这里是一条微博 内容
            spiderWeboInfo.setWeiboContent(o.select("div[node-type='feed_list_content']").get(0).text());
            //column 发布时间 node-type="feed_list_item_date"
            spiderWeboInfo.setFeedTime(o.select("a[node-type='feed_list_item_date']").get(0).attr("title"));
            //redis
            redisTemplate.opsForList().leftPush("spiderWeboInfoList",spiderWeboInfo);

        }
        return true;
    }


}
