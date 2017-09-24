package hu.tilos.radio.backend;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

@EnableAutoConfiguration
@ComponentScan
@Configuration
@SpringBootApplication
@EnableCaching
public class Starter {

    @Value("${mongo.host}")
    private String mongoHost;

    @Value("${mongo.db}")
    private String mongoDb;

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

    @Bean
    public DB createMongoDB() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(mongoHost, 27017);
        mongoClient.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return mongoClient.getDB(mongoDb);
    }

}
