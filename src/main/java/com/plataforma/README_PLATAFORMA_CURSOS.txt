#  Plataforma de Cursos Online

Este projeto é uma **plataforma de gestão de cursos online** desenvolvida em **Java** com **Swing** para interface gráfica e **MySQL** como banco de dados.  
O sistema segue a arquitetura **MVC (Model-View-Controller)**, com camadas **DAO** e **Service**, garantindo separação de responsabilidades e facilidade de manutenção.

---

##  Funcionalidades

-  **Gestão de usuários (alunos e administradores)**
-  **Cadastro e gerenciamento de cursos**
-  **Controle de matrículas**
-  **Registro de avaliações**
-  **Relatórios completos**:
  - Desempenho de alunos
  - Evasão
  - Ranking de instrutores
  - Evolução temporal de matrículas

---

##  Estrutura do Projeto

```
plataforma-cursos-javav2/
│── scripts/                  # Scripts SQL de criação, inserts e relatórios
│── src/main/java/com/plataforma/
│   ├── model/                # Entidades (Aluno, Curso, Matricula, Avaliacao)
│   ├── dao/                  # Acesso ao banco de dados
│   ├── service/              # Regras de negócio
│   ├── controller/           # Controle central da aplicação
│   ├── gui/                  # Interface Swing (painéis e telas)
│   └── database/             # Conexão JDBC
│── pom.xml                   # Configuração do Maven
```

---

## 🔧 Tecnologias Utilizadas

- **Java 17+**
- **Swing (UI desktop)**
- **MySQL 8+**
- **Maven** (gerenciamento de dependências)
- **JDBC** (conexão com banco de dados)

---

##  Banco de Dados

Scripts disponíveis em `/scripts`:
- `create-database.sql` → Criação do esquema
- `insert-sample-data.sql` → Dados de exemplo
- `queries-completas.sql` → Consultas gerais
- `queries-relatorios.sql` → Relatórios principais
- `queries-relatorios-avancados.sql` → Relatórios avançados



##  Autor

Desenvolvido por **Ulisses Magalhães**
