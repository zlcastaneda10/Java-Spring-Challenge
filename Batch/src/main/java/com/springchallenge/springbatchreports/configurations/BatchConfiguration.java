package com.springchallenge.springbatchreports.configurations;

import com.springchallenge.springbatchreports.models.AnimeDTO;
import com.springchallenge.springbatchreports.processors.AnimeItemProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    //job
    //steps
    //resource?
    //itemreader
    //itemprocessor
    //itemwriter
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    public JobBuilderFactory jobs;
    @Autowired
    public StepBuilderFactory steps;

    //input

    private Resource outputResource = new FileSystemResource("output/outputdata.csv");

    @Bean
    public FlatFileItemWriter<AnimeDTO> writer(){
        //Create writer instance
        FlatFileItemWriter<AnimeDTO> writer = new FlatFileItemWriter<>();

        //Set output file location
        writer.setResource(outputResource);

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<AnimeDTO>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<AnimeDTO>() {
                    {
                        setNames(new String[] { "animeId", "name", "rating","type","source" });
                    }
                });
            }
        });
        return writer;
    }

    //cual seria el equivalente a leer el archivo csv


    @Bean
    public Step step1() {
        return null;
    }






    @Bean
    public AnimeItemProcessor processor(){
        return new AnimeItemProcessor();
    }

    private Resource outputResource = new FileSystemResource("output/outputData.csv");



}
