package com.Bubo.bubo;



import com.Bubo.bubo.app.message.Message;
import com.Bubo.bubo.app.message.MessageRepository;
import com.Bubo.bubo.app.messageParent.MessageParent;
import com.Bubo.bubo.app.messageParent.MessageParentRepository;
import com.Bubo.bubo.app.customer.Customer;
import com.Bubo.bubo.app.customer.CustomerRepository;
import com.Bubo.bubo.app.user.User;
import com.Bubo.bubo.app.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDB {
    @Bean
    CommandLineRunner initDatabase(MessageParentRepository repository , CustomerRepository cr, UserRepository ur, MessageRepository mr) {
        return args -> {
//            log.info("Preloading " + repository.save(new MessageParent()));
//            log.info("Preloading " + repository.save(new MessageParent()));
              log.info("Preloading " + ur.save(new User("8c9a9873-38b0-402e-8353-7d443bd2a121","farzan","09019945527")));
              log.info("Preloading " + ur.save(new User("019d0ee5-ed1d-4901-8612-f64418569ff4","amirhosein","09120558801")));
//            log.info("Preloading " + mr.save(new Message()));
//            log.info("Preloading " + mr.save(new Message()));
        };
    }
}
