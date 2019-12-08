<h1><span style="text-decoration: underline;"><span style="color: #000000; text-decoration: underline;">OOP Ex4 - HideAndSeekWithGhosts</span></span></h1>
<p>Other OOP Exercises:</p>
<p>Ex 2 -&nbsp;<a href="https://github.com/ZviMints/OOP_2">https://github.com/ZviMints/OOP_2</a></p>
<p>Ex 3 -&nbsp;<a href="https://github.com/ZviMints/OOP_">https://github.com/ZviMints/OOP_3</a></p>
<p>Created during a computer communication course during the second year at Ariel University in the Department of Computer Science, 2018 <br /> <strong>Project site:</strong>&nbsp;<a href="https://zvimints.github.io/Ex4/.">https://zvimints.github.io/Ex4/</a>.<br /> <strong>Made by: </strong><a href="https://github.com/ZviMints">Zvi Mints</a> and <a href="https://github.com/orabu103">Or Abuhazira</a></p>
<h1>About The Project:</h1>
<p><img src="https://github.com/ZviMints/Ex4/raw/master/img/startGame.png" alt="" width="600" height="300" /></p>
<div id="tw-target-text-container" class="gsrt tw-ta-container tw-nfl">
<p id="tw-target-text" class="tw-data-text tw-ta tw-text-small" dir="ltr" data-placeholder="תרגום"><span lang="en"><span style="text-decoration: underline;"><strong>The Purpose of the game:</strong></span><br />to accumulate a <span style="text-decoration: underline;"><strong>maximum</strong></span> amount of points during the game.<br />Accumulation of points is a process of eating Pacmans and Fruits by the player.<br />each Pacman or Fruit that eaten by the Player increases the number of points by 1, the faster the Player finishing the game, the Player earn more points, however, the player can be eaten by ghosts and can hit the walls, The points can go down.</span></p>
</div>
<p>&nbsp;</p>
<p><span style="text-decoration: underline;"><strong>Menu:</strong></span></p>
<p><strong> <img src="https://github.com/ZviMints/Ex4/raw/master/img/Load.png" alt="" width="71" height="28" />&nbsp;Load - </strong>The Load button is responsible to load&nbsp;CSV file that includes location of the pacman, fruit, ghost and boxes.</p>
<p><strong> <img src="https://github.com/ZviMints/Ex4/raw/master/img/Start.png" alt="" width="67" height="28" />&nbsp;Play -&nbsp;</strong>Game Mode for the Player, the user selects a start mode for the player and then selects a direction, from where the game begins.</p>
<p><strong> <img src="https://github.com/ZviMints/Ex4/raw/master/img/Algo.png" alt="" width="67" height="28" />&nbsp;Algo -&nbsp;</strong>By clicking on the Algorithm button, the algorithm of the game starts to work, in order to understand how the algorithm works you can access the WIKI page and understand the process of selecting the steps of the algorithm</p>
<p><strong> <img src="https://github.com/ZviMints/Ex4/raw/master/img/Stat.png" alt="" width="67" height="28" />&nbsp;Rating - </strong>By clicking on the Statistics&nbsp; button, we will connect to a database which keeps all the data for our high score, the average of the other participants, and high score of the algorithm, By using this function, we can know the relation between our results and other people's results, note that the server is not stored by us but by&nbsp; an external server.</p>
<p><strong>Example (Rating Frame):</strong></p>
<p><strong><img src="https://github.com/ZviMints/Ex4/raw/master/img/statistic.png" width="600" height="300" /></strong></p>
<h1>Project Diagram:</h1>
<p><img src="https://github.com/ZviMints/Ex4/raw/master/img/ClassDiagram.jpg" width="800" height="863" /></p>
<h1>Class Hierarchy:</h1>
<p><img src="https://github.com/ZviMints/Ex4/raw/master/img/tree.png" width="816" height="600" /></p>
<h1>Packages:</h1>
<p><img src="https://github.com/ZviMints/Ex4/raw/master/img/allPack.png" width="143" height="400" /></p>
<p><strong>NOTE</strong>: Explanations for each class can be found on the Wiki page or later in this document</p>
<h1>Package - Algorithm:&nbsp;</h1>
<p><strong>Coordinate -&nbsp;</strong>This class is responsible for the representation of a Coordinate in the matrix. Each Coordinate has x and y values (in Pixels). In addition, there is an ancestor, an ancestor marked as PRED and an ancestor defined to be the Coordinate that reveals the current Coordinate, PRED used to Backtracking the path.</p>
<p><strong>FindShortestPathFromMat - </strong> The class is responsible to find the Shorest Path From input matrix.</p>
<p><strong>GameToMatrix - </strong> This Class is responsible to convert from Game to Matrix used for taking steps in the Algorithm.</p
<p><strong>Maze - </strong> This Class is Responsible to represent a Maze, Maze is a char[][] matrix with Objects such that Pacman that can be noticed by 'P' in the matrix, moreover we can see Objects Such that Fruit == 'F' ,Ghosts and Players.</p>
<h2><strong>The Algorithm <img src="https://media.wired.com/photos/5af2249a0b975d475fa7afbf/master/pass/algorithms_landlord-FINAL.jpg" alt="" width="40" height="30" />:</strong></h2>
<p><strong>Problem:</strong> for input file with number of Pacmans,Ghosts,Fruits find a route for a player to get the <strong>maximum</strong> score, when player can eat pacmans and fruits, and damaged by ghosts and boxes.</p>
<p><strong>Solution:&nbsp;</strong></p>
<p>The algorithm "reads" the game by creating a Maze object that represents the current situation as a two-dimensional matrix of the amount of pixels on the amount of pixels of the game and return a PATH that set to be the shortest route to the nearest fruit or pacman for the current point of the player.</p>
<p><span style="text-decoration: underline;"><strong>Input:</strong></span> State of the game</p>
<p><span style="text-decoration: underline;"><strong>Output</strong></span>: Shortest Path for Start point to End point</p>
<p><span style="text-decoration: underline;"><strong>Greedy Algorithm:</strong></span></p>
<p>0. Make Empty Queue (for the Coordinate) and make Empty ArrayList (for the Path)</p>
<p>1. Convert current game into a Matrix as described before</p>
<p>2. Make BFS SEARCH for finding the shortest path from START Point (Player Point)</p>
<p>&nbsp; &nbsp; to&nbsp;END Point, used by the Matrix.</p>
<p>3.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is intersects Fruit or Pacman, Make BackTracking and return the path</p>
<p>4.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is near Ghost, CONTINUE ( GO TO NEXT ITERATION )</p>
<p>5.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is intersects Box,or in invalid point, CONTINUE&nbsp;( GO TO NEXT ITERATION )</p>
<p>6.&nbsp; &nbsp; &nbsp; &nbsp;ELSE, add to the Queue all the neighbors of the current coordinate</p>
<p><strong>NOTE:</strong> BackTracking is adding to the ArrayList each Pred of the current Point, until the Pred is null</p>
<h1>Package -Coords:</h1>
<p>This Class is responsible for actions between Objects of the kind Point3D. The Class is used to Provide a solution for elementary actions between vectors and points in R^3 Vector space.</p>
<h1>Package - File format:</h1>
<p>This class is responsible to make Dynamic matrix[][] which is row contain Arraylist that represent line in csv the number of rows is the number of csv file lines.</p>
<h1>Package - Game:</h1>
<p>This class represent Game which include Fruits List and Pacmans List, this class can init Pacmans and Fruits from Matrix.</p>
<h1>Package - GUI:</h1>
<p><strong>Menu - </strong> This class represent the GUI Menu of the Project.</p>
<p><strong>Score - </strong> This class represents the game score and time remaining for the game in real time.</p>
<p><strong>StatisticFrame - </strong> This class represent the GUI Statistic of the Project.</p>
<p><strong>Statistic - </strong> This class is responsible for connecting to the DB and extracting information regarding statistics.</p>
<h1>Package - Map:</h1>
<p>This class can convert Pixel point to Geo Point and back.</p>
<p>Each map containst Map Map (Image) that represent the background of the Game.</p>
<h1>Package -MyFrame:</h1>
<p><strong>AlgoThread - </strong> TThis Class is responsible for the "Client" algorithm side, this is thread that make all the choices for each step in the game.</p>
<p><strong>GamePanel - </strong> The class is the main Panel of the game.</p>
<p><strong>MyFrame - </strong> This Class is responsible for the Main connection between all classes we can run this game and get a Frame. connect GUI,Algorithm,Game,Play and more...</p>
<h1>Package - Objects:</h1>
<p><strong>Element - </strong> This is an interface that each one of Fruit,Ghost,Pacman need to implement.</p>
<p><strong>Box <img src="./img/Box.png" /> - </strong> This Class represent Box. Every Box has BoxData which include relevant information about the Box such that ID and Geoms.</p>
<p><strong>Fruit <img src="./img/Fruit.png" /> - </strong> This Class represent Fruit. Every Fruit has FruitData which include relevant information about the Fruit such that ID and Geom.</p>
<p><strong>Ghost <img src="./img/Ghost.png" /> - </strong> This Class represent Ghost . Every Ghost has GhostData which include relevant information about the Ghost such that ID, Speed, Radius and Geom.</p>
<p><strong>Pacman <img src="./img/Pacman.png" /> - </strong> This Class represent Pacman . Every Pacman has PacmanData which include relevant information about the Pacman such that ID, Speed, Radius and Geom.</p>
<h1>Package - Player: <img src="./img/Player.png" /></h1>
<p><span lang="en">This package is responsible for representing a player, each player has a picture, degrees and ID name, the degrees represent the direction the player is looking at and this is the direction he will move during the game</span></p>
<h1>Package - JUNIT Testing: <img src="./img/Player.png" /></h1>
<table style="height: 193px;" width="481">
<tbody>
<tr>
<td style="width: 232.667px;">Class</td>
<td style="width: 232.667px;">
<pre id="tw-target-text" class="tw-data-text tw-ta tw-text-large" dir="ltr" data-placeholder="תרגום" data-fulltext=""><span lang="en"> Brief Explanation</span></pre>
</td>
</tr>
<tr>
<td style="width: 232.667px;">BoxTest</td>
<td style="width: 232.667px;">This class is responsible to test the BOX Object</td>
</tr>
<tr>
<td style="width: 232.667px;">FruitTest</td>
<td style="width: 232.667px;">This class is responsible to test the Fruit Object</td>
</tr>
<tr>
<td style="width: 232.667px;">GhostTest</td>
<td style="width: 232.667px;">This class is responsible to test the Ghost Object</td>
</tr>
<tr>
<td style="width: 232.667px;">MapTest</td>
<td style="width: 232.667px;">This class is responsible to test the Map, check the transfers from pixels to pixels and vice versa
<div id="tw-target-text-container" class="gsrt tw-ta-container tw-nfl">
<pre class="tw-data-text tw-ta tw-text-small" lang="en">&nbsp;</pre>
</div>
</td>
</tr>
<tr>
<td style="width: 232.667px;">MyCoordsTest</td>
<td style="width: 232.667px;">
<div id="tw-target-text-container" class="gsrt tw-ta-container tw-nfl">
<p id="tw-target-text" class="tw-data-text tw-ta tw-text-small" dir="ltr" data-placeholder="תרגום"><span lang="en">A class that checks all the elementary operations performed in the Coords Class</span></p>
<pre class="tw-data-text tw-ta tw-text-small" lang="en">&nbsp;</pre>
</div>
</td>
</tr>
<tr>
<td style="width: 232.667px;">PacmanTest</td>
<td style="width: 232.667px;">This class is responsible to test the Pacman Object</td>
</tr>
<tr>
<td style="width: 232.667px;">AlgoTest</td>
<td style="width: 232.667px;">
<div id="tw-target-text-container" class="gsrt tw-ta-container tw-nfl">
<p id="tw-target-text" class="tw-data-text tw-ta tw-text-small" dir="ltr" data-placeholder="תרגום"><span lang="en">Performs a test of the algorithm, given a matrix with initial and final state and with "obstacles", checks whether the output of the algorithm is the shortest path</span></p>
<pre class="tw-data-text tw-ta tw-text-small" lang="en">&nbsp;</pre>
</div>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
