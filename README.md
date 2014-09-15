# imGraph

Attempt at implementing [imGraph](http://euranova.eu/upl_docs/publications/imgraph--a-distributed-in-memory-graph-database.pdf) paper. 

[![Build Status](https://travis-ci.org/ashwanthkumar/imgraph.svg?branch=master)](https://travis-ci.org/ashwanthkumar/imgraph)

### Query DSL

imGraph has a DSL for querying Graph data, inspired from Neo4J's [Cypher](http://docs.neo4j.org/chunked/stable/cypher-query-lang.html).

To enable the DSL you need to import `import in.ashwanthkumar.imgraph.types.DataConversions._` in your current context.

To find all lovers of Romeo
```
Query()
  .MATCH(("name" ~ "Romeo", "age" ~ 22)) --> LABEL("loves") RETURN ("name", "age")
```

Above query would

1. find the node that has "name"="Romeo" and "age" = 22
2. find the outgoing edges that says "loves"
3. return the "name" and "age" props of the resulting nodes


To match any of the multiple predicates, `|` can be used
```
Query()
  .MATCH(("name" ~ "Romeo", "age" ~ "21") | ("age" ~ 18, "name" ~ "Juliet")) <-- LABEL("loves") RETURN("name", "age")
```

Above query would

1. find the node that has `{"name": "Romeo", "age": 21}` or `{"age": 18, "name": "Juliet"}`
2. find the incoming edges that says "loves"
3. return the "name" and "age" props of the resulting nodes

To find more usages, check the [QueryTest.scala](https://github.com/ashwanthkumar/imgraph/blob/master/src/test/scala/in/ashwanthkumar/imgraph/query/QueryTest.scala).

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
