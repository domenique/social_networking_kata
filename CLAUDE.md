# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview
This is a Java implementation of a Social Networking Kata (similar to Twitter) built as a console-based application. The codebase implements a clean architecture with hexagonal patterns, featuring posting messages, reading timelines, following users, and viewing aggregated walls.

## Build and Development Commands

### Build and Test
- **Build project**: `./gradlew build`
- **Run tests only**: `./gradlew test`
- **Run application**: `./gradlew run` (if configured) or compile and run `io.tripled.social.client.SocialNetworkApplication`

### Gradle Wrapper
- Use `./gradlew` on Unix/Mac or `gradlew.bat` on Windows
- Current Gradle version: 9.1.0

## Architecture Overview

### Package Structure
The codebase follows hexagonal/clean architecture principles:

- **Domain Layer** (`domain/`): Core business logic including `SocialNetwork`, `Message`, `UserName`, domain services
- **Application Layer** (`application/`): Use cases implementing business operations (Post, Read, Follow, ReadWall)
- **Infrastructure Layer** (`infrastructure/`): In-memory repositories and system adapters
- **Presentation Layer** (`presentation/`): Console-based REPL, controllers, and CLI request handling

### Key Components
- **SocialNetworkApplication**: Main entry point that wires up dependencies
- **ReadEvalPrintLoop**: Console-based REPL for user interaction
- **Controllers**: Handle specific command types (Post, Read, Follow, Wall)
- **Use Cases**: Implement business logic (DefaultPostMessageUseCase, DefaultReadMessagesUseCase, etc.)
- **Repository Pattern**: `SocialNetworkRepository` with in-memory implementation

### Dependency Injection
Manual dependency injection in `SocialNetworkApplication.createRepl()` method - all dependencies are wired together here.

## Testing Framework
- **Unit Tests**: JUnit Jupiter (JUnit 5) with Hamcrest matchers
- **Integration Tests**: Cucumber for BDD scenarios in `src/cucumber/`
- **Test Structure**: Follows same package structure as main code
- **Test Utilities**: Custom test doubles and spies in test packages

## Command Line Interface
The application accepts these command patterns:
- **Posting**: `<username> -> <message>`
- **Reading**: `<username>`
- **Following**: `<username> follows <another_username>`
- **Wall**: `<username> wall`

## Development Notes
- Uses Java 10+ (configured in `.travis.yml`)
- In-memory storage only (no persistence layer)
- Thread-based application startup
- Visitor pattern for request dispatching
- Custom message formatting with time-relative display