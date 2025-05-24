FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/OVA-Sistemas-O-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
COPY /lib/libsjf.so /usr/lib
ENV LD_LIBRARY_PATH=/usr/lib
ENTRYPOINT ["java","-jar","app.jar"]
