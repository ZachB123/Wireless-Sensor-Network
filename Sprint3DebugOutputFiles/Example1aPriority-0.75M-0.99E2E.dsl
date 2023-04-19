WARP program for graph Example1A
Scheduler Name: Priority
M: 0.75
E2E: 0.99
nChannels: 16
Time Slot	A	B	C
0	if has(F0) push(F0: A -> B, #1)	wait(#1)	sleep
1	wait(#2)	if has(F0) push(F0: B -> C, #2) else pull(F0: A -> B, #2)	wait(#2)
2	wait(#3)	if has(F0) push(F0: B -> C, #3) else pull(F0: A -> B, #3)	wait(#3)
3	wait(#4)	if has(F0) push(F0: B -> C, #4) else pull(F0: A -> B, #4)	wait(#4)
4	sleep	if has(F0) push(F0: B -> C, #5)	wait(#5)
5	sleep	if has(F0) push(F0: B -> C, #6)	wait(#6)
6	sleep	wait(#1)	if has(F1) push(F1: C -> B, #1)
7	wait(#12)	if has(F1) push(F1: B -> A, #12) else pull(F1: C -> B, #12)	wait(#12)
8	wait(#13)	if has(F1) push(F1: B -> A, #13) else pull(F1: C -> B, #13)	wait(#13)
9	wait(#14)	if has(F1) push(F1: B -> A, #14) else pull(F1: C -> B, #14)	wait(#14)
10	if has(F0) push(F0: A -> B, #5)	wait(#5)	sleep
11	wait(#7)	if has(F0) push(F0: B -> C, #7) else pull(F0: A -> B, #7)	wait(#7)
12	wait(#8)	if has(F0) push(F0: B -> C, #8) else pull(F0: A -> B, #8)	wait(#8)
13	wait(#9)	if has(F0) push(F0: B -> C, #9) else pull(F0: A -> B, #9)	wait(#9)
14	sleep	if has(F0) push(F0: B -> C, #10)	wait(#10)
15	sleep	if has(F0) push(F0: B -> C, #11)	wait(#11)
16	wait(#15)	if has(F1) push(F1: B -> A, #15)	sleep
17	wait(#0)	if has(F1) push(F1: B -> A, #0)	sleep
18	sleep	sleep	sleep
19	sleep	sleep	sleep
// All flows meet their deadlines
