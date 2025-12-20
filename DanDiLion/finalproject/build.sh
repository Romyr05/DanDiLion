#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"
mkdir -p out/classes

GSON_JAR="brainventory/gson-2.10.1.jar"

echo "Compiling Java sources to out/classes (classpath: $GSON_JAR)"
javac -cp "$GSON_JAR" -encoding UTF-8 -d out/classes $(find ./src/main/java -name "*.java")

echo "✓ Compilation finished. Classes in out/classes"
