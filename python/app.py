#-*- coding: utf-8 -*-
import sys
import alg_DFS

if len(sys.argv)<=1:
    print "Podaj nazwę pliku z listą krawędzi grafu"
    exit()

print "Przetwarzanie pliku: {}".format(sys.argv[1])

with open(sys.argv[1],'r') as f:
    input_axes = f.readlines()
    input_axes = [set(i.replace('\r','').replace('\n','').split(' ')) for i in input_axes]

d = alg_DFS.DFS(input_axes)
d.DepthFirstSearch()
