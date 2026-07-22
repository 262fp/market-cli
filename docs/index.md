# Fundamentos de Programación 2026-2

## Implementación vigente

El sílabo expresa los contenidos sin depender de un lenguaje. Durante esta etapa, las sesiones, prácticas y productos se implementan con **Python 3** en aplicaciones de consola. La migración didáctica a **Java 21** se realizará posteriormente y no condiciona las actividades actuales.

## Producto del curso

**Proyecto Integrador Final: solución CLI para un problema real y acotado.**

El producto evoluciona desde soluciones algorítmicas pequeñas hasta una aplicación que administra datos en memoria, ejecuta operaciones de búsqueda y ordenamiento, carga una instantánea al iniciar y reemplaza el archivo completo cuando el usuario decide guardar. Las estructuras de datos se incorporan sólo cuando responden a una necesidad del problema.

---

## Unidad I. Estructuras secuenciales y condicionales

**Producto:** Portafolio de Soluciones Algorítmicas: de la secuencia a la toma de decisiones.

| Sesión | Aplicación con Python | Evidencia |
|---|---|---|
| **S1** | Algoritmos, pseudocódigo, modelo EPS, variables y tipos de datos. | Algoritmo documentado y primer programa Python. |
| **S2** | Programación secuencial: operadores aritméticos y de asignación, expresiones, precedencia, conversión, `input` y `print`. | Programa secuencial con cálculos y conversiones. |
| **S3** | Programación condicional: operadores relacionales y lógicos, expresiones booleanas, `if`, `elif`, decisiones simples, compuestas, múltiples y anidadas. | Programa que aplica y valida reglas. |
| **S4** | Funciones, parámetros, retornos y ámbito; TDD mediante pruebas pequeñas con `assert` o `unittest`. | Soluciones secuenciales y condicionales organizadas en funciones. |
| **S5** | Evaluación de la Unidad I. | Portafolio funcional de la unidad. |

En esta unidad cada ejecución resuelve una operación. Los menús repetitivos y el CRUD todavía no forman parte del producto.

---

## Unidad II. Estructuras repetitivas y operaciones algorítmicas

**Producto:** Portafolio de Soluciones Modulares y Procesamiento Iterativo de Datos.

| Sesión | Aplicación con Python | Evidencia |
|---|---|---|
| **S6** | Ciclo `for`, contadores, acumuladores, series y listas como arreglos unidimensionales. | Recorrido y resumen de una colección. |
| **S7** | Ciclo `while`, centinelas, validación y menú repetitivo. El patrón `while True` con `break` representa el comportamiento de ejecución previa. | Menú que registra y lista datos en memoria. |
| **S8** | Ciclos anidados, búsqueda, ordenamiento, totales, promedios, máximos, mínimos y recursividad. | Funciones algorítmicas y solución recursiva guiada. |
| **S9** | Consulta, registro, actualización, eliminación y control de duplicados sobre listas. | CRUD completo en memoria. |
| **S10** | Cohesión, acoplamiento, complejidad introductoria, refactorización y persistencia básica mediante archivos de texto o CSV. | Aplicación refactorizada que carga al iniciar, opera en memoria y reemplaza la instantánea al guardar. |
| **S11** | Evaluación de la Unidad II. | Portafolio integrado y demostrable. |

La persistencia no constituye otro CRUD: el programa carga los datos, trabaja sobre las listas en memoria y guarda una nueva instantánea completa.

---

## Unidad III. Estructuras de datos e integración del producto

**Producto:** Proyecto Integrador Final: solución CLI funcional, documentada y sustentada.

| Sesión | Aplicación con Python | Evidencia |
|---|---|---|
| **S12** | Listas anidadas bidimensionales, tridimensionales, multidimensionales y arreglos paralelos. | Procesamiento de datos estáticos y tabulares. |
| **S13** | Listas, pilas y colas; uso de `list` y `collections.deque`. | Prototipo con estructuras dinámicas lineales. |
| **S14** | Árboles y grafos representados con listas y diccionarios; recorridos introductorios DFS y BFS. | Práctica guiada de estructuras no lineales. |
| **S15** | Sustentación y demostración del Proyecto Integrador Final. | Proyecto CLI ejecutable y defendido técnicamente. |
| **S16** | Evaluación final y sustentaciones pendientes. | Evidencia individual final. |

Los árboles y grafos se trabajan de forma guiada. Sólo se integran al proyecto cuando aportan valor real al caso; no se exige utilizarlos artificialmente.
