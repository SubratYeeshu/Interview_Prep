# Heap

- Heap is a Complete Binary Tree (All the levels are completely filled except the lowest one, which is filled from the left) which have heap order priority.

- There are two types of heap Max - Heap, Min - Heap

- Can be implemented using array, for any node at ith index, child nodes are on (2\*i + 1) and (2\*i + 2) and for any child node its parent is present on the ((i - 1) / 2) index (0 based indexing). For (1 based indexing) child nodes are on (2\*i) and (2\*i + 1)index and the parent node on (i / 2) index.

- 