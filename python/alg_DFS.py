#-*- coding: utf-8 -*-
import numpy as np

# Zagadka - a jak by tutaj reprezentować graf trywialny? :/ Generalnie na chwilę obecną nie obsługuję wierzchołków bez krawędzi

axes = ({0,1},{1,2},{2,3},{1,3},{3,4},{1,4},{4,5},{5,6})
#axes = ({0,1},{1,2},{0,2})




class DFS:

    def __init__(self, axes):
        self.axes = axes
        self.verticles_list = list(reduce(set.union, axes)) if len(axes)>1 else axes
        self.verticles_count = len(self.verticles_list)
        # wyliczanie macierzy sąsiedztwa - uwaga, od tego momentu będę się posługiwał numerami wierzchołków wg położenia w macierzy!
        self.adj_matrix = np.zeros((self.verticles_count, self.verticles_count),np.bool)
        for k,n in [a for a in axes if len(a)==2]:
            k_ind = self.verticles_list.index(k)
            n_ind = self.verticles_list.index(n)
            self.adj_matrix[k_ind, n_ind] = 1
            self.adj_matrix[n_ind, k_ind] = 1
        # przygotowywanie tablic na num i lowpt
        self.num = np.zeros(self.verticles_count,np.int8)
        self.lowpt = np.zeros(self.verticles_count,np.int8)
        self.i = 0
        self.stack = []
        # licznik skladowych dwuspojnych - tak dla picu
        self.two_coherent_components_counter = 0
        # slownik przeliczajacy numery wierzcholkow na etykiety
        self.verticles_to_labels_dict = dict(enumerate(self.verticles_list))

    def DepthFirstSearch(self):
        for x in range(self.verticles_count):
            if self.num[x]==0:
                self.VisitNode(x, None)
        print "number of twoCoherentComponents: {}".format(self.two_coherent_components_counter)

    def VisitNode(self, x, p):
        self.i += 1
        self.num[x] = self.i
        self.lowpt[x] = self.i
        #neighbours = reduce(set.union, [a.difference({u}) for a in axes if u in a])
        neighbours = [n for (n,k) in enumerate(self.adj_matrix[x,:]) if k and n!=x]
        for w in neighbours:
            if self.num[w]==0:
                self.stack.append({x, w})
                self.VisitNode(w, x)
                self.lowpt[x] = min(self.lowpt[x], self.lowpt[w])
                if self.lowpt[w] >= self.num[x]:
                    stack_index = self.stack.index({x,w})
                    nodes_in_component = reduce(set.union, self.stack[stack_index:])
                    nodes_in_component_formated = ", ".join(["Node{{name=\'{}\'}}".format(self.verticles_to_labels_dict[i]) for i in nodes_in_component])
                    print "TwoCoherentComponent{{Nodes=[{}], cutNode={}}}".format(nodes_in_component_formated, x)
                    self.stack = self.stack[:stack_index]
                    self.two_coherent_components_counter +=1
            elif self.num[w]<self.num[x] and w!=p:
                self.stack.append({x, w})
                self.lowpt[x] = min(self.lowpt[x], self.num[w])
