package com.depromeet.threedays.front.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class SlackApiConfig {

	@Value("${slack.api.token}")
	private String slackApiToken;

	private final Environment environment;

	public void sendMessage(String title, String message) {
		StringBuilder messageWithProfile = new StringBuilder();
		ArrayList<String> activeProfiles =
				new ArrayList<>(Arrays.asList(environment.getActiveProfiles()));

		if (activeProfiles.contains("data-local")) {
			messageWithProfile.append("local ");
		} else if (activeProfiles.contains("data-dev")) {
			messageWithProfile.append("dev ");
		} else {
			messageWithProfile.append("prod ");
		}
		messageWithProfile.append(title);

		SlackMessage slackMessage = initSlackMessage(messageWithProfile.toString(), message);

		SlackApi slackApi = new SlackApi("https://hooks.slack.com/services/" + slackApiToken);
		slackApi.call(slackMessage);
	}

	private SlackAttachment initSlackAttachment(String title) {
		SlackAttachment slackAttachment = new SlackAttachment("[ERROR]");
		slackAttachment.setFallback("Error");
		slackAttachment.setColor("danger");
		slackAttachment.setTitle(title);

		return slackAttachment;
	}

	private SlackMessage initSlackMessage(String title, String message) {
		SlackAttachment slackAttachment = initSlackAttachment(title);
		SlackMessage slackMessage = new SlackMessage("#exception-alert", "exception-bot", message);
		slackMessage.setIcon(":warning:");
		slackMessage.setAttachments(Collections.singletonList(slackAttachment));

		return slackMessage;
	}
}
