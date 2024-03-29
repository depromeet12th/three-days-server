package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FirebaseConfig {

	@Bean
	public FirebaseProperty fireBaseProperty() {
		return new FirebaseProperty();
	}

	@Profile("integration-test")
	@Bean
	public FirebaseApp localFireBaseApp() {
		return null;
	}

	@Profile("!integration-test")
	@Bean
	public FirebaseApp firebaseApp(FirebaseProperty firebaseProperty) throws IOException {
		FirebaseOptions options =
				FirebaseOptions.builder()
						.setCredentials(
								ServiceAccountCredentials.newBuilder()
										.setProjectId(firebaseProperty.getProjectId())
										.setPrivateKeyId(firebaseProperty.getPrivateKeyId())
										.setPrivateKeyString(firebaseProperty.getPrivateKey())
										.setClientEmail(firebaseProperty.getClientEmail())
										.setClientId(firebaseProperty.getClientId())
										.setTokenServerUri(URI.create(firebaseProperty.getTokenUri()))
										.build())
						.build();

		return FirebaseApp.initializeApp(options);
	}
}
