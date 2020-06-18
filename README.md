## Introduccion


## Historia seleccionada


## Componentes del proyecto

### Island
Paquete que tiene los principales componentes del proyecto. 
#### GameObject
Tiene los atributos en común por la mayoría de los elementos del juego. Contiene atributos como nombre, descripción y el género del nombre que se quiera utilizar, puramente para ser utilizado en idiomas que lo requieran. 

### Entities
Paquete que contiene lo relacionado con la interacción entre personajes o elementos que lo requieran. También estos tendrán las acciones que sean necesarias y suficientes para este proyecto.
#### Entity
Descripción general de una "persona" o elemento con el que se pueda interactuar en el juego. Esta tendrá razgos como vida, debilidades y fortalezas y las acciones que tienen todas las entidades en común. A su vez, una entidad tiene un estado con el que se modificarán comportamientos de la misma.
#### Attack
El ataque en si esta compuesto por un tipo de daño y un valor de daño, el cual se enviará de entidad a entidad cuando se provoque. Sólamente funciona como un elemento de datos.
#### NPC
Las entidades con las que podrá interactuar el jugador.
- Cuando mueren dropean los items que tengan
- Estando muertos pueden darle items al personaje si este los revisa (Ver)

#### UserCharacter
El personaje utilizado por el jugador, este tiene todas las acciones que sean necesarias en el juego y hará algún llamado para que finalice el juego. 

### Manager
Contendrá el manejo del juego y administración de datos y entren y salgan del mismo. También tendrá un manejador de palabras similares para dar comandos al jugador.

#### Game
Contiene todas las ubicaciones y los datos del jugador para el mapa que se haya cargado. Al cargar el mapa o "juego" esta clase vinculará las ubicaciones con sus respectivos accesos por como vienen de la fuente en formato json.

#### GameManager
Centro de control del juego, de aquí se recibiran mensajes para el jugador y se recibirán los mensajes con respecto a las distintas entidades del juego. Tiene un historial de mensajes recibidos y los mensajes se separarán en tres tipos: Historia, evento y personaje.

#### WordManager
Básicamente es una lista de verbos vinculados al sinónimo correspondiente, para hacer más facil la búsqueda de comandos del jugador. 

### States
Lugar donde se almacenan los estados, cada uno tiene algo en particular excepto "Dead" porque bueno está muerto.
#### State
Interfaz que contiene todos los métodos en comun entre todos los estados de una entidad.
#### Dead
F
#### Lost
La entidad que esté perdida podra hacer todo menos cambiar de ubicación.
#### Normal
Puede hacer lo que quiera mientras sea posible.
#### NPCNormal
Limitaciones para el npc, hay métodos que no utiliza y otros que tienen distinta implementación.

### Tools
Esta sección tendra enumeradores, herramientas de entrada y salida y alguna que otra clase que tal vez no entraba en otros paquetes.

#### DamageType
Enumerador con los tipos de daño que puede causar algun evnto.
#### GameObjectDeserializer
Deserializador de JSON, permite que se descarguen los elementos de un juego correctamente.(Ampliar)
#### Gender
Género, aplicado por requerimiento en el idioma español. Este tendrá los valores que le sean compatible al objeto utilizado. Por ejemplo:
Género femenino
- La
- Una
- terminación a

#### IdManager
Sólamente un contador que tiene utilidad en ejecuciones de pruebas internas, en lo externo se le deberá asignar numeros manualmente (JSON)
#### ItemDeserializer
Deserializador de JSON, permite que se descarguen los elementos de un juego correctamente.(Ampliar)
#### ItemType
Tipo de item que esté en el mundo. Puede ser que el item utilizado no corresponda a una herencia en particular y se necesita sólamente como llave, entonces por ejemplo se puede decir que ese item (base) es de tipo llave.
#### MessageType
El tipo de mensaje es algo que se utiliza sólamente en gameManager para enviar y recibir mensajes dentro del juego. Estos harán que se escriba de distintas maneras los mismos.
#### NPCType
El tipo de npc está relacionado a cómo actuará ante un ataque una entidad:
- Agresivo
- Pasivo
- In (Algo...No me acuerdo el nombre pero que no hacia nada tampoco)

#### ObjectType
Tipo de objeto es algo muy util para el uso de JSON, cuando se lee cada elemento del archivo del mapa cada uno tiene un atributo, el cual corresponderá a una clase determinada. Con esto se podrán instanciar clases sin ningún problema.

#### WordBuilder
Carga del mapa o lista de verbos a utilizar por el jugador
#### WorldLoader
Carga del mapa del juego, incluídos NPCs y jugador.

#### EndCondition
(Sin terminar) algo que este escuchando pero que no sea un listener exactamente. Todavía no se me ocurre como podría ser

##### Ideas
- UserCharacter: Podria hacer un llamado a "chequearFinal" en el juego por cada item que se agregue o por cada cambio de ubicación. 
