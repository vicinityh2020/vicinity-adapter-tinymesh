package com.tinymesh.vicinity.adapter.bootstrap;
import com.tinymesh.vicinity.adapter.client.TinyMStreamClient;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StreamBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private TinyMStreamClient tinyMStreamClient;

    /**
     * @param tinyMStreamClient
     *
     * {@link TinyMStreamClient}
     */
    public StreamBootstrap(TinyMStreamClient tinyMStreamClient) {
        this.tinyMStreamClient = tinyMStreamClient;
    }

    /**
     * @param contextRefreshedEvent
     *
     * {@link ContextRefreshedEvent}
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        tinyMStreamClient.streamDeviceUpdates();
    }
}
