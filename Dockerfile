FROM openjdk:17-alpine3.14
ARG SPRING_GROUP=spring
ARG SPRING_USERNAME=spring
RUN addgroup -S ${SPRING_GROUP} && adduser -S ${SPRING_USERNAME} -G ${SPRING_GROUP}
USER ${SPRING_USERNAME}
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "ru.red.otussimplerestfulcrud.OtusSimpleRESTfulCRUD", "--spring.profiles.active=cluster"]