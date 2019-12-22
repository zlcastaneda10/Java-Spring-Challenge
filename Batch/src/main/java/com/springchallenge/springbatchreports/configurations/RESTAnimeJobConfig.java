package com.springchallenge.springbatchreports.configurations;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import com.springchallenge.springbatchreports.readers.RESTAnimeReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RESTAnimeJobConfig {
    @Bean
    ItemReader<Integer> restAnimeReader(
            //Environment environment,
            RestTemplate restTemplate
    ){
        return new RESTAnimeReader(
                restTemplate);
    }
}
