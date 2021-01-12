package vn.edu.hcmus.crypto.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SuppressWarnings("HideUtilityClassConstructor")
public class CryptoServerApp
{
    public static void main(final String[] args)
    {
        SpringApplication.run(CryptoServerApp.class, args);
    }
}
