

from game import *
from learningAgents import ReinforcementAgent
from featureExtractors import *

import gridworld

import random
import util
import math
import copy


class QLearningAgent(ReinforcementAgent):
    """
    3번문제
    def __init__(self, **args):
        ReinforcementAgent.__init__(self, **args)

        self.qValues = util.Counter()
    def getQValue(self, state, action):
        if (state, action) not in self.qValues :
            return 0.0
        else:
            return self.qValues[state,action]

    def computeValueFromQValues(self, state):
        step = self.getLegalActions(state)
        scores = []
        if not step:
            return 0.0
        for action in step:
            scores.append(self.getQValue(state, action))
        return max(scores)

    def computeActionFromQValues(self, state):
        bestActions = []
        step = self.getLegalActions(state)
        if not step:
            return None
        maxQvalue = self.computeValueFromQValues(state)
        for action in step:
            if self.getQValue(state, action) == maxQvalue:
                bestActions.append(action)
        return max(bestActions)


    def getAction(self, state):
        step = self.getLegalActions(state)
        action = None
        if not step:
            return None
        if util.flipCoin(self.epsilon):
            action = step
        else:
            action = self.getPolicy(state)

        return action

    def update(self, state, action, nextState, reward):
        sample = reward + self.discount * self.computeValueFromQValues(nextState)
        self.qValues[(state, action)] = (1- self.alpha) * self.getQValue(state, action) + self.alpha * sample

    def getPolicy(self, state):
        return self.computeActionFromQValues(state)

    def getValue(self, state):
        return self.computeValueFromQValues(state)
    """
    """
    4번 문제 & 5번문제
    """
    def __init__(self, **args):
        ReinforcementAgent.__init__(self, **args)

        self.qValues = util.Counter()
    def getQValue(self, state, action):
        if (state, action) not in self.qValues:
            return 0.0
        else:
            return self.qValues[state,action]

    def computeValueFromQValues(self, state):
        step = self.getLegalActions(state)
        scores = []
        if not step:
            return 0.0
        for action in step:
            scores.append(self.getQValue(state, action))
        return max(scores)

    def computeActionFromQValues(self, state):
        bestActions = []
        step = self.getLegalActions(state)
        if not step:
            return None
        maxQvalue = self.computeValueFromQValues(state)
        for action in step:
            if self.getQValue(state, action) == maxQvalue:
                bestActions.append(action)
        return random.choice(bestActions)


    def getAction(self, state):
        step = self.getLegalActions(state)
        action = None
        if not step:
            return None
        if util.flipCoin(self.epsilon):
            action = random.choice(step)
        else:
            action = self.getPolicy(state)

        return action

    def update(self, state, action, nextState, reward):
        sample = reward + self.discount * self.computeValueFromQValues(nextState)
        self.qValues[(state, action)] = (1-self.alpha) * self.getQValue(state, action) + self.alpha * sample

    def getPolicy(self, state):
        return self.computeActionFromQValues(state)

    def getValue(self, state):
        return self.computeValueFromQValues(state)


class PacmanQAgent(QLearningAgent):
    "Exactly the same as QLearningAgent, but with different default parameters"

    def __init__(self, epsilon=0.05, gamma=0.8, alpha=0.2, numTraining=0, **args):
        """
        These default parameters can be changed from the pacman.py command line.
        For example, to change the exploration rate, try:
            python pacman.py -p PacmanQLearningAgent -a epsilon=0.1
        alpha    - learning rate
        epsilon  - exploration rate
        gamma    - discount factor
        numTraining - number of training episodes, i.e. no learning after these many episodes
        """
        args['epsilon'] = epsilon
        args['gamma'] = gamma
        args['alpha'] = alpha
        args['numTraining'] = numTraining
        self.index = 0  # This is always Pacman
        QLearningAgent.__init__(self, **args)

    def getAction(self, state):
        """
        Simply calls the getAction method of QLearningAgent and then
        informs parent of action for Pacman.  Do not change or remove this
        method.
        """
        action = QLearningAgent.getAction(self, state)
        self.doAction(state, action)
        return action


class ApproximateQAgent(PacmanQAgent):
    """
       6번 문제
    """

    def __init__(self, extractor='IdentityExtractor', **args):
        self.featExtractor = util.lookup(extractor, globals())()
        PacmanQAgent.__init__(self, **args)
        self.weights = util.Counter()

    def getWeights(self):
        return self.weights

    def getQValue(self, state, action):
        features = self.featExtractor.getFeatures(state, action)
        qValue = 0
        for feature, value in features.items():
            qValue += self.weights[feature] * value
        return qValue


    def update(self, state, action, nextState, reward: float):
        difference = (reward + self.discount * self.getValue(nextState)) - self.getQValue(state, action)
        features = self.featExtractor.getFeatures(state, action)
        for feature, value in features.items():
            self.weights[feature] += self.alpha * difference * value

    def final(self, state):
        # call the super-class final method
        PacmanQAgent.final(self, state)
