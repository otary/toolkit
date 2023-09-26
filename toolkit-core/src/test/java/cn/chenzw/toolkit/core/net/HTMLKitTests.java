package cn.chenzw.toolkit.core.net;

import cn.chenzw.toolkit.core.net.entity.HTMLMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@Slf4j
@RunWith(JUnit4.class)
public class HTMLKitTests {

    @Test
    public void testGetText() {
        String text = HTMLKit.getText("<html><div>xxxx</div><a>yyyy</a></html>");
        Assert.assertEquals("xxxxyyyy", text);
    }

    @Test
    public void testParseAsMetadata() throws IOException {
        URLConnection urlConnection = URI.create("https://app.haikei.app/").toURL().openConnection();
        urlConnection.connect();
        InputStream in = new BufferedInputStream(
                urlConnection.getInputStream()
        );
        String html = IOUtils.toString(in, StandardCharsets.UTF_8);
        //log.info("html => {}", html);

        HTMLMetadata htmlMetadata = HTMLKit.parseAsMetadata(html);
        log.info("HTMLMetadata => {}", htmlMetadata);

    }
}
