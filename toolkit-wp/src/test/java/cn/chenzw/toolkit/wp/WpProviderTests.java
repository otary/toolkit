package cn.chenzw.toolkit.wp;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import cn.chenzw.toolkit.wp.entity.WpShareInfo;
import cn.chenzw.toolkit.wp.provider.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.LoggerFactory;

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

    LanZouWpProvider lanZouWpProvider = new LanZouWpProvider();

    @Before
    public void setUp() {
        Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.DEBUG);
    }

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

        // 空文件
        WpShareInfo wpShareInfo6 = aliYunWpProvider.fetchShareInfo("https://www.aliyundrive.com/s/efRYPrQPuoB", "");
        log.info("wpShareInfo6 => {}", wpShareInfo6);
    }

    /**
     * 百度网盘
     */
    @Test
    public void testBaiduShareUrlMatches() {
        boolean matches = baiduWpProvider.shareUrlMatches("https://pan.baidu.com/s/16OfAq2t5ngToq8T72ZH2vw");
        log.info("matches => {}", matches);
    }

    /**
     * 百度网盘
     *
     * @throws Exception
     */
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

        // VIP
        WpShareInfo wpShareInfo4 = baiduWpProvider.fetchShareInfo("https://pan.baidu.com/s/12UZ4xS6mTZJQQ9r9V8y-oQ?pwd=8888", "");
        log.info("wpShareInfo4 => {}", wpShareInfo4);

    }

    @Test
    public void testBaiduExtractPassCodeFromShareUrl() {
        String passCode = baiduWpProvider.extractPassCodeFromShareUrl("https://pan.baidu.com/s/1sjxTjjQFiG8U-2ty6V576g?pwd=mad7");
        Assert.assertEquals("mad7", passCode);
    }


    /**
     * 夸克网盘
     */
    @Test
    public void testFetchQuarkShareInfo() throws Exception {
        WpShareInfo wpShareInfo7 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/24220bc80d1a", "");
        log.info("wpShareInfo6 => {}", wpShareInfo7);

        // 被封禁
        WpShareInfo wpShareInfo = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/d4443ee46957", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 带分享码 + 有效期
        WpShareInfo wpShareInfo2 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/82307c38d85d", "9GeS");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 带分享码，未输入
        WpShareInfo wpShareInfo3 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/82307c38d85d", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

        // 分享已失效
        WpShareInfo wpShareInfo4 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/00bc70142fe", "");
        log.info("wpShareInfo4 => {}", wpShareInfo4);

        // 没有作者信息
        WpShareInfo wpShareInfo5 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/50846937b7eb", "");
        log.info("wpShareInfo5 => {}", wpShareInfo5);

        // 分享被删除
        WpShareInfo wpShareInfo6 = quarkWpProvider.fetchShareInfo("https://pan.quark.cn/s/bf0d14560e7d", "");
        log.info("wpShareInfo6 => {}", wpShareInfo6);

    }

    @Test
    public void testExtractCloud189ShareUrls() {
        List<String> shareUrls = cloud189WpProvider.extractShareUrls("pan>支持多份刻录、多进程写入，支持 IDE/SCSI/USB/1394/SATA等等； 版本特点 采用Hook劫持补丁破解专业版 去后续检测升级提示，禁止自动检测升级 删除多国语言和帮助，关于界面显示永久许可 解压文件夹使用，可通过批处理添加右键菜单 下载地址 https://cloud.189.cn/web/share?code=NZzQzqryqyIz（访问码：3vgd） https://www.leijing.xyz/ 收藏 0\n");
        log.info("shareUrls => {}", shareUrls);

        List<String> shareUrls2 = cloud189WpProvider.extractShareUrls("https://cloud.189.cn/t/AZjmmmnyimya（访问码：ll40）");
        log.info("shareUrls2 => {}", shareUrls2);
    }

    /**
     * 天翼网盘
     */
    @Test
    public void testFetchCloud189ShareInfo() throws Exception {
        WpShareInfo wpShareInfo = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=uyiqmyJJZ7vi", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 需要分享码 + 过期时限
        WpShareInfo wpShareInfo2 = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=v22QRvYr6JNb", "6osm");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 过期
        WpShareInfo wpShareInfo3 = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=Q7jYfy3Q3Ybi", "t1hc");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

        // 自带分享码
        WpShareInfo wpShareInfo4 = cloud189WpProvider.fetchShareInfo("https://cloud.189.cn/web/share?code=ZVVfeijAbai2（访问码：sgg3）", "");
        log.info("wpShareInfo3 => {}", wpShareInfo4);
    }

    @Test
    public void testCloud189ExtractPassCodeFromShareUrl() {
        String passCode = cloud189WpProvider.extractPassCodeFromShareUrl("https://cloud.189.cn/web/share?code=ZVVfeijAbai2（访问码：sgg3）");
        Assert.assertEquals("sgg3", passCode);
    }


    /**
     * 115网盘
     */
    @Test
    public void testFetch115ShareInfo() throws Exception {
        WpShareInfo wpShareInfo = a115WpProvider.fetchShareInfo("https://115.com/s/sw6cauy3nuq?password=f7b8&#", "f7b8");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 密码 + 过期时限
        WpShareInfo wpShareInfo2 = a115WpProvider.fetchShareInfo("https://115.com/s/sw6camv3nuq?password=tec0", "tec0");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 分享取消
        WpShareInfo wpShareInfo3 = a115WpProvider.fetchShareInfo("https://115.com/s/sw3ajbw3wag", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);

    }

    @Test
    public void test115ExtractPassCodeFromShareUrl() {
        String passCode = a115WpProvider.extractPassCodeFromShareUrl("https://115.com/s/sw6cauy3nuq?password=f7b8&#");
        Assert.assertEquals("f7b8", passCode);

        String passCode2 = a115WpProvider.extractPassCodeFromShareUrl("https://115.com/s/sw6camv3nuq?password=tec0");
        Assert.assertEquals("tec0", passCode2);
    }

    @Test
    public void testFetchLanZouShareInfo() throws Exception {
        WpShareInfo wpShareInfo = lanZouWpProvider.fetchShareInfo("https://wwgl.lanzout.com/iucAp03j516f", "");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 带分享码
        WpShareInfo wpShareInfo2 = lanZouWpProvider.fetchShareInfo("https://wwgl.lanzout.com/iKytB03j3ljg", "33zw");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        // 分享过期
        WpShareInfo wpShareInfo3 = lanZouWpProvider.fetchShareInfo("https://wwgl.lanzout.com/iVtzX18yu9kd", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);
    }

    /**
     * UC网盘
     */
    @Test
    public void testFetchUcShareInfo() throws Exception {
        WpShareInfo wpShareInfo = ucWpProvider.fetchShareInfo("", "");
        log.info("wpShareInfo => {}", wpShareInfo);
    }

    /**
     * 迅雷网盘
     *
     * @throws Exception
     */
    @Test
    public void testFetchXunLeiShareInfo() throws Exception {
        WpShareInfo wpShareInfo = xunLeiWpProvider.fetchShareInfo("https://pan.xunlei.com/s/VNdjaEwAaT3L8HiYMnR3cIPzA1?pwd=ttfb", "ttfb");
        log.info("wpShareInfo => {}", wpShareInfo);

        // 过期期限
        WpShareInfo wpShareInfo2 = xunLeiWpProvider.fetchShareInfo("https://pan.xunlei.com/s/VNeXXIsjNTIKM8sY9CIkcj2GA1?pwd=zpbx#", "zpbx");
        log.info("wpShareInfo2 => {}", wpShareInfo2);

        WpShareInfo wpShareInfo3 = xunLeiWpProvider.fetchShareInfo("https://pan.xunlei.com/s/VNkLd_x8dClFEqFNCEq1E-lRA1?pwd=d5me&_t=1701326669492", "");
        log.info("wpShareInfo3 => {}", wpShareInfo3);
    }

    @Test
    public void testXunLeiExtractPassCodeFromShareUrl() {
        String code = xunLeiWpProvider.extractPassCodeFromShareUrl("https://pan.xunlei.com/s/VNdjaEwAaT3L8HiYMnR3cIPzA1?pwd=ttfb");
        Assert.assertEquals(code, "ttfb");

        String code2 = xunLeiWpProvider.extractPassCodeFromShareUrl("https://pan.xunlei.com/s/VNeXXIsjNTIKM8sY9CIkcj2GA1?pwd=zpbx#");
        Assert.assertEquals(code2, "zpbx");
    }


}
