# 📚 Documentação Completa - Plataforma de Cursos Online

## 🎯 **Visão Geral do Projeto**

Sistema completo de gestão de cursos online desenvolvido em Java com arquitetura em camadas (MVC + DAO + Service), interface Swing e banco de dados MySQL.

### **Tecnologias Utilizadas**
- **Java 17** - Linguagem de programação
- **Swing** - Interface gráfica
- **MySQL 8.0** - Banco de dados
- **JDBC** - Conectividade com banco
- **Maven** - Gerenciamento de dependências
- **IntelliJ IDEA** - IDE de desenvolvimento

---

## 🛠️ **Pré-requisitos e Instalação**

### **1. Java Development Kit (JDK 17)**

#### **Download e Instalação:**
1. Acesse: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
2. Baixe o JDK 17 para seu sistema operacional
3. Execute o instalador e siga as instruções

#### **Verificação da Instalação:**
\`\`\`bash
java -version
javac -version
\`\`\`

**Saída esperada:**
\`\`\`
java version "17.0.x" 2023-xx-xx LTS
Java(TM) SE Runtime Environment (build 17.0.x+xx-LTS-xxx)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.x+xx-LTS-xxx, mixed mode, sharing)
\`\`\`

#### **Configuração de Variáveis de Ambiente:**

**Windows:**
1. Painel de Controle → Sistema → Configurações Avançadas
2. Variáveis de Ambiente
3. Adicionar `JAVA_HOME`: `C:\Program Files\Java\jdk-17`
4. Adicionar ao `PATH`: `%JAVA_HOME%\bin`

**Linux/Mac:**
\`\`\`bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
export PATH=$JAVA_HOME/bin:$PATH
\`\`\`

---

### **2. MySQL Server 8.0**

#### **Download e Instalação:**
1. Acesse: https://dev.mysql.com/downloads/mysql/
2. Baixe o MySQL Community Server 8.0
3. Execute o instalador

#### **Configuração Durante a Instalação:**
- **Tipo de Configuração:** Development Computer
- **Porta:** 3306 (padrão)
- **Usuário Root:** Defina uma senha forte
- **Método de Autenticação:** Use Strong Password Encryption

#### **Verificação da Instalação:**
\`\`\`bash
mysql --version
\`\`\`

#### **Teste de Conexão:**
\`\`\`bash
mysql -u root -p
\`\`\`

---

### **3. MySQL Workbench**

#### **Download e Instalação:**
1. Acesse: https://dev.mysql.com/downloads/workbench/
2. Baixe o MySQL Workbench
3. Execute o instalador

#### **Configuração da Conexão:**
1. Abra o MySQL Workbench
2. Clique em "+" para nova conexão
3. Configure:
   - **Connection Name:** Plataforma Cursos
   - **Hostname:** localhost
   - **Port:** 3306
   - **Username:** root
   - **Password:** [sua senha]
4. Teste a conexão

---

### **4. Apache Maven**

#### **Download e Instalação:**
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe o Binary zip archive
3. Extraia para `C:\Program Files\Apache\maven` (Windows)

#### **Configuração de Variáveis de Ambiente:**
**Windows:**
1. Adicionar `MAVEN_HOME`: `C:\Program Files\Apache\maven`
2. Adicionar ao `PATH`: `%MAVEN_HOME%\bin`

#### **Verificação:**
\`\`\`bash
mvn -version
\`\`\`

---

### **5. IntelliJ IDEA**

#### **Download e Instalação:**
1. Acesse: https://www.jetbrains.com/idea/download/
2. Baixe a versão Community (gratuita)
3. Execute o instalador

#### **Configuração Inicial:**
1. Configure o JDK 17
2. Instale plugins necessários:
   - Maven Integration
   - Database Tools and SQL

---

## 🗄️ **Configuração do Banco de Dados**

### **1. Criação do Banco e Tabelas**

Abra o MySQL Workbench e execute os seguintes scripts:

#### **Script 1: Criação do Banco e Tabelas**
\`\`\`sql
-- Criação do banco de dados
DROP DATABASE IF EXISTS plataforma_cursos;
CREATE DATABASE plataforma_cursos;
USE plataforma_cursos;

-- Tabela Aluno
CREATE TABLE aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL,
    INDEX idx_aluno_cpf (cpf),
    INDEX idx_aluno_nome (nome)
);

-- Tabela Curso
CREATE TABLE curso (
    id_curso INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    carga_horaria INT NOT NULL CHECK (carga_horaria > 0),
    instrutor VARCHAR(100) NOT NULL,
    INDEX idx_curso_titulo (titulo),
    INDEX idx_curso_instrutor (instrutor)
);

-- Tabela Matricula
CREATE TABLE matricula (
    id_matricula INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT NOT NULL,
    id_curso INT NOT NULL,
    data_matricula DATE NOT NULL,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE,
    UNIQUE KEY uk_matricula_aluno_curso (id_aluno, id_curso),
    INDEX idx_matricula_data (data_matricula)
);

-- Tabela Avaliacao
CREATE TABLE avaliacao (
    id_avaliacao INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT NOT NULL,
    id_curso INT NOT NULL,
    nota DECIMAL(4,2) NOT NULL CHECK (nota >= 0 AND nota <= 10),
    feedback TEXT,
    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno) ON DELETE CASCADE,
    FOREIGN KEY (id_curso) REFERENCES curso(id_curso) ON DELETE CASCADE,
    UNIQUE KEY uk_avaliacao_aluno_curso (id_aluno, id_curso),
    INDEX idx_avaliacao_nota (nota),
    INDEX idx_avaliacao_data (data_avaliacao)
);
\`\`\`

#### **Script 2: Dados de Exemplo**
\`\`\`sql
-- Inserir alunos
INSERT INTO aluno (nome, cpf, data_cadastro) VALUES
('João Silva Santos', '123.456.789-01', '2024-01-15'),
('Maria Oliveira Costa', '987.654.321-02', '2024-01-20'),
('Pedro Henrique Lima', '456.789.123-03', '2024-02-01'),
('Ana Carolina Souza', '789.123.456-04', '2024-02-10'),
('Carlos Eduardo Pereira', '321.654.987-05', '2024-02-15'),
('Fernanda Rodrigues', '654.321.789-06', '2024-02-20'),
('Rafael Almeida', '147.258.369-07', '2024-03-01'),
('Juliana Ferreira', '369.258.147-08', '2024-03-05');

-- Inserir cursos
INSERT INTO curso (titulo, carga_horaria, instrutor) VALUES
('Java Fundamentals - Programação Orientada a Objetos', 40, 'Prof. Carlos Mendes'),
('Python para Iniciantes - Lógica de Programação', 30, 'Prof. Ana Lima'),
('Desenvolvimento Web Full Stack', 60, 'Prof. Roberto Silva'),
('Banco de Dados MySQL - Modelagem e Consultas', 35, 'Prof. Fernanda Costa'),
('JavaScript Moderno - ES6+ e Frameworks', 45, 'Prof. Diego Santos'),
('React.js - Desenvolvimento Frontend', 50, 'Prof. Marina Oliveira'),
('Node.js - Backend com JavaScript', 40, 'Prof. Lucas Pereira'),
('Spring Boot - Desenvolvimento Java Enterprise', 55, 'Prof. Patricia Alves');

-- Inserir matrículas
INSERT INTO matricula (id_aluno, id_curso, data_matricula) VALUES
(1, 1, '2024-01-16'), -- João em Java
(1, 3, '2024-01-17'), -- João em Web
(2, 2, '2024-01-21'), -- Maria em Python
(2, 4, '2024-01-22'), -- Maria em MySQL
(3, 1, '2024-02-02'), -- Pedro em Java
(3, 5, '2024-02-03'), -- Pedro em JavaScript
(4, 4, '2024-02-11'), -- Ana em MySQL
(4, 6, '2024-02-12'), -- Ana em React
(5, 7, '2024-02-16'), -- Carlos em Node.js
(5, 8, '2024-02-17'), -- Carlos em Spring Boot
(6, 2, '2024-02-21'), -- Fernanda em Python
(6, 3, '2024-02-22'), -- Fernanda em Web
(7, 5, '2024-03-02'), -- Rafael em JavaScript
(7, 6, '2024-03-03'), -- Rafael em React
(8, 1, '2024-03-06'), -- Juliana em Java
(8, 8, '2024-03-07'); -- Juliana em Spring Boot

-- Inserir avaliações
INSERT INTO avaliacao (id_aluno, id_curso, nota, feedback) VALUES
(1, 1, 8.5, 'Excelente participação e compreensão dos conceitos de POO'),
(1, 3, 9.2, 'Demonstrou grande habilidade no desenvolvimento web'),
(2, 2, 9.0, 'Muito dedicada e com ótimo raciocínio lógico'),
(2, 4, 8.8, 'Dominou bem os conceitos de modelagem de dados'),
(3, 1, 7.5, 'Bom desempenho, pode melhorar na prática de programação'),
(3, 5, 8.0, 'Boa evolução no aprendizado de JavaScript'),
(4, 4, 9.5, 'Excelente compreensão de banco de dados'),
(4, 6, 8.7, 'Muito boa no desenvolvimento com React'),
(5, 7, 8.3, 'Bom entendimento de desenvolvimento backend'),
(6, 2, 7.8, 'Progresso consistente em Python'),
(7, 5, 9.1, 'Excelente domínio de JavaScript moderno'),
(8, 1, 8.9, 'Muito boa em programação orientada a objetos');
\`\`\`

#### **Script 3: Views e Procedures Úteis**
\`\`\`sql
-- View para relatório completo de matrículas
CREATE VIEW vw_matriculas_completas AS
SELECT 
    m.id_matricula,
    a.nome AS nome_aluno,
    a.cpf,
    c.titulo AS titulo_curso,
    c.instrutor,
    c.carga_horaria,
    m.data_matricula,
    CASE 
        WHEN av.nota IS NOT NULL THEN av.nota 
        ELSE NULL 
    END AS nota,
    CASE 
        WHEN av.nota >= 9.0 THEN 'Excelente'
        WHEN av.nota >= 8.0 THEN 'Muito Bom'
        WHEN av.nota >= 7.0 THEN 'Bom'
        WHEN av.nota >= 6.0 THEN 'Regular'
        WHEN av.nota < 6.0 THEN 'Insuficiente'
        ELSE 'Não Avaliado'
    END AS conceito,
    av.feedback
FROM matricula m
JOIN aluno a ON m.id_aluno = a.id_aluno
JOIN curso c ON m.id_curso = c.id_curso
LEFT JOIN avaliacao av ON m.id_aluno = av.id_aluno AND m.id_curso = av.id_curso
ORDER BY m.data_matricula DESC;

-- View para estatísticas por curso
CREATE VIEW vw_estatisticas_curso AS
SELECT 
    c.id_curso,
    c.titulo,
    c.instrutor,
    c.carga_horaria,
    COUNT(DISTINCT m.id_aluno) AS total_alunos,
    COUNT(av.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(av.nota), 2) AS media_notas,
    MIN(av.nota) AS menor_nota,
    MAX(av.nota) AS maior_nota
FROM curso c
LEFT JOIN matricula m ON c.id_curso = m.id_curso
LEFT JOIN avaliacao av ON c.id_curso = av.id_curso
GROUP BY c.id_curso, c.titulo, c.instrutor, c.carga_horaria
ORDER BY total_alunos DESC;

-- View para estatísticas por aluno
CREATE VIEW vw_estatisticas_aluno AS
SELECT 
    a.id_aluno,
    a.nome,
    a.cpf,
    a.data_cadastro,
    COUNT(DISTINCT m.id_curso) AS total_cursos,
    COUNT(av.id_avaliacao) AS total_avaliacoes,
    ROUND(AVG(av.nota), 2) AS media_notas,
    SUM(c.carga_horaria) AS total_horas_cursadas
FROM aluno a
LEFT JOIN matricula m ON a.id_aluno = m.id_aluno
LEFT JOIN avaliacao av ON a.id_aluno = av.id_aluno
LEFT JOIN curso c ON m.id_curso = c.id_curso
GROUP BY a.id_aluno, a.nome, a.cpf, a.data_cadastro
ORDER BY media_notas DESC;

-- Procedure para relatório de desempenho
DELIMITER //
CREATE PROCEDURE sp_relatorio_desempenho_curso(IN curso_id INT)
BEGIN
    SELECT 
        a.nome AS aluno,
        a.cpf,
        m.data_matricula,
        COALESCE(av.nota, 0) AS nota,
        CASE 
            WHEN av.nota >= 9.0 THEN 'Excelente'
            WHEN av.nota >= 8.0 THEN 'Muito Bom'
            WHEN av.nota >= 7.0 THEN 'Bom'
            WHEN av.nota >= 6.0 THEN 'Regular'
            WHEN av.nota < 6.0 THEN 'Insuficiente'
            ELSE 'Não Avaliado'
        END AS conceito,
        av.feedback
    FROM matricula m
    JOIN aluno a ON m.id_aluno = a.id_aluno
    LEFT JOIN avaliacao av ON m.id_aluno = av.id_aluno AND m.id_curso = av.id_curso
    WHERE m.id_curso = curso_id
    ORDER BY av.nota DESC;
END //
DELIMITER ;
\`\`\`

---

## 🚀 **Configuração do Projeto no IntelliJ IDEA**

### **1. Importando o Projeto**

1. **Abra o IntelliJ IDEA**
2. **File → New → Project from Version Control** (se usando Git)
   - Ou **File → Open** para projeto local
3. **Selecione a pasta do projeto**
4. **Aguarde o IntelliJ detectar o Maven** e baixar dependências

### **2. Configuração do JDK**

1. **File → Project Structure** (Ctrl+Alt+Shift+S)
2. **Project Settings → Project**
3. **Project SDK:** Selecione Java 17
4. **Project Language Level:** 17

### **3. Configuração do Maven**

1. **File → Settings** (Ctrl+Alt+S)
2. **Build, Execution, Deployment → Build Tools → Maven**
3. **Maven home path:** Caminho da instalação do Maven
4. **User settings file:** `~/.m2/settings.xml`

### **4. Configuração da Conexão com Banco**

1. **View → Tool Windows → Database**
2. **+ → Data Source → MySQL**
3. **Configure:**
   - **Host:** localhost
   - **Port:** 3306
   - **Database:** plataforma_cursos
   - **User:** root
   - **Password:** [sua senha]
4. **Test Connection**

### **5. Configuração da Classe DatabaseConnection**

Edite o arquivo `src/main/java/com/plataforma/database/DatabaseConnection.java`:

\`\`\`java
private static final String URL = "jdbc:mysql://localhost:3306/plataforma_cursos";
private static final String USER = "root";
private static final String PASSWORD = "SUA_SENHA_AQUI"; // Altere aqui
\`\`\`

---

## ▶️ **Executando o Projeto**

### **1. Via IntelliJ IDEA**

1. **Navegue até:** `src/main/java/com/plataforma/Main.java`
2. **Clique com botão direito → Run 'Main.main()'**
3. **Ou use o atalho:** Ctrl+Shift+F10

### **2. Via Maven (Terminal)**

\`\`\`bash
# Navegar até a pasta do projeto
cd caminho/para/plataforma-cursos

# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn exec:java -Dexec.mainClass="com.plataforma.Main"
\`\`\`

### **3. Via JAR Executável**

\`\`\`bash
# Gerar JAR
mvn clean package

# Executar JAR
java -jar target/plataforma-cursos-1.0.0.jar
\`\`\`

---

## 🔧 **Resolução de Problemas Comuns**

### **1. Erro de Conexão com MySQL**

**Problema:** `Communications link failure`

**Soluções:**
\`\`\`sql
-- Verificar se o MySQL está rodando
sudo systemctl status mysql  # Linux
net start mysql              # Windows

-- Verificar usuários e permissões
SELECT user, host FROM mysql.user WHERE user = 'root';

-- Criar usuário se necessário
CREATE USER 'root'@'localhost' IDENTIFIED BY 'sua_senha';
GRANT ALL PRIVILEGES ON plataforma_cursos.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
\`\`\`

### **2. Erro de Driver JDBC**

**Problema:** `ClassNotFoundException: com.mysql.cj.jdbc.Driver`

**Solução:** Verificar se a dependência está no `pom.xml`:
\`\`\`xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
\`\`\`

### **3. Erro de Encoding**

**Problema:** Caracteres especiais não aparecem corretamente

**Solução:** Adicionar parâmetros na URL de conexão:
\`\`\`java
private static final String URL = "jdbc:mysql://localhost:3306/plataforma_cursos?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
\`\`\`

### **4. Erro de Timezone**

**Problema:** `The server time zone value 'XXX' is unrecognized`

**Solução:** Configurar timezone no MySQL:
\`\`\`sql
SET GLOBAL time_zone = '+00:00';
\`\`\`

---

## 📊 **Queries Úteis para Testes**

### **Consultas de Verificação**

\`\`\`sql
-- Verificar total de registros
SELECT 'Alunos' as tabela, COUNT(*) as total FROM aluno
UNION ALL
SELECT 'Cursos', COUNT(*) FROM curso
UNION ALL
SELECT 'Matrículas', COUNT(*) FROM matricula
UNION ALL
SELECT 'Avaliações', COUNT(*) FROM avaliacao;

-- Top 5 alunos com melhores médias
SELECT 
    a.nome,
    ROUND(AVG(av.nota), 2) as media,
    COUNT(av.id_avaliacao) as total_avaliacoes
FROM aluno a
JOIN avaliacao av ON a.id_aluno = av.id_aluno
GROUP BY a.id_aluno, a.nome
HAVING COUNT(av.id_avaliacao) > 0
ORDER BY media DESC
LIMIT 5;

-- Cursos mais populares
SELECT 
    c.titulo,
    COUNT(m.id_matricula) as total_matriculas,
    ROUND(AVG(av.nota), 2) as media_avaliacoes
FROM curso c
LEFT JOIN matricula m ON c.id_curso = m.id_curso
LEFT JOIN avaliacao av ON c.id_curso = av.id_curso
GROUP BY c.id_curso, c.titulo
ORDER BY total_matriculas DESC;

-- Relatório completo de um aluno específico
SELECT 
    a.nome as aluno,
    c.titulo as curso,
    c.instrutor,
    m.data_matricula,
    av.nota,
    av.feedback
FROM aluno a
JOIN matricula m ON a.id_aluno = m.id_aluno
JOIN curso c ON m.id_curso = c.id_curso
LEFT JOIN avaliacao av ON a.id_aluno = av.id_aluno AND c.id_curso = av.id_curso
WHERE a.id_aluno = 1  -- Alterar ID conforme necessário
ORDER BY m.data_matricula;
\`\`\`

### **Queries de Manutenção**

\`\`\`sql
-- Backup de dados importantes
CREATE TABLE backup_avaliacoes AS SELECT * FROM avaliacao;

-- Limpeza de dados de teste (CUIDADO!)
-- DELETE FROM avaliacao WHERE id_avaliacao > 0;
-- DELETE FROM matricula WHERE id_matricula > 0;
-- DELETE FROM curso WHERE id_curso > 0;
-- DELETE FROM aluno WHERE id_aluno > 0;

-- Reset AUTO_INCREMENT
-- ALTER TABLE aluno AUTO_INCREMENT = 1;
-- ALTER TABLE curso AUTO_INCREMENT = 1;
-- ALTER TABLE matricula AUTO_INCREMENT = 1;
-- ALTER TABLE avaliacao AUTO_INCREMENT = 1;

-- Verificar integridade referencial
SELECT 
    TABLE_NAME,
    CONSTRAINT_NAME,
    CONSTRAINT_TYPE
FROM information_schema.TABLE_CONSTRAINTS
WHERE TABLE_SCHEMA = 'plataforma_cursos';
\`\`\`

---

## 📱 **Interface do Sistema**

### **Funcionalidades Principais**

1. **Gestão de Alunos**
   - Cadastro com validação de CPF
   - Edição de dados pessoais
   - Exclusão com confirmação
   - Listagem ordenada

2. **Gestão de Cursos**
   - Cadastro com validação de carga horária
   - Controle de instrutores
   - Edição de informações
   - Exclusão com verificação de dependências

3. **Gestão de Matrículas**
   - Matrícula com validação de duplicatas
   - Seleção via ComboBox
   - Controle de datas
   - Cancelamento de matrículas

4. **Gestão de Avaliações**
   - Notas de 0 a 10
   - Feedback textual
   - Validação de matrícula prévia
   - Edição e exclusão

### **Validações Implementadas**

- **Alunos:** Nome obrigatório, CPF único, data não futura
- **Cursos:** Título obrigatório, carga horária 1-1000h
- **Matrículas:** Sem duplicatas, máximo 5 por aluno
- **Avaliações:** Nota 0-10, apenas para matriculados

---

## 🎯 **Próximos Passos**

### **Melhorias Sugeridas**

1. **Sistema de Login**
   - Autenticação de usuários
   - Diferentes níveis de acesso
   - Controle de sessão

2. **Relatórios Avançados**
   - Exportação para PDF
   - Gráficos de desempenho
   - Estatísticas detalhadas

3. **Módulos de Curso**
   - Divisão de cursos em módulos
   - Controle de progresso
   - Certificados

4. **API REST**
   - Endpoints para integração
   - Documentação Swagger
   - Autenticação JWT

5. **Interface Web**
   - Frontend React/Angular
   - Responsividade
   - PWA (Progressive Web App)

---

## 📞 **Suporte**

### **Contatos para Dúvidas**
- **Documentação:** Este arquivo
- **Issues:** GitHub Issues
- **Email:** suporte@plataformacursos.com

### **Recursos Adicionais**
- **MySQL Documentation:** https://dev.mysql.com/doc/
- **Java Documentation:** https://docs.oracle.com/en/java/
- **Maven Guide:** https://maven.apache.org/guides/
- **IntelliJ IDEA Help:** https://www.jetbrains.com/help/idea/

---

**🎉 Parabéns! Seu sistema está pronto para uso!**
