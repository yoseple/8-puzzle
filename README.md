# 8-puzzle
 The goal for this project was to write a program to solve the 8-puzzle problem and its natural generializations using the A* search algorith. In this I used the Best First search Algorithm where I defined a search node of the game to be a board,, the number of moves made to reach the board and the previous search node. How i did so was inserting the first inital search node (The initial board, 0moves , and a null previoous search node) into a prior queue. Then delete from the prioority queue the search node with the min prio and insert onto the prio queue all neighboring search nodes (those that can be reached in one move from the dequued search node). I repeated this procedure until the search node dequeued corresponds to a goal board. I used two priority functions called Hamming prioty which is the sum of number of tiles in the wrong positon and the Manhattan prio which is the sum of the vertical and horizontal distances from the tiles to their goal positions.
