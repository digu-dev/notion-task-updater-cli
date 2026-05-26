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

## Arquitetura e organização de pacotes

Base package: `com.digu.notiontaskupdatercli`

- `domain`: núcleo de negócio (entidades, value objects, regras puras). Não depende de Spring nem de detalhes de infraestrutura.
- `application`: orquestra casos de uso e define portas (entrada/saída) para comunicação com o domínio e com recursos externos.
- `adapter.in`: adapters de entrada (driving), como CLI e possíveis interfaces externas que disparam casos de uso.
- `adapter.out`: adapters de saída (driven), como integrações com Notion, persistência e mensageria.
- `config`: configuração de framework, bootstrap e composição de dependências.

### Regra de dependência

- Dependências devem apontar para dentro:
  - `adapter.in` -> `application` -> `domain`
  - `adapter.out` -> `application`/`domain` (implementando portas de saída)
- `domain` não depende de framework (Spring, banco, HTTP, mensageria etc.).

### Convenções de nomenclatura

- Casos de uso: sufixo `UseCase` (ex.: `CreateTaskUseCase`)
- Portas de entrada/saída: sufixo `Port` (ex.: `CreateTaskPort`, `TaskRepositoryPort`)
- Adapters: sufixo `Adapter` (ex.: `CliTaskAdapter`, `NotionTaskAdapter`)
- Configurações: sufixo `Config` (ex.: `CliConfig`, `NotionClientConfig`)

Essa estrutura permite evolução incremental para CLI, persistência, integração com Notion e mensageria sem acoplar o domínio a detalhes técnicos.
