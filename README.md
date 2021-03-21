# java-oop-exercise-easterRaces
 A JAVA console app practicing OOP structure, work with classes, abstract classes, interfaces & applying  SOLID  principles, which serves as a simple platform for storing information about drivers, cars, races and producing the result of those races.
 
 ## Structure
The structure of this app is implemented with Engine, Controller, ConsoleReader & ConsoleWriter.
There are also 3 types of entities and 3 repositories: Car, Driver, Race and a Repository for each one of them.

## Functionality 
The business logic of the program is concentrated around several commands in the Controller class managed by the Engine class.

### -CreateDriver Command

Creates a Driver with the given name and adds it to the appropriate repository.
The method returns the following message:
```
Driver {name} is created.
```
If a driver with the given name already exists in the driver repository, throws an IllegalArgumentException with message:
```
Driver {name} is already created.
```
### -CreateCar Command

Creates a Car with the provided model and horsepower and adds it to the repository.
If the Car already exists in the appropriate repository throws an IllegalArgumentException with the following message:
```
Car {model} is already created.
```
If the Car is successfully created, the method returns the following message:
```
{"MuscleCar"/ "SportsCar"} {model} is created.
```
### -AddCarToDriver Command

Gives the Car with given name to the Driver with given name (if exists).
If the Driver does not exist in the DriverRepository, throws IllegalArgumentException with message:
```
Driver {name} could not be found.
```
If the Car does not exist in the CarRepository, throws IllegalArgumentException with message:
```
Car {name} could not be found.
```
If everything is successful adds the Car to the Driver and returns the following message:
```
Driver {driver name} received car {car name}.
```
### -AddDriverToRace Command

Adds a Driver to the Race.
If the Race does not exist in the RaceRepository, throws an IllegalArgumentException with message:
```
Race {name} could not be found.
```
If the Driver does not exist in the DriverRepository, throws an IllegalArgumentException with message:
```
Driver {name} could not be found.
```
If everything is successful, you adds the Driver to the Race and returns the following message:
```
Driver {driver name} added in {race name} race.
```
### -CreateRace Command

Creates a Race with the given name and laps and adds it to the RaceRepository.
If the Race with the given name already exists, throws an IllegalArgumentException with message:
```
Race {name} is already created.
```
If everything is successful returns the following message:
```
Race {name} is created.
```
### -StartRace Command

This method starts the race. If everything is valid, it arranges all Drivers and then returns the three fastest Drivers. This is achieved by sorting all Drivers in descending order by the result of CalculateRacePoints method in the Car class. At the end, if everything is valid removes this Race from the race repository.

If the Race does not exist in RaceRepository, throws an IllegalArgumentException with message:
```
Race {name} could not be found.
```
If the participants in the race are less than 3, throws an IllegalArgumentException with message:
```
Race {race name} cannot start with less than 3 participants.
```
If everything is successful, returns the following message:
```
Driver {first driver name} wins {race name} race.

Driver {second driver name} is second in {race name} race.

Driver {third driver name} is third in {race name} race.
```
### -End Command

Exits the program.

## Input / Output

### Input
Below, you can see the format in which each command is expected in the input (each on a new line):
```
CreateDriver {name}
CreateCar {car type} {model} {horsepower}
AddCarToDriver {driver name} {car name}
AddDriverToRace {race name} {driver name}
CreateRace {name} {laps}
StartRace {race name}
End
```

### Output
The output of each command when returned is being printed on the console. If an exception is thrown during any of the commands' execution, the exception message is also being printed on the console.

### Example

Input:
```
CreateDriver Michael
CreateDriver Peter
CreateCar Sports Porsche 380
CreateCar Muscle Mustang 580
CreateCar Muscle Corvette 440
CreateRace Daytona 2
AddCarToDriver Michael Porsche
AddCarToDriver Peter Mustang
AddCarToDriver Michael Corvette
StartRace Daytona
AddDriverToRace Daytona Michael
AddDriverToRace Daytona Peter
StartRace Daytona
CreateDriver Brian
AddDriverToRace Daytona Brian
CreateCar Sports Mazda 350
AddCarToDriver Brian Mazda
AddDriverToRace Daytona Brian
StartRace Daytona
End
```
Output:
```
Driver Michael is created.
Driver Peter is created.
SportsCar Porsche is created.
MuscleCar Mustang is created.
MuscleCar Corvette is created.
Race Daytona is created.
Driver Michael received car Porsche.
Driver Peter received car Mustang.
Driver Michael received car Corvette.
Race Daytona cannot start with less than 3 participants.
Driver Michael added in Daytona race.
Driver Peter added in Daytona race.
Race Daytona cannot start with less than 3 participants.
Driver Brian is created.
Driver Brian could not participate in race.
SportsCar Mazda is created.
Driver Brian received car Mazda.
Driver Brian added in Daytona race.
Driver Michael wins Daytona race.
Driver Peter is second in Daytona race.
Driver Brian is third in Daytona race.
```
