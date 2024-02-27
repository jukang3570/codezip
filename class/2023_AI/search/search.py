import util


class SearchProblem:

    def getStartState(self):

        util.raiseNotDefined()

    def isGoalState(self, state):

        util.raiseNotDefined()

    def getSuccessors(self, state):
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return [s, s, w, s, w, w, s, w]


def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0


def aStarSearch(problem: SearchProblem, heuristic=nullHeuristic):
    "*** 1번문제 CODE ***"
    start_state = problem.getStartState()
    priority_queue = util.PriorityQueue()
    priority_queue.push((start_state, [], 0), 0)

    visited = set()

    while not priority_queue.isEmpty():
        current_state, actions, cost = priority_queue.pop()

        if problem.isGoalState(current_state):
            return actions

        if current_state not in visited:
            visited.add(current_state)
            for next_state, action, step_cost in problem.getSuccessors(current_state):
                next_cost = cost + step_cost
                next_heuristic = heuristic(next_state, problem)
                priority = next_cost + next_heuristic
                priority_queue.push((next_state, actions + [action], next_cost), priority)

    return []


# Abbreviations
astar = aStarSearch
