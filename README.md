# game-of-life

Conway's game of life implementation with command line output.

## Prerequisites

    * jdk 1.8 or higher version
    * gradle 2.10.2 or higher version

## How to Build?

Run the following command in order to build sources and package jar: `gradle clean jar`

## How to Use?

Go to the ./build/libs folder and run `java -jar game-of-life.jar`. 
You will see all of the available arguments. 

To start the game run `java -jar game-of-life.jar --start --capacity=10 --iterations=100`. 
This command will crate a random 10x10 board with randomly populated cells and run 100 iterations of the game.

# demo

test change