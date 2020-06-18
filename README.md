# Juego isla (Zork) 2020

##Introduccion


##Historia seleccionada


##Componentes del proyecto

###Island
Paquete que tiene los principales componentes del proyecto. 
####GameObject
Tiene los atributos en común por la mayoría de los elementos del juego. Contiene atributos como nombre, descripción y el género del nombre que se quiera utilizar, puramente para ser utilizado en idiomas que lo requieran. 

###Entities
Paquete que contiene lo relacionado con la interacción entre personajes o elementos que lo requieran. También estos tendrán las acciones que sean necesarias y suficientes para este proyecto.
####Entity
Descripción general de una "persona" o elemento con el que se pueda interactuar en el juego. Esta tendrá razgos como vida, debilidades y fortalezas y las acciones que tienen todas las entidades en común. A su vez, una entidad tiene un estado con el que se modificarán comportamientos de la misma.
####Attack
El ataque en si esta compuesto por un tipo de daño y un valor de daño, el cual se enviará de entidad a entidad cuando se provoque. Sólamente funciona como un elemento de datos.
####NPC
Las entidades con las que podrá interactuar el jugador, estas pueden llegar a desbloquear algun acceso
####UserCharacter

###Manager
####Game
####GameManager
####WordManager

###States
####Dead
####Lost
####Normal
####NPCNormal
####State

###Tools
####DamageType
####GameObjectDeserializer
####Gender
####IdManager
####ItemDeserializer
####ItemType
####MessageType
####NPCType
####ObjectType
####WordBuilder
####WorldLoader
####EndCondition
