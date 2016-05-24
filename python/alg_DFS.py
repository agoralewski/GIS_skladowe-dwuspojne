#-*- encoding: utf-8 -*-

class algorithm:

    def __init__(self):
        self.E = {(1,2),(2,3),(2,3),(1,4)}
        self.V = self.count_vertexes_from_edges(self.E)
        self.i = 0
        self.S = []
        self.num = dict()
        self.lowpt = dict()
        for x in self.V:
            self.num[x] = 0
        first_vertex = list(self.V)[0]
        for x in self.V:
            self.BICON(x, first_vertex)

    def BICON(self, v, u):
        self.i += 1
        self.num[v] = self.i
        self.lowpt[v] = self.i
        for w in self.Adj(v):
            if self.num[w]==0:
                # (v,w) jest krawędzią drzewa
                self.S.append((v,w))
                self.BICON(w,v)
                self.lowpt[v] = min(self.lowpt[v], self.lowpt[w])
                if self.lowpt[w] >= self.num[v]:
                    print "Rewolta! {}".format(v)
            elif self.num[w] < self.num[v] and w!=u:
                self.S.append((v,w))
                self.lowpt[v] = min(self.lowpt[v], self.num[w])

    def count_vertexes_from_edges(self, edges_list):
        return set(reduce(lambda x,y: x+y, edges_list))

    def Adj(self, v):
        adjancent_edges = [(x1, x2) for (x1, x2) in self.E if (x1==v)^(x2==v)]
        adjancent_vertexes = self.count_vertexes_from_edges(adjancent_edges)
        adjancent_vertexes.remove(v)
        return adjancent_vertexes
