package com.depromeet.threedays.data.entity.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import com.depromeet.threedays.data.enums.MemberStatus;
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

	@SuppressWarnings("FieldMayBeFinal")
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private MemberStatus status = MemberStatus.REGULAR;

	@Column(name = "resource", columnDefinition = "json")
	private String resource;

	@Column private Boolean notificationConsent;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	public MemberEntity updateName(String name) {
		this.name = name;
		return this;
	}

	public MemberEntity updateNotificationConsent(Boolean consent) {
		this.notificationConsent = consent;
		return this;
	}

	public MemberEntity updateResource(String resource) {
		this.resource = resource;
		return this;
	}

	public MemberEntity withdraw() {
		status = MemberStatus.WITHDRAWN;
		return this;
	}
}
