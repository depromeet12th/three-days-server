package com.depromeet.threedays.front

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification


@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith([RestDocumentationExtension.class, SpringExtension.class])
abstract class RestDocsSpecification extends Specification {

}