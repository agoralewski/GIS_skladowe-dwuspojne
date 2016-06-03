import sys

import networkx as nx
import matplotlib.pyplot as plt
from random import random
import re
#
if __name__ == "__main__":
    nodes_in_components = list()
    for i, line in enumerate(sys.stdin):
        nodes = re.findall(r"TwoCoherentComponent\{Nodes=\[(.*)\], cutNode='.*'\}", line)
        if nodes:
            nodes = re.findall(r"\'(.*?)\'(?:, )?", nodes[0])
        print nodes
        if nodes:
            nodes_in_components.append(nodes)



print nodes_in_components

