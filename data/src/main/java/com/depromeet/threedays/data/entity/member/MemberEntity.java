package com.depromeet.threedays.data.entity.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "member")
public class MemberEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column private String certificationId;

	@Column private String name;

	@Column
	@Enumerated(EnumType.STRING)
	private CertificationSubject certificationSubject;

	@Column(name = "resource", columnDefinition = "json")
	private String resource;

	@Column private Boolean notificationConsent;

	@Column(nullable = false, updatable = false)
	@Builder.Default
	private LocalDateTime createAt = LocalDateTime.now();

	@Column(nullable = false)
	private LocalDateTime updateAt;


	public void updateName(String name) {
		this.name = name;
		this.updateAt = LocalDateTime.now();
	}

	public void updateNotificationConsent(Boolean consent) {
		this.notificationConsent = consent;
		this.updateAt = LocalDateTime.now();
	}

	public void updateResource(String resource) {
		this.resource = resource;
		this.updateAt = LocalDateTime.now();
	}
}
