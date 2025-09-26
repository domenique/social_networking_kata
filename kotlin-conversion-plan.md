# Java to Kotlin Conversion Plan

## Current State Analysis
- **70 Java files** total (41 main, 24 test, 5 cucumber)
- **Clean architecture** with domain/application/infrastructure/presentation layers
- **Dependencies**: JUnit 5, Hamcrest, Cucumber
- **Build**: Gradle with Java plugin

## Conversion Strategy

### Phase 1: Build Configuration (1-2 hours)
1. **Update build.gradle**:
   - Replace `apply plugin: 'java'` with `apply plugin: 'kotlin'`
   - Add Kotlin stdlib dependency
   - Add kotlin-gradle-plugin
   - Configure Kotlin compilation options
   - Update JUnit to work with Kotlin

2. **Update directory structure**:
   - Keep existing `src/main/java` → `src/main/kotlin`
   - Keep existing `src/test/java` → `src/test/kotlin`
   - Keep existing `src/cucumber/java` → `src/cucumber/kotlin`

### Phase 2: Core Domain Conversion (3-4 hours)
1. **Convert domain objects first** (value objects are easiest):
   - `UserName` → data class with validation
   - `Message` → data class with comparison logic
   - `FollowingRelationship` → data class
   - Domain interfaces (`Messages`, `Relationships`, etc.)

2. **Convert domain services**:
   - `SocialNetwork` → leverage Kotlin features (nullable types, collections)
   - `MessagePrinter` → convert to sealed class or object

### Phase 3: Application Layer (2-3 hours)
1. **Convert use cases**:
   - Leverage Kotlin's concise syntax
   - Use extension functions where appropriate
   - Convert to more functional style with lambda expressions

### Phase 4: Infrastructure Layer (2-3 hours)
1. **Convert repositories**:
   - `InMemoryMessages` → use Kotlin collections
   - `InMemoryRelationships` → use Kotlin collections
   - Simplify with Kotlin's collection operations

### Phase 5: Presentation Layer (3-4 hours)
1. **Convert controllers and REPL**:
   - Leverage Kotlin's when expressions
   - Use sealed classes for request types
   - Convert visitor pattern to more idiomatic Kotlin

### Phase 6: Testing Migration (2-3 hours)
1. **Update test framework**:
   - Migrate to Kotlin Test or keep JUnit 5 with Kotlin
   - Convert test doubles and utilities
   - Update Cucumber step definitions for Kotlin

### Phase 7: Final Integration (1-2 hours)
1. **Validate conversion**:
   - Run all tests
   - Verify application still works
   - Update CLAUDE.md with Kotlin specifics

## Key Kotlin Improvements Expected
- **Data classes** for domain objects (eliminate boilerplate)
- **Null safety** throughout the codebase
- **Extension functions** for domain operations
- **Sealed classes** for request/command types
- **Collection operations** to replace Java streams
- **When expressions** instead of if-else chains
- **String templates** for message formatting

## Estimated Total Time: 15-20 hours
## Risk Level: Medium (well-structured codebase makes conversion straightforward)

## Before You Start
1. Create a new branch: `git checkout -b kotlin-conversion`
2. Ensure all tests pass: `./gradlew test`
3. Consider doing conversion incrementally with mixed Java/Kotlin compilation