# 🚀 TraceOn API

## Sobre o Projeto

O **TraceOn** é uma API desenvolvida em **Java 21 + Spring Boot** para monitoramento e controle de dispositivos espaciais, gerenciamento de missões, coleta de telemetrias, geração de alertas, tratamento de ocorrências, planejamento de manutenções e gerenciamento de usuários.

O projeto foi estruturado utilizando conceitos de **Domain-Driven Design (DDD)**, separando claramente as responsabilidades de negócio em **Bounded Contexts**, permitindo evolução independente de cada módulo.

---

# Como Executar

## Pré-requisitos

- Java 21
- Maven 3.9+

## Executando a aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

---

# Como Testar

Os exemplos deste documento podem ser executados utilizando:

- Postman
- Insomnia
- Thunder Client (VS Code)

Todos os endpoints utilizam JSON como formato de entrada e saída.

---

# Arquitetura

A aplicação segue uma arquitetura inspirada em DDD e Clean Architecture.

Estrutura padrão dos contextos:

```text
context/
│
├── application/
│   └── Services
│
├── controller/
│   └── Controllers REST
│
├── domain/
│   ├── entity/
│   ├── enums/
│   ├── event/
│   ├── exception/
│   └── repository/
│
├── dto/
│
└── infrastructure/
    └── repository/
```

---

# Aplicação do DDD

Cada contexto representa uma área específica do domínio.

Atualmente a solução é composta pelos seguintes contextos:

* Device Context
* Mission Context
* Telemetry Context
* Alert Context
* Occurrence Context
* Maintenance Context
* Identity Context

Cada contexto possui:

* Entidades próprias
* Regras de negócio próprias
* Exceções específicas
* Casos de uso isolados
* Repositórios independentes

---

# Device Context

Responsável pelo gerenciamento dos dispositivos espaciais monitorados pelo sistema.

## Aggregate Root

```text
DispositivoEspacial
```

## Responsabilidades

* Cadastro de dispositivos
* Atualização de dados
* Controle operacional
* Controle de manutenção

## Casos de Uso

### Criar dispositivo

```text
POST /devices
```

#### Exemplo de Request

```json
{
  "nome": "Voyager I",
  "codigoSerial": "SAT-001",
  "modelo": "VX-100",
  "fabricante": "NASA",
  "energiaAtual": 85,
  "integridadeEstrutural": 100,
  "firmwareVersion": "1.0.0"
}
```

### Buscar dispositivos

```text
GET /devices
```

### Buscar dispositivo por ID

```text
GET /devices/{id}
```

### Atualizar dispositivo

```text
PUT /devices/{id}
```

#### Exemplo de Request

```json
{
  "nome": "Voyager I",
  "modelo": "VX-200",
  "energiaAtual": 90,
  "firmwareVersion": "1.1.0"
}
```

### Remover dispositivo

```text
DELETE /devices/{id}
```

### Ativar dispositivo

```text
PATCH /devices/{id}/ativar
```

### Desativar dispositivo

```text
PATCH /devices/{id}/desativar
```

### Colocar em manutenção

```text
PATCH /devices/{id}/manutencao
```

## Estados do Dispositivo

```text
OPERACIONAL
MANUTENCAO
DESATIVADO
```

---

# Mission Context

Responsável pelo planejamento e execução de missões espaciais.

## Aggregate Root

```text
Missao
```

## Entidades Internas

```text
EventoMissao
```

## Responsabilidades

* Planejamento de missões
* Controle de ciclo de vida
* Associação de dispositivos
* Registro de eventos operacionais

## Casos de Uso

### Criar missão

```text
POST /missions
```

#### Exemplo de Request

```json
{
  "codigo": "MIS-001",
  "nome": "Missão Marte",
  "objetivo": "Mapeamento de superfície",
  "descricao": "Monitoramento geológico da região norte",
  "prioridade": "ALTA",
  "dataFimPrevista": "2026-12-31T23:59:59"
}
```

### Atualizar missão

```text
PUT /missions/{id}
```

#### Exemplo de Request

```json
{
  "nome": "Missão Marte Atualizada",
  "descricao": "Nova descrição operacional",
  "prioridade": "CRITICA"
}
```

### Iniciar missão

```text
PATCH /missions/{id}/iniciar
```

### Pausar missão

```text
PATCH /missions/{id}/pausar
```

### Finalizar missão

```text
PATCH /missions/{id}/finalizar
```

### Cancelar missão

```text
PATCH /missions/{id}/cancelar
```

### Associar dispositivo

```text
POST /missions/{id}/devices/{deviceId}
```

### Desassociar dispositivo

```text
DELETE /missions/{id}/devices/{deviceId}
```

---

# Telemetry Context

Responsável pela coleta e armazenamento de dados operacionais dos dispositivos.

## Aggregate Root

```text
Telemetria
```

## Value Objects

```text
Localizacao
```

## Responsabilidades

* Registrar telemetrias
* Histórico operacional
* Última leitura disponível
* Alimentar análise automática de alertas

## Dados Monitorados

* Temperatura interna
* Temperatura externa
* Nível de energia
* Radiação
* Qualidade do sinal
* Localização espacial

## Casos de Uso

### Registrar telemetria

```text
POST /devices/{id}/telemetries
```

#### Exemplo de Request

```json
{
  "temperaturaInterna": 42.5,
  "temperaturaExterna": -80.0,
  "pressao": 1.2,
  "radiacao": 18.4,
  "sinalComunicacao": 95,
  "statusColeta": "VALIDA",
  "localizacao": {
    "latitude": 15.75,
    "longitude": 30.22,
    "altitude": 420.5,
    "velocidade": 27500,
    "direcao": 180
  }
}
```

### Buscar histórico

```text
GET /devices/{id}/telemetries
```

### Buscar última telemetria

```text
GET /devices/{id}/telemetries/latest
```

---

# Alert Context

Responsável por detectar e representar situações anormais.

## Aggregate Root

```text
Alerta
```

## Entidades Internas

```text
EventoAlerta
```

## Responsabilidades

* Geração de alertas
* Controle de severidade
* Controle de resolução
* Integração com ocorrências

## Tipos de Alerta

```text
TEMPERATURA_ELEVADA
TEMPERATURA_CRITICA
ENERGIA_BAIXA
RADIACAO_ELEVADA
SINAL_FRACO
```

## Severidades

```text
BAIXA
MEDIA
ALTA
CRITICA
```

## Status

```text
ABERTO
EM_ANALISE
RESOLVIDO
IGNORADO
```

## Regras Automáticas

### Temperatura

```text
> 80°C  → TEMPERATURA_ELEVADA
> 95°C  → TEMPERATURA_CRITICA
```

### Energia

```text
< 20% → ENERGIA_BAIXA
```

### Sinal

```text
< 30% → SINAL_FRACO
```

### Radiação

```text
Acima do limite operacional → RADIACAO_ELEVADA
```

## Casos de Uso

### Criar alerta

```text
POST /alerts
```

#### Exemplo de Request

```json
{
  "tipo": "TEMPERATURA_CRITICA",
  "severidade": "CRITICA",
  "descricao": "Temperatura acima do limite operacional",
  "deviceId": "uuid"
}
```

* Buscar alertas
* Resolver alerta
* Ignorar alerta
* Alterar severidade

---

# Occurrence Context

Responsável pelo tratamento operacional de problemas identificados.

## Aggregate Root

```text
Ocorrencia
```

## Entidades Internas

```text
EventoOcorrencia
```

## Responsabilidades

* Investigação de problemas
* Tratamento operacional
* Encerramento de incidentes

## Fluxo

```text
ABERTA
↓
EM_ANALISE
↓
EM_TRATAMENTO
↓
ENCERRADA
```

ou

```text
ABERTA
↓
CANCELADA
```

## Casos de Uso

### Criar ocorrência

```text
POST /occurrences
```

#### Exemplo de Request

```json
{
  "titulo": "Falha de comunicação",
  "descricao": "Dispositivo ficou sem comunicação por mais de 15 minutos",
  "categoria": "COMUNICACAO",
  "deviceId": "uuid",
  "alertId": "uuid"
}
```

### Iniciar análise

```text
PATCH /occurrences/{id}/analise
```

### Iniciar tratamento

```text
PATCH /occurrences/{id}/tratamento
```

### Encerrar ocorrência

```text
PATCH /occurrences/{id}/encerrar
```

### Cancelar ocorrência

```text
PATCH /occurrences/{id}/cancelar
```

---

# Maintenance Context

Responsável pela manutenção preventiva e corretiva dos dispositivos.

## Aggregate Root

```text
Manutencao
```

## Entidades Internas

```text
ChecklistManutencao
```

## Eventos

```text
MaintenanceScheduledEvent
```

## Responsabilidades

* Planejamento de manutenção
* Execução de manutenção
* Histórico técnico
* Controle de checklist

## Tipos

```text
PREVENTIVA
CORRETIVA
EMERGENCIAL
REMOTA
```

## Status

```text
ABERTA
EM_EXECUCAO
AGUARDANDO_PECA
CONCLUIDA
CANCELADA
```

## Casos de Uso

### Criar manutenção

```text
POST /maintenances
```

#### Exemplo de Request

```json
{
  "tipo": "CORRETIVA",
  "prioridade": "ALTA",
  "descricaoProblema": "Perda recorrente de comunicação",
  "agendadaPara": "2026-07-10T09:00:00",
  "deviceId": "uuid"
}
```

### Iniciar manutenção

```text
PATCH /maintenances/{id}/iniciar
```

### Aguardar peça

```text
PATCH /maintenances/{id}/aguardando-peca
```

### Concluir manutenção

```text
PATCH /maintenances/{id}/concluir
```

### Cancelar manutenção

```text
PATCH /maintenances/{id}/cancelar
```

### Adicionar item de checklist

```text
POST /maintenances/{id}/checklists
```

#### Exemplo de Request

```json
{
  "descricao": "Verificar módulo de comunicação",
  "ordemExecucao": 1
}
```

---

# Identity Context

Responsável pela gestão dos usuários da plataforma.

## Aggregate Root

```text
Usuario
```

## Responsabilidades

* Cadastro de usuários
* Controle de acesso
* Gestão de perfis
* Ativação e bloqueio de contas

## Perfis

```text
ADMINISTRADOR
OPERADOR
ANALISTA
TECNICO
```

## Status

```text
ATIVO
INATIVO
BLOQUEADO
```

## Casos de Uso

### Criar usuário

```text
POST /users
```

#### Exemplo de Request

```json
{
  "nome": "João Silva",
  "email": "joao@traceon.com",
  "senha": "123456",
  "perfil": "OPERADOR"
}
```

### Atualizar usuário

```text
PUT /users/{id}
```

#### Exemplo de Request

```json
{
  "nome": "João Silva",
  "email": "joao.silva@traceon.com",
  "perfil": "SUPERVISOR"
}
```

### Ativar usuário

```text
PATCH /users/{id}/ativar
```

### Bloquear usuário

```text
PATCH /users/{id}/bloquear
```

### Desativar usuário

```text
PATCH /users/{id}/desativar
```

---

# Tratamento Global de Exceções

A aplicação centraliza o tratamento de erros através da classe:

```java
HttpExceptionHandler
```

Responsabilidades:

* Capturar exceções de domínio
* Padronizar respostas HTTP
* Evitar tratamento repetido nos controllers
* Melhorar rastreabilidade dos erros

Estrutura padrão:

```json
{
  "message": "Descrição do erro",
  "status": 400
}
```

---

# DTOs

Todos os endpoints utilizam DTOs para:

* Entrada de dados (Request)
* Saída de dados (Response)

Benefícios:

* Isolamento do domínio
* Controle de serialização
* Segurança
* Evolução independente da API

---

# Repositórios

Atualmente o projeto utiliza implementações em memória para fins acadêmicos e de prototipação.

Exemplos:

```java
DispositivoRepository
InMemoryMissionRepository
TelemetryRepository
AlertRepository
OccurrenceRepository
MaintenanceRepository
UserRepository
```

Todos implementam contratos definidos no domínio.

---

# Eventos de Domínio

O sistema utiliza Domain Events para registrar acontecimentos importantes.

Exemplos:

```text
MissionStartedEvent
AlertGeneratedEvent
MaintenanceScheduledEvent
OccurrenceCreatedEvent
```

Esses eventos representam fatos do domínio e permitem futuras integrações assíncronas.

---

# Fluxo Geral da Plataforma

```text
Dispositivo
    ↓
Telemetria
    ↓
Alerta
    ↓
Ocorrência
    ↓
Manutenção
```

1. O dispositivo envia telemetria.
2. A telemetria é analisada.
3. Alertas são gerados automaticamente.
4. Alertas podem originar ocorrências.
5. Ocorrências podem originar manutenções.
6. A manutenção atualiza o estado operacional do dispositivo.

---

# Tecnologias

* Java 21
* Spring Boot
* Spring Web
* Jakarta Validation
* Lombok
* UUID
* Domain-Driven Design (DDD)

---

# Objetivo Acadêmico

O TraceOn foi desenvolvido como uma plataforma de monitoramento espacial orientada a domínio, com foco em boas práticas de modelagem, separação de responsabilidades e implementação de conceitos de DDD aplicados a sistemas distribuídos e críticos.
