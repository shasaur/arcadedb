# arcadedb
Super Fast Multi-Model DBMS

## Differences between OrientDB and ArcadeDB

- ArcadeDB "types" are the "classes" in OrientDB
- ArcadeDB "buckets" are similar to the "clusters" in OrientDB
- ArcadeDB shares the same database instance across threads. Much easier developing with ArcadeDB than with OrientDB with multi-threads applications
- ArcadeDB uses thread locals only to manage transactions, while OrientDB makes a strong usage of TL internally, making hard to pass the db instance across threads and a pool is needed
- There is no base V and E classes in ArcadeDB, but vertex and edge are types of records
- ArcadeDB saves every type and property name in the dictionary to compress the record by storing only the names ids (varint)
- ArcadeDB keeps the MVCC counter on the page rather than on the record
- ArcadeDB manage everything as files and pages
- ArcadeDB allows custom page size per bucket/index
- ArcadeDB doesn't break record across pages, but rather create a placeholder pointing to the page that has the record. This allows the RID to be immutable without the complexity of managing splitted records

## What Arcade does not support

- ArcadeDB doesn't support storing records with a size major than the page size. You can always create a bucket with a larger page size, but this can be done only at creation time
- ArcadeDB cannot be replicated in a distributed system (yet, it's in the roadmap)
- ArcadeDB remote server supports only HTTP/JSON, no binary protocol is available
- ArcadeDB doesn't provide a dirty manager, so it's up to the developer to mark the object to save by calling `.save()` method. This makes the code of ArcadeDB smaller without handling edge cases

## What Arcade has more than OrientDB

- ArcadeDB saves every type and property name in the dictionary to compress the record by storing only the names ids
- ArcadeDB asynchronous API automatically balance the load on the available cores
- ArcadeDB is much Faster
- ArcadeDB uses much less RAM
- ArcadeDB allows to execute operation in asynchronously way (by using `.async()`)
- ArcadeDB is lighweight, the engine is <200Kb
