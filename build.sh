#!/bin/bash

# Configuration des chemins
SRC_DIR="/home/ndroso/Documents/Study/ITU/L2/Work-S4/TP_S4/Web dynamique/06-framework/S4_framework/src"
BIN_DIR="/home/ndroso/Documents/Study/ITU/L2/Work-S4/TP_S4/Web dynamique/06-framework/S4_framework/bin"
LIB_DIR="/home/ndroso/Documents/Study/ITU/L2/Work-S4/TP_S4/Web dynamique/06-framework/S4_framework/lib"
JAR_OUT="/home/ndroso/Documents/Study/ITU/L2/Work-S4/TP_S4/Web dynamique/06-framework/S4_framework/itFramework.jar"

echo "=== Nettoyage du dossier bin ==="
rm -rf "$BIN_DIR"/*
mkdir -p "$BIN_DIR"

echo "=== Compilation du FrontControllerServlet ==="
# On inclut le servlet-api.jar dans le classpath (-cp) pour la compilation
javac -cp "$LIB_DIR/*" -d "$BIN_DIR" "$SRC_DIR"/com/dev/itFramework/FrontControllerServlet.java

if [ $? -eq 0 ]; then
    echo "Compilation réussie."
    echo "=== Création du fichier .jar ==="
    jar -cvf "$JAR_OUT" -C "$BIN_DIR" .
    echo "Succès ! Le framework est prêt : $JAR_OUT"
else
    echo "Erreur lors de la compilation."
fi
