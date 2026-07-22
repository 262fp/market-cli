# FUNDAMENTOS DE PROGRAMACIÓN 2026-2

## Implementación del curso

El sílabo expresa los contenidos de manera independiente del lenguaje. En este proyecto, las actividades, laboratorios y productos se implementan con **Java** mediante aplicaciones de consola.

## Producto del curso

**Proyecto Integrador Final: solución CLI desarrollada con Java para atender un problema real y acotado.**

El producto se construye a partir de dos portafolios progresivos y se consolida en una aplicación con menú repetitivo, validaciones, operaciones de gestión en memoria, búsqueda, ordenamiento, procesamiento de datos, carga inicial y reemplazo completo de datos mediante archivo. Las estructuras se incorporan cuando responden a una necesidad del problema, no únicamente para acumular tecnologías.

---

# UNIDAD 1 - Estructuras secuenciales y condicionales

### Resultado de aprendizaje

Diseña y construye algoritmos interactivos en consola que resuelven problemas de baja complejidad mediante el modelo Entrada-Proceso-Salida, variables, operaciones, estructuras condicionales y funciones, manteniendo un flujo lógico coherente.

### Producto de la unidad

**Portafolio de Soluciones Algorítmicas: de la secuencia a la toma de decisiones.**

El portafolio contiene programas Java organizados mediante métodos y distribuidos en tres niveles: soluciones secuenciales; condicionales simples y compuestas; y condicionales múltiples y anidadas. En esta unidad cada ejecución resuelve una operación; todavía no se implementan menús repetitivos.

| Sesión | Tema aplicado con Java | Producto de sesión |
|---|---|---|
| **S1** | **Algoritmos y fundamentos de Java:** pseudocódigo, modelo Entrada-Proceso-Salida, estructura de una aplicación de consola, método `main`, variables, constantes y tipos de datos primitivos. | Repositorio Java y primeros programas que capturan, procesan y muestran datos. |
| **S2** | **Programación secuencial con Java:** operadores aritméticos y de asignación, precedencia, expresiones aritméticas, conversión y parseo de tipos, entrada con `Scanner` y salida con `System.out`. | Programas secuenciales con cálculos, conversiones y comprobación de resultados. |
| **S3** | **Programación condicional con Java:** operadores relacionales (`==`, `!=`, `<`, `>`, `<=`, `>=`), operadores lógicos (`&&`, `||`, `!`), expresiones `boolean`, `if`, `if-else`, cadenas de decisiones, `switch` y condicionales anidadas. | Programas que validan datos y resuelven decisiones simples, compuestas, múltiples y anidadas. |
| **S4** | **Funciones y TDD con Java:** métodos con retorno y métodos `void`, parámetros y ámbito local, aplicados a problemas secuenciales y condicionales; pruebas con JUnit 5 y ciclo rojo–verde–refactorización. Java utiliza paso por valor, incluso cuando el valor transferido es una referencia. | Catálogo de soluciones Java organizado en métodos y con verificación automatizada de casos normales y límites. |
| **S5** | **Evaluación de la Unidad I.** | **Producto U1:** Portafolio de Soluciones Algorítmicas. |

---

# UNIDAD 2 - Estructuras repetitivas y operaciones algorítmicas

> **U2 = U1 + repetición + arreglos + operaciones en memoria + modularización**

### Resultado de aprendizaje

Descompone problemas en módulos funcionales y reutilizables, aplicando estructuras repetitivas, arreglos unidimensionales y transferencia de datos mediante parámetros y retornos para gestionar y procesar información en memoria y conservar su estado mediante persistencia básica.

### Producto de la unidad

**Portafolio de Soluciones Modulares y Procesamiento Iterativo de Datos.**

El portafolio reúne ejercicios de repetición y una aplicación CLI organizada en métodos Java, capaz de cargar los datos al iniciar y reemplazar el contenido completo del archivo con el estado de los arreglos. Su nivel avanzado constituye la base del Proyecto Integrador Final.

| Sesión | Tema aplicado con Java | Producto de sesión |
|---|---|---|
| **S6** | **Estructuras repetitivas definidas con Java:** sentencia `for`, índices, contadores, acumuladores, series y arreglos unidimensionales (`Tipo[]`). | Registro y listado de datos almacenados en un arreglo, además de ejercicios de series y acumulación. |
| **S7** | **Estructuras repetitivas condicionales con Java:** sentencias `while` y `do-while`, centinelas, validación con `Scanner`, condiciones de parada y menú repetitivo. | Menú de consola que valida las entradas y permite ejecutar repetidamente las opciones principales. |
| **S8** | **Ciclos anidados, recursividad y procesamiento acumulativo con Java:** algoritmos de búsqueda y ordenamiento; totales, promedios, máximos y mínimos; comparación funcional entre soluciones iterativas y recursivas. | Métodos de búsqueda, ordenamiento y procesamiento acumulativo verificados con JUnit 5, más un algoritmo recursivo guiado. |
| **S9** | **Operaciones con arreglos en Java:** consulta, actualización, eliminación lógica o desplazamiento, control de capacidad y duplicados, reutilizando los algoritmos desarrollados en S8. | Gestión de los datos almacenados en memoria con operaciones verificadas mediante pruebas. |
| **S10** | **Refactorización, optimización algorítmica y persistencia básica con Java:** cohesión y acoplamiento; análisis y mejora de recorridos, búsquedas y ordenamientos a partir de su complejidad; carga inicial mediante `Files.readAllLines()` y reemplazo completo mediante `Files.write()`. El CRUD continúa operando únicamente sobre los arreglos en memoria. | Aplicación refactorizada en métodos cohesivos, con una mejora algorítmica justificada y comprobada mediante pruebas, carga automática de datos al iniciar y una opción para guardar el estado completo de los arreglos. |
| **S11** | **Evaluación de la Unidad II.** | **Producto U2:** Portafolio de Soluciones Modulares y Procesamiento Iterativo de Datos. |

---

# UNIDAD 3 - Estructuras de datos e integración del producto

> **U3 = portafolios U1 y U2 + estructuras de datos pertinentes + Proyecto Integrador Final**

### Resultado de aprendizaje

Selecciona e integra estructuras de datos estáticas y dinámicas pertinentes para desarrollar, demostrar y sustentar una solución CLI funcional orientada a un problema real y acotado.

### Producto de la unidad

**Proyecto Integrador Final: solución CLI funcional en Java para un problema real y acotado.**

El estudiante puede atender una necesidad de una microempresa, actividad productiva, entorno universitario o comunidad. El proyecto conserva la carga inicial y el guardado completo desarrollados en U2, y debe emplear justificadamente arreglos multidimensionales o paralelos y al menos una estructura dinámica lineal. Árboles y grafos se demuestran en una práctica guiada y solo se integran al producto cuando aportan valor al caso.

| Sesión | Tema aplicado con Java | Producto de sesión |
|---|---|---|
| **S12** | **Estructuras estáticas con Java:** arreglos bidimensionales (`Tipo[][]`), tridimensionales (`Tipo[][][]`), multidimensionales y paralelos; índices, recorridos, capacidad, registro y listado. | Aplicación de arreglos bidimensionales o paralelos al proyecto y ejercicios guiados con dimensiones mayores. |
| **S13** | **Estructuras dinámicas lineales con Java:** `List`/`ArrayList`, `Queue` y `Deque`/`ArrayDeque` para representar listas, colas y pilas mediante operaciones básicas. | Evidencias de uso de listas, colas y pilas; integración justificada de al menos una estructura lineal al proyecto. |
| **S14** | **Estructuras dinámicas no lineales con Java:** representación introductoria de árboles y grafos, recorrido básico de un árbol y representación guiada de un grafo mediante estructuras proporcionadas o colecciones de Java. | Práctica guiada de árboles y grafos; integración al proyecto únicamente si el caso lo justifica. |
| **S15** | **Sustentación y demostración del Proyecto Integrador Final:** funcionamiento, decisiones algorítmicas, manejo de parámetros e índices, entradas inválidas, casos límite e impacto de la solución. | **Producto final:** aplicación CLI en Java demostrada y sustentada. |
| **S16** | **Evaluación final, sustentaciones y demostraciones pendientes.** | Evaluación individual y cierre de presentaciones pendientes. |

---

# Evolución del producto

## Unidad 1

- Repositorio de soluciones Java organizado por niveles.
- Entrada, proceso y salida.
- Cálculos y conversiones.
- Expresiones booleanas y decisiones.
- Métodos Java con parámetros y retornos.
- Pruebas con JUnit 5 y aplicación introductoria de TDD.
- Validación de resultados.

## Unidad 2

- Repeticiones definidas y condicionales implementadas con `for`, `while` y `do-while`.
- Arreglos unidimensionales.
- Menú interactivo con validaciones.
- Repeticiones anidadas y procesamiento acumulativo.
- Registro, consulta, actualización y eliminación en memoria.
- Búsqueda, control de duplicados y ordenamiento básico.
- Análisis de complejidad y mejora algorítmica justificada aplicada a búsqueda y ordenamiento.
- Recursividad mediante un ejercicio guiado.
- Carga inicial y reemplazo completo de datos en un archivo, sin CRUD directo sobre este.

## Unidad 3

- Arreglos multidimensionales o paralelos aplicados de forma justificada.
- Uso de al menos una estructura dinámica lineal de Java.
- Prácticas guiadas de árboles y grafos.
- Integración y estabilización del Proyecto Integrador Final.
- Demostración técnica y sustentación del impacto.
