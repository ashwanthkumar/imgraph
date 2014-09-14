# imGraph

Attempt at implementing [imGraph](http://euranova.eu/upl_docs/publications/imgraph--a-distributed-in-memory-graph-database.pdf) paper. 

[![Build Status](https://travis-ci.org/ashwanthkumar/imgraph.svg?branch=master)](https://travis-ci.org/ashwanthkumar/imgraph)

### Notes

- Doesn't support undirected graphs
- Following use cases will be supported
    - Monodic Aggregations
    - Graph Traversals
    - ID based GETs
    - Property Search
- Good to have features
    - [Spark](https://spark.apache.org/) Connector to support Spark as computation platform on the graph
    - [Hama](https://hama.apache.org/) Vertex Reader to run graph computations on Hama from imGraph 
    - Use [Blueprints](http://blueprints.tinkerpop.com/) for property graph model
