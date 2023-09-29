# MineSweeper

https://central.sonatype.com/artifact/org.apache.maven/apache-maven?smo=true

#### Table of Contents

- [What is it?](#what-is-it)
- [Installation](#installation)
- [Tutorial](#tutorial)
- [License](#license)

# <a name="what-is-it">What is it?</a>

_Minesweeper_

Minesweeper is a logic puzzle video game. The game features a grid of clickable squares, with hidden "mines" scattered throughout the board. The objective is to clear the board without detonating any mines, with help from clues about the number of neighboring mines in each field.

# <a name="installation">Installation</a>

## Requirements

- Java 8+
- Maven 3.2.5 or later

## Installation

1. Clone the repo.

```bash
$ git clone https://github.com/Paul-MacKenzie-Mitchell/MineSweeper
```

2. cd to the MineSweeper folder, and then build it using the Maven.

```bash
$ cd MineSweeper
$ mvn package
```

3. From the MineSweeper folder run the jar.

```
$ java -jar target/Minesweeper-1.0-SNAPSHOT.jar
```

# <a name="tutorial">Tutorial</a>

1. Each Minesweeper game starts out with a grid of unmarked squares. After clicking one of these squares, some of the squares will disappear, some will remain blank, and some will have numbers on them. It's your job to use the numbers to figure out which of the blank squares have mines and which are safe to click

2. The mouse is the only tool that you'll need to play Minesweeper. The left mouse button is used to click squares that don't contain mines, while the right mouse button is used to flag squares that contain mines.

3. A number on a square refers to the number of mines that are currently touching that square. For example, if there are two squares touching each other and one of the squares has "1" on it, you know that the square next to it has a mine beneath it.

4. Click squares that you believe do not have mines under and place flags on squares with a right click, until all the squares that do not have mines are uncovered. This will result in a Win. Clicking on a mine accidently will result in a loss.

5. Before the game begins you can select one of three difficulties. Each harder level creating a larger board with more mines.

# <a name="license">License</a>

_MineSweeper_ is distributed under [The MIT License](http://choosealicense.com/licenses/mit/).

The MIT License (MIT)

Copyright (c) 2023 Paul Mitchell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

```
