# Single Source Shortest Path Unweighted Graph

## Problem statement

Given a unweighted graph find the shortest path of all node from source S in linear time (not logarithmic - Dijkstra)

## Approach 1.1 : BFS

- Time Complexity : O(V + E) 
- Auxiliary Space: O(V)

```cpp
#include<bits/stdc++.h> 
using namespace std; 

void BFS(vector<int> adj[], int V, int s,int dist[]) { 
	bool visited[V]; 
	for(int i = 0; i < V; i++) 
		visited[i] = false; 

	queue<int>  q;
	
	visited[s] = true; 
	q.push(s); 

	while(q.empty()==false) 
	{ 
		int u = q.front(); 
		q.pop();
		 
		for(int v : adj[u]){
		    if(visited[v]==false){
		        dist[v]=dist[u]+1;
		        visited[v]=true;
		        q.push(v);
		    }
		} 
	} 
} 

void addEdge(vector<int> adj[], int u, int v){
    adj[u].push_back(v);
    adj[v].push_back(u);
}

int main() { 
	int V=4;
	vector<int> adj[V];
	addEdge(adj,0,1); 
	addEdge(adj,1,2); 
	addEdge(adj,2,3); 
	addEdge(adj,0,2); 
	addEdge(adj,1,3);
    int dist[V];
    for(int i=0;i<V;i++){
        dist[i]=INT_MAX;
    }
	dist[0]=0;
	BFS(adj,V,0,dist); 
    
    for(int i=0;i<V;i++){
        cout<<dist[i]<<" ";
    }

	return 0; 
} 
```

## Approach 1.2 : BFS + Printing

- Time Complexity : O(V + E) 
- Auxiliary Space: O(V)

```cpp
vector<int> shortestPath( vector<pair<int,int>> edges , int n , int m, int s , int t){ vector<int>adjList[n+1];

   for(int i=0;i<edges.size();i++){
        adjList[edges[i].first].push_back(edges[i].second);        
        adjList[edges[i].second].push_back(edges[i].first);
    }

      queue<pair<int, int>> pq;
      vector<int>dist(n+1, 1e9); // shortest distance vector    
      dist[s] = 0;
      vector<int>path(n+1, 1); // to store shortest paths  

      for(int i=1;i<=n;i++)path[i] = i; 

      vector<int>ans; //to store ans path  
      pq.push({0,s});
	  
	  while(pq.size()!=0){  
		int node = pq.front().second; 
		int dis = pq.front().first;
		pq.pop(); 
		for(auto it : adjList[node]){ 
			if(dis+1<dist[it]){   
				dist[it] = dis+1;  
				path[it] = node;  
				pq.push({dist[it],it});
			}
		}
	}

	int dest = t;
	while(path[dest]!=dest){ 
		ans.push_back(dest);   
		dest = path[dest]; 
	}
	ans.push_back(s);  
	reverse(ans.begin(),ans.end());   
	return ans; 
}  
```