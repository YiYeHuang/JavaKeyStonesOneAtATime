## System design: Load Balance notes

### Round Robin
Distributed the new request to the next available server

pro: easy algorithm

cons: all backend server are required to have similar resource to fully utilize this algorithm 

### Weighted Robin
Distributed the new request to the next available server based on initial assigned weight
E.g. a server with weight 5 vs a server weight 1 will have 5:1 request handle ratio

pro: Utilize stronger machine

cons: only works good on short connections. when some connections are long, and happened to all 
requested on the weaker machine, that server a more likely to go dead

### Least Connections
Distributed the new request to the server currently has the least connection

### Weight Least Connections
Distributed the new request to the server currently has the least connection and based on the server weight

### Random
Distributed the new request to a random server

### Consistent Hashing
For a safe scaling the avoid mod function that causing cascading failure
- consistent hashing ring: implementation example cassandra
- when a new server is added, only affecting one near by server