# ingswAM2021-Bachir-LaManna-Maranelli
## Maestri del Rinascimento - AM66
### Software engineering final project 2021 - Politecnico di Milano

![alt text](https://github.com/alessandromaranelli/ingswAM2021-Bachir-LaManna-Maranelli/blob/master/blob/copertina.jpg)

## Description:
Masters of Renaissance Board Game
Masters of Renaissance Board Game is the final test of "Software Engineering", course of "Computer Science Engineering" held at Politecnico di Milano (2020/2021).

Teacher Alessandro Margara

The final version includes:

  initial UML diagram;
  final UML diagram, generated from the code by automated tools;
  working game implementation, which has to be rules compliant;
  source code of the implementation;
  source code of unity tests.

## Students:
* Alessandro Maranelli - 10661029
* Amir Bachir Kaddis Beshay - 10659740
* Flavio La Manna - 10620549

## Implemented features
* Basic rules
* Complete rules
* CLI
* GUI
* Socket
* Multiple games 
* Resilience

## Tests' coverage
|Package|Method coverage [%]|Line coverage [%]|
|--------------|:------------:|:-----------------:|
|__model__|100%|98%|


## Libraries e Plugins
|Library/Plugin|Description|
|--------------|-----------|
|__maven__|tool to manage softwares based on Java and build automation|
|__maven-shade-plugin__|plugin to create an uber-jar|
|__gson__|plugin used to customize Json translation settings|
|__junit__|framework dedicated to Java for unit testing|



## JAR Execution
### Server

To execute the server digit the following command on the terminal:

```
java -jar AM66-Server.jar 
```


### Client

To launch the client digit the following command:

To run CLI it his necessary to use wsl and set "Ms Gothic" font.

```
java -jar AM66-Client.jar 127.0.0.1 portNumber CLI   if CLI
java -jar AM66-Client.jar 127.0.0.1 portNumber GUI   if GUI
```
Here is a list of commands necessary to play the game in CLI
https://github.com/alessandromaranelli/ingswAM2021-Bachir-LaManna-Maranelli/blob/master/blob/copertina.jpg