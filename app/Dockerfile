FROM eclipse-temurin:17-jre-alpine

ARG JAR_FILE="build/libs/app-*.jar"

COPY ${JAR_FILE} app.jar

ENV	PROFILE develop
ENV TZ=Asia/Seoul

ARG RELEASE_VERSION
ENV DD_VERSION=${RELEASE_VERSION}

EXPOSE 8080

ENTRYPOINT ["java", \
			"-Djava.security.egd=file:/dev/./urandom", \
			"-Dspring-boot.run.arguments=--threedays.datasource.write.jdbc-url=${DB_HOSTNAME}, --threedays.datasource.read.jdbc-url=${DB_HOSTNAME}, --threedays.datasource.write.username=${DB_USERNAME}, --threedays.datasource.read.username=${DB_USERNAME}, --threedays.datasource.read.password=${DB_PASSWORD}, --threedays.datasource.write.password=${DB_PASSWORD}", \
			"-XX:MaxGCPauseMillis=100", \
			"-XX:InitialRAMPercentage=50.0", \
			"-XX:MaxRAMPercentage=80.0", \
			"-jar", "app.jar"]
