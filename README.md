<h1><span style="text-decoration: underline;"><span style="color: #000000; text-decoration: underline;">OOP Ex4</span></span></h1>
<p>Created during a computer communication course during the second year at Ariel University in the Department of Computer Science, 2018 <br /> <strong>Project site:</strong>&nbsp;<a href="https://zvimints.github.io/OOP_3/.">https://zvimints.github.io/OOP_3/.</a><br /> <strong>Made by: </strong><a href="https://github.com/ZviMints">Zvi Mints</a> and <a href="https://github.com/orabu103">Or Abuhazira</a></p>
<h1>About The Project:</h1>
<style="width: 290.667px;"><img src="./img/startGame.png" width="700px" height="410px"alt="" />

<p><strong>The Purpose of the game - </strong>Eat all the fruits and pacman on the board, and reach maximum score.</strong></p>
<p><style="width: 290.667px;"><img src="./img/Load.png" alt="" /></p>
<p><strong> Load - </strong>First of all we need to load a CSV file that includes location of the pacman, fruit, ghost and boxes</p>
<p><style="width: 290.667px;"><img src="./img/Start.png" alt="" /></p>
<p><strong> Play - </strong>Then we need to select an initial position for the player and when you click play by pressing the mouse to change the position of the player.</p>
<p><style="width: 290.667px;"><img src="./img/Algo.png" alt="" /></p>
<p><strong> Algo - </strong>By clicking on an algo we allow the algorithms to run.</p>
<p><style="width: 290.667px;"><img src="./img/Stat.png" alt="" /></p>
<p><strong> Rating - </strong>When we click on statistics we will connect to a database which keeps all the data for our high score, the average of the other participants, and high score of the algorithm.</p>

<p><strong>Example:</strong></p>
<style="width: 290.667px;"><img src="./img/statistic.png" width="700px" height="410px"alt="" />

<h1>Project Diagram:</h1>
<p><img src="./img/ClassDiagram.jpg" /></p>

<h1>Class Hierarchy:</h1>
<p><img src="./img/tree.png" /></p>


<h1>Packages:</h1>
<p><img src="./img/allPack.png" width="150px" height="400x" alt="" /></p>

<h1>Algorithm:</h1>
<p><img src="./img/algoPack.png" width="220px" height="230x" alt="" /></p>
<p><strong>Coordinate - </strong> This is class that represent a Coordinate each Coordinate have x,y and pred.</strong></p>
<p><strong>FindShortestPathFromMat - </strong> The class is responsible to find the Shorest Path From input matrix.</strong></p>
<p><strong>GameToMatrix - </strong> This Class is responsible to convert from Game to Matrix used for taking steps in the Algorithm.</strong></p>
<p><strong>Maze - </strong> This Class is Responsible to Represent a Maze, Maze is a char[][] matrix with Objects such that Pacman that can be noticed by 'P' in the matrix, moreover we can see Objects Such that Fruit == 'F' ,Ghosts and Players.</strong></p>

<h2><strong>The Algorithm of the project is as following:</strong></h2>
<p><strong>Problem:</strong> for input file with number of Pacmans,Ghosts,Fruits find a route for a player to get the <b>maximum</b> score, when player can eat pacmans and fruits, and damaged by ghosts and boxes.</p>
<p><strong>Solution: </strong>&nbsp; (Pseudo code)</p>
<p><span style="text-decoration: underline;"><strong>Greedy Algorithm:</strong></span></p>
<p>0.Make Empty Queue (for the coordinates) and make Empty ArrayList (for the path)</p>
<p>1.Convert current game into a Matrix as described before</p>
<p>2.Make BFS SEARCH for finding the shortest path from START Point(Player Point) to END Point, used by the Matrix.</p>
<p>3.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is intersects Fruit or Pacman, Make BackTracking and return the path</p>
<p>4.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is near Ghost, CONTINUE</p>
<p>5.&nbsp; &nbsp; &nbsp; &nbsp;IF coordinate is intersects Box,or in invalid point, CONTINUE</p>
<p>6.&nbsp; &nbsp; &nbsp; &nbsp;ELSE, add to the Queue all the neighbors of the current coordinate</p>
<p><b>NOTE:</b> BackTracking is adding to the ArrayList each Pred of the current Point, until the Pred is null</p>


<h1>Coords:</h1>
<p>This Class is responsible for actions between Objects of the kind Point3D. The Class is used to Provide a solution for elementary actions between vectors and points in R^3 Vector space.</p>

<h1>File format:</h1>
<p> This class is responsible to make Dynamic matrix[][] which is row contain Arraylist that represent line in csv the number of rows is the number of csv file lines.</p>

<h1>Game:</h1>
<p>This class represent Game which include Fruits List and Pacmans List,
 this class can init Pacmans and Fruits from Matrix.</p>

<h1>GUI:</h1>
<p><img src="./img/guiPack.png" width="200px" height="200x" alt="" /></p>
<p><strong>Menu - </strong> This class represent the GUI Menu of the Project.</strong></p>
<p><strong>Score - </strong> This class represents the game score and time remaining for the game in real time.</strong></p>
<p><strong>StatisticFrame - </strong> This class represent the GUI Statistic of the Project.</strong></p>
<p><strong>Statistic - </strong> This class is responsible for connecting to the DB and extracting information regarding statistics.</strong></p>

<h1>Map:</h1>
<p>This class can convert Pixel point to Geo Point and back.</p>
<p>Each map containst Map Map (Image) that represent the background of the Game.</p>

<h1>MyFrame:</h1>
<p><img src="./img/myframePack.png" width="200px" height="170x" alt="" /></p>
<p><strong>AlgoThread - </strong> TThis Class is responsible for the "Client" algorithm side, this is thread that make all the choices for each step in the game.</strong></p>
<p><strong>GamePanel - </strong> The class is the main Panel of the game.</strong></p>
<p><strong>MyFrame - </strong> This Class is responsible for the Main connection between all classes we can run this game and get a Frame. connect GUI,Algorithm,Game,Play and more...</strong></p>

<h1>Object:</h1>
<p><img src="./img/objectPack.png" width="200px" height="300x" alt="" /></p>
<p><strong>Element - </strong> This is an interface that each one of Fruit,Ghost,Pacman need to implement.</strong></p>
<p><strong>Box <img src="./img/Box.png" /> - </strong> This Class represent Box. Every Box has BoxData which include relevant information about the Box such that ID and Geoms.</strong></p>
<p><strong>Fruit <img src="./img/Fruit.png" /> - </strong> This Class represent Fruit. Every Fruit has FruitData which include relevant information about the Fruit such that ID and Geom.</strong></p>
<p><strong>Ghost <img src="./img/Ghost.png" /> - </strong> This Class represent Ghost  . Every Ghost  has GhostData which include relevant information about the Ghost such that ID, Speed, Radius and Geom.</strong></p>
<p><strong>Pacman <img src="./img/Pacman.png" /> - </strong> This Class represent Pacman  . Every Pacman has PacmanData which include relevant information about the Pacman such that ID, Speed, Radius and Geom.</strong></p>

<h1>Player: <img src="./img/Player.png" /></h1>
<p> </p>
