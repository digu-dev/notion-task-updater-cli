# Plano do Projeto — notion-task-updater-cli

## Contexto

Repositório: `digu-dev/notion-task-updater-cli`  
Repo ID: `1249584676`  
URL: `https://github.com/digu-dev/notion-task-updater-cli`

Objetivo: criar uma aplicação CLI interativa em **Java + Spring**, com **arquitetura hexagonal**, persistência em banco de dados, integração com **Notion**, e evolução posterior com **mensageria**, **Spring Security**, testes e observabilidade.

---

## Visão Geral da Solução

A aplicação permitirá que o usuário preencha, via terminal, os campos de um objeto `Task`. Esses dados serão:

1. validados pela aplicação;
2. persistidos em banco de dados;
3. sincronizados com o Notion;
4. futuramente processados de forma assíncrona por mensageria.

### Stack recomendada

- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway**
- **Spring Shell** para CLI
- **Spring WebClient** para integração com Notion
- **RabbitMQ** para mensageria
- **Spring Security** para autenticação/autorização

---

## Arquitetura Proposta

### Camadas

- **domain**: regras de negócio puras
- **application**: casos de uso
- **adapter.in**: entrada via CLI e mensageria
- **adapter.out**: persistência, Notion e fila
- **config**: configurações do Spring

### Estrutura sugerida

```text
src/main/java/com/digu/notiontaskupdatercli
├── domain
├── application
├── adapter
│   ├── in
│   └── out
└── config
```

---

## Roadmap por Fases

### Fase 1 — MVP funcional

- setup do projeto Spring Boot
- modelagem de `Task`
- persistência com PostgreSQL
- CLI interativa
- integração síncrona com Notion
- listagem e consulta de tasks

### Fase 2 — Robustez

- RabbitMQ
- processamento assíncrono
- retry
- tratamento de falhas
- comandos de reprocessamento

### Fase 3 — Evolução

- Spring Security
- observabilidade
- testes ampliados
- documentação operacional

---

## Quadro Kanban

### Colunas

1. **Backlog**
2. **Ready**
3. **In Progress**
4. **Review/Test**
5. **Done**
6. **Blocked**

### Regras

- cada card deve caber em 1 a 2 dias
- não iniciar mais de 2 cards por vez
- mover para `Review/Test` antes de `Done`
- usar `Blocked` para dependências externas como token do Notion e infra local

---

## Épicos

1. Fundação do projeto
2. Domínio e arquitetura hexagonal
3. CLI interativa
4. Persistência
5. Integração com Notion
6. Mensageria e resiliência
7. Segurança
8. Qualidade, testes e documentação

---

## Lista de Stories

### Epic 1 — Fundação do projeto

#### Story 1.1
**Como desenvolvedor, quero inicializar o projeto Spring Boot para ter uma base pronta para evolução.**

Checklist:
- [ ] criar projeto com Java 21
- [ ] configurar Maven ou Gradle
- [ ] adicionar dependências base
- [ ] configurar classe principal
- [ ] validar startup local

Critério de aceite:
- [ ] aplicação sobe localmente sem erro

#### Story 1.2
**Como desenvolvedor, quero definir a estrutura inicial de pacotes para suportar arquitetura hexagonal.**

Checklist:
- [ ] criar pacote `domain`
- [ ] criar pacote `application`
- [ ] criar `adapter.in`
- [ ] criar `adapter.out`
- [ ] criar `config`
- [ ] documentar convenção

Critério de aceite:
- [ ] estrutura inicial criada e consistente

#### Story 1.3
**Como desenvolvedor, quero configurar o projeto para execução local padronizada.**

Checklist:
- [ ] criar `.gitignore`
- [ ] criar `application.yml`
- [ ] criar `application-local.yml`
- [ ] configurar variáveis de ambiente
- [ ] atualizar README inicial

Critério de aceite:
- [ ] projeto pode ser executado localmente com instruções mínimas

### Epic 2 — Domínio e arquitetura hexagonal

#### Story 2.1
**Como sistema, quero representar uma Task no domínio para encapsular as regras de negócio.**

Checklist:
- [ ] criar classe `Task`
- [ ] definir campos principais
- [ ] criar construtores/factory methods
- [ ] validar obrigatoriedade do título
- [ ] validar status e prioridade

Critério de aceite:
- [ ] entidade de domínio sem dependência de framework

#### Story 2.2
**Como desenvolvedor, quero criar enums de domínio para padronizar estados e prioridades.**

Checklist:
- [ ] criar `TaskStatus`
- [ ] criar `Priority`
- [ ] criar `SyncStatus`

Critério de aceite:
- [ ] estados da task estão centralizados no domínio

#### Story 2.3
**Como desenvolvedor, quero definir as portas da aplicação para desacoplar domínio e infraestrutura.**

Checklist:
- [ ] criar `CreateTaskUseCase`
- [ ] criar `ListTasksUseCase`
- [ ] criar `FindTaskByIdUseCase`
- [ ] criar `TaskRepositoryPort`
- [ ] criar `NotionPort`
- [ ] criar `TaskEventPublisherPort`

Critério de aceite:
- [ ] casos de uso e integrações dependem de interfaces

#### Story 2.4
**Como usuário da aplicação, quero criar task por meio de um caso de uso para garantir consistência do fluxo.**

Checklist:
- [ ] criar `CreateTaskCommand`
- [ ] implementar `CreateTaskService`
- [ ] salvar task via porta
- [ ] definir `PENDING_SYNC` ou status inicial
- [ ] retornar resultado da criação

Critério de aceite:
- [ ] caso de uso cria task corretamente

### Epic 3 — CLI interativa

#### Story 3.1
**Como usuário, quero iniciar a aplicação por terminal para interagir com o sistema.**

Checklist:
- [ ] adicionar Spring Shell ou estratégia escolhida
- [ ] criar comando base
- [ ] validar execução do shell

Critério de aceite:
- [ ] comandos podem ser executados no terminal

#### Story 3.2
**Como usuário, quero preencher os campos de uma task via terminal para cadastrar tarefas sem interface gráfica.**

Checklist:
- [ ] criar comando `task:create`
- [ ] pedir título
- [ ] pedir descrição
- [ ] pedir status
- [ ] pedir prioridade
- [ ] pedir data limite
- [ ] chamar caso de uso

Critério de aceite:
- [ ] usuário consegue cadastrar task pelo terminal

#### Story 3.3
**Como usuário, quero listar tasks no terminal para acompanhar o que foi cadastrado.**

Checklist:
- [ ] criar comando `task:list`
- [ ] formatar saída
- [ ] mostrar status de sync

Critério de aceite:
- [ ] tasks são exibidas de forma legível

#### Story 3.4
**Como usuário, quero consultar uma task específica para ver seu estado detalhado.**

Checklist:
- [ ] criar comando `task:show --id`
- [ ] buscar task por id
- [ ] exibir detalhes completos

Critério de aceite:
- [ ] comando retorna detalhes de uma task existente

### Epic 4 — Persistência

#### Story 4.1
**Como sistema, quero persistir tasks em banco para manter histórico e status.**

Checklist:
- [ ] configurar PostgreSQL
- [ ] configurar datasource
- [ ] testar conexão

Critério de aceite:
- [ ] aplicação conecta ao banco localmente

#### Story 4.2
**Como desenvolvedor, quero versionar o schema do banco para garantir consistência de ambientes.**

Checklist:
- [ ] adicionar Flyway
- [ ] criar migration inicial
- [ ] criar tabela `tasks`

Critério de aceite:
- [ ] banco sobe com migration executada

#### Story 4.3
**Como sistema, quero ter um adapter de persistência para salvar e consultar tasks sem acoplar o domínio ao JPA.**

Checklist:
- [ ] criar `TaskEntity`
- [ ] criar repository Spring Data
- [ ] criar mapper entity-domain
- [ ] criar `TaskRepositoryAdapter`

Critério de aceite:
- [ ] salvar, buscar e listar tasks via adapter

### Epic 5 — Integração com Notion

#### Story 5.1
**Como sistema, quero configurar acesso ao Notion para sincronizar tasks com uma database.**

Checklist:
- [ ] definir `NOTION_TOKEN`
- [ ] definir `NOTION_DATABASE_ID`
- [ ] criar `NotionProperties`
- [ ] validar carregamento de configuração

Critério de aceite:
- [ ] credenciais/configurações disponíveis via properties

#### Story 5.2
**Como sistema, quero criar um adapter HTTP para enviar task ao Notion.**

Checklist:
- [ ] criar client com `WebClient`
- [ ] montar headers
- [ ] criar payload
- [ ] tratar retorno da API
- [ ] capturar `notionPageId`

Critério de aceite:
- [ ] task é criada com sucesso no Notion

#### Story 5.3
**Como sistema, quero sincronizar uma task criada localmente com o Notion para manter as plataformas alinhadas.**

Checklist:
- [ ] implementar `SyncTaskToNotionUseCase`
- [ ] buscar task no banco
- [ ] enviar task ao Notion
- [ ] salvar `notionPageId`
- [ ] atualizar `syncStatus`

Critério de aceite:
- [ ] sync atualiza corretamente o estado da task

#### Story 5.4
**Como usuário, quero ver quando a sincronização falhar para poder agir sobre o problema.**

Checklist:
- [ ] tratar falhas de integração
- [ ] salvar `SYNC_ERROR`
- [ ] registrar mensagem de erro
- [ ] exibir erro no terminal

Critério de aceite:
- [ ] falhas de sync ficam visíveis

### Epic 6 — Mensageria e resiliência

#### Story 6.1
**Como sistema, quero publicar evento após criação de task para desacoplar o envio ao Notion.**

Checklist:
- [ ] configurar RabbitMQ
- [ ] criar exchange
- [ ] criar queue
- [ ] criar routing key
- [ ] publicar evento `TaskCreated`

Critério de aceite:
- [ ] evento é enviado para fila após criação da task

#### Story 6.2
**Como sistema, quero consumir o evento de criação para sincronizar a task de forma assíncrona.**

Checklist:
- [ ] criar listener/consumer
- [ ] receber `taskId`
- [ ] buscar task
- [ ] enviar para Notion
- [ ] atualizar status

Critério de aceite:
- [ ] sincronização ocorre de forma assíncrona

#### Story 6.3
**Como operador, quero reprocessar tasks com erro para recuperar falhas temporárias.**

Checklist:
- [ ] criar comando `task:retry --id`
- [ ] validar estado da task
- [ ] reenfileirar evento ou chamar sync
- [ ] atualizar logs

Critério de aceite:
- [ ] tasks com erro podem ser reprocessadas

#### Story 6.4
**Como sistema, quero tratar falhas temporárias com retry e DLQ para aumentar resiliência.**

Checklist:
- [ ] configurar retry
- [ ] configurar dead-letter queue
- [ ] definir política de tentativas
- [ ] registrar erro final

Critério de aceite:
- [ ] falhas recorrentes não derrubam o processamento

### Epic 7 — Segurança

#### Story 7.1
**Como operador, quero autenticar meu acesso para proteger o uso da aplicação.**

Checklist:
- [ ] definir estratégia de autenticação
- [ ] criar usuários/roles
- [ ] configurar `PasswordEncoder`
- [ ] validar login inicial

Critério de aceite:
- [ ] aplicação exige autenticação em ambiente protegido

#### Story 7.2
**Como administrador, quero restringir comandos sensíveis por perfil para reduzir riscos operacionais.**

Checklist:
- [ ] definir permissões por comando
- [ ] proteger retry/sync manual
- [ ] proteger comandos administrativos

Critério de aceite:
- [ ] comandos respeitam autorização por role

### Epic 8 — Qualidade, testes e documentação

#### Story 8.1
**Como desenvolvedor, quero testes unitários do domínio para validar regras críticas.**

Checklist:
- [ ] testar criação de task válida
- [ ] testar título obrigatório
- [ ] testar enums/estados
- [ ] testar transições de sync

Critério de aceite:
- [ ] regras principais do domínio estão cobertas

#### Story 8.2
**Como desenvolvedor, quero testes de integração da persistência para garantir funcionamento do banco.**

Checklist:
- [ ] testar save
- [ ] testar findById
- [ ] testar listagem
- [ ] testar update de sync

Critério de aceite:
- [ ] persistência funciona em ambiente de teste

#### Story 8.3
**Como desenvolvedor, quero testes da integração com Notion para reduzir regressões.**

Checklist:
- [ ] mockar client HTTP
- [ ] testar sucesso
- [ ] testar falha
- [ ] testar parsing de resposta

Critério de aceite:
- [ ] adapter do Notion tem cobertura dos fluxos críticos

#### Story 8.4
**Como time, queremos documentação clara para subir e operar o projeto.**

Checklist:
- [ ] atualizar README
- [ ] documentar arquitetura
- [ ] documentar variáveis de ambiente
- [ ] documentar comandos CLI
- [ ] adicionar `docker-compose`

Critério de aceite:
- [ ] qualquer dev consegue rodar o projeto com a documentação

---

## Kanban Inicial Preenchido

### Backlog
- Story 5.1
- Story 5.2
- Story 5.3
- Story 5.4
- Story 6.1
- Story 6.2
- Story 6.3
- Story 6.4
- Story 7.1
- Story 7.2
- Story 8.1
- Story 8.2
- Story 8.3
- Story 8.4

### Ready
- Story 1.1
- Story 1.2
- Story 1.3
- Story 2.1
- Story 2.2

### In Progress
- vazio no início

### Review/Test
- vazio no início

### Done
- vazio no início

### Blocked
- criação da integration no Notion
- obtenção de token
- obtenção de database id

---

## Checklist Mestre do Projeto

### Fundação
- [ ] projeto Spring Boot criado
- [ ] Java 21 configurado
- [ ] dependências base instaladas
- [ ] README inicial criado

### Arquitetura
- [ ] estrutura hexagonal criada
- [ ] domínio desacoplado de framework
- [ ] portas de entrada e saída definidas

### CLI
- [ ] terminal interativo funcionando
- [ ] comando de criação funcionando
- [ ] comando de listagem funcionando
- [ ] comando de detalhamento funcionando

### Banco
- [ ] PostgreSQL configurado
- [ ] Flyway configurado
- [ ] tabela de tasks criada
- [ ] persistência funcionando

### Notion
- [ ] token configurado
- [ ] database id configurado
- [ ] integração HTTP funcionando
- [ ] sync de task funcionando

### Mensageria
- [ ] RabbitMQ configurado
- [ ] evento publicado
- [ ] consumer funcionando
- [ ] retry funcionando
- [ ] DLQ funcionando

### Segurança
- [ ] autenticação definida
- [ ] autorização por perfis definida
- [ ] comandos sensíveis protegidos

### Qualidade
- [ ] testes de domínio
- [ ] testes de integração
- [ ] logs estruturados
- [ ] docker-compose
- [ ] documentação completa

---

## Cronograma Sugerido

### Semana 1 — Fundação
Entregas:
- Story 1.1
- Story 1.2
- Story 1.3
- Story 2.1
- Story 2.2

Resultado:
- projeto sobe
- estrutura está organizada
- domínio inicial existe

### Semana 2 — Casos de uso + persistência
Entregas:
- Story 2.3
- Story 2.4
- Story 4.1
- Story 4.2
- Story 4.3

Resultado:
- task pode ser criada e persistida no banco

### Semana 3 — CLI funcional
Entregas:
- Story 3.1
- Story 3.2
- Story 3.3
- Story 3.4

Resultado:
- usuário cria, lista e consulta tasks pela CLI

### Semana 4 — Notion
Entregas:
- Story 5.1
- Story 5.2
- Story 5.3
- Story 5.4

Resultado:
- task criada localmente sincroniza com Notion
- falhas ficam registradas

### Semana 5 — Mensageria + qualidade
Entregas:
- Story 6.1
- Story 6.2
- Story 6.3
- Story 8.1
- Story 8.2
- Story 8.4

Resultado:
- sincronização assíncrona
- documentação melhor
- testes principais prontos

### Semana 6 — Robustez final
Entregas:
- Story 6.4
- Story 7.1
- Story 7.2
- Story 8.3

Resultado:
- retries e DLQ
- autenticação/autorização
- integração mais segura

---

## Estimativa de Tempo com Agentes de IA

### Faixas resumidas
- **MVP enxuto**: 5 a 8 dias úteis
- **MVP robusto**: 10 a 15 dias úteis
- **Versão mais próxima de produção**: 15 a 30 dias úteis

### Recomendação prática
- **Entrega 1**: CLI + banco + sync direto com Notion
- **Entrega 2**: RabbitMQ + retry + segurança + testes ampliados

---

## Tabela de Esforço

| ID | Frente / Entrega | Escopo | Dependências | Otimista | Realista | Conservador |
|---|---|---|---|---:|---:|---:|
| 1 | Setup inicial | Spring Boot, Java 21, build, estrutura base, configs iniciais | nenhuma | 0,5 | 1 | 2 |
| 2 | Arquitetura hexagonal | pacotes, portas, adapters, convenções | 1 | 0,5 | 1 | 2 |
| 3 | Domínio Task | entidade, enums, regras básicas, comandos | 2 | 0,5 | 1 | 2 |
| 4 | Persistência | JPA, PostgreSQL, Flyway, mapeamentos | 3 | 1 | 2 | 3 |
| 5 | CLI interativa | Spring Shell/comandos, prompts, saída formatada | 3,4 | 1 | 2 | 3 |
| 6 | Integração Notion | WebClient, auth, payload, parsing, erros | 3,4 | 1 | 2 | 4 |
| 7 | Sync síncrona inicial | salvar task e enviar ao Notion no mesmo fluxo | 5,6 | 0,5 | 1 | 2 |
| 8 | Mensageria | RabbitMQ, publish/consume, evento TaskCreated | 4,7 | 1 | 2 | 4 |
| 9 | Retry + DLQ | reprocesso, tratamento de falhas, políticas de retry | 8 | 0,5 | 1,5 | 3 |
| 10 | Spring Security | autenticação, roles, autorização por comando | 5 | 1 | 2 | 4 |
| 11 | Testes unitários | domínio, casos de uso, validações | 3,7 | 1 | 2 | 3 |
| 12 | Testes integração | banco, Notion mockado, mensageria | 4,6,8 | 1 | 2 | 4 |
| 13 | Observabilidade | logs, actuator, erros de sync, rastreabilidade | 7,8 | 0,5 | 1 | 2 |
| 14 | Documentação + DX | README, setup local, envs, docker-compose | 1,4,8 | 0,5 | 1 | 2 |

### Totais por cenário

| Cenário | Total estimado |
|---|---:|
| Otimista | 10 dias úteis |
| Realista | 21,5 dias úteis |
| Conservador | 40 dias úteis |

---

## Prioridade Recomendada

### Alta
- 1.1, 1.2, 1.3
- 2.1, 2.2, 2.3, 2.4
- 4.1, 4.2, 4.3
- 3.1, 3.2
- 5.1, 5.2, 5.3

### Média
- 3.3, 3.4
- 5.4
- 6.1, 6.2, 6.3
- 8.1, 8.2, 8.4

### Baixa
- 6.4
- 7.1, 7.2
- 8.3

---

## Próximos Passos Recomendados

1. criar o projeto Spring Boot
2. estruturar a arquitetura hexagonal
3. modelar a entidade `Task`
4. configurar PostgreSQL + Flyway
5. implementar `task:create`
6. persistir no banco
7. integrar com Notion
8. evoluir para mensageria e segurança
