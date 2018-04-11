package com.tinymesh.vicinity.adapter.bootstrap;
import com.tinymesh.vicinity.adapter.client.StreamTinyMCloud;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StreamBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private StreamTinyMCloud streamTinyMCloud;

    public StreamBootstrap(StreamTinyMCloud streamTinyMCloud) {
        this.streamTinyMCloud = streamTinyMCloud;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

      //  streamTinyMCloud.streamTinyM();
    }
}
