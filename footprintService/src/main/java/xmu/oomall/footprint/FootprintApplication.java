package xmu.oomall.footprint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "xmu.oomall.footprint.mapper")
@SpringBootApplication
public class FootprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootprintApplication.class, args);
    }

}
