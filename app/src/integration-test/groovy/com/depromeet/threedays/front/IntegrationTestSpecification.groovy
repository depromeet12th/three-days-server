package com.depromeet.threedays.front

import com.depromeet.threedays.front.config.ApplicationConfig
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [ApplicationConfig, ApplicationTestConfig])
abstract class IntegrationTestSpecification extends Specification {

}

@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [ApplicationConfig, ApplicationTestConfig])
abstract class AsyncIntegrationTestSpecification extends Specification {

}

@Configuration
@ComponentScan
class ApplicationTestConfig {

}
