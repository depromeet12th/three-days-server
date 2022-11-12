package com.depromeet.threedays.data.partner;

import java.time.LocalDateTime;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "partner")
public class PartnerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long objectiveId;

	@Column(nullable = false)
	private Integer level;

	@Column(nullable = false)
	private String characterType;

	@Column(nullable = false, updatable = false)
	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;
}
