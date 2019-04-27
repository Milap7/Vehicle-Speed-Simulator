# Vehicle-Speed-Simulator
A driverless vehicle requires a program to determine what the appropriate speed is at which it should drive. When a sensor event number is entered, then output the desired speed. There are three driving modes: Normal Mode, Speed Mode and Safe Mode. 

## Given Sensor Events:
### 1. Traffic 
### 2. Clear Traffic
### 3. Rainy Weather
### 4. Clear Weather
### 5. Slippery Road
### 6. Slippet Road Clear
### 7. Turbo
### 8. Speed Limit X

## The source code is written in Java, using the Factory Design Pattern that follows SOLID principles.

##### Prerequisites: `Java and Apache Maven version 3.6.0(brew install maven)`

##### Build: `mvn package` or 

##### Run: `java -cp target/speed-simulator-1.0-SNAPSHOT.jar com.here.Driver NORMAL`, to change Driving Mode replace NORMAL with SPORT or SAFE

##### Run Tests: `mvn test`

##### To Exit Program: `Ctrl+C`

### Source Code Description: 
The source code is designed using the **Factory Design Pattern**. The Abstract Class Factory creates two Concrete Factory Classes, ModeFactory and EventFactory. 
**ModeFactory creates one of three Driving Modes(Normal, Sport, Safe)** and **EventFactory creates one of three Events(NormalEvent, SportEvent, SafeEvent).**
&nbsp

#### Tests:
#### 4 Behaviour oriented test using Cucumber.
#### 62 Unit Tests using JUnit.
