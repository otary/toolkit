package cn.chenzw.toolkit.spring.listener;

import cn.chenzw.toolkit.spring.core.SpringContextHolder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class AppContextStartListener implements ApplicationListener<ContextRefreshedEvent> {

  /*  @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("----------------:" + event.getApplicationContext().getId() + "::" + event.getApplicationContext());
    }*/

    /*@Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("----------------:" + event.getApplicationContext().getId() + "::" + event.getApplicationContext());
    }*/

   /* @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("----------------:" + event.getApplicationContext().getId() + "::" + event.getApplicationContext());
    }*/


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SpringContextHolder.addContext(event.getApplicationContext());
    }
}
