package com.depromeet.threedays.data.entity.member;

import com.depromeet.threedays.data.enums.CertificationSubject;
import java.io.Serializable;
import javax.persistence.*;
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

	@Column private String name;

	@Column
	@Enumerated(EnumType.STRING)
	private CertificationSubject certificationSubject;

	@Column private String profile;

	@Column private Boolean notificationConsent;
}
