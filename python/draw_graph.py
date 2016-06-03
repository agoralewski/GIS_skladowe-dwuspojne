#-*- encoding: utf-8 -*-
import sys
import networkx as nx
import matplotlib.pyplot as plt
from random import random
import re


if __name__ == "__main__":
    # wczytywanie listy krawedzi
    if len(sys.argv)<=1:
        print "Podaj nazwę pliku z listą krawędzi grafu"
        exit()

    with open(sys.argv[1],'r') as f:
        input_axes = f.readlines()
        input_axes = [tuple(i.replace('\r','').replace('\n','').split(' ')) for i in input_axes]

    # przetwarzanie outputu z algorytmu
    nodes_in_components = list()
    for i, line in enumerate(sys.stdin):
        nodes = re.findall(r"TwoCoherentComponent\{Nodes=\[(.*)\], cutNode='.*'\}", line)
        if nodes:
            nodes = re.findall(r"\'(.*?)\'(?:, )?", nodes[0])
        if nodes:
            nodes_in_components.append(nodes)
    # tworzenie grafu
    g = nx.Graph()
    colors = list()
    nodes_to_add = list()
    for nodes_in_component in nodes_in_components:
        color = (random(), random(), random())
        nodes_count = 0
        for node in nodes_in_component:
            if node not in nodes_to_add:
                #g.add_node(node)
                nodes_to_add.append(node)
                nodes_count += 1
        colors += [color for i in xrange(nodes_count)]
    g.add_nodes_from(nodes_to_add)
    g.add_edges_from(input_axes)
    nx.draw_circular(g, nodelist=nodes_to_add, node_color=colors) # jawnie specyfikuje nodelist, bo inaczej nie ma sensu podawac colors - zmieni sie kolejnosc
    plt.show()

