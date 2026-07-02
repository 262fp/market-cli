# Instalación de Java 21

Esta guía permite instalar o verificar **Java 21 JDK** para compilar y ejecutar los productos Java del curso.

## 1. Instalar gestor de paquetes

### Windows

Si trabajas en Windows y no tienes Chocolatey, abre **PowerShell como administrador** y ejecuta:

```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

Luego cierra y vuelve a abrir PowerShell.

Si no vas a usar Chocolatey, también puedes descargar Java 21 directamente desde:

<https://adoptium.net>

### macOS

Si trabajas en macOS y no tienes Homebrew, abre Terminal y ejecuta:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

Luego cierra y vuelve a abrir Terminal.

## 2. Instalar Java 21

### Windows con Chocolatey

```powershell
choco install temurin21 -y
```

### macOS con Homebrew

```bash
brew install --cask temurin@21
```

### Linux Debian/Ubuntu

```bash
sudo apt update
sudo apt install -y openjdk-21-jdk
```

Si tu distribución no incluye `openjdk-21-jdk`, puedes instalar Temurin 21 desde Adoptium:

<https://adoptium.net/temurin/releases/?version=21>

## 3. Verificar instalación

En PowerShell, Terminal de macOS o bash en Linux:

```bash
java -version
javac -version
```

Resultado esperado:

```text
versión 21
javac 21
```

En algunas instalaciones la salida puede verse así:

```text
openjdk version "21.x.x"
javac 21.x.x
```

## 4. Compilar un producto del curso

Desde la carpeta del producto:

```bash
cd market-cli-u1
javac App.java
java App
```

Para Unidad 2:

```bash
cd market-cli-u2
javac App.java
java App
```

Para el producto final:

```bash
cd market-cli-final
javac App.java
java App
```

## 5. Problemas frecuentes

### `javac` no se reconoce

Verifica que instalaste el **JDK**, no sólo el JRE.

En Windows, cierra y vuelve a abrir PowerShell después de instalar Java.

### Aparece Java 17 u otra versión

Puede haber otra versión primero en el `Path`.

Verifica en Windows:

```powershell
where java
where javac
```

En macOS/Linux:

```bash
which java
which javac
```

Configura `JAVA_HOME` para apuntar a Java 21 si fuera necesario.
