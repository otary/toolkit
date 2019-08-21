package cn.chenzw.toolkit.spring.listener;

import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author chenzw
 */
public class AppContextStartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SpringContextHolder.addContext(event.getApplicationContext());
    }
}
