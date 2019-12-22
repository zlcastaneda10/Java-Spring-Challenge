package com.springchallenge.springbatchreports.readers;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class RESTAnimeReader implements ItemReader<Integer> {
    private final RestTemplate restTemplate;
    private int nextAnimeIndex;
    private List<Integer> animesData;

    public RESTAnimeReader( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.nextAnimeIndex = 0;
    }

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (animeDataIsNotInitialized()) {
            animesData = fetchStudentDataFromAPI();
        }

        Integer nextAnime = null;

        if (nextAnimeIndex < animesData.size()) {
            nextAnime = animesData.get(nextAnimeIndex);
            nextAnimeIndex++;
        }

        return nextAnime;
    }

    private boolean animeDataIsNotInitialized() {
        return this.animesData == null;
    }

    private List<Integer> fetchStudentDataFromAPI() {

        Integer[] response =  restTemplate.getForObject(
                "http://localhost:8085/anime/top?limit=10&genre=Romance",
                Integer[].class
        );

        return Arrays.asList(response);
    }

}
