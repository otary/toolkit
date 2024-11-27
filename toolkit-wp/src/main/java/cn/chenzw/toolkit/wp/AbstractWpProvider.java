package cn.chenzw.toolkit.wp;

import cn.chenzw.toolkit.wp.enums.Wp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author chenzw
 */
public abstract class AbstractWpProvider implements WpProvider {

    @Override
    public boolean shareUrlMatches(String shareUrl) {
        return getShareUrlPattern().matcher(shareUrl.trim())
                .matches();
    }

    @Override
    public List<String> extractShareUrls(String content) {
        List<String> shareUrls = new ArrayList<>();
        Matcher matcher = getShareUrlPattern().matcher(content);
        while (matcher.find()) {
            shareUrls.add(matcher.group());
        }
        return shareUrls;
    }

    @Override
    public String extractShareId(String shareUrl) {
        Matcher matcher = getShareUrlPattern().matcher(shareUrl);
        if (!matcher.find()) {
            return "";
        }
        return matcher.group(1);
    }

    @Override
    public String extractPassCodeFromShareUrl(String shareUrl) {
        Matcher matcher = getShareUrlPattern().matcher(shareUrl);
        if (!matcher.find()) {
            return "";
        }
        return matcher.group(2);
    }

    @Override
    public boolean support(Wp wp) {
        return wp == getType();
    }
}
