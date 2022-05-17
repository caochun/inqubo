package info.nemoworks.bid;

import info.nemoworks.bid.model.Bid;
import info.nemoworks.bid.process.BidProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BidApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }


    @Bean
    public Bid getBid(){
        return new Bid();
    }
}
