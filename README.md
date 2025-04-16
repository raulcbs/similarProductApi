# Similar Product API

## Descripción del proyecto
Similar Product API es un servicio web reactivo 
que proporciona recomendaciones de productos similares 
basado en identificadores de producto. 
Esta API actúa como una capa intermedia que consume servicios 
externos para obtener información detallada de productos 
y ofrecer recomendaciones relevantes. 

## Características principales
- **Reactividad**: Utiliza Spring WebFlux para manejar solicitudes de manera asíncrona.
- **Consumo de servicios externos**: Se integra con servicios externos para obtener información de productos.
- **Manejo de errores**: Implementa un manejo de errores robusto para garantizar la estabilidad del servicio.
- **Documentacion API**: Genera documentación de la API utilizando Swagger.


## Estructura del proyecto
- **Controller**: Maneja las solicitudes HTTP entrantes.
- **Service**: Contiene la lógica de negocio y las llamadas a servicios externos.
- **Facade**: Orquesta múltiples operaciones de servicio
- **Config**: Configuración de componentes como WebClient
- **DTO**: Objetos de transferencia de datos
- **Exception**: Manejo personalizado de excepciones

## Endpoints
- `GET /product/{productId}/similar`: Obtiene productos similares al indicado por ID.

## Configuracion
La aplicación se configura a través del archivo `application.yml` donde se pueden ajustar:
```yaml
api:
  base-url: http://localhost:3001  # URL del servicio externo
```

## Documentación API
La documentación Swagger está disponible en:
- http://localhost:5000/swagger-ui.html
- http://localhost:5000/api-docs