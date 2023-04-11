package cn.chenzw.toolkit.spring.listener;

import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

public class AppContextStopListener implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        SpringContextHolder.removeContext(event.getApplicationContext());
    }
}
