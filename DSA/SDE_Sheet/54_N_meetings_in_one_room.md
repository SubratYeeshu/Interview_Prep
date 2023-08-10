# N meetings in one room

## Problem statement  

- There is one meeting room in a firm. There are N meetings in the form of (start[i], end[i]) where start[i] is start time of meeting i and end[i] is finish time of meeting i. What is the maximum number of meetings that can be accommodated in the meeting room when only one meeting can be held in the meeting room at a particular time? 
- Note: Start time of one chosen meeting can't be equal to the end time of the other chosen meeting.

## Approach 1 : Using extra space

Time complexity : O(N) 
Auxiliary space : O(N)

```cpp
class meetingInfo{
    public: 
    int startTime;
    int endTime;
    int pos;
};

// The meeting with the least finishing time should be completed first
int maxMeetings(int start[], int end[], int n){
    int ans = 0;
    vector<meetingInfo> meetings (n);
    for(int i = 0 ; i < n ; i++){
        meetings[i].startTime = start[i];
        meetings[i].endTime = end[i];
        meetings[i].pos = i;
    }
    
    // Sorting based on end time and position
    sort(meetings.begin(), meetings.end(), [](meetingInfo m1, meetingInfo m2){
        if(m1.endTime < m2.endTime)return true;
        else if(m1.endTime > m2.endTime)return false;
        else if(m1.pos < m2.pos)return true;
        else return false;
    });
    
    // Storing the index for valid meetings for maximization
    vector<int> ansIdx; 
    int limit = meetings[0].endTime;
    ansIdx.push_back(meetings[0].pos);
    for(int i = 1 ; i < n ; i++){
        if(limit < meetings[i].startTime){
            limit = meetings[i].endTime;
            ansIdx.push_back(meetings[i].pos);
        }
    }
    
    return ansIdx.size();
}
```