package com.depromeet.threedays.data.entity.client;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
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
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	public void updateFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
