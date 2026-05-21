# 🏦 Desafio Técnico Itaú - Java 10x (2024-2025)

**Baseado no repositório oficial:** https://github.com/horacio3m/Desafio-tecnico-itau-java10x

---

## 📌 Visão Geral

Criar uma **API REST em Spring Boot que gerencia transações financeiras** e fornece estatísticas sobre elas.

**Stack Obrigatório:**
- Java 17+
- Spring Boot 4.0.1+
- REST API (sem banco de dados externo)
- Armazenamento em memória

---

## 🎯 Endpoints Obrigatórios

### 1️⃣ **POST `/transacao` - Adicionar Transação**

Recebe uma transação e a armazena em memória.

#### Request:
```json
{
    "valor": 100.50,
    "dataHora": "2024-01-15T10:30:00Z"
}
```

#### Validações:
- ✅ `valor` obrigatório
- ✅ `dataHora` obrigatório (formato ISO 8601)
- ❌ Valor não pode ser negativo
- ❌ Data não pode ser no futuro
- ✅ Aceita valores decimais (Double)

#### Respostas:
| Status | Significado |
|--------|------------|
| `201 Created` | Transação armazenada com sucesso |
| `422 Unprocessable Entity` | Erro de validação (valor negativo, data futura, etc) |
| `400 Bad Request` | JSON inválido ou campos ausentes |

---

### 2️⃣ **DELETE `/transacao` - Limpar Todas as Transações**

Apaga todas as transações armazenadas em memória.

#### Request:
```
DELETE /transacao
```

#### Response:
```
200 OK  →  Transações deletadas com sucesso
```

---

### 3️⃣ **GET `/estatistica` - Estatísticas dos Últimos 60 Segundos**

Retorna estatísticas das transações **dos últimos N segundos** (configurável).

#### Request:
```
GET /estatistica
```

#### Response:
```json
{
    "soma": 100.50,
    "min": 100.50,
    "max": 100.50,
    "media": 100.50,
    "quantidade": 1
}
```

#### Campos:
| Campo | Descrição |
|-------|-----------|
| `soma` | Soma total dos valores |
| `min` | Menor valor |
| `max` | Maior valor |
| `media` | Média dos valores |
| `quantidade` | Quantidade de transações |

#### Regras Importantes:
- 📊 Considera **apenas transações dos últimos 60 segundos** (padrão)
- 🔄 Se não houver transações → todos os valores = `0`
- ⚡ Cálculos devem ser **eficientes** (não iterar todas as transações)
- 🔧 O intervalo é **configurável** em `application.yml`

#### Response:
```
200 OK  →  JSON com as estatísticas
```

---

## ⚙️ Configuração

### `application.yml`

```yaml
server:
  port: 8080

estatistica:
  segundos: 60  # Intervalo em segundos (configurável)
```

O arquivo está em `src/main/resources/application.yml`

---

## ⚠️ Restrições Importantes

| Restrição | Detalhe | Status |
|-----------|---------|--------|
| **Sem Banco de Dados** | H2, MySQL, PostgreSQL = NÃO | ❌ |
| **Sem Cache Externo** | Redis, Memcached = NÃO | ❌ |
| **Armazenamento** | Apenas em Memória (HashMap, ArrayList, etc) | ✅ |
| **Persistência** | Transações perdidas ao reiniciar | ✅ |
| **Formato** | Apenas JSON | ✅ |

---

## 🛠️ Tecnologias Utilizadas (do projeto oficial)

```xml
<properties>
  <java.version>17</java.version>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>4.0.1</version>
  </dependency>
  
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```

---

## 📁 Estrutura de Diretórios Recomendada

```
src/
├── main/
│   ├── java/
│   │   └── dev/java10x/itauJava10x/
│   │       ├── Transacoes/
│   │       │   ├── TransacaoController.java
│   │       │   ├── TransacaoService.java
│   │       │   ├── TransacaoRepository.java
│   │       │   ├── Transacao.java (DTO/Entity)
│   │       │   └── TransacaoRequest.java
│   │       ├── Estatisticas/
│   │       │   ├── EstatisticaController.java
│   │       │   ├── EstatisticaService.java
│   │       │   └── EstatisticaResponse.java
│   │       └── ItauJava10xApplication.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── dev/java10x/itauJava10x/
            ├── TransacaoControllerTests.java
            ├── EstatisticaControllerTests.java
            └── ItauJava10xApplicationTests.java
```

---

## 🚀 Como Rodar Localmente

### 1. Clone o repositório
```bash
git clone https://github.com/horacio3m/Desafio-tecnico-itau-java10x.git
cd Desafio-tecnico-itau-java10x
```

### 2. Build
```bash
# Com Maven Wrapper (Linux/Mac)
./mvnw clean install

# Ou no Windows
mvnw.cmd clean install
```

### 3. Executar
```bash
# Com Maven Wrapper (Linux/Mac)
./mvnw spring-boot:run

# Ou executar o JAR
java -jar target/itauJava10x-0.0.1-SNAPSHOT.jar
```

### 4. Testar com cURL

```bash
# Adicionar uma transação
curl -X POST http://localhost:8080/transacao \
  -H "Content-Type: application/json" \
  -d '{"valor": 100.50, "dataHora": "2024-01-15T10:30:00Z"}'

# Obter estatísticas
curl http://localhost:8080/estatistica

# Deletar todas as transações
curl -X DELETE http://localhost:8080/transacao
```

---

## 🧪 Testes

```bash
# Rodar todos os testes
./mvnw test

# Rodar teste específico
./mvnw test -Dtest=TransacaoControllerTests
```

---

## ✨ Extras/Diferenciais (Não Obrigatórios)

Implementar qualquer um desses pode destacar sua candidatura:

1. **Testes Completos** (JUnit 5 + Mockito)
   - Testes do caminho feliz
   - Testes de erro/validação
   - Testes de performance

2. **Documentação Swagger/OpenAPI**
   ```xml
   <dependency>
     <groupId>org.springdoc</groupId>
     <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
     <version>2.0.2</version>
   </dependency>
   ```
   - Endpoint: `http://localhost:8080/swagger-ui.html`

3. **Tratamento de Erros Customizado**
   - `@ExceptionHandler` para validações
   - Respostas HTTP adequadas

4. **Logs Estruturados**
   - SLF4J + Logback
   - Log de transações recebidas

5. **Containerização**
   - Dockerfile para rodar em container
   - Não precisa publicar no Docker Hub

6. **Limpeza de Transações Antigas**
   - Implementar thread que remove transações > 60s
   - Evita acúmulo em memória

---

## 📝 Checklist de Implementação

### Fase 1: Setup
- [ ] Criar projeto Spring Boot 4.0.1
- [ ] Adicionar dependências (Spring Web, Lombok)
- [ ] Estruturar pacotes

### Fase 2: Endpoints
- [ ] Implementar POST `/transacao` com validações
- [ ] Implementar DELETE `/transacao`
- [ ] Implementar GET `/estatistica`

### Fase 3: Lógica
- [ ] Armazenar transações em memória
- [ ] Calcular estatísticas dos últimos 60s
- [ ] Validar datas (não futuro, formato correto)
- [ ] Validar valores (não negativo)

### Fase 4: Testes
- [ ] Testes unitários para Services
- [ ] Testes de integração para Controllers
- [ ] Testes de validação

### Fase 5: Polish
- [ ] Configurar `application.yml`
- [ ] Adicionar Swagger/OpenAPI
- [ ] Logs estruturados
- [ ] README com instruções

---

## 🎯 Dicas Importantes

1. **Use DTOs** para receber dados da API
2. **Valide no Controller ou em Validator customizado**
3. **Use Service para lógica de negócio**
4. **Use Repository para acesso aos dados** (mesmo que em memória)
5. **Implemente `Comparable` ou use `comparator` para min/max**
6. **Use `System.currentTimeMillis()` para comparar datas**
7. **Considere usar `ConcurrentHashMap` para thread-safety**

---

## 📌 Diferenças do Desafio Antigo (2020)

| Aspecto | Antigo (2020) | Novo (2024-2025) |
|---------|--------------|------------------|
| Java | 8+ | 17+ |
| Spring Boot | 2.x | 4.0.1+ |
| Nome campo stats | `count` | `quantidade` |
| Nome campo stats | `sum` | `soma` |
| Nome campo stats | `avg` | `media` |
| Campo dataHora | ISO 8601 + timezone | ISO 8601 formato mais flexível |

---

## 🔗 Referências Úteis

- Repositório oficial: https://github.com/horacio3m/Desafio-tecnico-itau-java10x
- Vídeo do Fiasco: https://www.youtube.com/watch?v=OH_DIXOqkYU
- Spring Boot Docs: https://spring.io/projects/spring-boot
- Java 17 Features: https://www.oracle.com/java/technologies/javase/17-relnotes.html

---

## ❓ Dúvidas Frequentes

**P: Preciso usar banco de dados?**
R: Não, armazene tudo em memória.

**P: Como fica quando reinicio a aplicação?**
R: Todas as transações são perdidas (comportamento esperado).

**P: Preciso fazer deploy?**
R: Não, apenas ter o código no GitHub/GitLab público.

**P: Qual a versão mínima do Java?**
R: Java 17 (conforme o projeto oficial).

**P: O intervalo de 60 segundos é fixo?**
R: É o padrão, mas pode ser configurável via `application.yml`.

---

## 🎓 O que é Avaliado

✅ **Qualidade do código** (limpeza, legibilidade)  
✅ **Organização do projeto** (estrutura de pastas)  
✅ **Testes** (cobertura e qualidade)  
✅ **Tratamento de erros** (validações)  
✅ **Performance** (cálculos eficientes)  
✅ **Documentação** (README, Swagger)  
✅ **Git** (commits bem estruturados)  

---

**Boa sorte com o desafio! 🚀**
