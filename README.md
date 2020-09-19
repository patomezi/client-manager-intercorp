# client-manager-intercorp
Develop challenge. Developed by Mariano Mezi

## Assumptions:
-   Se decidió utilizar paths algo distintos a los indicados en el enunciado para respetar un poco más la convención rest.
-   Al ser la edad un input al igual que la fecha de nacimiento (lo que no debería, al ser la edad un valor calculado) se decidió incluir al menos una validación al momento de ingresar los datos, para evitar inconsistencias (fecha de nacimiento 1992 y edad 2 años por ejemplo)
-   Ante la falta de especificación en el enunciado, el algoritmo calculador de fecha de muerte provee fechas futuras y pasadas (para contemplar el caso de clientes que hayan sido cargados en el sistema hace mucho tiempo, y que posiblemente se encuentren muertos en la actualidad)
-   La fecha probable de muerte se decidió calcularla en cada consulta, para permitir agregar variables al algoritmo que pudiesen alterar la fecha probable de muerte (aunque en este caso solo depende de la fecha de nacimiento y el resultado será siempre el mismo)
-   Se utiliza una base h2 para la persistencia de los datos.
