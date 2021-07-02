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


## Implemented Functionalities
| Functionality | Status |
|:-----------------------|:------------------------------------:|
| Basic rules | [![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| Complete rules | [![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| Socket |[![GREEN](http://placehold.it/15/44bb44/44bb44)]()|
| CLI | [![GREEN](http://placehold.it/15/44bb44/44bb44)]() |
| GUI |[![GREEN](http://placehold.it/15/44bb44/44bb44)]()|
| Multiple games | [![GREEN](http://placehold.it/15/44bb44/44bb44)]()|
| Resilience | [![GREEN](http://placehold.it/15/44bb44/44bb44)]()|
| Persistence | [![RED](http://placehold.it/15/f03c15/f03c15)]() |
| Editor | [![RED](http://placehold.it/15/f03c15/f03c15)]() |
| Local games | [![RED](http://placehold.it/15/f03c15/f03c15)]() |

## Tests' coverage
|Package|Method coverage [%]|Line coverage [%]|
|--------------|:------------:|:-----------------:|
|__model__|100%|98%|


## JAR Execution

JAR files are available in the deliverables folder

### Server

To execute the server digit the following command on the terminal:

```
java -jar AM66-Server.jar 
```


### Client

To launch the client digit the following command: 

For GUI
```
java -jar AM66-Client.jar 127.0.0.1 portNumber GUI 
```
For CLI
```
java -jar AM66-Client.jar 127.0.0.1 portNumber CLI 
```
Note that to run CLI it his necessary to use wsl and set "Ms Gothic" font.
GUI interfaces instead are not supported on wsl so GUI needs to be launched on some other terminal/console (powershell, cmd, ..)


Here is a list of commands necessary to play the game in CLI
https://github.com/alessandromaranelli/ingswAM2021-Bachir-LaManna-Maranelli/blob/master/blob/commands.txt
