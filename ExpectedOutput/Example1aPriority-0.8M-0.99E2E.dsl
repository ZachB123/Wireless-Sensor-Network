WARP program for graph Example1A
Scheduler Name: Priority
M: 0.8
E2E: 0.99
nChannels: 16
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)
2	wait(#3)	if has(F0) push(F0: B -> C, #3) else pull(F0: A -> B, #3)	wait(#3)
3	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)
4	sleep	if has(F0) push(F0: B -> C, #5)	wait(#5)
5	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)
6	wait(#10)	if has(F1) push(F1: B -> A, #10) else pull(F1: C -> B, #10)	wait(#10)
7	wait(#11)	if has(F1) push(F1: B -> A, #11) else pull(F1: C -> B, #11)	wait(#11)
8	wait(#12)	if has(F1) push(F1: B -> A, #12) else pull(F1: C -> B, #12)	wait(#12)
9	wait(#13)	if has(F1) push(F1: B -> A, #13)	sleep
10	if has(F0) push(F0: A -> B, #5)	wait(#5)	sleep
11	wait(#6)	if has(F0) push(F0: B -> C, #6) else pull(F0: A -> B, #6)	wait(#6)
12	wait(#7)	if has(F0) push(F0: B -> C, #7) else pull(F0: A -> B, #7)	wait(#7)
13	wait(#8)	if has(F0) push(F0: B -> C, #8) else pull(F0: A -> B, #8)	wait(#8)
14	sleep	if has(F0) push(F0: B -> C, #9)	wait(#9)
15	sleep	sleep	sleep
16	sleep	sleep	sleep
17	sleep	sleep	sleep
18	sleep	sleep	sleep
19	sleep	sleep	sleep
// All flows meet their deadlines
