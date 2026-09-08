# Heli

A minimal Helidon SE service.

## Requirements

- GraalVM JDK 25
- `native-image`

## Run on the JVM

```bash
./gradlew run
curl http://localhost:8080/
```

Expected response:

```text
Hello World!
```

## Build a native executable

```bash
./gradlew nativeCompile
./build/native/nativeCompile/helidon-hello
```

## Test

```bash
./gradlew test
```
