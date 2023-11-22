package com.br.clinca;

import com.br.clinca.dtos.MedicoDTO;
import com.br.clinca.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class ClinicaApplication {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ClinicaApplication.class, args);

		ClinicaApplication application = context.getBean(ClinicaApplication.class);
		application.initializeDatabase();
	}

	private void initializeDatabase() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			ClassPathResource resource = new ClassPathResource("queries.sql");
			byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
			String content = new String(bytes, StandardCharsets.UTF_8);

			String[] queries = content.split(";");

			for (String query : queries) {
				String trimmedQuery = query.trim();
				if (!trimmedQuery.isEmpty()) {
					jdbcTemplate.execute(trimmedQuery);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}