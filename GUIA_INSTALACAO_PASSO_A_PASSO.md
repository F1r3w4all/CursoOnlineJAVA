# 🚀 Guia de Instalação Passo a Passo - Plataforma de Cursos Online

## 📋 **Checklist de Pré-requisitos**

Antes de começar, certifique-se de ter:
- [ ] Computador com Windows 10/11, macOS ou Linux
- [ ] Conexão com internet estável
- [ ] Pelo menos 4GB de RAM disponível
- [ ] 5GB de espaço livre em disco
- [ ] Permissões de administrador

---

## 🔧 **Passo 1: Instalação do Java JDK 17**

### **1.1 Download do JDK**
1. Acesse: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
2. Aceite os termos de licença
3. Baixe a versão adequada para seu sistema operacional:
   - **Windows:** `jdk-17_windows-x64_bin.exe`
   - **macOS:** `jdk-17_macos-x64_bin.dmg`
   - **Linux:** `jdk-17_linux-x64_bin.tar.gz`

### **1.2 Instalação**

#### **Windows:**
1. Execute o arquivo `.exe` baixado
2. Clique em "Next" nas telas de instalação
3. Mantenha o diretório padrão: `C:\Program Files\Java\jdk-17`
4. Aguarde a instalação concluir

#### **macOS:**
1. Abra o arquivo `.dmg` baixado
2. Execute o instalador `.pkg`
3. Siga as instruções na tela
4. Digite sua senha quando solicitado

#### **Linux (Ubuntu/Debian):**
\`\`\`bash
# Opção 1: Via apt (recomendado)
sudo apt update
sudo apt install openjdk-17-jdk

# Opção 2: Download manual
wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz
sudo tar -xzf jdk-17_linux-x64_bin.tar.gz -C /opt/
sudo ln -s /opt/jdk-17 /opt/java
\`\`\`

### **1.3 Configuração de Variáveis de Ambiente**

#### **Windows:**
1. Pressione `Win + R`, digite `sysdm.cpl` e pressione Enter
2. Clique na aba "Avançado"
3. Clique em "Variáveis de Ambiente"
4. Em "Variáveis do Sistema", clique em "Novo"
5. Nome: `JAVA_HOME`
6. Valor: `C:\Program Files\Java\jdk-17`
7. Edite a variável `Path` e adicione: `%JAVA_HOME%\bin`

#### **macOS/Linux:**
Adicione ao arquivo `~/.bashrc` ou `~/.zshrc`:
\`\`\`bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64  # Linux
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home  # macOS
export PATH=$JAVA_HOME/bin:$PATH
\`\`\`

Recarregue o terminal:
\`\`\`bash
source ~/.bashrc  # ou ~/.zshrc
\`\`\`

### **1.4 Verificação**
Abra um novo terminal/prompt e execute:
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

---

## 🗄️ **Passo 2: Instalação do MySQL Server**

### **2.1 Download do MySQL**
1. Acesse: https://dev.mysql.com/downloads/mysql/
2. Selecione seu sistema operacional
3. Baixe o MySQL Community Server 8.0

### **2.2 Instalação**

#### **Windows:**
1. Execute o arquivo `.msi` baixado
2. Escolha "Custom" para instalação personalizada
3. Selecione:
   - MySQL Server 8.0
   - MySQL Workbench 8.0
   - MySQL Shell 8.0
4. Clique em "Execute" para instalar

#### **Configuração do MySQL (Windows):**
1. **Configuration Type:** Development Computer
2. **Connectivity:**
   - Port: `3306`
   - X Protocol Port: `33060`
3. **Authentication Method:** Use Strong Password Encryption
4. **Accounts and Roles:**
   - Root Password: `Digite uma senha forte`
   - Confirme a senha
5. **Windows Service:**
   - Service Name: `MySQL80`
   - Start at System Startup: ✅
6. Clique em "Execute" para aplicar configurações

#### **macOS:**
\`\`\`bash
# Usando Homebrew (recomendado)
brew install mysql

# Iniciar o MySQL
brew services start mysql

# Configurar senha root
mysql_secure_installation
\`\`\`

#### **Linux (Ubuntu/Debian):**
\`\`\`bash
# Atualizar repositórios
sudo apt update

# Instalar MySQL Server
sudo apt install mysql-server

# Configurar instalação segura
sudo mysql_secure_installation

# Iniciar serviço
sudo systemctl start mysql
sudo systemctl enable mysql
\`\`\`

### **2.3 Verificação da Instalação**
\`\`\`bash
mysql --version
\`\`\`

### **2.4 Teste de Conexão**
\`\`\`bash
mysql -u root -p
\`\`\`
Digite a senha configurada. Se conectar com sucesso, digite `exit` para sair.

---

## 🔧 **Passo 3: Instalação do MySQL Workbench**

### **3.1 Download**
1. Acesse: https://dev.mysql.com/downloads/workbench/
2. Selecione seu sistema operacional
3. Baixe a versão mais recente

### **3.2 Instalação**

#### **Windows:**
1. Execute o arquivo `.msi` baixado
2. Siga o assistente de instalação
3. Aceite os termos de licença
4. Clique em "Install"

#### **macOS:**
1. Abra o arquivo `.dmg` baixado
2. Arraste o MySQL Workbench para a pasta Applications
3. Abra o Launchpad e execute o MySQL Workbench

#### **Linux:**
\`\`\`bash
# Ubuntu/Debian
sudo apt install mysql-workbench-community

# CentOS/RHEL
sudo yum install mysql-workbench-community
\`\`\`

### **3.3 Configuração da Conexão**
1. Abra o MySQL Workbench
2. Clique no ícone "+" ao lado de "MySQL Connections"
3. Configure:
   - **Connection Name:** `Plataforma Cursos Local`
   - **Hostname:** `localhost`
   - **Port:** `3306`
   - **Username:** `root`
4. Clique em "Store in Keychain/Vault" e digite a senha
5. Clique em "Test Connection"
6. Se bem-sucedido, clique em "OK"

---

## 📦 **Passo 4: Instalação do Apache Maven**

### **4.1 Download**
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe o "Binary zip archive": `apache-maven-3.9.x-bin.zip`

### **4.2 Instalação**

#### **Windows:**
1. Extraia o arquivo ZIP para `C:\Program Files\Apache\maven`
2. Adicione às variáveis de ambiente:
   - `MAVEN_HOME`: `C:\Program Files\Apache\maven`
   - Adicione ao `Path`: `%MAVEN_HOME%\bin`

#### **macOS:**
\`\`\`bash
# Usando Homebrew
brew install maven

# Ou instalação manual
wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz
sudo tar -xzf apache-maven-3.9.5-bin.tar.gz -C /opt/
sudo ln -s /opt/apache-maven-3.9.5 /opt/maven

# Adicionar ao ~/.zshrc ou ~/.bash_profile
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH
\`\`\`

#### **Linux:**
\`\`\`bash
# Ubuntu/Debian
sudo apt install maven

# Ou instalação manual
wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.tar.gz
sudo tar -xzf apache-maven-3.9.5-bin.tar.gz -C /opt/
sudo ln -s /opt/apache-maven-3.9.5 /opt/maven

# Adicionar ao ~/.bashrc
export MAVEN_HOME=/opt/maven
export PATH=$MAVEN_HOME/bin:$PATH
\`\`\`

### **4.3 Verificação**
\`\`\`bash
mvn -version
\`\`\`

**Saída esperada:**
\`\`\`
Apache Maven 3.9.x
Maven home: /opt/maven
Java version: 17.0.x, vendor: Oracle Corporation
\`\`\`

---

## 💻 **Passo 5: Instalação do IntelliJ IDEA**

### **5.1 Download**
1. Acesse: https://www.jetbrains.com/idea/download/
2. Baixe a versão **Community** (gratuita)
3. Selecione seu sistema operacional

### **5.2 Instalação**

#### **Windows:**
1. Execute o arquivo `.exe` baixado
2. Siga o assistente de instalação
3. Opções recomendadas:
   - ✅ Create Desktop Shortcut
   - ✅ Add "bin" folder to PATH
   - ✅ Add "Open Folder as Project"
   - ✅ .java - IntelliJ IDEA Community Edition

#### **macOS:**
1. Abra o arquivo `.dmg` baixado
2. Arraste o IntelliJ IDEA para Applications
3. Abra o aplicativo

#### **Linux:**
\`\`\`bash
# Usando snap
sudo snap install intellij-idea-community --classic

# Ou download manual
wget https://download.jetbrains.com/idea/ideaIC-2023.x.x.tar.gz
sudo tar -xzf ideaIC-2023.x.x.tar.gz -C /opt/
sudo ln -s /opt/idea-IC-xxx.xxx.x /opt/intellij

# Criar desktop entry
sudo nano /usr/share/applications/intellij.desktop
\`\`\`

### **5.3 Configuração Inicial**
1. **Primeira execução:**
   - Aceite os termos de uso
   - Escolha o tema (Dark ou Light)
   - Pule a instalação de plugins adicionais por enquanto

2. **Configurar JDK:**
   - File → Project Structure
   - Project Settings → Project
   - Project SDK → Add SDK → JDK
   - Selecione o diretório do JDK 17

---

## 🗃️ **Passo 6: Configuração do Banco de Dados**

### **6.1 Criação do Banco**
1. Abra o MySQL Workbench
2. Conecte-se à instância local
3. Abra uma nova query tab (Ctrl+T)
4. Execute o script de criação:

\`\`\`sql
-- Criar banco de dados
DROP DATABASE IF EXISTS plataforma_cursos;
CREATE DATABASE plataforma_cursos 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE plataforma_cursos;
\`\`\`

### **6.2 Criação das Tabelas**
Execute o script completo das tabelas (disponível em `scripts/create-database.sql`):

\`\`\`sql
-- Tabela Aluno
CREATE TABLE aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL,
    INDEX idx_aluno_cpf (cpf),
    INDEX idx_aluno_nome (nome)
) ENGINE=InnoDB;

-- [Continue com as outras tabelas...]
\`\`\`

### **6.3 Inserção de Dados de Teste**
Execute o script de dados de exemplo (disponível em `scripts/insert-sample-data.sql`).

### **6.4 Verificação**
Execute para verificar se tudo foi criado corretamente:
\`\`\`sql
SHOW TABLES;
SELECT COUNT(*) FROM aluno;
SELECT COUNT(*) FROM curso;
SELECT COUNT(*) FROM matricula;
SELECT COUNT(*) FROM avaliacao;
\`\`\`

---

## 📁 **Passo 7: Configuração do Projeto**

### **7.1 Estrutura de Diretórios**
Crie a seguinte estrutura de pastas:
\`\`\`
plataforma-cursos/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── plataforma/
│                   ├── controller/
│                   ├── dao/
│                   ├── database/
│                   ├── gui/
│                   ├── model/
│                   ├── service/
│                   └── Main.java
├── scripts/
├── pom.xml
└── README.md
\`\`\`

### **7.2 Configuração do pom.xml**
Crie o arquivo `pom.xml` na raiz do projeto:

\`\`\`xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.plataforma</groupId>
    <artifactId>plataforma-cursos</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
</project>
\`\`\`

### **7.3 Configuração da Conexão**
Edite o arquivo `DatabaseConnection.java` com suas credenciais:

\`\`\`java
private static final String URL = "jdbc:mysql://localhost:3306/plataforma_cursos";
private static final String USER = "root";
private static final String PASSWORD = "SUA_SENHA_AQUI"; // Altere aqui
\`\`\`

---

## ▶️ **Passo 8: Executando o Projeto**

### **8.1 Importar no IntelliJ IDEA**
1. Abra o IntelliJ IDEA
2. File → Open
3. Selecione a pasta do projeto `plataforma-cursos`
4. Aguarde o IntelliJ detectar o Maven e baixar dependências

### **8.2 Configurar Run Configuration**
1. Run → Edit Configurations
2. Clique em "+" → Application
3. Configure:
   - **Name:** `Plataforma Cursos`
   - **Main class:** `com.plataforma.Main`
   - **Use classpath of module:** `plataforma-cursos`

### **8.3 Executar**
1. Clique no botão "Run" (▶️) ou pressione Shift+F10
2. Verifique no console se a conexão foi estabelecida
3. A interface gráfica deve aparecer

---

## 🔍 **Passo 9: Verificação e Testes**

### **9.1 Teste de Funcionalidades**
1. **Aba Alunos:**
   - Adicione um novo aluno
   - Edite um aluno existente
   - Exclua um aluno

2. **Aba Cursos:**
   - Cadastre um novo curso
   - Modifique informações de um curso

3. **Aba Matrículas:**
   - Matricule um aluno em um curso
   - Verifique se não permite matrículas duplicadas

4. **Aba Avaliações:**
   - Registre uma avaliação
   - Teste validações de nota (0-10)

### **9.2 Verificação no Banco**
No MySQL Workbench, execute:
\`\`\`sql
SELECT * FROM vw_matriculas_completas;
SELECT * FROM vw_estatisticas_curso;
SELECT * FROM vw_dashboard_geral;
\`\`\`

---

## 🚨 **Solução de Problemas Comuns**

### **Problema 1: Erro de Conexão MySQL**
\`\`\`
Communications link failure
\`\`\`
**Solução:**
1. Verifique se o MySQL está rodando:
   \`\`\`bash
   # Windows
   net start mysql80
   

2. Teste a conexão manual:
   \`\`\`bash
   mysql -u root -p -h localhost -P 3306
   \`\`\`

### **Problema 2: ClassNotFoundException**
\`\`\`
java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
\`\`\`
**Solução:**
1. Verifique se a dependência MySQL está no `pom.xml`
2. Execute: `mvn clean install`
3. Refresh do projeto no IntelliJ (Ctrl+Shift+O)

### **Problema 3: Erro de Encoding**
\`\`\`
Incorrect string value
\`\`\`
**Solução:**
Altere a URL de conexão para:
\`\`\`java
private static final String URL = "jdbc:mysql://localhost:3306/plataforma_cursos?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
\`\`\`

### **Problema 4: Erro de Timezone**
\`\`\`
The server time zone value is unrecognized
\`\`\`
**Solução:**
No MySQL Workbench, execute:
\`\`\`sql
SET GLOBAL time_zone = '+00:00';
\`\`\`

---

## ✅ **Checklist Final**

Antes de considerar a instalação completa, verifique:

- [ ] Java 17 instalado e configurado
- [ ] MySQL Server rodando na porta 3306
- [ ] MySQL Workbench conectando com sucesso
- [ ] Maven instalado e funcionando
- [ ] IntelliJ IDEA abrindo projetos Java
- [ ] Banco `plataforma_cursos` criado com dados
- [ ] Projeto compilando sem erros
- [ ] Interface gráfica abrindo corretamente
- [ ] Todas as funcionalidades CRUD funcionando
- [ ] Dados sendo persistidos no banco

---

## 🎉 **Parabéns!**

Se chegou até aqui, sua **Plataforma de Cursos Online** está funcionando perfeitamente! 

### **Próximos Passos:**
1. Explore todas as funcionalidades
2. Teste cenários diferentes
3. Analise os relatórios no MySQL Workbench
4. Customize conforme suas necessidades

### **Suporte:**
- Documentação completa em `DOCUMENTACAO_COMPLETA.md`
- Queries úteis em `scripts/queries-completas.sql`
- Em caso de dúvidas, verifique os logs no console do IntelliJ

**🚀 Sua plataforma está pronta para uso!**
