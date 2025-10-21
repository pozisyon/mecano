package com.annuaire.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.annuaire.api",    // ton contrôleur REST
        "com.annuaire.core"    // ton module core (service, repository, model)
})
@EnableJpaRepositories(basePackages = "com.annuaire.core.repository")
@EntityScan(basePackages = "com.annuaire.core.model") // ⚠️ c’est ici la clé !
public class AnnuaireApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnnuaireApplication.class, args);
    }
}
