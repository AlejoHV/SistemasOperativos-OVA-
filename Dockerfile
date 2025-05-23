FROM openjdk:17

# Crear un directorio de trabajo
WORKDIR /app

# Argumento para el JAR
ARG JAR_FILE=target/OVA-Sistemas-O-0.0.1-SNAPSHOT.jar

# Copiar el jar y la librería nativa
COPY ${JAR_FILE} app.jar
COPY libalgoritmo.so /app/native/

# Configurar la ruta de la librería nativa
ENV LD_LIBRARY_PATH=/app/native:$LD_LIBRARY_PATH

# Exponer el puerto de Spring Boot
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]

