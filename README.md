# Fundamentos de Programación

Material del curso de Fundamentos de Programación 2026-2 de la Universidad Peruana Unión.

## Implementación vigente

Las 16 guías de sesión y los productos de referencia activos trabajan con **Python 3**. El sílabo mantiene los temas independientes del lenguaje. La adaptación a **Java 21** se realizará en una etapa posterior; las carpetas Java existentes se conservan como material de transición.

## Estructura

```text
market-cli/
  docs/                Sílabo, sesiones y documentación del proyecto
  pymarket-cli-u1/     Referencia Python para la Unidad I
  pymarket-cli-u2/     Referencia Python para la Unidad II
  pymarket-cli-final/  Referencia Python para el producto final
  market-cli-u1/       Material Java reservado para la transición
  market-cli-u2/       Material Java reservado para la transición
  market-cli-final/    Material Java reservado para la transición
  mkdocs.yml           Navegación del libro digital
```

## Progresión

- **Unidad I:** algoritmos, programación secuencial y condicional, funciones y TDD introductorio.
- **Unidad II:** ciclos, listas, operaciones algorítmicas, CRUD en memoria, refactorización e instantánea de datos.
- **Unidad III:** estructuras estáticas y dinámicas, integración y sustentación del Proyecto Integrador Final.

La aplicación carga datos al iniciar, trabaja en memoria y reemplaza la instantánea completa cuando se guarda. No se plantea un CRUD directo sobre archivos.

## Ejecutar una referencia Python

```bash
cd pymarket-cli-u2
python app.py
```

La documentación detallada se publica mediante MkDocs a partir de [docs/index.md](docs/index.md).
