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
Rock, Paper, Scissors! I use an RPS enum to represent the item for a player, with a ```score()``` method returning the
score for the item. The ```RPS.of()``` is the factory method (taking a 1 letter string as an input). RPS provides
convenience methods ```loserFor()``` and ```winnerFor()``` to test game results.
A ```Game``` record is used to represent a single game. The ```Game.of()``` method takes a line from the input and 
transforms it into a ```Game``` with two players (```RPS``` instances). The ```Game.score()``` returns the value of the 
game. That's enough for part 1.
For part 2, I added a ```RPS.of2()```, taking a 1 letter string and the RPS for the other player, to determine the right
ite to use for player 2. ```Game.of2()``` uses this new ```RPS.of2()``` to build a game according to the rules explained
by the Elf using the RPS convenience methods. The calculation of the total score is unchanged, but now using 
```Game::of2``` in the stream for mapping an input line into a ```Game```.

## Day 3
```Rucksacks```, with ```RucksackItem```s (with a priority) in two compartments, without duplication in any compartment.
The ```RucksackItem``` record holds the character and it's ```priority()``` methods calculates priority from the char
value. The ```RucksackItems.of()``` creates a ```Set<RucksackItem>``` from  a string (set has no doubles), and
```RucksackItems.of2()``` creates a ```List<Set<RucksackItem>>``` from a string (using ```RucksackItems.of()```splitting
the string in 2 halves).
The ```Rucksack.shared()``` returns the one RucksackItem that is the intersection between the two compartments (using 
the Google Guave Sets class). For part 1, transform the input in a ```List<Rucksack>```, stream the list, find the shared
items and sum theis priorities. For part 2, ```Rucksack.shared()``` can also take two other rucksacks and find the shared
rucksack item among ```allItems()``` of all three rucksacks. Iterate the rucksack list (in groups of 3), find the
shared item across all 3 rucksacks and sum its priority.

## Day 4
Created a ```Range``` record to contain one range, and a ```RangePair``` record to contain two. The ```Range``` ensures
the lower and upper values are in the right order (and the input seems to be so as well, but you might never know). The 
```RangePair.fullContainment()``` returns true if one of the ranges fully contains the other. That's enough to
solve part 1. For part 2 ```RangePair.overlap()``` returns true is there is an overlap between the two ranges. For the
rest calculating the result is pretty similar. 
The only caveat in this puzzle is of course that second can be contained in first, and the other way around (same goes for overlap).

## Day 5
Solved this day in two steps. First solved answers to part 1 and 2, and didn't waste any time on parsing the layout
of the crate stacks. In step two, I parsed the full input, and refactored the Cargo class, to correctly parameterize
the use of a separate crate strategy.

First ```Crate``` represents a crate with an ```id``` (letter). A ```Creates``` is a class representing a stack of 
crates, with operations to ```take()``` and ```add()``` crates (behaving like a stack, i.e. LIFO). A convenience
method can create a Crates instance using a string (indicating the crates from bottom to top). The ```top()``` method
returns the id of the crate on top. A ```Crane``` is a functional interface, which can take n elements from a 
```Crates``` instance. ```Cargo``` represents the entire set of crate stacks, with operations to ```take()```
n elements from one of the crate stacks using a ```Crane``` instance, and to ```add()``` a 'taken' crate stack to some 
other crate stack in the cargo.

Parsing the second part of the input (after the blank line) is simple using a regexp, and mapping the details int
an ```Instruction``` record. Parsing the first part is a bot more difficult, however all columns have width 4 (the 
second character in each column being a crate id), and the last char on the last line (of the first part) indicates
the number of columns. Using that you can parse the lines into ```StringBuilder```s containing the crate ids.

Now part 1 and two of day 5 become quite easy, just create a Cargo instance using the list of "crate id strings",
and process all instructions using the default ```Crane```, which takes n elements from a crate stack. Part 2, is just
as simple, with a slightly different ```Crane```, which reverses the crate stack that was taken, before it is added 
again (so the original order is preserved). 






