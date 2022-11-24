FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE="build/libs/app-*.jar"

COPY ${JAR_FILE} app.jar

ENV	PROFILE default
ENV TZ=Asia/Seoul
ENV DB_HOSTNAME=${DB_HOSTNAME}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

EXPOSE 8080

ENTRYPOINT ["java", \
			"-XX:MaxGCPauseMillis=100", \
			"-XX:InitialRAMPercentage=50.0", \
			"-XX:MaxRAMPercentage=80.0", \
			"-jar", "app.jar"]
