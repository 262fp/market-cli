# Instalación de GraalVM 21 y Native Image

Esta guía permite instalar **GraalVM 21** para compilar programas Java como ejecutables nativos usando `native-image`.

Para el curso, este paso es opcional. Sirve para convertir `market-cli-final` en un `.exe` en Windows o en un ejecutable nativo en Linux/macOS.

## 1. Instalar GraalVM 21

### Windows con Chocolatey

Abre PowerShell como administrador:

```powershell
choco install graalvm-jdk21 -y
```

Cierra y vuelve a abrir PowerShell.

### Windows con descarga manual

También puedes descargar GraalVM 21 desde:

<https://www.graalvm.org/downloads/>

Luego configura variables de entorno:

```powershell
$env:JAVA_HOME="C:\ruta\a\graalvm-jdk-21"
$env:GRAALVM_HOME=$env:JAVA_HOME
$env:Path="$env:JAVA_HOME\bin;$env:Path"
```

Para dejarlo permanente, agrega `JAVA_HOME`, `GRAALVM_HOME` y `%JAVA_HOME%\bin` en las variables de entorno de Windows.

### macOS con Homebrew

```bash
brew install --cask graalvm-jdk@21
```

Luego verifica o configura `JAVA_HOME` según la ruta instalada por Homebrew.

### Linux

Descarga GraalVM 21 desde:

<https://www.graalvm.org/downloads/>

Descomprime y configura:

```bash
export JAVA_HOME=/ruta/a/graalvm-jdk-21
export GRAALVM_HOME=$JAVA_HOME
export PATH=$JAVA_HOME/bin:$PATH
```

Puedes agregar esas líneas a `~/.bashrc` o `~/.zshrc`.

## 2. Verificar GraalVM

```bash
java -version
native-image --version
```

En Windows también puedes probar:

```powershell
native-image.cmd --version
```

Resultado esperado:

```text
GraalVM
Native Image
versión 21
```

## 3. Instalar Visual Studio Build Tools en Windows

Visual Studio Build Tools **no viene instalado por defecto** en Windows. Para generar ejecutables nativos, `native-image.cmd` necesita un compilador C/C++.

Descarga **Build Tools for Visual Studio 2022** o superior desde:

<https://visualstudio.microsoft.com/downloads/>

En el instalador selecciona la carga:

```text
Desktop development with C++
```

Verifica que incluya:

- MSVC.
- Windows 10/11 SDK.
- C++ CMake tools for Windows.
- Herramientas C++ para x64.

Finaliza la instalación y reinicia la terminal.

## 4. Abrir la terminal correcta en Windows

Después de instalar Visual Studio Build Tools, abre:

```text
x64 Native Tools Command Prompt for VS 2022
```

Desde esa terminal debes ejecutar los comandos de compilación nativa.

!!! warning "No usar PowerShell para native-image en Windows"
    Para generar el ejecutable nativo con `native-image.cmd`, no uses PowerShell normal ni PowerShell como administrador. Aunque PowerShell esté abierto como administrador, normalmente no carga las variables del compilador C/C++ que necesita GraalVM.

    Usa **x64 Native Tools Command Prompt for VS 2022**. Esa terminal ya carga `cl.exe`, MSVC y el SDK de Windows.

Verifica que el compilador C++ esté disponible:

```bat
where cl
cl
```

La ruta esperada debe contener algo parecido a:

```text
VC\Tools\MSVC\...\bin\Hostx64\x64\cl.exe
```

Si `where cl` no muestra nada, no continúes con `native-image.cmd`. Abre la terminal correcta: **x64 Native Tools Command Prompt for VS 2022**.

## 5. Generar ejecutable nativo del producto final

Desde la carpeta del producto:

```bat
cd C:\262\262fp\market-cli\market-cli-final
javac App.java
native-image.cmd -cp . App market-cli-final
```

Ejecutar:

```bat
market-cli-final.exe
```

## 6. Generar ejecutable nativo en macOS/Linux

```bash
cd market-cli-final
javac App.java
native-image -cp . App market-cli-final
./market-cli-final
```

## 7. Problemas frecuentes

### `native-image.cmd` no se reconoce

GraalVM no está en el `Path` o no está activo como `JAVA_HOME`.

Verifica:

```powershell
java -version
where java
where native-image.cmd
```

### Error relacionado con compilador C/C++

Falta instalar Visual Studio Build Tools o no estás usando la terminal **x64 Native Tools Command Prompt for VS 2022**.

Recuerda: PowerShell no es suficiente para este paso, ni siquiera como administrador.

### El ejecutable no se genera

Revisa primero que el programa compile y ejecute normalmente:

```bash
javac App.java
java App
```

Si eso falla, corrige primero el código antes de usar `native-image`.
