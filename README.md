X in a row
================================
*Author and developer: David Föllenz, 28/06/2013*

User description:
-------------------------

"X in a row" is a minigame also known as Connect4, Captain's Mistress and Four in a Line. It's a 
two player game in which the player first choose a color and then take turns dropping colored disks 
from the top into a grid. Common grid sizes are 7 columns to 6 rows vertically.  The pieces fall 
straight down, occupying the next available space within the column. The object of the game is to 
connect four of one's own discs of the same color next to each other vertically, horizontally, 
or diagonally before the opponent. Players can be chosen as bots if desired.

There are a couple of benefits the game provides:
* Easy handle
* Individual playground configuration
* One- and Two-Player mode
* Individual player configuration


Extensibility:
-------------------------

<dl>
  <dt>Scalability:</dt>
  <dd>The playground can be extended from 5 to 50 rows to 5 to 50 columns. Currently those sizes are limited to 8 X 10 for graphical reasons. X in a row allows users to change the scanner size dynamically. That feature is already considered in code and is just missing in the graphic UI.</dd>
  <dt>Artificial Intelligence:</dt>
  <dd>Bots are having an offensive play. They first seek over rows and columns in horizontal way from left to right. If they find any line of SCANNER_SIZE/2 chips of its own color they select another cell in that array. If there is no line like described they try vertical, diagonal (left down to reight up) and diagonal2 (reight down to left up) next. If there can't be found an array with the necessary amount of own chips the bot goes to select any column randomly.</dd>
  <dt>Architecture:</dt>
  <dd>X in a row is implemented by the Model View Controller architecture pattern. This pattern allows to separate the business logic from user interactions. Each component provides an interface for communication reasons to outside components. Layers are loose coupled in order to make use from to Dependency Injection by Google Guice.</dd>
</dl>







