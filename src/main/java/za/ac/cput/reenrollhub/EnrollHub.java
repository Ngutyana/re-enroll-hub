package za.ac.cput.reenrollhub;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import za.ac.cput.reenrollhub.domain.entity.University;
import za.ac.cput.reenrollhub.domain.entity.database.CPUTDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.RhodesDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.UJDatabase;
import za.ac.cput.reenrollhub.domain.entity.database.UNISADatabase;
import za.ac.cput.reenrollhub.repositoty.database.CPUTDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.RhodesDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.UJDatabaseRepository;
import za.ac.cput.reenrollhub.repositoty.database.UNISADatabaseRepository;
import za.ac.cput.reenrollhub.service.UniversityService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class EnrollHub {

	public static void main(String[] args) {
		SpringApplication.run(EnrollHub.class, args);
	}
	@Bean
	public CorsFilter corsFilter(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin", "X-Requested-With",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Access-Control-Allow-Origin"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	private final UniversityService universityService;
	private final CPUTDatabaseRepository cputDatabaseRepository;
	private final RhodesDatabaseRepository rhodesDatabaseRepository;
	private final UJDatabaseRepository ujDatabaseRepository;
	private final UNISADatabaseRepository unisaDatabaseRepository;

	@PostConstruct
	public void initData() {
		//populateDatabases();
	}

	private void populateDatabases() {
		universities().forEach(universityName -> {
			University university = University.builder().name(universityName).build();
			universityService.save(university);
		});

		cputDatabaseRepository.saveAll(cputDatabaseList());
		rhodesDatabaseRepository.saveAll(rhodesDatabaseList());
		ujDatabaseRepository.saveAll(ujDatabaseList());
		unisaDatabaseRepository.saveAll(unisaDatabaseList());
	}

	private List<String> universities() {
		List<String> universities = new ArrayList<>();
		universities.add("Cape Peninsula University of Technology");
		universities.add("Rhodes University");
		universities.add("Stellenbosch University");
		universities.add("University of Johannesburg");
		universities.add("University of South Africa");
		universities.add("Vaal University of Technology");

		return universities;
	}

	private List<CPUTDatabase> cputDatabaseList() {
		List<CPUTDatabase> cputDatabaseList = new ArrayList<>();

		cputDatabaseList.add(new CPUTDatabase(0, "Likho", "Zulu", "20054855", "0800055555", "20054855@cput.ac.za", "likho@gmail.com", "Long Street, 20", "FUNDED", "NSFAS", new Date(), "APPLICATIONS DEVELOPMENT", "3rd Year", "INFORMATICS AND DESIGN"));
		cputDatabaseList.add(new CPUTDatabase(0, "Mike", "Tyson", "2005412", "0218455", "2005412@cput.ac.za", "mike@gmail.com", "Down Street, 4210", "FUNDED", "NSFAS", new Date(), "NETWORKING", "2rd Year", "ENGINEERING"));
		cputDatabaseList.add(new CPUTDatabase(0, "Unathi", "What-What", "22458155", "0605615455", "22458155@cput.ac.za", "unathi@gmail.com", "South Street, 740", "NOT FUNDED", "", new Date(), "APPLICATIONS DEVELOPMENT", "1rd Year", "INFORMATICS AND DESIGN"));

		return cputDatabaseList;
	}

	private List<RhodesDatabase> rhodesDatabaseList() {
		List<RhodesDatabase> rhodesDatabaseList = new ArrayList<>();

		rhodesDatabaseList.add(new RhodesDatabase(0, "Sihle", "France", "414141", "0800055555", "414141@rhodes.ac.za", "sihle@gmail.com", "Dom Street, 740", "FUNDED", "NSFAS", new Date(), "APPLICATIONS DEVELOPMENT", "3rd Year", "DRAWING AND DESIGN"));
		rhodesDatabaseList.add(new RhodesDatabase(0, "Xola", "KuuK", "535253", "055584", "535253@rhodes.ac.za", "xola@gmail.com", "King Street, 10", "FUNDED", "NSFAS", new Date(), "PHYSICAL SCIENCE", "2rd Year", "EDUCATION"));
		rhodesDatabaseList.add(new RhodesDatabase(0, "Lee", "Joice", "565652", "032555", "565652@rhodes.ac.za", "lee@gmail.com", "Ion Street, 0", "NOT FUNDED", "", new Date(), "APPLICATIONS DEVELOPMENT", "1rd Year", "COMPUTER JUNKIES"));

		return rhodesDatabaseList;
	}

	private List<UJDatabase> ujDatabaseList() {
		List<UJDatabase> ujDatabaseList = new ArrayList<>();

		ujDatabaseList.add(new UJDatabase(0, "SMA", "Jinks", "230002", "0365555", "230002@uj.ac.za", "sma@gmail.com", "Loop Street, 4777", "NOT FUNDED", "", new Date(), "ADULTERY", "3rd Year", "HOMO DEVELOPMENT"));
		ujDatabaseList.add(new UJDatabase(0, "Cruger", "Zwe", "919191", "02275165", "919191@uj.ac.za", "cool@gmail.com", "Sweet Street, 6", "FUNDED", "SHOPRIGHT", new Date(), "FOOD BIOTECH", "2rd Year", "FOOD AND TECHNOLOGY"));
		ujDatabaseList.add(new UJDatabase(0, "Dolph", "Mce", "505850", "01565665", "505850@uj.ac.za", "dic@gmail.com", "Moola Street, 50", "FUNDED", "FUNDZA LUSAKA", new Date(), "APPLICATIONS DEVELOPMENT", "1rd Year", "ZOO KEEPER"));

		return ujDatabaseList;
	}

	private List<UNISADatabase> unisaDatabaseList() {
		List<UNISADatabase> unisaDatabaseList = new ArrayList<>();

		unisaDatabaseList.add(new UNISADatabase(0, "NJONGO", "Pink", "414142", "025555", "414142@unisa.ac.za", "nj@gmail.com", "OOP Street, 475", "NOT FUNDED", "", new Date(), "DEEP LEARNING", "1rd Year", "JENKINS DRIVER DEVELOPER"));
		unisaDatabaseList.add(new UNISADatabase(0, "Wendy", "Blue", "858585", "015558", "858585@unisa.ac.za", "we@gmail.com", "Green Street, 85", "FUNDED", "GURUs", new Date(), "AI DEVELOPER", "2rd Year", "AI MODEL"));
		unisaDatabaseList.add(new UNISADatabase(0, "Amanda", "Red", "96589658", "0236555", "96589658@unisa.ac.za", "am@gmail.com", "Sexy Street, 140", "FUNDED", "ATHI", new Date(), "APPLICATIONS DEVELOPMENT", "1rd Year", "CODING"));

		return unisaDatabaseList;
	}



}
