# 🚀 Plataforma de Cursos Online - Sistema Completo

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.9-red.svg)](https://maven.apache.org/)
[![Swing](https://img.shields.io/badge/GUI-Swing-green.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)

## 📋 **Visão Geral**

Sistema completo de gestão de cursos online desenvolvido em Java com arquitetura em camadas (MVC + DAO + Service), interface gráfica Swing e banco de dados MySQL. Inclui sistema completo de relatórios e estatísticas.

### ✨ **Principais Funcionalidades**

- 👥 **Gestão de Alunos** - CRUD completo com validações
- 📚 **Gestão de Cursos** - Controle de cursos, instrutores e categorias
- 📝 **Gestão de Matrículas** - Sistema de matrículas com controle de status
- ⭐ **Gestão de Avaliações** - Notas, feedback e conceitos
- 📊 **Sistema de Relatórios** - Relatórios completos e estatísticas avançadas

---

## 🏗️ **Arquitetura**

### **Padrões Implementados**
- **MVC (Model-View-Controller)** - Separação de responsabilidades
- **DAO (Data Access Object)** - Acesso a dados
- **Service Layer** - Regras de negócio
- **Singleton** - Conexão com banco de dados

### **Tecnologias Utilizadas**
- **Java 17** - Linguagem principal
- **Swing** - Interface gráfica
- **MySQL 8.0** - Banco de dados
- **JDBC** - Conectividade
- **Maven** - Gerenciamento de dependências

---

## 📊 **Sistema de Relatórios**

### **Tipos de Relatórios Disponíveis**

1. **📈 Relatório por Aluno**
   - Histórico completo de cursos
   - Notas e conceitos
   - Estatísticas de performance
   - Média geral e progresso

2. **📚 Relatório por Curso**
   - Lista de alunos matriculados
   - Estatísticas da turma
   - Média de satisfação
   - Taxa de conclusão

3. **📊 Estatísticas Gerais**
   - Dashboard do sistema
   - Métricas globais
   - Totais e médias
   - Indicadores de performance

4. **📝 Matrículas Detalhadas**
   - Por aluno ou curso
   - Status e datas
   - Histórico completo

5. **⭐ Avaliações Completas**
   - Por aluno ou curso
   - Notas e feedback
   - Conceitos e estatísticas

---

## 🗄️ **Banco de Dados**

### **Estrutura Expandida**

#### **Entidades Principais**
- **Aluno** (20 registros) - Dados pessoais completos
- **Curso** (15 registros) - Cursos em 7 categorias
- **Matricula** (50 registros) - Relacionamentos aluno-curso
- **Avaliacao** (30 registros) - Notas e feedback
- **Auditoria** - Log de operações

#### **Categorias de Cursos**
- 💻 Programação (Java, Python, JavaScript)
- 🌐 Desenvolvimento Web (Full Stack, React, Angular)
- ⚙️ Backend (Node.js, Spring Boot)
- 🔧 DevOps (Docker, Kubernetes)
- 📊 Data Science (Machine Learning)
- 📱 Mobile (Flutter)
- 🔒 Segurança (Cybersecurity)
- ☁️ Cloud (AWS)
- 🎨 Design (UI/UX)

---

## 🚀 **Instalação e Execução**

### **Pré-requisitos**
- Java 17+
- MySQL 8.0+
- Maven 3.6+
- IntelliJ IDEA (recomendado)

### **Passo a Passo**

1. **Clone o repositório**
   \`\`\`bash
   git clone [url-do-repositorio]
   cd plataforma-cursos-completa
   \`\`\`

2. **Configure o banco de dados**
   \`\`\`sql
   mysql -u root -p < scripts/database-completo.sql
   \`\`\`

3. **Configure a conexão**
   \`\`\`java
   // Em DatabaseConnection.java
   private static final String PASSWORD = "SUA_SENHA";
   \`\`\`

4. **Execute a aplicação**
   \`\`\`bash
   mvn clean compile exec:java
   \`\`\`

### **Documentação Completa**
📖 Consulte `GUIA_INSTALACAO_PASSO_A_PASSO.md` para instruções detalhadas.

---

## 🎯 **Como Usar**

### **Interface Principal**
A aplicação possui 5 abas principais:

1. **👥 Alunos** - Cadastro e gestão de alunos
2. **📚 Cursos** - Cadastro e gestão de cursos
3. **📝 Matrículas** - Realizar e gerenciar matrículas
4. **⭐ Avaliações** - Registrar notas e feedback
5. **📊 Relatórios** - Gerar relatórios e estatísticas

### **Gerando Relatórios**
1. Acesse a aba "📊 Relatórios"
2. Selecione o tipo de relatório desejado
3. Escolha aluno/curso se necessário
4. Clique em "Gerar Relatório"
5. Analise os resultados na tabela e estatísticas

---

## 📈 **Funcionalidades Avançadas**

### **Validações Implementadas**
- ✅ CPF único e válido
- ✅ Carga horária entre 1-1000h
- ✅ Notas entre 0-10
- ✅ Datas não futuras
- ✅ Matrículas sem duplicatas
- ✅ Máximo 5 matrículas por aluno

### **Recursos Especiais**
- 🔄 Atualização automática de dados
- 📊 Cálculo de médias em tempo real
- 🎯 Conceitos automáticos (Excelente, Bom, etc.)
- 📋 Histórico completo de operações
- 🔍 Filtros e buscas avançadas

---

## 📊 **Estatísticas do Sistema**

### **Dados de Demonstração**
- **20 alunos** com dados completos
- **15 cursos** em múltiplas categorias
- **50 matrículas** com status variados
- **30 avaliações** com feedback detalhado
- **100+ registros** de auditoria

### **Métricas Disponíveis**
- Taxa de conclusão por curso
- Média de
