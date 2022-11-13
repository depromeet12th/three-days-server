package com.depromeet.threedays.front.controller.objective;

import com.depromeet.threedays.front.controller.request.objective.SaveObjectiveRequest;
import com.depromeet.threedays.front.domain.model.Objective;
import com.depromeet.threedays.front.domain.usecase.objective.SaveObjectiveUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/objectives")
public class ObjectiveController {

	private final SaveObjectiveUseCase saveUseCase;

	@PostMapping
	public ApiResponse<Objective> add(@RequestBody @Valid final SaveObjectiveRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(request));
	}
}
