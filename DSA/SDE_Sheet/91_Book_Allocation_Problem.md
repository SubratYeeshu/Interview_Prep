# Allocate minimum number of pages

- You have N books, each with Ai number of pages. M students need to be allocated contiguous books, with each student getting at least one book. Out of all the permutations, the goal is to find the permutation where the student with the most books allocated to him gets the minimum number of pages, out of all possible permutations.

- Binary Search (Find min of max / max of min pattern)

## Approach 1 : Binary Search 

Time complexity : O(NLogN) 
Space complexity : O(1)

```cpp
int allotPages(int A[], int n, int pages){
    int p = pages, students = 1;
    for(int i = 0 ; i < n ; i++){
        if(p >= A[i]){
            p -= A[i];
        }else{
            p = pages;
            students++;
            if(p >= A[i]) p -= A[i];  
            else break;
        }
    }
    return students;
}

int findPages(int A[], int N, int M){
    // Binary Search on number of pages
    
    if(M > N)return -1;
    // sort(A, A + N);  Contiguos allocation
    int low = *max_element(A, A + N), high = accumulate(A, A + N, 0), ans = 0;
    
    while(low <= high){
        int mid = (low + high) / 2;
        
        
        int allotedStudents = allotPages(A, N, mid);
        if(allotedStudents <= M){  // Decrease the number of pages to adjust more peoples
            ans = mid;
            high = mid - 1;
        }else{
            low = mid + 1;
        }
    }
    
    return ans;
}
```