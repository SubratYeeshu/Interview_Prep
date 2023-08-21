# Minimize Max Distance to Gas Station

## Problem statement 

- We have an horizontal number line. On that number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = size of the stations array. Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized. We have to find the smallest possible value of D. Find the answer exactly to 2 decimal places.

## Approac 1 : Linear Search

Time complexity : O(N^2) 
Space complexity : O(N)

```cpp
long double minimiseMaxDistance(vector<int> &arr, int k) {
    int n = arr.size(); //size of array.
    vector<int> howMany(n - 1, 0);

    //Pick and place k gas stations:
    for (int gasStations = 1; gasStations <= k; gasStations++) {
        //Find the maximum section
        //and insert the gas station:
        long double maxSection = -1;
        int maxInd = -1;
        for (int i = 0; i < n - 1; i++) {
            long double diff = arr[i + 1] - arr[i];
            long double sectionLength =
                diff / (long double)(howMany[i] + 1);
            if (sectionLength > maxSection) {
                maxSection = sectionLength;
                maxInd = i;
            }
        }
        //insert the current gas station:
        howMany[maxInd]++;
    }

    //Find the maximum distance i.e. the answer:
    long double maxAns = -1;
    for (int i = 0; i < n - 1; i++) {
        long double diff = arr[i + 1] - arr[i];
        long double sectionLength =
            diff / (long double)(howMany[i] + 1);
        maxAns = max(maxAns, sectionLength);
    }
    return maxAns;
}
```

## Approach 2 : Priority Queue

Time complexity : O(NLogN + KLogN) 
Space complexity : O(N)

```cpp
double findSmallestMaxDist(vector<int> &stations, int K){
    // Sections will be divided to put gas station
    
    int n = stations.size();
    priority_queue<pair<double, int>> pq;
    vector<int> numberOfGasStationInBetween (n - 1, 0);
    
    for(int i = 1 ; i < n ; i++){
        double diff = stations[i] - stations[i - 1];
        pq.push({diff, i - 1});
    }
    
    while(K != 0){
        double maxDistance = pq.top().first;
        int secIndex = pq.top().second;
        pq.pop();
        
        numberOfGasStationInBetween[secIndex]++;
        
        double initialDifference = stations[secIndex + 1] - stations[secIndex];
        double newSectorGap = (double)initialDifference / (double)(numberOfGasStationInBetween[secIndex] + 1);
        
        pq.push({newSectorGap, secIndex});
        K--;
    }
    
    return pq.top().first;
}
```

## Approach 3 : Binary Search

Time complexity : O(NLogN + KLogN) 
Space complexity : O(1)

```cpp
bool isValid(vector<int>& stations, int K, double distance, int n){
    int new_stations = 0;
    for(int i = 1 ; i < n ; i++)
        new_stations += floor((stations[i] - stations[i-1]) / distance);
        
    return new_stations <= K;
}
double findSmallestMaxDist(vector<int> &stations, int K){
    int n = stations.size();
    double start = 0, end = stations[n  - 1] - stations[0];
    while(end - start > 1e-6){
        double mid = start + (end - start) / 2.0;
        if(isValid(stations, K, mid, n))
            end = mid;
        else
            start = mid;
    }
    
    return start + 0.000001;
}
```