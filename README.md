# Juego isla (Zork) 2020

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
###### Idea
- Podria hacer un llamado a "chequearFinal" en el juego por cada item que se agregue o por cada cambio de ubicación.  

### Manager
Contendrá el manejo del juego y administración de datos y entren y salgan del mismo. También tendrá un manejador de palabras similares para dar comandos al jugador.

#### Game
Contiene todas las ubicaciones y los datos del jugador para el mapa que se haya cargado. Al cargar el mapa o "juego" esta clase vinculará las ubicaciones con sus respectivos accesos por como vienen de la fuente en formato json.

#### GameManager
#### WordManager

### States
#### Dead
#### Lost
#### Normal
#### NPCNormal
#### State

### Tools
#### DamageType
#### GameObjectDeserializer
#### Gender
#### IdManager
#### ItemDeserializer
#### ItemType
#### MessageType
#### NPCType
#### ObjectType
#### WordBuilder
#### WorldLoader
#### EndCondition