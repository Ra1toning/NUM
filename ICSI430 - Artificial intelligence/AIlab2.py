from queue import PriorityQueue

class UndirectedGraph:

     def __init__(self, graph_dict):
         self.graph_dict = graph_dict

     def get_nodes(self):
         return list(self.graph_dict.keys())

     def get_edges(self):
         edges = []

         for node in self.graph_dict:
             for neighbor, weight in self.graph_dict[node].items():
                 edges.append((node, neighbor, weight))
         return edges

def dfs(graph, start, goal):
    visited = set()
    stack = [(start, [start])]
    
    while stack:
        node, path = stack.pop()
        
        if node not in visited:
            if node == goal:
                return path
            
            visited.add(node)
            neighbors = graph.graph_dict[node]
            for neighbor in neighbors:
                if neighbor not in visited:
                    stack.append((neighbor, path + [neighbor]))
                    break 

def bfs(graph, start, goal):

     visited = set()
     queue = [(start, [start])]
     while queue:
         node, path = queue.pop(0)
         if node not in visited:
             if node == goal:
                 return path
                 
             visited.add(node)
             for neighbor in graph.graph_dict[node]:
                 if neighbor not in visited:
                     queue.append((neighbor, path + [neighbor]))

def ucs(graph, start, goal):

     visited = set()
     queue = PriorityQueue()
     queue.put((0, start, [start]))
     while not queue.empty():
         cost, node, path = queue.get()

         if node not in visited:
             if node == goal:
                 return path, cost

             visited.add(node)
             for neighbor in graph.graph_dict[node]:
                 if neighbor not in visited:
                     new_cost = cost + graph.graph_dict[node][neighbor]
                     queue.put((new_cost, neighbor, path + [neighbor]))

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

dfs_path = dfs(romania_map, 'Arad', 'Bucharest')

dfs_cost = 0

for i in range(len(dfs_path)-1):
     dfs_cost += romania_map.graph_dict[dfs_path[i]][dfs_path[i+1]]

for x in dfs_path:
     print(x, end=" > ")
print("Dfs Cost: ", dfs_cost)

bfs_path = bfs(romania_map, 'Arad', 'Bucharest')

bfs_cost = 0
for i in range(len(bfs_path)-1):
     bfs_cost += romania_map.graph_dict[bfs_path[i]][bfs_path[i+1]]

for x in bfs_path:
     print(x, end=" > ")
print("BFS Cost: ", bfs_cost)

ucs_path, ucs_cost = ucs(romania_map, 'Arad', 'Bucharest')

for x in ucs_path:
     print(x, end=" > ")
print("UCS Cost: ", ucs_cost)