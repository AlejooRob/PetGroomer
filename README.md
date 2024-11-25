# PetGroomer - Registro de Citas para Mascotas

## Descripción
PetGroomer es una aplicación web desarrollada en Spring Boot que permite gestionar las citas para el cuidado de mascotas. Los usuarios pueden registrar clientes, empleados, mascotas y servicios ofrecidos. Cuando se registra una nueva cita, se envía un correo electrónico automático al cliente para confirmar los detalles de la cita.

## Características
- Registro de clientes, empleados, mascotas y servicios disponibles.
- Creación y gestión de citas para las mascotas.
- Envío automático de correos electrónicos para notificar a los clientes sobre las citas.
- Seguridad implementada con Spring Security.

## Tecnologías Utilizadas
- **Backend**: Spring Boot
- **Seguridad**: Spring Security
- **Correo Electrónico**: Spring Boot Starter Mail
- **Base de Datos**: PostgreSQL
- **Servidor Local**: La aplicación corre en `localhost:8080`

## Instalación y Ejecución
1. **Clonar el repositorio**:
   ```bash
   git clone <https://github.com/AlejooRob/PetGroomer>
   ```

2. **Configurar la Base de Datos**:
   - Asegúrate de tener PostgreSQL instalado y configurado.
   - Crea una base de datos llamada `PetGroomer`.
   - Actualiza las credenciales de la base de datos en `application.properties`:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/PetGroomer
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la Aplicación**:
   - Dirígete a [http://localhost:8080](http://localhost:8080) en tu navegador.

## Uso de la Aplicación
- **Registro de Clientes**: Registra nuevos clientes con sus datos de contacto.
- **Registro de Empleados**: Registra empleados encargados de realizar los servicios.
- **Registro de Mascotas**: Ingresa las mascotas asociadas a los clientes.
- **Servicios**: Define los diferentes servicios que se ofrecen (baño, corte de pelo, etc.).
- **Citas**: Registra nuevas citas para mascotas, y un correo de confirmación se enviará automáticamente al cliente.

## Seguridad
La aplicación utiliza Spring Security para garantizar que solo los usuarios autorizados puedan acceder a las funciones de gestión de citas y datos.

## Configuración del Correo Electrónico
Para enviar correos electrónicos de confirmación, debes configurar las propiedades del servidor SMTP en `mail.properties`:
```properties
email.username=tu correo
email.password=tu contraseña de aplicacion

```

## Requisitos
- **Java 21 o superior**
- **Maven**
- **PostgreSQL**

## Contribuciones
Las contribuciones son bienvenidas. Por favor, realiza un fork del repositorio y abre un pull request con tus mejoras.

## Contacto
Para cualquier duda o sugerencia, por favor contacta a [alejolink001@gmail.com](mailto:alejolink001@gmail.com).


