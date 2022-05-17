package info.nemoworks.bid;

import info.nemoworks.bid.model.Bid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BidApplication {
    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }



    @Bean
    Bid bid(){
        return new Bid();
    }


}
