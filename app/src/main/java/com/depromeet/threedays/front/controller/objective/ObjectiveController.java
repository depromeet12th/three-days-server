package com.depromeet.threedays.front.controller.objective;

import com.depromeet.threedays.front.controller.request.objective.SaveObjectiveRequest;
import com.depromeet.threedays.front.domain.model.objective.Objective;
import com.depromeet.threedays.front.domain.model.objective.ObjectiveOverview;
import com.depromeet.threedays.front.domain.usecase.objective.SaveObjectiveUseCase;
import com.depromeet.threedays.front.domain.usecase.objective.SearchObjectiveUseCase;
import com.depromeet.threedays.front.support.ApiResponse;
import com.depromeet.threedays.front.support.ApiResponseGenerator;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/objectives")
public class ObjectiveController {

	private final SaveObjectiveUseCase saveUseCase;

	private final SearchObjectiveUseCase searchUseCase;

	@PostMapping
	public ApiResponse<Objective> add(@RequestBody @Valid final SaveObjectiveRequest request) {
		return ApiResponseGenerator.success(saveUseCase.execute(request));
	}

	@GetMapping
	public ApiResponse<List<ObjectiveOverview>> browse() {
		return ApiResponseGenerator.success(searchUseCase.execuete());
	}
}
