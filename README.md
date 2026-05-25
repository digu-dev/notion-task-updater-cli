# notion-task-updater-cli

Bootstrap inicial da aplicação CLI com Spring Boot 3.x para evolução futura (arquitetura hexagonal, persistência, integrações e segurança).

## Requisitos

- Java 21
- Maven 3.9+

## Executar localmente

```bash
mvn clean test
mvn spring-boot:run
```

A configuração inicial não exige banco ativo para startup local (PostgreSQL/Flyway permanecem no classpath para próximas stories).
