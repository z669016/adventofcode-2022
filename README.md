# Advent of code 2022

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
method passes the visitor on to its children (nodes in its node list).  So, the visitor has no knowledge on the data 
structure, and only knows what to do with the different types of node. the ```DirectorySizeFinder``` simply builds a 
```List<Directory>``` with all ```Directory``` instances with the right size.

For optimization, ```Directory.size()``` uses memoization, i.e. only the very first time it gets called, it calculates 
the size (summing the size of all its child nodes) and stores the size in a property.

For part 2, first calculate the disk size to free up (```NEEDED_DISK_SPACE - (TOTAL_DISK_SPACE - root.size())```), and 
then use the ```DirectorySmallestFinder``` visitor to find the smallest directory to delete.

There could have been some caveats in this challenge, like moving down a directory without first having performed a 'la'
command, performing a 'cd'  command into a file, or performing a 'ls' command twice on the same directory. But none of
that was in my puzzle input.

## Day 8
Today looked a bit tricky, so I needed to read the instructions carefully. I created a ```Forrest``` class for the 
actual work, which holds the input data in a ```char[][]``` grid. 
For part 1, the ```visibleTrees()``` walks the entire forrest, and for each location, uses ```isVisible()``` and simply
counts all visible trees. ```isVisible()``` walks to the left, right, up, and down to see if there is a higher tree 
before the edge of the forrest.
On part 2, I first populate a ```int[]] score``` grid, with the scenic score for each tree and then simply return the
highest score in the score grid.

## Day 9
That was more complicated than it initially seemed to be. For Part 1 I created the ```Rope``` record from a head ```Point``` 
and a tail ```Point```, with operations to ```move(Direction)``` which would return a new rope with an updated head and 
tail, ```move(Instruction)``` which would return a list of ropes (one for each instruction step), and a 
```move(List<Instruction))``` which again would return a list of ropes. The update of the tail, was easier than 
expected, as soon as the combination new-head and old-tail would become invalid, the new-tail would be equal to the 
old-head.

For part 2, I first tried to construct a ```LongRope``` from a ```List<Rope>```, but that became too confusing (too 
many points to keep up to date). Then I renamed ```Rope``` to ```ShortRope```, extracted its interface into ```Rope```, 
created an AbstractRope (containing the move methods and head and tail methods), and implemented a ```LongRope``` 
extending the ```AbstractRope``` and implementing the ```Rope``` interface wrapping a ```List<Point>```. This time 
update of the consecutive points after update of the previous one, proved to be a bit nasty and required some 
trial/error and careful checking in-between states and the samples provided...

In the end everything worked well, and I'm pretty okay with the implementation as well.

## Day 10
Today, some kind of virtual machine again, which needs to process instructions. Each instruction runs in 1 or 2 cycles
and during cycles some magic happens on the device. The instructions operate on the device, so modeled this with a 
```Device``` class, an ```Instruction``` interface, and a ```Compiler``` which compiles the input into a 
```List<Instruction>``` to be processed by the ```Device.process()```. ```Instruction.exec()``` receives a reference to
the CPU to perform the internal updates. ```Device.process()``` keeps running the program until a certain stop condition
was met (```Predicate<Integer>```) based on the cycle value.

For part 1, the processing needs to end after at least 220 cycles. The **noop** instruction calls ```Device.cycle()``` 
once and doesn't do anything else. The **addr** instruction calls ```Device.cycle()``` twice and then updates the value
of the devices' X register. The actual work for the puzzle is done in the ```Device.cycle()``` method. For part 1, cycle 
gathers ```SignalStrength```s values at cycles 20, 60, 100, 140, 180, and 220. Getting the updating of the cycle count 
right was the most tricky part for today. Once I moved that into the ```Device.cycle()``` things became quite simple.

For part 2, I added a ```char[][] crt``` to the device, and call ```Device.draw()``` from ```Device.cycle()``` before 
increasing the cycle number. ```Device.draw()``` determines the sprite middle point (an x-axis value), calculates the 
drawing position (y, x value), and if ```sprite-middle - 1 <= x <= sprite-middle + 1``` it puts a '#' on the crt,
and otherwise it puts an '.'. ```Device.crt()``` returns the crt screen as a multi line string.

## Day 11
Nice game to simulate, at least for part 1, there will be some caveat on part 2. Created a ```Monkey``` class, and a 
```Game``` class. The ```Game``` class does the parsing, and can take ```rounds()``` on the list of monkeys. The 
```Game.monkeyBusiness()``` simply sorts the List<Monkey> based on the number of inspections in reversed order, and 
uses the top 2 to calculate the monkey business value.

For part 2, you need to prevent numbers get too big. I tried using a ```BigInteger``` for the worry-values, but that 
ran way too long. However, later I found that you can also reduce the values, by using the lowest-common-multiple of 
all divider values. After each calculation, you can reduce the value again by taking the remainder after dividing the 
value by the lcm of all divisors. For part 2, I replaced the default bored function (```value -> value -> 3```) with 
the lcm version (```value -> value % lcm```). For the rest, the solution is identical.

## Day 12
A BFS today, and the rules for next possible step are determined by the height differences. Pretty straight forward.
The ```HeightMap``` record holds the ```Grid``` and provides methods to find ```start()``` point, ```end()``` point, 
and ```next()``` possible locations from a current position. The ```Finder.solve()``` finds the shortest path using
breadth-search-first from a starting-point. For part one start in S, and move to E (where E is height z). 
For part 2, added a ```HeightMap.findAllLowest()```, and find the shortest route for all of them. Then filter the 
solutions (drop all empty solutions), and find the minimum length amongst the possible (non-empty) solutions.

## Day 13
A day to really carefully read the description, and don't make any assumptions. The ```SignalPacket``` contains a single
packet from the input. It wraps a ```List<Object>``` which can contain an ```Integer``` (value) or another 
```List<Object>``` which allows for nesting of structures. The factory method ```SignalPacket.from()``` uses the classes
of the tokenizer package, to translate the input into ```SignalPacket```s. Tokenization is pretty straight forward. The 
```SignalPacket.test()``` compares two values and returns an ```IN_ORDER```, ```NOT_IN_ORDER``` or ```CONTINUE```.
For part 1, ```SignalPacketPair```s are created from the input, and the ```compare()``` method returns the comparison 
result. The result can be calculated from streaming and filtering in ```List<SignalPacketPair>```.
For part 2, I've implemented ```Comparable<T>``` on the ```SignalPacket```. Then I created a new 
```List<SignalPacket>``` from the input, added the two additional packets, and sorted list. Then search for the 2-packet 
and 6-packet and calculated the decoder key. Using record as a type for SignalPacket made the search easier as you get
the ```equals()``` for free.

## Day 14
Okay, let's address this one step by step. The input must be converted into lines, and lines can consist of multiple 
parts (which are lines in itself). Created a record ```Line``` which can provide an Iterator<Line> which takes you 
through the individual parts (which are ```Line```s in itself).
Then, I created a class Lines, with the method ```asSmallGrid(List<Line>)``` which determines the min/max X and Y and 
creates a ```Grid```, and then ```draw(line)``` all the individual lines-parts into the grid. The ```Sand``` class has 
a ```Sand.poor(grid)``` which pours sand into the grid from the starting position, and returns the number of sand units
that could be poured into the grid.
For part 2, I created a ```asLargeGrid(List<Line>)``` which is just 4 times as wide and one line deeper. Then added a 
```Sand.fill(grid)``` based on the ```Sand.pour(grid)``` method with a slightly changed end-condition.  

## Day 15
That's been an interesting challenge ... started with a ```Beacon``` and a ```Sensor``` record including factory 
methods to extract ```Beacon```s and ```Sensor```s from the input data.
Initially I converted the test data into a grid, places the beacons and sensors on the grid, set the grid-cells that 
were covered by a beacon (set all the cells in range of a sensor according to the sensors distance to its connected
beacon). That worked great for the sample, but end in an ```OutOfMemory``` exception on the actual input data.

Changed the approach, and added a ```Range``` record, to hold a range along a Y axis of the area wrapping a lower and 
upper X value. And added methods to determine overlap between ranges (partially, fully, or adjacent), and to combine
overlapping ranges into a single range.

Then implemented a ```Sensor.rangeForRow()``` which would return the X-range for a certain row that 
would be covered by that sensor (based on the Manhattan distance of the sensor to that row). The 
```Day15.usedPositionsInRangeForRow()``` would get all the sensor-covered ranges for the specific row, combines them,
gets the size, and deducts the total size with the number of sensors and beacons on that row (as sensor and beacon 
positions cannot be used, so should also not be counted for). That solves part 1.

For part 2, first the min-Y and max-Y values were calculated from all beacons in with an X and Y value in the given 
range (0 - 4.000.000). As there was only one possibility, it would mean that in that min-Y to max-Y range, there would
be only one row, which would have two cover ranges (with only a single X position not covered). So, simply check the  
covered ranges for all Y values in that range, and the first row with 2 ranges is the one we need. Then create a beacon
for the specific x/y location, and return its ```Beacon.turingFrequency()```.

## Day 16
Inspired by [Johannes](https://github.com/jerchende), I refactored my solution to use the [Floyd-Warshall](https://www.baeldung.com/cs/floyd-warshall-shortest-path) algorithm for shortest path. 
This reduced the runtime for part 2 from 20 minutes to 90 seconds. The ```Valve``` class is just a record with a 
factory method for parsing the input. The ```Routes``` class creates a map for all shortest routes between valuable 
valves (valves with a flow rate > 0) using the Floyd-Warshall algorithm. The ```Valves``` class contains the 
```maximumPressure()``` to solve part 1. ```maximumPressureWithHelp()``` solves part 2, by generating a set of half of 
the possible routes, and finds the max pressure when those are visited bby me while the others get visited by an 
elephant (helper).

## Day 17
Yes, Tetris! ... Started with a ```Rock``` class to represent the different types of falling rock, and a convenience
class ```Rocks```, that contains the static Rock instances to drop, and implements ```Supplier<Rock>``` to deliver an
endless stream of rocks to be dropped into the cave. The ```Push``` class produces gas pushes to the left and right, to
be used during the drop.
The ```Cave``` class does the actual work. ```Cave.run()``` simply drops the required number of rocks. ```Cave.drop()```
determines the position of the next rock, using the ```Push``` while the rock is falling. Beware, when a rock cannot be 
pushed, it can still fall further down. ```drop()``` performs a ```draw()``` or the rock in its rest position, and 
returns the increase of the height of the tower of rock. This is enough for part1.
Part 2, the solution of part 1, cannot be used (I immediately got an out-of-memory when I tried). There solution must 
be based on repetition. I extended ```Cave``` int ```CaveAnalytics```  which does the same, but collects 
analytical data during falling of the rock. It looks for a pattern in the increases, and indeed finds a pattern which 
with the test data starts after dropping rock 26 and lasts for 35 drops. In order to solve part two, you now only need 
to know the content of that repeating block (the increases during that sequence of 3). The ```CaveSequence``` is another
extension of ```Cave``` that uses the input data to get the sequence and the height at the start of the repeating 
sequence. Now you have all ingredients to calculate the height for any ridiculous number of dropping rock.

## Day 18
Created a ```Droplet``` class that contained the list of lava points. Calculated the surface points by adding 6 for 
every point minus every time the Manhattan-distance to another point equals 1. Lost a lot of time on part 2, doing it
without a grid. Finally implemented a grid, and refactored part 1. Then I screwed up with changing exterior air into 
steam. Never try to be too smart ... clearly...

## Day 19
This feels like a BFS. Created a ```Blueprint``` record, to handle ... blueprints, and search for a max using BFS. 
BlueprintState record, to check for history and manage search state, a ```Prod``` record to record produced items, and 
a ```Robots``` record to maintain the manufactured robots.

Some optimizations are required:
- always create a Geode Cracking robot when possible and ignore the other options (impacts variation)
- never create more robots than the amount of products you can actually consume in one minute (impacts variation)
- maximize the production to the max amount you can use in the remaining time (reduces history)
- keep the max geodes collected during a specific minute, and purge options with fewer geodes manufactured at that point 
in time (purge variation). This one is a bit risky, especially when you change the queue order (I tried a priority 
- queue, but that made it fail on the test data)


Part one runs in ~25 seconds, and part 2 takes ~105 seconds. 

## Day 20
It took me a while before I realized that the position where to insert (at the beginning or the end) didn't matter at 
all. So I ditched my solution and started all over again. I created a ```Value``` class in which I store the value, 
and build an array of ```Value``` objects. The value objects get connected in a double linked list, and the array can 
be used to run through the list in the original order. The mixin has one caveat, move ```count % (list.size - 1)```
because the number you move won't "jump over" himself. The ```mix()``` method, simply goes over the array, and moves 
the ```Value``` object the required number of positions forward or backward. To get the coordinates, use 1000, 2000, 
3000 modulo ```list.size()``` to find the right values. 

For part 2, I changed the factory method, to multiply the values with the key given. and added a count parameter to 
the mix method. Finally, just to be sure, I changed the value from type ```int``` into type ```long```. For the rest no 
change required to solve part 2. 

## Day 21
This was fun ... build a tree from the input, where a node is either an ```Operation```, with a left and right operand
and an operator, or a ```Value```. I've chosen for each node to implement ```Supplier<Long>```, so it could return its
value. A ```Value``` just returns it's ... value, while an ```Operation``` calculates the result, from the values of 
its operands. I did add a cache to the nodes, not sure if that was required (why did I go against the YAGNI principle?).
I gave every node access to a ```Values``` object, which holds a map of all nodes (as you cannot build the actual tree
before all input has been parsed). This works like a charm for part 1.
For part 2, I changed the nodes and made them implement ```Supplier<String>```, so in case a value is not a long, the 
node would return a string version of the nodes expression like ```(((4 + (2 * (humn - 3))) / 4) + 150)```. To find the
value for ```humn```, take the value of the parent node (which is known, even for the root where the value of the 
comparison is ```true```), and using the parent value, you can reverse the operation of the child node to determine the
value of the node that does not return a long value (as string). Using the value for the unknown node, recursively 
drill down to the ```humn``` node.

## Day 22
So, I have a ```StrangeBoard``` class, and I have to move across the board, and make turns. The strange board class 
holds the current location (a ```Point```) and the ```Facing```. It can ```move(strategy)``` one position into the 
direction of the facing, or multiple steps ```move(strategy, distance)```. ```move(strategy)``` won't change the 
current location if a wall is hit. The ```MoveStrategy``` handles how to move across the board, and for part 1 this is 
done using ```BoardStrategy```.
The ```Path``` class provides an ```Iterator<String>``` to split the path input into separate tokens. The 
```PasswordResolver.resolve(board, strategy, path)``` takes the board, and uses the provided strategy to walk the path.
For part 2, I created a ```CubeStrategy```, and getting the jumps and directions right was hard. I cut a paper cube to
visualize it for myself. The actual puzzle input had a different folded cube compared to the sample, so my approach
didn't work for the sample as the wrapping depends on the way the map is folded.

## Day 23
First I've created the mechanism to generate the ```ValidDirection``` options for each round, which would return the 
right (ever-changing) order. A ```ValidDirection``` holds a ```List<Point>``` of 3 points to check amd the ```Point``` 
to move to in case all are unused. ```ValidDirections``` implements ```Supplier<List<ValidDirection>``` and returns 
the changed order on every ```get()``` request. I decided not to use an  actual grid, because that would have to expand
on each round, but did wrap each ```Elf``` position (a ```Point```). An ```Elf``` can 
```propose(validDirections,elves)```  (find a proposed new position) and ```move()``` (to the proposed position). The
```ELves``` class holds a list of all ```Elf``` instances, and can ```move(validDirections)```, which first makes
a proposal for each elf (using a ```Map<Point,List<Elf>``` to collect info of elves and their proposal to identify
elves moving to the same position) and them moves the elves that move into unique positions. I decided 
```Elves.move()``` to return the number of elves that actually moves in each round. ```ELves.toString()``` creates a 
grid containing the map of the current elves positions.
Part 1 is solved by calling ```Elves.move()``` 10 times, then create the map (```toString()```) and count the open
places. Thanks to ```Elves.move()``` returning the number of moved elves, part 2 just loops until ```move()``` 
returns 0 (it runs in 87 seconds, which is indeed too much)

## Day 24
This sounds like a BFS problem again, although the state in between also depends on teh contents of the board (location
of the blizzards). Created a ```Blizzard``` record to store the state of a blizzard (location, facing, symbol, max,
and reset). ```Blizzard.next()``` returns the next blizzard state, wrapping the valley if required.
The ```Valley``` record holds a ```List<Blizzard>```, and the in and out location, and also a ```next()``` method, that
returns the next state of the valley with all blizzards moved one step. To support BFS, ```Valley``` also contains a
```contains(point)``` method (determines if the point is in the Valley or equals in/out location), and an ```isOpen()``` 
method, which indicates if a certain position is free to enter. The ```toString()``` and ```toString(point)``` are used
for visualization and storing state.

```PathFinder.solve()``` performs a BFS from the initial point (```valley.in()```) towards the end (```valley.out()```),
and that will do for part 1. For part 2, I added a ```PathFinder.solve(initialState, found)``` to start a BFS to a 
goal, using a specific initial state. To solve part 2, reuse the result of part 1 as the initial-state to a second BFS
and find the ```valley.in()```. Then use that end-state to again perform a BFS towards the end (```valley.out()```). 
Reusing the end-state of the previous search ensures the minute count is continued, and the blizzard states are correct
when moving to the start and end again. 

A single shortest path search takes ~30 seconds, which is too much, optimization is required.

Learned something new on remembering history during the search: it appeared to be very memory efficient to store state 
as a ```String```. I ran out-of-memory, when I initially stored history as a```Pair<Valley,Point>```, where ```Valley```
contained a ```List<Blizzard>```, and a ```Blizzard``` was a record containing to method references: one method to 
determine wrapping (```Predicate<Point>```) and one method returning a new starting point for the blizzard 
(```Supplier<Point>```). 

## Day 25
This one  took me some time to wrap my head around it. The normal mathematics on numbers doesn't apply, because 
although the radix is 5, there are no symbols for values 3 and 4 as you would normally have. So, the max value for a 
value of 4 positions is not 5^4 - 1, but 2 * 5^3 + 2 * 5^2 + 2 * 5^1 + 2 * 5^0. But okay, when I got that clear, it 
wasn't difficult anymore.

## Java comparison
This week, Java 21 has been released, so I decided to compare runtime between Java 17 and Java 21. To do so, I created
an All class, that run all individual challenges by loading the DayXX class and calling the ```main()``` method.

I ran the All class with Java 17 and Java 21, and noticed some remarkable differences on the long running challenges:
* Day 16: Java 17: 80333ms, **Java 21: 22918ms** --> Floyd-Warshall algorithm, parallel stream
* Day 19: Java 17: 33473ms, Java 21: 31934ms --> BFS algorithm
* Day 23: **Java 17: 75674ms**, Java 21: 91568ms --> streaming with Set and List
* Day 24: **Java 17: 50819ms**, Java 21: 61255ms --> BFS algorithm
* Overall: Java 17: 242263ms, **Java 21: 209563ms** 

So four long running challenges, on two challenges Java 17 is much faster, on one both are comparable, and on one 
Java 21 is much faster.  
