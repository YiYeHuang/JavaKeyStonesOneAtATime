## System design: Search Autocomplete System

Analysis
- base structure is trie tree
- contain both read and write operations
- search can be really related to the area
- search can be really related to personal
- usually exist in search engine
- separate from search function. if the service crashes, search operation is still available

### Request part
- the service should be wrapped in a API call 
- should set up the number of result that return for API call
- String[10] getAutoComplete(char input)

                                
                                   |----API server 1 
                                   |
    requests --> load balancer --> |----API server 2
                                   |
                                   |----API server 3 

API server 1 handles range A ~ H
API server 2 handles range I ~ P
API server 3 handles range Q ~ Z

when there are more requests coming, auto scaling should split the server with min cost
should use consistent hashing 

                                
                                   |----API server 1 
                                   |
                                   |----API server 2
    requests --> load balancer --> |
                                   |----API server 3
                                   |
                                   |----API server 4

API server 1 handles range A ~ D
API server 4 handles range E ~ H
API server 2 handles range I ~ P
API server 3 handles range Q ~ Z

### Store part
- it is nice that the result would be persist forever, but, as this is a helper service
- response time needs to be fast
- content needs to be refreshed fast
- use redis cluster (index character key mapping to a trie tree)


                                   |----API server 1 |
                                   |
                                   |----API server 2 |
    requests --> load balancer --> |                   -----> redis cluster 
                                   |----API server 3 |
                                   |
                                   |----API server 4 |

#### A B deployment 
- trie tree can be updated, write and read operation should be separated


                                   |----API server 1 |
                                   |
                                   |----API server 2 | ----- existing -----> read only redis
    requests --> load balancer --> |                   
                                   |----API server 3 | ----- nonexisting --> write only redis
                                   |
                                   |----API server 4 |

- after period, flopping the w/r redis, read redis copy over write redis into itself


### Opt 1, local host
Saving network cost and computing cost by build local trie tree to fill up some of the response
- String[10] getAutoComplete(char input) split to 
	- String[4] getAutoComplete(char input)
	- String[6] getAutoCompleteLocal(char input)


                                        |----API server 1 |
                 ---local trie tree     |
                 |                      |----API server 2 | ----- existing -----> read only redis
    requests ----> load balancer -----> |
                                        |----API server 3 | ----- nonexisting --> write only redis
                                        |
                                        |----API server 4 |

### Opt 2, data center
it is not likely that Japanese searching trend is similar to US trend



                                              |----API server 1 |
                                              |
                                              |----API server 2 | ----- existing -----> read only redis
                      - us load balancer ---> |
                      |                       |----API server 3 | ----- nonexisting --> write only redis
                      |                       |
                      |                       |----API server 4 |
    requests -----routing  
             |        |
             |        |
             |        |                       |----API server 1 |
             |        |                       |
             |        |                       |----API server 2 | ----- existing -----> read only redis
             |        - asia load balancer -> |
             |                                |----API server 3 | ----- nonexisting --> write only redis
             |                                |
             |                                |----API server 4 |
             |
             |
             |---local trie tree 
            