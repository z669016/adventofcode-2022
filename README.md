# Advent of code 2021

## Convenience methods and classes
I have a small library with some convenience methods used for other AoC exercises. Like the ```ResourceLines``` class 
to read a resource file and transform the content into a ```List<String>```, or the CSV class to read a resource 
file containing comma separated values and returning a List of these values, optionally after transformation from 
```String``` to ```Integer```.

Also uses the algorithm library, which contains generic classes for addressing classic compute problems (from the book 
**Classic Computer Science Problems In Java** (c) Manning.com - 2020) 

It was never my intention to create the shortest program possible. I did try to create clear and simple implementations.

## Day 1
December 1, 2022, looking forward to a month of puzzles! Day 1 in general is for warming up. Create a list of 
```ElF``` each carrying a list of calories (int). For part 1, find the Elf with max ```Elf::calories```. To solve
part 2, order the list according to ```ELf::Calories``` and take the top-3.

## Day 2
Rock, Paper, Scissors! I use a RPS enum to represent the item for a player, with a ```score()``` method returning the
score for the item. The ```RPS.of()``` is the factory method (taking a 1 letter string as an input). 
A ```Game``` record is used to represent a single game. The ```Game.of()``` method takes a line from the input and 
transforms it into a ```Game``` with two players (```RPS``` instances). The ```Game.score()``` returns the value of the 
game. That's enough for part 1.
For part 2, I added a ```RPS.of2()```, taking a 1 letter string and the RPS for the other player, to determine the right
ite to use for player 2. ```Game.of2()``` uses this new ```RPS.of2()``` to build a game according to the rules explained
by the Elf. The calculation of the total sscore is unchanged, but now using ```Game::of2```.



