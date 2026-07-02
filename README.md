# Fundamentos de Programacion

Recursos del curso de Fundamentos de Programacion 2026 de la Universidad Peruana Union.

Este espacio ahora presenta los silabos actualizados de Fundamentos de Programacion y sirve como base para reorganizar el material del curso alrededor de una aplicacion de consola desarrollada con programacion estructurada.

## Estructura

```text
262fp/
  market-cli-u1/       Producto Java de Unidad 1
  market-cli-u2/       Producto Java de Unidad 2
  market-cli-final/    Producto Java final del curso, equivalente a Unidad 3
  pymarket-cli-u1/     Producto Python de Unidad 1
  pymarket-cli-u2/     Producto Python de Unidad 2
  pymarket-cli-final/  Producto Python final del curso
  docs/              Silabos, portada del libro digital y material de sesiones
  mkdocs.yml         Configuracion del sitio
```

## Producto del curso

Aplicacion de consola para la gestion de una entidad de negocio, desarrollada mediante programacion estructurada, integrando estructuras secuenciales, condicionales, repetitivas, subprogramas, recursividad, estructuras de datos basicas y archivos para registrar, consultar, actualizar, eliminar, procesar y presentar informacion.

## Productos por unidad

Los productos siguen la misma idea progresiva usada en POO: cada unidad deja una version ejecutable del sistema y la Unidad 3 corresponde al producto final del curso.

| Carpeta Java | Carpeta Python | Unidad | Producto |
|---|---|---|---|
| `market-cli-u1` | `pymarket-cli-u1` | Unidad 1 | Menu basico de gestion CLI con registro, calculos y validaciones iniciales. |
| `market-cli-u2` | `pymarket-cli-u2` | Unidad 2 | Aplicacion CLI con CRUD modular, memoria, archivos basicos y consultas. |
| `market-cli-final` | `pymarket-cli-final` | Unidad 3 | Aplicacion CLI completa con persistencia, movimientos, consultas, matrices, diccionarios y reportes. |

Cada carpeta incluye su propio `README.md`. Los productos Java se ejecutan con `javac` y `java`:

```bash
cd market-cli-u1
javac App.java
java App
```

```bash
cd market-cli-u2
javac App.java
java App
```

```bash
cd market-cli-final
javac App.java
java App
```

Los productos Python equivalentes se ejecutan con:

```bash
cd pymarket-cli-u1
python app.py
```

```bash
cd pymarket-cli-u2
python app.py
```

```bash
cd pymarket-cli-final
python app.py
```

## Estado actual

1. Los silabos 2026-1 y 2026-2 ya fueron actualizados a Fundamentos de Programacion.
2. Los productos de Unidad 1, Unidad 2 y producto final ya estan organizados como aplicaciones CLI progresivas.
