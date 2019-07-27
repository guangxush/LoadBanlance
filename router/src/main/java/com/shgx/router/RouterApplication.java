package com.shgx.router;

import com.shgx.router.listener.RequestListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RouterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouterApplication.class, args);
        SpringApplication springApplication = new SpringApplication(RouterApplication.class);
        springApplication.addListeners(new RequestListener(), new RequestListener());
    }

}
