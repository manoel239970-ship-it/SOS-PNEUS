# SOS Pneus DMO

Aplicativo Android para suporte rápido e prático para motoristas com pneu furado, permitindo seguir instruções passo a passo ou solicitar ajuda profissional próxima, mesmo sem internet.

## 📱 Características

- **Offline-First**: Funciona completamente offline
- **Tutorial Passo a Passo**: Guia visual e textual para troca de pneu
- **Emergência**: Solicitação de ajuda via ligação ou mensagem
- **Contatos Favoritos**: Cadastro e gerenciamento de borracheiros
- **GPS Nativo**: Localização funciona offline
- **Interface Simples**: Design intuitivo para uso em emergências

## 🏗️ Arquitetura

- **MVVM** (Model-View-ViewModel)
- **Clean Architecture**
- **Jetpack Compose** para UI
- **Room Database** para armazenamento local
- **StateFlow** para gerenciamento de estado
- **Coroutines** para operações assíncronas

## 📦 Estrutura do Projeto

```
app/src/main/java/com/example/sospneus/
├── data/
│   ├── dao/              # Interfaces Room DAO
│   ├── database/         # Configuração do Room Database
│   ├── entity/           # Entidades Room
│   ├── mapper/           # Mappers Entity <-> Model
│   ├── model/            # Modelos de dados
│   └── repository/       # Implementações dos repositories
├── domain/
│   └── repository/       # Interfaces dos repositories
├── di/                   # Injeção de dependências
├── ui/
│   ├── navigation/       # Navegação
│   ├── screens/          # Telas Compose
│   └── theme/            # Tema e cores
├── utils/                # Utilitários (NetworkUtils, GPSHelper)
└── viewmodel/            # ViewModels
```

## 🚀 Funcionalidades

### MVP (Funcionalidades Obrigatórias)

1. ✅ **Passo a passo de troca de pneu** (RF01)
2. ✅ **Solicitação de ajuda emergencial** (RF03)
3. ✅ **Contatos favoritos** (RF04)

### Funcionalidades Desejáveis

- ⚠️ **Localização de borracheiros próximos** (RF02) - Estrutura criada, integração com Google Maps pendente

## 🛠️ Tecnologias

- **Kotlin**
- **Jetpack Compose**
- **Room Database**
- **ViewModel + StateFlow**
- **Navigation Compose**
- **Google Play Services Location**
- **Coroutines**

## 📋 Requisitos

- Android 10 (API 24) ou superior
- Permissões:
  - `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION`
  - `INTERNET` (opcional, para funcionalidades online)
  - `CALL_PHONE` (para ligações)
  - `SEND_SMS` (para mensagens)

## 🎨 Tema

O aplicativo usa um tema personalizado com cores **vermelho** e **cinza** em fundo branco, otimizado para uso em emergências.

## 📝 Notas

- O aplicativo funciona completamente offline
- Os dados do tutorial são carregados localmente
- GPS funciona nativamente sem necessidade de internet
- Contatos favoritos são armazenados localmente no Room Database

## 🔧 Configuração

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize as dependências do Gradle
4. Execute o aplicativo

## 📄 Licença

Este projeto foi desenvolvido como parte do MVP do aplicativo SOS Pneus DMO.

