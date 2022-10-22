package com.depromeet.threedays.data.entity.member;

import com.depromeet.threedays.data.entity.member.certification.Certification;
import com.depromeet.threedays.data.entity.member.converter.ProfileAttributeConverter;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Table(name = "member")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	Long id;

	String name;

	@Embedded Certification certification = new Certification();

	@Column(columnDefinition = "varchar(255)")
	@Convert(converter = ProfileAttributeConverter.class)
	Profile profile;

	String fcmToken;
}
