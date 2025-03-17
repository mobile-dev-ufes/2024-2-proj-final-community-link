# CommunityLink

## 📌 Introdução
O **CommunityLink** é um sistema desenvolvido para auxiliar projetos e ações sociais, proporcionando maior visibilidade por meio de uma plataforma digital.
Com o CommunityLink, organizações e indivíduos podem divulgar seus projetos, gerenciar ações e facilitar o engajamento da comunidade.

Um vídeo demonstrativo do projeto pode ser visto em: https://youtu.be/pFeKg9TDjLc?feature=shared

## 🎯 Público-Alvo
O CommunityLink é voltado para:
- Organizações não governamentais (ONGs);
- Líderes comunitários;
- Voluntários e doadores;
- Empresas que desejam apoiar ações sociais;
- Público em geral interessado em contribuir com projetos sociais.

## 🔥 Principais Funcionalidades

### 🏷️ Gerenciamento de Usuários
- Criar conta e realizar login de forma segura;
- Recuperação de senha via e-mail;
- Edição de perfil e gerenciamento de informações pessoais;

### 📢 Divulgação de Projetos
- Criar e gerenciar projetos sociais;
- Editar informações de projetos existentes;
- Adicionar membros;

### 🎭 Ações e Eventos
- Criar eventos e ações sociais vinculadas a um projeto;
- Gerenciar ações como eventos e campanhas de arrecadação;
- Controle de doações realizadas para ações específicas.

### 💰 Doações
- Realizar doações diretas para projetos e ações;
- Confirmar recebimento de doações;
- Histórico de doações realizadas e recebidas.

### 🔎 Exploração e Busca
- Buscar projetos e ações sociais por palavras-chave;
- Descobrir eventos e campanhas ativas;
- Visualizar histórico de ações realizadas.

### Suporte a Inglês
- O Aplicativo foi desenvolvido utilizando as boas práticas do Android
- Uma opção para mudar o app para inglês é mostrada tanto na tela de login 
  quanto na tela de perfil

## 🛠️ Tecnologias Utilizadas
A plataforma foi desenvolvida utilizando:

### **Frontend e Backend**
- **Kotlin**: Linguagem principal para desenvolvimento da lógica e interação do app.
- **XML**: Definição de layout para a interface do usuário.

### **Banco de Dados e Backend Services**
- **Firebase**: Framework backend para autenticação e gerenciamento do banco de dados (Firestore).

### **Ferramentas de Desenvolvimento**
- **Android Studio**: IDE para desenvolvimento do aplicativo.
- **Gradle**: Gerenciador de pacotes e build automation.
- **GitHub**: Controle de versão e colaboração do código.

## 📱 Como Usar
1. **Criar uma conta** e faça login na plataforma.
2. **Explorar projetos e ações sociais** na tela principal (Dashboard).
3. **Criar um novo projeto** na aba perfil em meus projetos é possível criar um novo projeto.
4. **Cadastrar ações** dentro da pagina dos projetos é possível criar novas ações como eventos ou campanhas de doação.
5. **Gerenciar membros e ações** dentro de um projeto.
6. **Acompanhar o impacto social** de suas contribuições.

## 🏗️ Arquitetura do Sistema
O CommunityLink segue um modelo arquitetural baseado nos padrões **Camadas e Partições**:
- **Camada de Interface com o Usuário (CIU)**: Define a interação visual com os usuários (XML + ViewModel);
- **Camada de Lógica de Negócio (CLN)**: Implementação das regras do sistema;
- **Camada de Gerência de Dados (CGD)**: Persistência dos dados no banco de dados utilizando o padrão DAO (Data Access Object).

O padrão **MVVM (Model-View-ViewModel)** é utilizado para garantir a separação de responsabilidades entre interface e lógica de negócio.

## ✅ Requisitos Não Funcionais
- **Usabilidade**: Interface intuitiva e de fácil aprendizado.
- **Segurança**: Controle de acesso por perfis de usuário e criptografia de senhas utilizando o Firebase.
- **Portabilidade**: Compatível com dispositivos Android, garantindo desempenho fluido.

## 🚀 Contribuição e Desenvolvimento
Para contribuir com o desenvolvimento do CommunityLink:
1. Clone o repositório:
   ```sh
   git clone https://github.com/mobile-dev-ufes/2024-2-proj-final-community-link
   ```
2. Abra o projeto no Android Studio.
3. Configure as dependências utilizando Gradle.
4. Teste e implemente novas funcionalidades!

## 📄 Licença
Este projeto é de código aberto e pode ser utilizado para fins acadêmicos e sociais. Consulte os termos da licença para mais detalhes.

---
📧 **Contato:** Para dúvidas e sugestões, entre em contato com os desenvolvedores.

