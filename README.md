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
of the crate stacks. In step two, I parsed the full input, and refactored the Cargo class to correctly parameterize
the use of a separate crane strategy.

```Crate``` represents a crate with an ```id``` (letter). ```Creates``` is a class representing a stack of 
crates, with operations to ```take()``` and ```add()``` crates (behaving like a stack, i.e. LIFO). A convenience
method can create a ```Crates``` instance using a string (indicating the crates from bottom to top). The ```top()``` 
method returns the id of the crate on top, and ```reverse()``` returns the ```Crates``` in reversed order. 
A ```Crane``` is a functional interface, to take n elements from a ```Crates``` instance. ```Cargo``` represents the 
entire set of crate stacks of the input, with operations to ```take()```n elements from one of the crate stacks using 
a ```Crane``` instance, and to ```add()``` a 'taken' crate stack to some other crate stack in the cargo.

Parsing the second part of the input (after the blank line) is simple using a regexp, and mapping the details into
an ```Instruction``` record. Parsing the first part is a bit more difficult, however all columns have width 4 (the 
second character in each column being a crate id), and the last char on the last line (of the first part) indicates
the number of columns. Using that you can parse the lines into ```StringBuilder```s containing the crate ids.

Now part 1 and 2 become quite easy, just create a Cargo instance using the list of "crate id strings",
and process all instructions using the default ```Crane```, which takes n elements from a crate stack (as a consequence 
the order of the taken crates gets reversed when added). Part 2, is just as simple, with a slightly different 
```Crane```, which reverses the crate stack that was taken before it is added again (so the original order is 
preserved). 

## Day 6
Created a ```Packet``` record to wrap a character sequence, and added a ```startOfPacket()``` method that iterated over 
the sequence to find 4 unequal consecutive characters which I load into 4 characters. To validate the that the 
characters are unequal, I added the characters to a ```Set<Integer>``` which must be of size 4. There is a clear pattern 
in the solution with the value 4.
I was surprised by the 2nd part which only changes the marker size from 4 to 14. I only needed to make the 
```startOfPacket()``` flexible with the size. So, I added ```size``` as a parameter, and instead of 4 characters, I 
used an array of the required size. For the rest, no change required.  

## Day 7
Two issues to solve, fist parse the log-data into a file system structure: a tree of ```Node```s (with a name and a 
size) which can be a ```File``` or a ```Directory``` (containing a ```List<Node>```). Start with current working 
directory being equal to "/" (```Directory.ROOT```). If a log line starts with 'd' it contains a directory name, when 
it starts with a digit it contains a file size and name, and otherwise it's a command. Just ignore '$ ls' commands, 
but  on a '$ cd' command you need to change the current directory to the directory with that name (which must be in the 
node list of the current working directory).

For part 1, I used the [visitor pattern](https://www.gofpatterns.com/behavioral/patterns/visitor-pattern.php). A visitor 
is a ```Consumer<Node>```, and when handed to a node (```Node.visit(visitor)```), the node calls the visitors 
```accep(this)``` method to do it's magic on the node, and in case of a ```Directory``` node the ```Directory.visit()``` 
method passes the visitor on to it's children (nodes in its node list).  So, the visitor has no knowledge on the data 
structure, and only knows what to do with the different types of node. the ```DirectorySizeFinder``` simply builds a 
```List<Directory>``` with all ```Directory``` instances with the right size.

For optimization, ```Directory.size()``` uses memoization, i.e. only the very first time it gets called, it calculates 
the size (summing the size of all its child nodes) and stores the size in a property.

For part 2, first calculate the disk size to free up (```NEEDED_DISK_SPACE - (TOTAL_DISK_SPACE - root.size())```), and 
then use the ```DirectorySmallestFinder``` visitor to find the smallest directory to delete.

There could have been some caveats in this challenge, like moving down a directory without first having performed a 'la'
command, performing a 'cd'  command into a file, or performing a 'ls' command twice on the same directory. But none of
that was in my puzzle input.



