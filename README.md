# Pyramid-X-game

## Introduction

The Pyramid-X game,  Players guide a renowned archaeology professor through the mystical Pyramid of X, navigating various chamber types with the goal of reaching treasure chambers while avoiding traps and hazards.

## Learning Outcomes

Through the gameplay, participants will:

- Implement doubly linked lists
- Develop an extended stack abstract data type (ADT) utilizing doubly linked lists
- Utilize stacks to find a path in a dynamically generated map, symbolizing the pyramid

## Game Description

The Pyramid of X is divided into hexagonal chambers, each with unique properties:
- **Sealed Chambers**: Impassable, cannot be entered
- **Lighted Chambers**: Safe to enter due to visible traps from ample light
- **Dim Chambers**: Adjacent to lighted chambers, barely safe
- **Dark Chambers**: Unsafe with no adjacent lighted chambers, to be avoided
- **Entrance and Treasure Chambers**: Starting point and objectives of the game, both well-lit

Players must strategically navigate from the entrance chamber to all accessible treasure chambers without entering any sealed or dark chambers, adhering to pathfinding constraints prioritizing safety and treasure.

## Implementation

### Classes and Structures

1. **DLStack.java**: Implements a stack using a doubly linked list to track the path through the pyramid.
2. **PathFinder.java**: Contains the logic to compute the safest path to the treasure chambers using the stack.

### Pathfinding Algorithm

A custom algorithm is employed to determine the path:
- Start from the entrance and push it onto the stack.
- Use the stack to explore adjacent chambers, prioritizing treasure chambers and lighted paths.
- Retrace steps when no further moves are possible, popping chambers off the stack.

## Visual Representation

The game visually represents the pyramid and chambers, providing real-time feedback on pathfinding decisions and chamber statuses. This aids in understanding the underlying data structure manipulations and algorithmic choices.

