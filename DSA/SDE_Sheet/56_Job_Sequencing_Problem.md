# Job Sequencing Problem

## Problem statement

- Given a set of N jobs where each job has a deadline and profit associated with it. Each job takes 1 unit of time to complete and only one job can be scheduled at a time. We earn the profit associated with job if and only if the job is completed by its deadline. Find the number of jobs done and the maximum profit. 

## Approach 1 : Greedy

Time complexity : O(N \* LogN + N \* M) 
Auxiliary space : O(deadline)

```cpp
// Perform job as late as possible with max profit
vector<int> JobScheduling(Job arr[], int n){ 
    int numberOfJobs = 0, maxProfit = 0, maxiDeadLine = -1;
    for(int i = 0 ; i < n ; i++)maxiDeadLine = max(maxiDeadLine, arr[i].dead);
    
    // Sorting based on profit
    sort(arr, arr + n, [](Job j1, Job j2){
        if(j1.profit > j2.profit)return true;
        return false;
    });
    // Filling the job array
    vector<int> schedule(maxiDeadLine + 1, -1);
    
    for(int i = 0 ; i < n ; i++){
        int id = arr[i].id, deadline = arr[i].dead, profit = arr[i].profit;
        
        for(int k = deadline ; k > 0 ; k--){
            if(schedule[k] == -1){
                schedule[k] = id;
                numberOfJobs++;
                maxProfit += profit;
                break;
            }
        }
    }
    
    return {numberOfJobs, maxProfit};
} 
```