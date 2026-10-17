# La Fonda del Papu — Taller 02

Programa en Java para registrar el consumo de choripanes y terremotos. Permite consultar, agregar, editar y eliminar registros, calcular métricas y guardar los cambios.

## Integrantes

| Nombre | RUT | Carrera | GitHub |
| --- | --- | --- | --- |
| Ma. Sofía Escalona | 26.965.672-7 | ITI | [sofiphobic](https://github.com/sofiphobic) |
| Priscilla Álvarez Ugarte | 20.988.062-8 | ITI | [Apriscilla-lab](https://github.com/Apriscilla-lab) |

## Estructura del proyecto

- `src/Main.java`: menú e ingreso de datos por consola.
- `src/GestorFonda.java`: carga, administración, métricas y guardado.
- `src/Participante.java`: información de un participante.
- `src/Choripan.java`: consumidor, fecha, medidas y cálculo de KCal.
- `src/Terremoto.java`: consumidor, fecha, litros y cálculo de KCal.
- `Participantes.txt`: nombres de los participantes.
- `Consumido.txt`: registros de consumo.

El diagrama de clases se entrega en PDF junto al proyecto.

## Cómo ejecutar

1. Tener Java 21 y Eclipse con soporte para Java.
2. Descargar y descomprimir el repositorio.
3. En Eclipse, seleccionar File > Import > General > Existing Projects into Workspace.
4. Seleccionar la carpeta que contiene el archivo `.project` y finalizar.
5. Mantener `Participantes.txt` y `Consumido.txt` en la raíz del proyecto, junto a la carpeta `src`.
6. Abrir `Main.java` y seleccionar Run As > Java Application.
7. Ingresar las opciones en la consola y presionar Enter.

## Menú principal

1. Ver registros.
2. Actualizar registros.
3. Calcular métricas.
4. Salir.

La actualización permite agregar, editar y eliminar registros. Para eliminar se solicita confirmación. Al editar se ingresan todos los datos nuevos.

Cada cambio aceptado se guarda en `Consumido.txt`. Si falla el guardado, se informa y se permite reintentar.

## Métricas

1. Total de choripanes.
2. Cantidad de terremotos y litros totales.
3. Choripanes por usuario.
4. Terremotos y litros por usuario.
5. KCal por usuario.
6. Días ebrio por usuario.

Los cálculos utilizados son:

- Choripán: `(largo * ancho) / 3.0`.
- Terremoto: `(litros * 10) / 3.0`.
- Día ebrio: una fecha con al menos 2 litros acumulados por participante. Cada fecha se cuenta una sola vez.

## Formato de los archivos

`Participantes.txt` contiene un nombre por línea.

`Consumido.txt` utiliza punto y coma como separador:

```text
Choripan;Koshao;9;4;2026-09-16
Terremoto;MrNacho;0.5;2026-09-16
```

Las fechas se ingresan como `AAAA-MM-DD` y los decimales con punto. Los consumidores se eligen desde la lista de participantes.

Se utilizan arreglos fijos con capacidad para 200 participantes y un máximo combinado de 200 consumos.
