Backwash!!

If an open cell connected to the virtual Bottom and it percolates already, that open cell will be full, even if it actually shouldn't

**Solution 1: 100/100, no bonus**

Two unionFind, one has virtual Top and Bottom, the other only has virtual Top

See Submission_1

Failed the bonus memory check:

> Test 2 (bonus): check that total memory <= 11 n^2 + 128 n + 1024 bytes
>    -  failed memory test for n = 64

**Solution 2: 100/100, with bonus**

Not use virtual Bottom, use a separate grid (byte for saving space) to store four states:

1. close
2. open but not connect with bottom or top (not full)
3. open and connected to bottom (not full)
4. open and connect to top (full)

When a cell with state 3 connect with a cell with state 4, then it's percolated. Use a Boolean to store it. (edge case, grid size = 1, the cell is both 3 and 4)

See Submission_2