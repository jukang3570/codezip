
from util import manhattanDistance
from game import Directions
import random, util

from game import Agent
from pacman import GameState

class ReflexAgent(Agent):
    """
    A reflex agent chooses an action at each choice point by examining
    its alternatives via a state evaluation function.

    The code below is provided as a guide.  You are welcome to change
    it in any way you see fit, so long as you don't touch our method
    headers.
    """


    def getAction(self, gameState: GameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {NORTH, SOUTH, WEST, EAST, STOP}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState: GameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        "*** YOUR CODE HERE ***"
        return successorGameState.getScore()

def scoreEvaluationFunction(currentGameState: GameState):
    """
    This default evaluation function just returns the score of the state.
    The score is the same one displayed in the Pacman GUI.

    This evaluation function is meant for use with adversarial search agents
    (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
    This class provides some common elements to all of your
    multi-agent searchers.  Any methods defined here will be available
    to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

    You *do not* need to make any changes here, but you can if you want to
    add functionality to all your adversarial search agents.  Please do not
    remove anything, however.

    Note: this is an abstract class: one that should not be instantiated.  It's
    only partially specified, and designed to be extended.  Agent (game.py)
    is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
    2번문제
    """
    def getAction(self, gameState):
        bestAction = None
        bestValue = float("-inf")
        alpha = float("-inf")
        step = gameState.getLegalActions(0)
        for action in step:
            successor = gameState.generateSuccessor(0, action)
            value = self.minValue(successor, 1, 0, alpha, float("inf"))
            if value > bestValue:
                bestValue = value
                bestAction = action
            alpha = max(alpha, bestValue)
        return bestAction

    def maxValue(self, state, agentIndex, depth, alpha, beta):
        step = state.getLegalActions(0)
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        v = float("-inf")
        for action in step:
            successor = state.generateSuccessor(agentIndex, action)
            v = max(v, self.minValue(successor, agentIndex + 1, depth, alpha, beta))
            if v > beta:
                return v
            alpha = max(alpha, v)
        return v

    def minValue(self, state, agentIndex, depth, alpha, beta):
        step = state.getLegalActions(agentIndex)
        if state.isWin() or state.isLose():
            return self.evaluationFunction(state)
        v = float("inf")
        numAgents = state.getNumAgents()
        if agentIndex == numAgents - 1:
            for action in step:
                successor = state.generateSuccessor(agentIndex, action)
                tmp = self.maxValue(successor, 0, depth+1, alpha, beta)
                v = min(v, tmp)
                if v < alpha:
                    return v
                beta = min(beta, v)
        else:
            for action in step:
                successor = state.generateSuccessor(agentIndex, action)
                tmp = self.minValue(successor, agentIndex + 1, depth, alpha, beta)
                v = min(v, tmp)
                if v < alpha:
                    return v
                beta = min(beta, v)
        return v
