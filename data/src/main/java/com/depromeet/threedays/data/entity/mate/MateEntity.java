package com.depromeet.threedays.data.entity.mate;

import com.depromeet.threedays.data.enums.MateStatus;
import com.depromeet.threedays.data.enums.MateType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Builder(toBuilder = true)
@Table(name = "mate")
@EntityListeners(AuditingEntityListener.class)
public class MateEntity {

	private static final int INITIAL_LEVEL = 0;
	private static final List<Integer> LEVEL_UP_SECTION = Arrays.asList(1, 4, 8, 14, 22);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mate_id")
	private Long id;

	@Column(nullable = false)
	private Long memberId;

	@Column(nullable = false)
	private Long habitId;

	@Column(nullable = false)
	private String title;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MateType characterType;

	@SuppressWarnings("FieldMayBeFinal")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@Builder.Default
	private MateStatus status = MateStatus.ACTIVE;

	@SuppressWarnings("FieldMayBeFinal")
	@Column(nullable = false)
	@Builder.Default
	private Integer level = INITIAL_LEVEL;

	@Column private LocalDateTime levelUpAt;

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updateAt;

	@SuppressWarnings("FieldMayBeFinal")
	@Column(nullable = false)
	@Builder.Default
	private Boolean deleted = false;

	@Transient
	public List<Integer> getLevelUpSection() {
		return Collections.unmodifiableList(LEVEL_UP_SECTION);
	}
}
