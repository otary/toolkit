package cn.chenzw.toolkit.wp;

import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.provider.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

/**
 * @author chenzw
 */
@Slf4j
@RunWith(JUnit4.class)
public class WpProviderTests {

    AliYunWpProvider aliYunWpProvider = new AliYunWpProvider();

    BaiduWpProvider baiduWpProvider = new BaiduWpProvider();

    QuarkWpProvider quarkWpProvider = new QuarkWpProvider();

    Cloud189WpProvider cloud189WpProvider = new Cloud189WpProvider();

    A115WpProvider a115WpProvider = new A115WpProvider();

    UcWpProvider ucWpProvider = new UcWpProvider();

    XunLeiWpProvider xunLeiWpProvider = new XunLeiWpProvider();

    /**
     * 阿里云盘
     */
    @Test
    public void testAliYunShareUrlMatches() {
        boolean matches = aliYunWpProvider.shareUrlMatches(" https://www.aliyundrive.com/s/KQZAbUsukai");
        log.info("matches => {}", matches);
    }

    @Test
    public void testAliYunExtractShareUrls() {
        List<String> shareUrls = aliYunWpProvider.extractShareUrls("合集\t【850T】蓝光原盘/影视剧/动漫/综艺等等\n" +
                "(需要搭配秒传,方式使用.)\thttps://www.aliyundrive.com/s/HCL2nhLB3uE\n" +
                "国产剧\t[爆笑短剧-浴室王者乔杉/大鹏成名作] \n" +
                "屌丝男士 1-4季1080P\thttps://www.aliyundrive.com/s/Fpwty7a8cZv\n" +
                "合集\t【71部】2021年 最新全集完结 中外电视剧\thttps://www.aliyundrive.com/s/7MB54ZC8ZW7\n" +
                "国产剧\t宰相刘罗锅.全40集.1996.简繁中字￡CMCT小五\thttps://www.aliyundrive.com/s/SME9bRaPg4X\n" +
                "国产剧\t一起深呼吸 MP4/1080P 全集 (2021)\thttps://www.aliyundrive.com/s/fgtgB8BxXbq\n" +
                "国产剧\t原来你是这样的顾先生 MP4/1080P 全集 (2021)\thttps://www.aliyundrive.com/s/79pdoxRteXo\n" +
                "国产剧\t你和我的倾城时光 MP4/1080P 全集 (2018)\thttps://www.aliyundrive.com/s/8LaeKFZAHUr");
        log.info("shareUrls => {}", shareUrls);
    }

    @Test
    public void testAliYunExtractShareId() {
        String shareId = aliYunWpProvider.extractShareId("https://www.aliyundrive.com/s/KQZAbUsukai");
        log.info("shareId => {}", shareId);
    }

    @Test
    public void testFetchAliYunShareInfo() throws Exception {
        // 无分享码
        WpShareInfo wpShareInfo = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/XuUW1WGwQRy", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 有效期
        WpShareInfo wpShareInfo2 = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/YTXgZ1AiUfb", "");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 需要passCode
        WpShareInfo wpShareInfo3 = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/oR43nuiayD8", "3c6d");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

        // 分享过期/取消
        WpShareInfo wpShareInfo4 = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/2qASmkUKoHV", "");
        log.info("wpShareInfo4 => {}", wpShareInfo4);

        // 禁止访问
        WpShareInfo wpShareInfo5 = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/ypJdAY1trka", "");
        log.info("wpShareInfo5 => {}", wpShareInfo5);
    }

    /**
     * 百度网盘
     */
    @Test
    public void testBaiduShareUrlMatches() {
        boolean matches = baiduWpProvider.shareUrlMatches("https://pan.baidu.com/s/16OfAq2t5ngToq8T72ZH2vw");
        log.info("matches => {}", matches);

    }

    @Test
    public void testFetchBaiduShareInfo() throws Exception {
        WpShareInfo wpShareInfo = baiduWpProvider.fetchShareInfo("https://pan.baidu.com/s/16OfAq2t5ngToq8T72ZH2vw", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 分享过期
        WpShareInfo wpShareInfo2 = baiduWpProvider.fetchShareInfo("https://pan.baidu.com/s/1gVNTwLHQHBHtVRxxoXIh3A", "");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 需要passCode + 分享过期
        WpShareInfo wpShareInfo3 = baiduWpProvider.fetchShareInfo("https://pan.baidu.com/s/1xp6qzzF1Q06NMP_3cd4-Dg?pwd=28i9", "28i9");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

    }


    /**
     * 夸克网盘
     */
    @Test
    public void testFetchQuarkShareInfo() throws Exception {
        WpShareInfo wpShareInfo = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/d4443ee46957", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 带验证码 + 有效期
        WpShareInfo wpShareInfo2 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/82307c38d85d", "9GeS");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 带验证码，未输入
        WpShareInfo wpShareInfo3 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/82307c38d85d", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

        // 分享已失效
        WpShareInfo wpShareInfo4 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/00bc70142fe", "");
        log.info("wpShareInfo4 => {}", wpShareInfo4);

    }


    /**
     * 天翼网盘
     */
    @Test
    public void testFetchCloud189ShareInfo() throws Exception {
        WpShareInfo wpShareInfo = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=uyiqmyJJZ7vi", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 带分享码 + 过期时限
        WpShareInfo wpShareInfo2 = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=v22QRvYr6JNb", "6osm");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 过期
        WpShareInfo wpShareInfo3 = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=Q7jYfy3Q3Ybi", "t1hc");
        log.info("wpShareInfo3 => {}", wpShareInfo3);
    }

    /**
     * 115网盘
     */
    @Test
    public void testFetch115ShareInfo() throws Exception {
        /*WpShareInfo wpShareInfo = a115WpProvider.fetchShareInfo("https://115.com/s/sw6cauy3nuq?password=f7b8&#", "f7b8");
        log.info("wpShareInfo => {}", wpShareInfo);*/

        // 密码 + 过期时限
       /* WpShareInfo wpShareInfo2 = a115WpProvider.fetchShareInfo("https://115.com/s/sw6camv3nuq?password=tec0", "tec0");
        log.info("wpShareInfo2 => {}", wpShareInfo2);*/

        // 分享取消
        WpShareInfo wpShareInfo3 = a115WpProvider.fetchShareInfo("https://115.com/s/sw3ajbw3wag", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

    }

    /**
     * UC网盘
     */
    @Test
    public void testFetchUcShareInfo() throws Exception{
        WpShareInfo wpShareInfo = ucWpProvider.fetchShareInfo("", "");
        log.info("wpShareInfo => {}", wpShareInfo);
    }

    /**
     * 迅雷网盘
     * @throws Exception
     */
    @Test
    public void testFetchXunLeiShareInfo() throws Exception{
      /*  WpShareInfo wpShareInfo = xunLeiWpProvider.fetchShareInfo("https://pan.xunlei.com/s/VNdjaEwAaT3L8HiYMnR3cIPzA1?pwd=ttfb", "ttfb");
        log.info("wpShareInfo => {}", wpShareInfo);*/

        // 过期期限
        WpShareInfo wpShareInfo2 = xunLeiWpProvider.fetchShareInfo("https://pan.xunlei.com/s/VNeXXIsjNTIKM8sY9CIkcj2GA1?pwd=zpbx#", "zpbx");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

      /*  「链接：https://pan.xunlei.com/s/VNeXX3og550Xg-Edbj1nwAHyA1?pwd=9xr5# 提取码：9xr5”复制这段内容后打开手机迅雷App，查看更方便」

    「链接：https://pan.xunlei.com/s/VNeXXBpwZL9Ly44XQBOKMQzgA1# 提取码：jest”复制这段内容后打开手机迅雷App，查看更方便」
*/

    }

}
