package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDatabase(
			OperatoreRepository operatoreRepository,
			JdbcTemplate jdbcTemplate) {
		return args -> {
			if (operatoreRepository.findByUsername("admin").isEmpty()) {
				Operatore admin = new Operatore();
				admin.setNome("Alessandro");
				admin.setCognome("Verdi");
				admin.setUsername("admin");
				admin.setPassword("admin123");
				admin.setEmail("admin@concessionaria.com");
				admin.setRuolo("admin");
				operatoreRepository.save(admin);
				System.out.println(">>> Operatore admin di default inserito nel database!");
			}

			System.out.println(">>> Avvio popolamento database con JdbcTemplate...");

			// Seed MARCA with exact IDs
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (1, 'Alfa Romeo') ON DUPLICATE KEY UPDATE NomeMarca = 'Alfa Romeo'");
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (2, 'Audi') ON DUPLICATE KEY UPDATE NomeMarca = 'Audi'");
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (3, 'Porsche') ON DUPLICATE KEY UPDATE NomeMarca = 'Porsche'");
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (4, 'BMW') ON DUPLICATE KEY UPDATE NomeMarca = 'BMW'");
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (5, 'Mercedes') ON DUPLICATE KEY UPDATE NomeMarca = 'Mercedes'");
			jdbcTemplate.execute("INSERT INTO MARCA (IDMarca, NomeMarca) VALUES (6, 'Volkswagen') ON DUPLICATE KEY UPDATE NomeMarca = 'Volkswagen'");

			// Seed MODELLO with exact IDs
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (1, 1, 'Alfa Romeo Giulia', 'Berlina Premium', 45000.00) ON DUPLICATE KEY UPDATE IDMarca = 1, NomeModello = 'Alfa Romeo Giulia', Descrizione = 'Berlina Premium', PrezzoBase = 45000.00");
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (2, 2, 'Audi A3 Sportback', 'Compact Sport', 36000.00) ON DUPLICATE KEY UPDATE IDMarca = 2, NomeModello = 'Audi A3 Sportback', Descrizione = 'Compact Sport', PrezzoBase = 36000.00");
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (3, 3, 'Porsche 911 Carrera', 'Icona Sportiva', 125000.00) ON DUPLICATE KEY UPDATE IDMarca = 3, NomeModello = 'Porsche 911 Carrera', Descrizione = 'Icona Sportiva', PrezzoBase = 125000.00");
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (4, 4, 'BMW M4 Coupe', 'Coupé Sportiva', 98000.00) ON DUPLICATE KEY UPDATE IDMarca = 4, NomeModello = 'BMW M4 Coupe', Descrizione = 'Coupé Sportiva', PrezzoBase = 98000.00");
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (5, 5, 'Mercedes-AMG GT', 'Supercar di Lusso', 140000.00) ON DUPLICATE KEY UPDATE IDMarca = 5, NomeModello = 'Mercedes-AMG GT', Descrizione = 'Supercar di Lusso', PrezzoBase = 140000.00");
			jdbcTemplate.execute("INSERT INTO MODELLO (IDModello, IDMarca, NomeModello, Descrizione, PrezzoBase) VALUES (6, 6, 'Volkswagen Golf', 'Compatta Iconica', 29000.00) ON DUPLICATE KEY UPDATE IDMarca = 6, NomeModello = 'Volkswagen Golf', Descrizione = 'Compatta Iconica', PrezzoBase = 29000.00");

			// Seed COLORE with exact IDs
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (1, 'Bianco Alfa', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Bianco Alfa', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (2, 'Rosso Competizione', 1200.00) ON DUPLICATE KEY UPDATE NomeColore = 'Rosso Competizione', Sovrapprezzo = 1200.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (3, 'Grigio Daytona', 950.00) ON DUPLICATE KEY UPDATE NomeColore = 'Grigio Daytona', Sovrapprezzo = 950.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (4, 'Nero Mythos', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Nero Mythos', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (5, 'Bianco Ghiaccio', 900.00) ON DUPLICATE KEY UPDATE NomeColore = 'Bianco Ghiaccio', Sovrapprezzo = 900.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (6, 'Giallo Racing', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Giallo Racing', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (7, 'Nero Soft', 1500.00) ON DUPLICATE KEY UPDATE NomeColore = 'Nero Soft', Sovrapprezzo = 1500.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (8, 'Rosso Crayon', 2800.00) ON DUPLICATE KEY UPDATE NomeColore = 'Rosso Crayon', Sovrapprezzo = 2800.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (9, 'Verde Isle of Man', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Verde Isle of Man', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (10, 'Giallo Sao Paulo', 1400.00) ON DUPLICATE KEY UPDATE NomeColore = 'Giallo Sao Paulo', Sovrapprezzo = 1400.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (11, 'Nero Carbonio', 1100.00) ON DUPLICATE KEY UPDATE NomeColore = 'Nero Carbonio', Sovrapprezzo = 1100.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (12, 'Bianco Polare', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Bianco Polare', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (13, 'Grigio Selenite Magno', 2200.00) ON DUPLICATE KEY UPDATE NomeColore = 'Grigio Selenite Magno', Sovrapprezzo = 2200.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (14, 'Giallo Sole', 1800.00) ON DUPLICATE KEY UPDATE NomeColore = 'Giallo Sole', Sovrapprezzo = 1800.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (15, 'Grigio Urano', 0.00) ON DUPLICATE KEY UPDATE NomeColore = 'Grigio Urano', Sovrapprezzo = 0.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (16, 'Rosso Re', 750.00) ON DUPLICATE KEY UPDATE NomeColore = 'Rosso Re', Sovrapprezzo = 750.00");
			jdbcTemplate.execute("INSERT INTO COLORE (IDColore, NomeColore, Sovrapprezzo) VALUES (17, 'Nero Perla', 850.00) ON DUPLICATE KEY UPDATE NomeColore = 'Nero Perla', Sovrapprezzo = 850.00");

			// Seed OPTIONAL with exact IDs
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (1, 'Cerchi da 19\" Sport', 'Cerchi in lega sportivi', 1300.00) ON DUPLICATE KEY UPDATE Nome = 'Cerchi da 19\" Sport', Descrizione = 'Cerchi in lega sportivi', Prezzo = 1300.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (2, 'Tetto Panoramico', 'Tetto in vetro apribile', 1600.00) ON DUPLICATE KEY UPDATE Nome = 'Tetto Panoramico', Descrizione = 'Tetto in vetro apribile', Prezzo = 1600.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (3, 'Pacchetto ADAS Livello 2', 'Assistenza alla guida avanzata', 1800.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto ADAS Livello 2', Descrizione = 'Assistenza alla guida avanzata', Prezzo = 1800.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (4, 'Fari LED Matrix', 'Proiettori anteriori intelligenti', 1100.00) ON DUPLICATE KEY UPDATE Nome = 'Fari LED Matrix', Descrizione = 'Proiettori anteriori intelligenti', Prezzo = 1100.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (5, 'Pacchetto S-Line interior', 'Finiture sportive interne S-Line', 1500.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto S-Line interior', Descrizione = 'Finiture sportive interne S-Line', Prezzo = 1500.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (6, 'Audio Bang & Olufsen', 'Impianto audio premium 3D', 950.00) ON DUPLICATE KEY UPDATE Nome = 'Audio Bang & Olufsen', Descrizione = 'Impianto audio premium 3D', Prezzo = 950.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (7, 'Freni Carbo-Ceramica PCCB', 'Freni ad altissime prestazioni', 9200.00) ON DUPLICATE KEY UPDATE Nome = 'Freni Carbo-Ceramica PCCB', Descrizione = 'Freni ad altissime prestazioni', Prezzo = 9200.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (8, 'Asse Posteriore Sterzante', 'Migliora agilità e stabilità', 2300.00) ON DUPLICATE KEY UPDATE Nome = 'Asse Posteriore Sterzante', Descrizione = 'Migliora agilità e stabilità', Prezzo = 2300.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (9, 'Pacchetto Sport Chrono', 'Cronometro analogico e modalità Sport Plus', 2400.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto Sport Chrono', Descrizione = 'Cronometro analogico e modalità Sport Plus', Prezzo = 2400.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (10, 'Pacchetto Carbonio M', 'Finiture esterne in carbonio', 4500.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto Carbonio M', Descrizione = 'Finiture esterne in carbonio', Prezzo = 4500.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (11, 'Scarico Sportivo M', 'Suono sportivo ottimizzato', 2100.00) ON DUPLICATE KEY UPDATE Nome = 'Scarico Sportivo M', Descrizione = 'Suono sportivo ottimizzato', Prezzo = 2100.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (12, 'Cerchi Forgiati M da 20\"', 'Cerchi ultraleggeri neri', 2800.00) ON DUPLICATE KEY UPDATE Nome = 'Cerchi Forgiati M da 20\"', Descrizione = 'Cerchi ultraleggeri neri', Prezzo = 2800.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (13, 'Pacchetto Aerodinamico AMG', 'Alettone posteriore fisso e deflettori', 3800.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto Aerodinamico AMG', Descrizione = 'Alettone posteriore fisso e deflettori', Prezzo = 3800.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (14, 'Sedili Performance AMG', 'Sedili sportivi a guscio', 2500.00) ON DUPLICATE KEY UPDATE Nome = 'Sedili Performance AMG', Descrizione = 'Sedili sportivi a guscio', Prezzo = 2500.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (15, 'Scarico Performance AMG', 'Valvole di scarico attive per sound estremo', 1800.00) ON DUPLICATE KEY UPDATE Nome = 'Scarico Performance AMG', Descrizione = 'Valvole di scarico attive per sound estremo', Prezzo = 1800.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (16, 'Pacchetto R-Line exterior', 'Cerchi sportivi e paraurti R-Line', 1200.00) ON DUPLICATE KEY UPDATE Nome = 'Pacchetto R-Line exterior', Descrizione = 'Cerchi sportivi e paraurti R-Line', Prezzo = 1200.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (17, 'Proiettori LED IQ.Light', 'Fari Matrix adattivi', 950.00) ON DUPLICATE KEY UPDATE Nome = 'Proiettori LED IQ.Light', Descrizione = 'Fari Matrix adattivi', Prezzo = 950.00");
			jdbcTemplate.execute("INSERT INTO OPTIONAL (IDOptional, Nome, Descrizione, Prezzo) VALUES (18, 'Navigatore Discover Pro', 'Schermo infotainment 10\" con navigatore', 800.00) ON DUPLICATE KEY UPDATE Nome = 'Navigatore Discover Pro', Descrizione = 'Schermo infotainment 10\" con navigatore', Prezzo = 800.00");

			System.out.println(">>> Database allineato e riempito con le nuove auto, colori e optional!");
		};
	}
}