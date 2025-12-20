#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

GSON_JAR="brainventory/gson-2.10.1.jar"

if [ ! -d out/classes ]; then
  echo "No compiled classes found. Run ./build.sh first." >&2
  exit 1
fi

echo "Starting DanDiLion Cellphone Simulator..."
java -cp out/classes:$GSON_JAR finalproject.Cellphone
