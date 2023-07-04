package com.depromeet.threedays.front.client.property.auth;

import java.io.IOException;
import java.io.StringReader;
import lombok.Getter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppleAuthProperty extends AuthRequestProperty {

	@Value("${apple.key.uri}")
	private String keyURI;

	@Value("${apple.keyId}")
	private String keyId;

	@Value("${apple.serviceId}")
	private String serviceId;

	@Value("${apple.teamId}")
	private String teamId;

	@Value("${apple.p8}")
	private String pEight;

	public AppleAuthProperty(
			@Value("${apple.host}") String host,
			@Value("${apple.token.uri}") String uri,
			@Value("${apple.unlink.uri}") String unlink) {
		super(host, uri, unlink);
	}

	@Override
	public String getAdminKey() {
		return null;
	}

	public byte[] getPrivateKey() {
		byte[] content = null;

		try (PemReader pemReader = new PemReader(new StringReader(getPEight()))) {
			{
				PemObject pemObject = pemReader.readPemObject();
				content = pemObject.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
}
