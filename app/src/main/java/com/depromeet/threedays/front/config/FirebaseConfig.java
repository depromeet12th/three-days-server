package com.depromeet.threedays.front.config;

import com.depromeet.threedays.front.client.property.FirebaseProperty;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

	@Bean
	public FirebaseProperty fireBaseProperty() {
		return new FirebaseProperty();
	}

	@Bean
	public FirebaseApp fireBaseApp(FirebaseProperty firebaseProperty) throws IOException {
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
						.setDatabaseUrl(firebaseProperty.getDataBaseUri())
						.build();

		return FirebaseApp.initializeApp(options);
	}
}
