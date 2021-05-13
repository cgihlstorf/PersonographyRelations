## CS 106 Lab 7 - Deduplication (A pair programming lab)
## Choosing the partner
You are free to choose your partner for this lab i.e. you can choose to work with a student who you have already worked with or someone else. 

Member1: Caroline Gihlstorf

Member2: Jenna Krussman

Number of Late Days Using for this lab: 0

---
## Tasks: 
[Tasks are listed here](https://github.com/Haverford-College-USA/cs106-lab7/blob/master/Tasks.md)

## Files
The lab files are available [here as well](https://drive.google.com/drive/folders/1m3ZQEFMVSLzL-BC3qnRieLtZEk3r_u3e?usp=sharing) 

### Runtime Analysis

See questions in the lab writeup, answer below.

1. All Pairs: 137617.0 ms or 137.617 s in a medium file
   Hash Linear: 105.0 ms in a medium file
   Quicksort: 256.0 ms in a medium file
   
2. We have some spikes in our graphs do to other computer functions. Talked with Sara about this problem.    
   
3. Linear Hashmap Deduplication has the fastest run time. It has a run time of O(n), since it only uses non imbedded for loops and all the other operations are in constant time. Quicksort Deduplication has the next best runtime. It has a runtime of O(nlogn) because of the while loop which takes O(n) time and recursion which takes O(logn) time. All Pairs Deduplication has the logest runtime of O(n^2) since it uses imbedded for loops and in the worst case each for loop will go through the entire voterEntries list if there are no duplicates.

---

### Lab Questionnaire

(None of your answers below will affect your grade; this is to help refine lab
assignments in the future)

1. Approximately, how many hours did you take to complete this lab? (provide
  your answer as a single integer on the line below) 11

2. How difficult did you find this lab? (1-5, with 5 being very difficult and 1
  being very easy) 3

3. Describe the biggest challenge you faced on this lab: Our biggest challenge was figuring out our quicksort coding. We really struggled with its implementation.
