package com.depromeet.threedays.data.entity.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "client")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(name = "fcm_token", length = 1000, nullable = false)
	private String fcmToken;

	@Column(name = "identification_key", length = 1000, nullable = false)
	private String identificationKey;

	@Column(nullable = false, updatable = false)
	@Builder.Default
	private LocalDateTime createAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updateAt;


	public void updateFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
