package one.dio.labpadroesprojetospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot gerado pelo Spring Inicializr
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring WEB
 * - H2 Database
 * - Open Feign
 * 
 * @author Luciano
 * 
 */
@EnableFeignClients // Habilita o Feign Client em nosso projeto
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
