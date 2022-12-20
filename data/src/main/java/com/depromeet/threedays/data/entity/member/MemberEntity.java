package com.depromeet.threedays.data.entity.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import java.io.Serializable;
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
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
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
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	public void updateName(String name) {
		this.name = name;
	}

	public void updateNotificationConsent(Boolean consent) {
		this.notificationConsent = consent;
	}

	public void updateResource(String resource) {
		this.resource = resource;
	}
}
