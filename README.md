# [OpenAiAssistantApi](https://github.com/Marc0Franc0/OpenAiAssistantApi#openaiassistantapi)

API REST diseñada para implementar y facilitar el uso de la API de OpenAI en un sistema de chat basado en texto.
Permite interactuar con el sistema de chat mediante dos endpoints principales.

## Características
- Consumo de API OpenAI: Integración con la API de OpenAI para procesar y 
generar respuestas coherentes y contextualizadas utilizando tecnologías avanzadas 
de NLP (Procesamiento del Lenguaje Natural).

## Tecnologías
- Spring Boot 3.2.4
- Spring AI 0.8.1
- Maven

## Endpoints

### GET /generateText

Este endpoint permite enviar mensajes de texto al sistema de chat para su procesamiento utilizando la API de OpenAI y obtener una respuesta coherente en formato de cadena de texto.
```shell
curl http://localhost:8080/api/v1/ai/generateText?message=Hola
```
- Response
```
{"generation":"Texto generado por api"}
```

### GET /generateTextStream

Este endpoint permite enviar mensajes de texto al sistema de chat para su procesamiento utilizando la API de OpenAI y obtener una respuesta coherente en formato de flujo de datos.
```shell
curl http://localhost:8080/api/v1/ai/generateTextStream?message=Hola
```
- Response
```
"Texto generado por api"
```

### GET /generateImage

Este endpoint permite solcitar un tipo de imágen para que la API de OpenAi devuelva cinco imágenes de acuerdo a lo solicitado.
```shell
curl http://localhost:8080/api/v1/ai/generateImage?message=ejemplo
```
- Response
```
{
  "request": "Ejemplo",
  "imagesUrl": [
    "url1",
    "url2",
    "url3",
    "url4",
    "url5"
    ]
}
```
## Instalación y configuración
Sigue estos pasos para instalar y configurar la API:
1. Clonar repositorio
```shell
git clone https://github.com/Marc0Franc0/OpenAiAssistantApi
```
2. Configura la API de OpenAI:
- Regístrate en OpenAI para obtener una clave API.
- Dirigite a: OpenAIAssistantAPI/App/src/main/resources/application.yml
- Configura la clave API en el archivo de configuración:
Modifica el valor de ${SPRING_AI_OPENAI_APIKEY} con tu clave API
3. Seguir pasos para ejecución con Maven

## Ejecución con Maven

Para construir y ejecutar la aplicación necesita:

- [JDK 21+](https://www.oracle.com/java/technologies/downloads/#java21)
- [Maven 3+](https://maven.apache.org)

Ejecutar localmente
1. Instala las dependencias
```shell
cd OpenAiAssistantApi
mvn clean install
```
2. Inicia la app
```shell
cd App
mvn spring-boot:run
```

Dirigirse a:
- [Documentación en formato JSON](http://localhost:8080/api/v3/api-docs)
- [Documentación Swagger con interfaz gráfica](http://localhost:8080/doc/swagger-ui/index.html)