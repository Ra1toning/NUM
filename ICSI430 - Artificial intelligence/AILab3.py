
from queue import PriorityQueue

class UndirectedGraph:
    def __init__(self, graph_dict):
        self.graph_dict = graph_dict

    def get_vertices(self):
        return list(self.graph_dict.keys())

    def get_edges(self):
        edges = []
        for node in self.graph_dict:
            for neighbor, weight in self.graph_dict[node].items():
                edges.append((node, neighbor, weight))
        return edges

def astar(graph, start, goal, heuristic):
    visited = set()
    queue = PriorityQueue()
    queue.put((0, start, [start], 0))
    while not queue.empty():
        cost, node, path,actual_cost = queue.get()
        if node not in visited:
            if node == goal:
                actual_cost = 0
                for idx,x in enumerate(path):
                    if idx == len(path)-1:
                        break
                    actual_cost += graph.graph_dict[x][path[idx+1]]
                return path, actual_cost, cost
            visited.add(node)
            for neighbor in graph.graph_dict[node]:
                if neighbor not in visited:
                    new_cost = actual_cost + graph.graph_dict[node][neighbor]
                    queue.put(((new_cost + heuristic(neighbor)), neighbor, path + [neighbor], new_cost))


romania_map = UndirectedGraph({

     'Arad': {'Zerind': 75, 'Sibiu': 140, 'Timisoara': 118},

     'Zerind': {'Arad': 75, 'Oradea': 71},

     'Oradea': {'Zerind': 71, 'Sibiu': 151},

     'Timisoara': {'Arad': 118, 'Lugoj': 111},

     'Lugoj': {'Timisoara': 111, 'Mehadia': 70},

     'Mehadia': {'Lugoj': 70, 'Drobeta': 75},

     'Drobeta': {'Mehadia': 75},

     'Sibiu': {'Arad': 140, 'Oradea': 151, 'Fagaras': 99, 'Rimnicu': 80},

     'Rimnicu': {'Sibiu': 80, 'Pitesti': 97},

     'Fagaras': {'Sibiu': 99, 'Bucharest': 211},

     'Pitesti': {'Rimnicu': 97, 'Bucharest': 101},

     'Bucharest': {'Fagaras': 211, 'Pitesti': 101, 'Giurgiu': 90, 'Urziceni': 85},

     'Giurgiu': {'Bucharest': 90},

     'Urziceni': {'Bucharest': 85, 'Hirsova': 98, 'Vaslui': 142},

     'Hirsova': {'Urziceni': 98, 'Eforie': 86},

     'Eforie': {'Hirsova': 86},

     'Vaslui': {'Iasi': 92, 'Urziceni': 142},

     'Iasi': {'Vaslui': 92, 'Neamt': 87},

     'Neamt': {'Iasi': 87}

})
def heuristic(node):
    euclidian_distance = {
    'Arad': 366,
    'Bucharest': 0,
    'Craiova': 160,
    'Drobeta': 242,
    'Eforie': 161,
    'Fagaras': 176,
    'Giurgiu': 77,
    'Hirsova': 151,
    'Iasi': 226,
    'Lugoj': 244,
    'Mehadia': 241,
    'Neamt': 234,
    'Oradea': 380,
    'Pitesti': 100,
    'Rimnicu': 193,
    'Sibiu': 253,
    'Timisoara': 329,
    'Urziceni': 80,
    'Vaslui': 199,
    'Zerind': 374
    }
    return euclidian_distance[node]
astar_path, astar_actual_cost,astar_cost = astar(romania_map, 'Arad', 'Bucharest', heuristic)
for x in astar_path:
    print(x, end=" > ")
print("A* Cost: ", astar_actual_cost)
