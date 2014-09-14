# imGraph

Attempt at implementing [imGraph](http://euranova.eu/upl_docs/publications/imgraph--a-distributed-in-memory-graph-database.pdf) paper. 

[![Build Status](https://travis-ci.org/ashwanthkumar/imgraph.svg?branch=master)](https://travis-ci.org/ashwanthkumar/imgraph)

### Query DSL

imGraph has a DSL for querying Graph data, inspired from Neo4J's [Cypher](http://docs.neo4j.org/chunked/stable/cypher-query-lang.html).

To find all lovers of Romeo
```
Query()
  .MATCH(("name" ~ "Romeo") | ("age" ~ 22)) --> LABEL("loves") RETURN ("name", "age")
```

Above query would

1. find the node that has "name"="Romeo" and "age" = 22
2. find the outgoing edges that says "loves"
3. return the "name" and "age" props of the resulting nodes


Similarly to find who all loves Romeo
```
Query()
  .MATCH(("name" ~ "Romeo") | ("age" ~ 22)) <-- LABEL("loves") RETURN("name", "age")
```

*Query DSL is still WIP. Any kind of feedback is welcome.*

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
