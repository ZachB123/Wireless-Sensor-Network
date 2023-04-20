WARP program for graph StressTest4
Scheduler Name: Priority
M: 0.9
E2E: 0.9
nChannels: 16
Time Slot	A	B	C	D	E	F	G	H	I	J	K	L
0	sleep	if has(F1) push(F1: B -> C, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
1	sleep	wait(#2)	if has(F1) push(F1: C -> D, #2) else pull(F1: B -> C, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
2	if has(F4) push(F4: A -> B, #1)	wait(#1)	if has(F1) push(F1: C -> D, #3)	wait(#3)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
3	if has(F4) push(F4: A -> B, #2)	wait(#2)	if has(F2) push(F2: C -> D, #0)	wait(#0)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
4	if has(F5) push(F5: A -> B, #9)	wait(#9)	wait(#1)	if has(F2) push(F2: D -> E, #1) else pull(F2: C -> D, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
5	sleep	if has(F4) push(F4: B -> C, #15)	wait(#15)	wait(#2)	if has(F2) push(F2: E -> F, #2) else pull(F2: D -> E, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	sleep
6	sleep	if has(F4) push(F4: B -> C, #0)	wait(#0)	wait(#3)	if has(F2) push(F2: E -> F, #3) else pull(F2: D -> E, #3)	if has(F2) push(F2: F -> G, #1) else wait(#3)	wait(#1)	sleep	sleep	sleep	sleep	sleep
7	if has(F5) push(F5: A -> B, #10)	wait(#10)	if has(F3) push(F3: C -> D, #12)	wait(#12)	wait(#2)	if has(F2) push(F2: F -> G, #2) else pull(F2: E -> F, #2)	if has(F2) push(F2: G -> H, #2) else wait(#2)	wait(#2)	sleep	sleep	sleep	sleep
8	if has(F7) push(F7: A -> B, #1)	wait(#1)	wait(#3)	if has(F3) push(F3: D -> E, #3) else pull(F3: C -> D, #3)	wait(#3)	wait(#3)	if has(F2) push(F2: G -> H, #3) else pull(F2: F -> G, #3)	if has(F2) push(F2: H -> I, #1) else wait(#3)	wait(#1)	sleep	sleep	sleep
9	sleep	if has(F4) push(F4: B -> C, #1)	wait(#1)	wait(#4)	if has(F3) push(F3: E -> J, #4) else pull(F3: D -> E, #4)	wait(#4)	if has(F2) push(F2: G -> H, #4) else pull(F2: F -> G, #4)	if has(F2) push(F2: H -> I, #2) else wait(#4)	wait(#2)	wait(#4)	sleep	sleep
10	sleep	if has(F5) push(F5: B -> C, #11)	wait(#11)	wait(#5)	if has(F3) push(F3: E -> J, #5) else pull(F3: D -> E, #5)	sleep	if has(F2) push(F2: G -> H, #5)	wait(#5)	sleep	if has(F3) push(F3: J -> K, #1) else wait(#5)	wait(#1)	sleep
11	if has(F7) push(F7: A -> B, #2)	wait(#2)	if has(F4) push(F4: C -> D, #8)	wait(#8)	wait(#2)	sleep	sleep	if has(F2) push(F2: H -> I, #3)	wait(#3)	if has(F3) push(F3: J -> K, #2) else pull(F3: E -> J, #2)	if has(F3) push(F3: K -> L, #2) else wait(#2)	wait(#2)
12	if has(F9) push(F9: A -> B, #7)	wait(#7)	wait(#5)	if has(F4) push(F4: D -> E, #5) else pull(F4: C -> D, #5)	wait(#5)	sleep	sleep	sleep	sleep	wait(#3)	if has(F3) push(F3: K -> L, #3) else pull(F3: J -> K, #3)	wait(#3)
13	if has(F9) push(F9: A -> B, #8)	wait(#8)	wait(#6)	if has(F4) push(F4: D -> E, #6) else pull(F4: C -> D, #6)	wait(#6)	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #4)	wait(#4)	sleep
14	sleep	sleep	wait(#7)	if has(F4) push(F4: D -> E, #7) else pull(F4: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #5)	wait(#5)
15	sleep	if has(F5) push(F5: B -> C, #12)	wait(#12)	if has(F4) push(F4: D -> E, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
16	sleep	if has(F5) push(F5: B -> C, #13)	wait(#13)	sleep	if has(F4) push(F4: E -> J, #6)	sleep	sleep	sleep	sleep	wait(#6)	sleep	sleep
17	sleep	sleep	if has(F5) push(F5: C -> D, #8)	wait(#8)	wait(#10)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #10) else pull(F4: E -> J, #10)	wait(#10)	sleep
18	sleep	sleep	if has(F5) push(F5: C -> D, #9)	wait(#9)	wait(#11)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #11) else pull(F4: E -> J, #11)	if has(F4) push(F4: K -> L, #11) else wait(#11)	wait(#11)
19	sleep	sleep	if has(F5) push(F5: C -> D, #10)	wait(#10)	wait(#12)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #12) else pull(F4: E -> J, #12)	if has(F4) push(F4: K -> L, #12) else wait(#12)	wait(#12)
20	sleep	if has(F1) push(F1: B -> C, #3)	wait(#3)	sleep	if has(F4) push(F4: E -> J, #10)	sleep	sleep	sleep	sleep	wait(#10)	if has(F4) push(F4: K -> L, #13)	wait(#13)
21	sleep	wait(#4)	if has(F1) push(F1: C -> D, #4) else pull(F1: B -> C, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #13)	wait(#13)	sleep
22	sleep	sleep	if has(F1) push(F1: C -> D, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
23	sleep	if has(F6) push(F6: B -> C, #8)	wait(#8)	if has(F5) push(F5: D -> E, #6)	wait(#6)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
24	sleep	if has(F6) push(F6: B -> C, #9)	wait(#9)	if has(F5) push(F5: D -> E, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
25	sleep	sleep	if has(F6) push(F6: C -> D, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
26	sleep	wait(#5)	if has(F6) push(F6: C -> D, #5) else pull(F7: B -> C, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
27	sleep	wait(#12)	if has(F7) push(F7: C -> D, #12) else pull(F7: B -> C, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
28	sleep	wait(#13)	if has(F7) push(F7: C -> D, #13) else pull(F7: B -> C, #13)	if has(F7) push(F7: D -> E, #14) else wait(#13)	wait(#14)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
29	sleep	wait(#14)	if has(F7) push(F7: C -> D, #14) else pull(F9: B -> C, #14)	wait(#14)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
30	sleep	if has(F9) push(F9: B -> C, #15)	wait(#15)	if has(F7) push(F7: D -> E, #15)	wait(#15)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
31	sleep	sleep	if has(F8) push(F8: C -> D, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
32	sleep	sleep	wait(#6)	if has(F8) push(F8: D -> E, #6) else pull(F8: C -> D, #6)	wait(#6)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
33	sleep	if has(F9) push(F9: B -> C, #0)	wait(#0)	wait(#10)	if has(F8) push(F8: E -> F, #10) else pull(F8: D -> E, #10)	wait(#10)	sleep	sleep	sleep	sleep	sleep	sleep
34	sleep	sleep	sleep	wait(#11)	if has(F8) push(F8: E -> F, #11) else pull(F8: D -> E, #11)	if has(F8) push(F8: F -> G, #11) else wait(#11)	wait(#11)	sleep	sleep	sleep	sleep	sleep
35	sleep	sleep	if has(F9) push(F9: C -> D, #12)	wait(#12)	wait(#12)	if has(F8) push(F8: F -> G, #12) else pull(F8: E -> F, #12)	if has(F8) push(F8: G -> H, #12) else wait(#12)	wait(#12)	sleep	sleep	sleep	sleep
36	sleep	sleep	wait(#15)	if has(F9) push(F9: D -> E, #15) else pull(F9: C -> D, #15)	wait(#15)	wait(#13)	if has(F8) push(F8: G -> H, #13) else pull(F8: F -> G, #13)	if has(F8) push(F8: H -> I, #3) else wait(#13)	wait(#3)	sleep	sleep	sleep
37	sleep	sleep	wait(#0)	if has(F9) push(F9: D -> E, #0) else pull(F9: C -> D, #0)	if has(F9) push(F9: E -> J, #3) else wait(#0)	wait(#14)	if has(F8) push(F8: G -> H, #14) else pull(F8: F -> G, #14)	if has(F8) push(F8: H -> I, #4) else wait(#14)	wait(#4)	wait(#3)	sleep	sleep
38	sleep	sleep	wait(#1)	if has(F9) push(F9: D -> E, #1) else pull(F9: C -> D, #1)	if has(F9) push(F9: E -> J, #4) else wait(#1)	sleep	if has(F8) push(F8: G -> H, #15)	wait(#15)	sleep	if has(F9) push(F9: J -> K, #11) else wait(#4)	wait(#11)	sleep
39	sleep	sleep	sleep	wait(#5)	if has(F9) push(F9: E -> J, #5) else pull(F9: D -> E, #5)	sleep	sleep	if has(F8) push(F8: H -> I, #5)	wait(#5)	if has(F9) push(F9: J -> K, #12) else wait(#5)	if has(F9) push(F9: K -> L, #10) else wait(#12)	wait(#10)
40	sleep	if has(F1) push(F1: B -> C, #5)	wait(#5)	sleep	wait(#13)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #13) else pull(F9: E -> J, #13)	if has(F9) push(F9: K -> L, #11) else wait(#13)	wait(#11)
41	sleep	wait(#6)	if has(F1) push(F1: C -> D, #6) else pull(F1: B -> C, #6)	wait(#6)	if has(F9) push(F9: E -> J, #7)	sleep	sleep	sleep	sleep	wait(#7)	if has(F9) push(F9: K -> L, #12)	wait(#12)
42	sleep	sleep	if has(F1) push(F1: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #14)	wait(#14)	sleep
43	sleep	sleep	if has(F10) push(F10: C -> D, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
44	sleep	sleep	wait(#11)	if has(F10) push(F10: D -> E, #11) else pull(F10: C -> D, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
45	sleep	sleep	sleep	wait(#2)	if has(F10) push(F10: E -> J, #2) else pull(F10: D -> E, #2)	sleep	sleep	sleep	sleep	wait(#2)	sleep	sleep
46	sleep	sleep	sleep	wait(#3)	if has(F10) push(F10: E -> J, #3) else pull(F10: D -> E, #3)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #7) else wait(#3)	wait(#7)	sleep
47	sleep	sleep	sleep	sleep	wait(#8)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #8) else pull(F10: E -> J, #8)	if has(F10) push(F10: K -> L, #3) else wait(#8)	wait(#3)
48	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	wait(#4)	if has(F10) push(F10: K -> L, #4) else pull(F10: J -> K, #4)	wait(#4)
49	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #10)	wait(#10)	sleep
50	sleep	sleep	if has(F2) push(F2: C -> D, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: K -> L, #5)	wait(#5)
51	sleep	sleep	wait(#4)	if has(F2) push(F2: D -> E, #4) else pull(F2: C -> D, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
52	sleep	sleep	sleep	wait(#5)	if has(F2) push(F2: E -> F, #5) else pull(F2: D -> E, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep
53	sleep	sleep	sleep	wait(#6)	if has(F2) push(F2: E -> F, #6) else pull(F2: D -> E, #6)	if has(F2) push(F2: F -> G, #6) else wait(#6)	wait(#6)	sleep	sleep	sleep	sleep	sleep
54	sleep	sleep	if has(F3) push(F3: C -> D, #14)	wait(#14)	wait(#7)	if has(F2) push(F2: F -> G, #7) else pull(F2: E -> F, #7)	if has(F2) push(F2: G -> H, #7) else wait(#7)	wait(#7)	sleep	sleep	sleep	sleep
55	sleep	sleep	wait(#6)	if has(F3) push(F3: D -> E, #6) else pull(F3: C -> D, #6)	wait(#6)	wait(#8)	if has(F2) push(F2: G -> H, #8) else pull(F2: F -> G, #8)	if has(F2) push(F2: H -> I, #4) else wait(#8)	wait(#4)	sleep	sleep	sleep
56	sleep	sleep	sleep	wait(#7)	if has(F3) push(F3: E -> J, #7) else pull(F3: D -> E, #7)	wait(#9)	if has(F2) push(F2: G -> H, #9) else pull(F2: F -> G, #9)	if has(F2) push(F2: H -> I, #5) else wait(#9)	wait(#5)	wait(#7)	sleep	sleep
57	sleep	sleep	sleep	wait(#8)	if has(F3) push(F3: E -> J, #8) else pull(F3: D -> E, #8)	sleep	if has(F2) push(F2: G -> H, #10)	wait(#10)	sleep	if has(F3) push(F3: J -> K, #5) else wait(#8)	wait(#5)	sleep
58	sleep	sleep	sleep	sleep	wait(#6)	sleep	sleep	if has(F2) push(F2: H -> I, #6)	wait(#6)	if has(F3) push(F3: J -> K, #6) else pull(F3: E -> J, #6)	if has(F3) push(F3: K -> L, #6) else wait(#6)	wait(#6)
59	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	wait(#7)	if has(F3) push(F3: K -> L, #7) else pull(F3: J -> K, #7)	wait(#7)
60	sleep	if has(F1) push(F1: B -> C, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #8)	wait(#8)	sleep
61	sleep	wait(#8)	if has(F1) push(F1: C -> D, #8) else pull(F1: B -> C, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #9)	wait(#9)
62	sleep	sleep	if has(F1) push(F1: C -> D, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
63	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
64	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
65	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
66	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
67	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
68	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
69	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
70	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
71	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
72	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
73	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
74	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
75	if has(F4) push(F4: A -> B, #3)	wait(#3)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
76	wait(#2)	if has(F4) push(F4: B -> C, #2) else pull(F4: A -> B, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
77	sleep	wait(#12)	if has(F4) push(F4: C -> D, #12) else pull(F4: B -> C, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
78	sleep	wait(#13)	if has(F4) push(F4: C -> D, #13) else pull(F4: B -> C, #13)	if has(F4) push(F4: D -> E, #9) else wait(#13)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
79	if has(F5) push(F5: A -> B, #11)	wait(#11)	wait(#10)	if has(F4) push(F4: D -> E, #10) else pull(F4: C -> D, #10)	if has(F4) push(F4: E -> J, #11) else wait(#10)	sleep	sleep	sleep	sleep	wait(#11)	sleep	sleep
80	sleep	if has(F1) push(F1: B -> C, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
81	sleep	wait(#10)	if has(F1) push(F1: C -> D, #10) else pull(F1: B -> C, #10)	wait(#10)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
82	if has(F5) push(F5: A -> B, #12)	wait(#12)	if has(F1) push(F1: C -> D, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
83	sleep	sleep	wait(#12)	if has(F4) push(F4: D -> E, #12) else pull(F4: C -> D, #12)	if has(F4) push(F4: E -> J, #12) else wait(#12)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #14) else wait(#12)	wait(#14)	sleep
84	sleep	if has(F5) push(F5: B -> C, #14)	wait(#14)	wait(#13)	if has(F4) push(F4: E -> J, #13) else pull(F4: D -> E, #13)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #15) else wait(#13)	if has(F4) push(F4: K -> L, #15) else wait(#15)	wait(#15)
85	sleep	wait(#11)	if has(F5) push(F5: C -> D, #11) else pull(F5: B -> C, #11)	wait(#11)	wait(#0)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #0) else pull(F4: E -> J, #0)	if has(F4) push(F4: K -> L, #0) else wait(#0)	wait(#0)
86	sleep	wait(#12)	if has(F5) push(F5: C -> D, #12) else pull(F5: B -> C, #12)	wait(#12)	if has(F4) push(F4: E -> J, #15)	sleep	sleep	sleep	sleep	wait(#15)	if has(F4) push(F4: K -> L, #1)	wait(#1)
87	sleep	sleep	if has(F5) push(F5: C -> D, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #1)	wait(#1)	sleep
88	sleep	if has(F6) push(F6: B -> C, #10)	wait(#10)	if has(F5) push(F5: D -> E, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
89	sleep	if has(F6) push(F6: B -> C, #11)	wait(#11)	if has(F5) push(F5: D -> E, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
90	sleep	sleep	if has(F6) push(F6: C -> D, #6)	wait(#6)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
91	sleep	sleep	if has(F6) push(F6: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
92	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
93	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
94	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
95	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
96	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
97	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
98	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
99	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
100	sleep	if has(F1) push(F1: B -> C, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
101	sleep	wait(#12)	if has(F1) push(F1: C -> D, #12) else pull(F1: B -> C, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
102	if has(F7) push(F7: A -> B, #3)	wait(#3)	if has(F1) push(F1: C -> D, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
103	if has(F7) push(F7: A -> B, #4)	wait(#4)	if has(F2) push(F2: C -> D, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
104	if has(F9) push(F9: A -> B, #9)	wait(#9)	wait(#7)	if has(F2) push(F2: D -> E, #7) else pull(F2: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
105	sleep	if has(F7) push(F7: B -> C, #8)	wait(#8)	wait(#8)	if has(F2) push(F2: E -> F, #8) else pull(F2: D -> E, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep
106	sleep	if has(F7) push(F7: B -> C, #9)	wait(#9)	wait(#9)	if has(F2) push(F2: E -> F, #9) else pull(F2: D -> E, #9)	if has(F2) push(F2: F -> G, #10) else wait(#9)	wait(#10)	sleep	sleep	sleep	sleep	sleep
107	if has(F9) push(F9: A -> B, #10)	wait(#10)	if has(F3) push(F3: C -> D, #0)	wait(#0)	wait(#11)	if has(F2) push(F2: F -> G, #11) else pull(F2: E -> F, #11)	if has(F2) push(F2: G -> H, #11) else wait(#11)	wait(#11)	sleep	sleep	sleep	sleep
108	sleep	sleep	wait(#9)	if has(F3) push(F3: D -> E, #9) else pull(F3: C -> D, #9)	wait(#9)	wait(#12)	if has(F2) push(F2: G -> H, #12) else pull(F2: F -> G, #12)	if has(F2) push(F2: H -> I, #7) else wait(#12)	wait(#7)	sleep	sleep	sleep
109	sleep	if has(F7) push(F7: B -> C, #10)	wait(#10)	wait(#10)	if has(F3) push(F3: E -> J, #10) else pull(F3: D -> E, #10)	wait(#13)	if has(F2) push(F2: G -> H, #13) else pull(F2: F -> G, #13)	if has(F2) push(F2: H -> I, #8) else wait(#13)	wait(#8)	wait(#10)	sleep	sleep
110	sleep	if has(F9) push(F9: B -> C, #1)	wait(#1)	wait(#11)	if has(F3) push(F3: E -> J, #11) else pull(F3: D -> E, #11)	sleep	if has(F2) push(F2: G -> H, #14)	wait(#14)	sleep	if has(F3) push(F3: J -> K, #9) else wait(#11)	wait(#9)	sleep
111	sleep	sleep	if has(F7) push(F7: C -> D, #15)	wait(#15)	wait(#10)	sleep	sleep	if has(F2) push(F2: H -> I, #9)	wait(#9)	if has(F3) push(F3: J -> K, #10) else pull(F3: E -> J, #10)	if has(F3) push(F3: K -> L, #10) else wait(#10)	wait(#10)
112	sleep	sleep	wait(#0)	if has(F7) push(F7: D -> E, #0) else pull(F7: C -> D, #0)	wait(#0)	sleep	sleep	sleep	sleep	wait(#11)	if has(F3) push(F3: K -> L, #11) else pull(F3: J -> K, #11)	wait(#11)
113	sleep	wait(#1)	if has(F7) push(F7: C -> D, #1) else pull(F9: B -> C, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #12)	wait(#12)	sleep
114	sleep	if has(F9) push(F9: B -> C, #2)	wait(#2)	if has(F7) push(F7: D -> E, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #13)	wait(#13)
115	sleep	sleep	if has(F8) push(F8: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
116	sleep	sleep	wait(#9)	if has(F8) push(F8: D -> E, #9) else pull(F8: C -> D, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
117	sleep	sleep	sleep	wait(#13)	if has(F8) push(F8: E -> F, #13) else pull(F8: D -> E, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep
118	sleep	sleep	sleep	wait(#14)	if has(F8) push(F8: E -> F, #14) else pull(F8: D -> E, #14)	if has(F8) push(F8: F -> G, #15) else wait(#14)	wait(#15)	sleep	sleep	sleep	sleep	sleep
119	sleep	sleep	if has(F9) push(F9: C -> D, #0)	wait(#0)	wait(#0)	if has(F8) push(F8: F -> G, #0) else pull(F8: E -> F, #0)	if has(F8) push(F8: G -> H, #0) else wait(#0)	wait(#0)	sleep	sleep	sleep	sleep
120	sleep	if has(F1) push(F1: B -> C, #13)	wait(#13)	sleep	sleep	wait(#1)	if has(F8) push(F8: G -> H, #1) else pull(F8: F -> G, #1)	if has(F8) push(F8: H -> I, #6) else wait(#1)	wait(#6)	sleep	sleep	sleep
121	sleep	wait(#14)	if has(F1) push(F1: C -> D, #14) else pull(F1: B -> C, #14)	wait(#14)	sleep	wait(#2)	if has(F8) push(F8: G -> H, #2) else pull(F8: F -> G, #2)	if has(F8) push(F8: H -> I, #7) else wait(#2)	wait(#7)	sleep	sleep	sleep
122	sleep	sleep	if has(F1) push(F1: C -> D, #15)	wait(#15)	sleep	sleep	if has(F8) push(F8: G -> H, #3)	wait(#3)	sleep	sleep	sleep	sleep
123	sleep	sleep	wait(#3)	if has(F9) push(F9: D -> E, #3) else pull(F9: C -> D, #3)	wait(#3)	sleep	sleep	if has(F8) push(F8: H -> I, #8)	wait(#8)	sleep	sleep	sleep
124	sleep	sleep	wait(#4)	if has(F9) push(F9: D -> E, #4) else pull(F9: C -> D, #4)	if has(F9) push(F9: E -> J, #8) else wait(#4)	sleep	sleep	sleep	sleep	wait(#8)	sleep	sleep
125	sleep	sleep	wait(#5)	if has(F9) push(F9: D -> E, #5) else pull(F9: C -> D, #5)	if has(F9) push(F9: E -> J, #9) else wait(#5)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #15) else wait(#9)	wait(#15)	sleep
126	sleep	sleep	sleep	wait(#10)	if has(F9) push(F9: E -> J, #10) else pull(F9: D -> E, #10)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #0) else wait(#10)	if has(F9) push(F9: K -> L, #13) else wait(#0)	wait(#13)
127	sleep	sleep	if has(F10) push(F10: C -> D, #11)	wait(#11)	wait(#1)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #1) else pull(F9: E -> J, #1)	if has(F9) push(F9: K -> L, #14) else wait(#1)	wait(#14)
128	sleep	sleep	if has(F10) push(F10: C -> D, #12)	wait(#12)	if has(F9) push(F9: E -> J, #12)	sleep	sleep	sleep	sleep	wait(#12)	if has(F9) push(F9: K -> L, #15)	wait(#15)
129	sleep	sleep	sleep	if has(F10) push(F10: D -> E, #14)	wait(#14)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #2)	wait(#2)	sleep
130	sleep	sleep	sleep	wait(#5)	if has(F10) push(F10: E -> J, #5) else pull(F10: D -> E, #5)	sleep	sleep	sleep	sleep	wait(#5)	sleep	sleep
131	sleep	sleep	sleep	wait(#6)	if has(F10) push(F10: E -> J, #6) else pull(F10: D -> E, #6)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #11) else wait(#6)	wait(#11)	sleep
132	sleep	sleep	sleep	sleep	wait(#12)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #12) else pull(F10: E -> J, #12)	if has(F10) push(F10: K -> L, #6) else wait(#12)	wait(#6)
133	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	wait(#7)	if has(F10) push(F10: K -> L, #7) else pull(F10: J -> K, #7)	wait(#7)
134	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #14)	wait(#14)	sleep
135	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: K -> L, #8)	wait(#8)
136	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
137	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
138	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
139	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
140	sleep	if has(F1) push(F1: B -> C, #15)	wait(#15)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
141	sleep	wait(#0)	if has(F1) push(F1: C -> D, #0) else pull(F1: B -> C, #0)	wait(#0)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
142	sleep	sleep	if has(F1) push(F1: C -> D, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
143	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
144	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
145	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
146	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
147	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
148	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
149	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
150	if has(F4) push(F4: A -> B, #5)	wait(#5)	if has(F2) push(F2: C -> D, #6)	wait(#6)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
151	if has(F4) push(F4: A -> B, #6)	wait(#6)	wait(#10)	if has(F2) push(F2: D -> E, #10) else pull(F2: C -> D, #10)	wait(#10)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
152	sleep	if has(F4) push(F4: B -> C, #5)	wait(#5)	wait(#11)	if has(F2) push(F2: E -> F, #11) else pull(F2: D -> E, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep
153	sleep	if has(F4) push(F4: B -> C, #6)	wait(#6)	wait(#12)	if has(F2) push(F2: E -> F, #12) else pull(F2: D -> E, #12)	if has(F2) push(F2: F -> G, #14) else wait(#12)	wait(#14)	sleep	sleep	sleep	sleep	sleep
154	if has(F5) push(F5: A -> B, #13)	wait(#13)	if has(F3) push(F3: C -> D, #2)	wait(#2)	wait(#15)	if has(F2) push(F2: F -> G, #15) else pull(F2: E -> F, #15)	if has(F2) push(F2: G -> H, #15) else wait(#15)	wait(#15)	sleep	sleep	sleep	sleep
155	if has(F5) push(F5: A -> B, #14)	wait(#14)	wait(#12)	if has(F3) push(F3: D -> E, #12) else pull(F3: C -> D, #12)	wait(#12)	wait(#0)	if has(F2) push(F2: G -> H, #0) else pull(F2: F -> G, #0)	if has(F2) push(F2: H -> I, #10) else wait(#0)	wait(#10)	sleep	sleep	sleep
156	sleep	if has(F4) push(F4: B -> C, #7)	wait(#7)	wait(#13)	if has(F3) push(F3: E -> J, #13) else pull(F3: D -> E, #13)	wait(#1)	if has(F2) push(F2: G -> H, #1) else pull(F2: F -> G, #1)	if has(F2) push(F2: H -> I, #11) else wait(#1)	wait(#11)	wait(#13)	sleep	sleep
157	sleep	if has(F5) push(F5: B -> C, #1)	wait(#1)	wait(#14)	if has(F3) push(F3: E -> J, #14) else pull(F3: D -> E, #14)	sleep	if has(F2) push(F2: G -> H, #2)	wait(#2)	sleep	if has(F3) push(F3: J -> K, #14) else wait(#14)	wait(#14)	sleep
158	sleep	sleep	if has(F4) push(F4: C -> D, #0)	wait(#0)	wait(#15)	sleep	sleep	if has(F2) push(F2: H -> I, #12)	wait(#12)	if has(F3) push(F3: J -> K, #15) else pull(F3: E -> J, #15)	if has(F3) push(F3: K -> L, #15) else wait(#15)	wait(#15)
159	sleep	sleep	wait(#14)	if has(F4) push(F4: D -> E, #14) else pull(F4: C -> D, #14)	wait(#14)	sleep	sleep	sleep	sleep	wait(#0)	if has(F3) push(F3: K -> L, #0) else pull(F3: J -> K, #0)	wait(#0)
160	sleep	if has(F1) push(F1: B -> C, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #1)	wait(#1)	sleep
161	sleep	wait(#2)	if has(F1) push(F1: C -> D, #2) else pull(F1: B -> C, #2)	wait(#2)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #2)	wait(#2)
162	sleep	sleep	if has(F1) push(F1: C -> D, #3)	wait(#3)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
163	sleep	sleep	wait(#15)	if has(F4) push(F4: D -> E, #15) else pull(F4: C -> D, #15)	if has(F4) push(F4: E -> J, #0) else wait(#15)	sleep	sleep	sleep	sleep	wait(#0)	sleep	sleep
164	sleep	sleep	wait(#0)	if has(F4) push(F4: D -> E, #0) else pull(F4: C -> D, #0)	if has(F4) push(F4: E -> J, #1) else wait(#0)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #2) else wait(#1)	wait(#2)	sleep
165	sleep	if has(F5) push(F5: B -> C, #2)	wait(#2)	wait(#2)	if has(F4) push(F4: E -> J, #2) else pull(F4: D -> E, #2)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #3) else wait(#2)	if has(F4) push(F4: K -> L, #3) else wait(#3)	wait(#3)
166	sleep	if has(F5) push(F5: B -> C, #3)	wait(#3)	sleep	wait(#4)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #4) else pull(F4: E -> J, #4)	if has(F4) push(F4: K -> L, #4) else wait(#4)	wait(#4)
167	sleep	sleep	if has(F5) push(F5: C -> D, #14)	wait(#14)	if has(F4) push(F4: E -> J, #4)	sleep	sleep	sleep	sleep	wait(#4)	if has(F4) push(F4: K -> L, #5)	wait(#5)
168	sleep	sleep	wait(#10)	if has(F5) push(F5: D -> E, #10) else pull(F5: C -> D, #10)	wait(#10)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #5)	wait(#5)	sleep
169	sleep	sleep	if has(F5) push(F5: C -> D, #0)	wait(#0)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
170	sleep	if has(F6) push(F6: B -> C, #12)	wait(#12)	if has(F5) push(F5: D -> E, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
171	sleep	wait(#8)	if has(F6) push(F6: C -> D, #8) else pull(F6: B -> C, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
172	sleep	sleep	if has(F6) push(F6: C -> D, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
173	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
174	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
175	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
176	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
177	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
178	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
179	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
180	sleep	if has(F1) push(F1: B -> C, #3)	wait(#3)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
181	sleep	wait(#4)	if has(F1) push(F1: C -> D, #4) else pull(F1: B -> C, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
182	sleep	sleep	if has(F1) push(F1: C -> D, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
183	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
184	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
185	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
186	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
187	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
188	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
189	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
190	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
191	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
192	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
193	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
194	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
195	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
196	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
197	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
198	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
199	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
200	sleep	if has(F1) push(F1: B -> C, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
201	sleep	wait(#6)	if has(F1) push(F1: C -> D, #6) else pull(F1: B -> C, #6)	wait(#6)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
202	if has(F7) push(F7: A -> B, #5)	wait(#5)	if has(F1) push(F1: C -> D, #7)	wait(#7)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
203	if has(F7) push(F7: A -> B, #6)	wait(#6)	if has(F2) push(F2: C -> D, #8)	wait(#8)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
204	if has(F9) push(F9: A -> B, #11)	wait(#11)	wait(#13)	if has(F2) push(F2: D -> E, #13) else pull(F2: C -> D, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
205	sleep	if has(F7) push(F7: B -> C, #11)	wait(#11)	wait(#14)	if has(F2) push(F2: E -> F, #14) else pull(F2: D -> E, #14)	wait(#14)	sleep	sleep	sleep	sleep	sleep	sleep
206	sleep	if has(F7) push(F7: B -> C, #12)	wait(#12)	wait(#15)	if has(F2) push(F2: E -> F, #15) else pull(F2: D -> E, #15)	if has(F2) push(F2: F -> G, #2) else wait(#15)	wait(#2)	sleep	sleep	sleep	sleep	sleep
207	if has(F9) push(F9: A -> B, #13)	wait(#13)	if has(F3) push(F3: C -> D, #4)	wait(#4)	wait(#3)	if has(F2) push(F2: F -> G, #3) else pull(F2: E -> F, #3)	if has(F2) push(F2: G -> H, #3) else wait(#3)	wait(#3)	sleep	sleep	sleep	sleep
208	sleep	sleep	wait(#15)	if has(F3) push(F3: D -> E, #15) else pull(F3: C -> D, #15)	wait(#15)	wait(#4)	if has(F2) push(F2: G -> H, #4) else pull(F2: F -> G, #4)	if has(F2) push(F2: H -> I, #13) else wait(#4)	wait(#13)	sleep	sleep	sleep
209	sleep	if has(F7) push(F7: B -> C, #13)	wait(#13)	wait(#0)	if has(F3) push(F3: E -> J, #0) else pull(F3: D -> E, #0)	wait(#5)	if has(F2) push(F2: G -> H, #5) else pull(F2: F -> G, #5)	if has(F2) push(F2: H -> I, #14) else wait(#5)	wait(#14)	wait(#0)	sleep	sleep
210	sleep	if has(F9) push(F9: B -> C, #3)	wait(#3)	wait(#1)	if has(F3) push(F3: E -> J, #1) else pull(F3: D -> E, #1)	sleep	if has(F2) push(F2: G -> H, #6)	wait(#6)	sleep	if has(F3) push(F3: J -> K, #2) else wait(#1)	wait(#2)	sleep
211	sleep	sleep	if has(F7) push(F7: C -> D, #2)	wait(#2)	wait(#3)	sleep	sleep	if has(F2) push(F2: H -> I, #15)	wait(#15)	if has(F3) push(F3: J -> K, #3) else pull(F3: E -> J, #3)	if has(F3) push(F3: K -> L, #3) else wait(#3)	wait(#3)
212	sleep	sleep	wait(#4)	if has(F7) push(F7: D -> E, #4) else pull(F7: C -> D, #4)	wait(#4)	sleep	sleep	sleep	sleep	wait(#4)	if has(F3) push(F3: K -> L, #4) else pull(F3: J -> K, #4)	wait(#4)
213	sleep	wait(#4)	if has(F7) push(F7: C -> D, #4) else pull(F9: B -> C, #4)	wait(#4)	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #5)	wait(#5)	sleep
214	sleep	if has(F9) push(F9: B -> C, #5)	wait(#5)	if has(F7) push(F7: D -> E, #5)	wait(#5)	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #6)	wait(#6)
215	sleep	sleep	if has(F8) push(F8: C -> D, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
216	sleep	sleep	wait(#12)	if has(F8) push(F8: D -> E, #12) else pull(F8: C -> D, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
217	sleep	sleep	sleep	wait(#0)	if has(F8) push(F8: E -> F, #0) else pull(F8: D -> E, #0)	wait(#0)	sleep	sleep	sleep	sleep	sleep	sleep
218	sleep	sleep	sleep	wait(#1)	if has(F8) push(F8: E -> F, #1) else pull(F8: D -> E, #1)	if has(F8) push(F8: F -> G, #3) else wait(#1)	wait(#3)	sleep	sleep	sleep	sleep	sleep
219	sleep	sleep	if has(F9) push(F9: C -> D, #4)	wait(#4)	wait(#4)	if has(F8) push(F8: F -> G, #4) else pull(F8: E -> F, #4)	if has(F8) push(F8: G -> H, #4) else wait(#4)	wait(#4)	sleep	sleep	sleep	sleep
220	sleep	if has(F1) push(F1: B -> C, #7)	wait(#7)	sleep	sleep	wait(#5)	if has(F8) push(F8: G -> H, #5) else pull(F8: F -> G, #5)	if has(F8) push(F8: H -> I, #9) else wait(#5)	wait(#9)	sleep	sleep	sleep
221	sleep	wait(#8)	if has(F1) push(F1: C -> D, #8) else pull(F1: B -> C, #8)	wait(#8)	sleep	wait(#6)	if has(F8) push(F8: G -> H, #6) else pull(F8: F -> G, #6)	if has(F8) push(F8: H -> I, #10) else wait(#6)	wait(#10)	sleep	sleep	sleep
222	sleep	sleep	if has(F1) push(F1: C -> D, #9)	wait(#9)	sleep	sleep	if has(F8) push(F8: G -> H, #7)	wait(#7)	sleep	sleep	sleep	sleep
223	sleep	sleep	wait(#7)	if has(F9) push(F9: D -> E, #7) else pull(F9: C -> D, #7)	wait(#7)	sleep	sleep	if has(F8) push(F8: H -> I, #11)	wait(#11)	sleep	sleep	sleep
224	sleep	sleep	wait(#8)	if has(F9) push(F9: D -> E, #8) else pull(F9: C -> D, #8)	if has(F9) push(F9: E -> J, #13) else wait(#8)	sleep	sleep	sleep	sleep	wait(#13)	sleep	sleep
225	if has(F4) push(F4: A -> B, #7)	wait(#7)	wait(#9)	if has(F9) push(F9: D -> E, #9) else pull(F9: C -> D, #9)	if has(F9) push(F9: E -> J, #14) else wait(#9)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #3) else wait(#14)	wait(#3)	sleep
226	wait(#8)	if has(F4) push(F4: B -> C, #8) else pull(F4: A -> B, #8)	wait(#8)	wait(#15)	if has(F9) push(F9: E -> J, #15) else pull(F9: D -> E, #15)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #4) else wait(#15)	if has(F9) push(F9: K -> L, #0) else wait(#4)	wait(#0)
227	sleep	wait(#4)	if has(F4) push(F4: C -> D, #4) else pull(F4: B -> C, #4)	wait(#4)	wait(#5)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #5) else pull(F9: E -> J, #5)	if has(F9) push(F9: K -> L, #1) else wait(#5)	wait(#1)
228	sleep	wait(#5)	if has(F4) push(F4: C -> D, #5) else pull(F4: B -> C, #5)	if has(F4) push(F4: D -> E, #2) else wait(#5)	wait(#2)	sleep	sleep	sleep	sleep	if has(F9) push(F9: J -> K, #6)	wait(#6)	sleep
229	if has(F5) push(F5: A -> B, #15)	wait(#15)	wait(#3)	if has(F4) push(F4: D -> E, #3) else pull(F4: C -> D, #3)	if has(F4) push(F4: E -> J, #5) else wait(#3)	sleep	sleep	sleep	sleep	wait(#5)	if has(F9) push(F9: K -> L, #2)	wait(#2)
230	if has(F5) push(F5: A -> B, #0)	wait(#0)	wait(#4)	if has(F4) push(F4: D -> E, #4) else pull(F4: C -> D, #4)	if has(F4) push(F4: E -> J, #6) else wait(#4)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #6) else wait(#6)	wait(#6)	sleep
231	sleep	if has(F5) push(F5: B -> C, #5)	wait(#5)	wait(#7)	if has(F4) push(F4: E -> J, #7) else pull(F4: D -> E, #7)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #7) else wait(#7)	if has(F4) push(F4: K -> L, #7) else wait(#7)	wait(#7)
232	sleep	wait(#1)	if has(F5) push(F5: C -> D, #1) else pull(F5: B -> C, #1)	wait(#1)	wait(#8)	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #8) else pull(F4: E -> J, #8)	if has(F4) push(F4: K -> L, #8) else wait(#8)	wait(#8)
233	sleep	wait(#2)	if has(F5) push(F5: C -> D, #2) else pull(F5: B -> C, #2)	wait(#2)	if has(F4) push(F4: E -> J, #9)	sleep	sleep	sleep	sleep	wait(#9)	if has(F4) push(F4: K -> L, #9)	wait(#9)
234	sleep	sleep	if has(F5) push(F5: C -> D, #3)	wait(#3)	sleep	sleep	sleep	sleep	sleep	if has(F4) push(F4: J -> K, #10)	wait(#10)	sleep
235	sleep	if has(F6) push(F6: B -> C, #14)	wait(#14)	if has(F5) push(F5: D -> E, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
236	sleep	if has(F6) push(F6: B -> C, #15)	wait(#15)	if has(F5) push(F5: D -> E, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
237	sleep	sleep	if has(F6) push(F6: C -> D, #10)	wait(#10)	if has(F9) push(F9: E -> J, #1)	sleep	sleep	sleep	sleep	wait(#1)	sleep	sleep
238	sleep	sleep	if has(F6) push(F6: C -> D, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
239	sleep	sleep	if has(F10) push(F10: C -> D, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
240	sleep	if has(F1) push(F1: B -> C, #9)	wait(#9)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
241	sleep	wait(#10)	if has(F1) push(F1: C -> D, #10) else pull(F1: B -> C, #10)	wait(#10)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
242	sleep	sleep	if has(F1) push(F1: C -> D, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
243	sleep	sleep	wait(#1)	if has(F10) push(F10: D -> E, #1) else pull(F10: C -> D, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
244	sleep	sleep	sleep	wait(#8)	if has(F10) push(F10: E -> J, #8) else pull(F10: D -> E, #8)	sleep	sleep	sleep	sleep	wait(#8)	sleep	sleep
245	sleep	sleep	sleep	wait(#9)	if has(F10) push(F10: E -> J, #9) else pull(F10: D -> E, #9)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #15) else wait(#9)	wait(#15)	sleep
246	sleep	sleep	sleep	sleep	wait(#0)	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #0) else pull(F10: E -> J, #0)	if has(F10) push(F10: K -> L, #9) else wait(#0)	wait(#9)
247	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	wait(#10)	if has(F10) push(F10: K -> L, #10) else pull(F10: J -> K, #10)	wait(#10)
248	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: J -> K, #2)	wait(#2)	sleep
249	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	if has(F10) push(F10: K -> L, #11)	wait(#11)
250	sleep	sleep	if has(F2) push(F2: C -> D, #10)	wait(#10)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
251	sleep	sleep	wait(#0)	if has(F2) push(F2: D -> E, #0) else pull(F2: C -> D, #0)	wait(#0)	sleep	sleep	sleep	sleep	sleep	sleep	sleep
252	sleep	sleep	sleep	wait(#1)	if has(F2) push(F2: E -> F, #1) else pull(F2: D -> E, #1)	wait(#1)	sleep	sleep	sleep	sleep	sleep	sleep
253	sleep	sleep	sleep	wait(#2)	if has(F2) push(F2: E -> F, #2) else pull(F2: D -> E, #2)	if has(F2) push(F2: F -> G, #6) else wait(#2)	wait(#6)	sleep	sleep	sleep	sleep	sleep
254	sleep	sleep	if has(F3) push(F3: C -> D, #6)	wait(#6)	wait(#7)	if has(F2) push(F2: F -> G, #7) else pull(F2: E -> F, #7)	if has(F2) push(F2: G -> H, #7) else wait(#7)	wait(#7)	sleep	sleep	sleep	sleep
255	sleep	sleep	wait(#2)	if has(F3) push(F3: D -> E, #2) else pull(F3: C -> D, #2)	wait(#2)	wait(#8)	if has(F2) push(F2: G -> H, #8) else pull(F2: F -> G, #8)	if has(F2) push(F2: H -> I, #0) else wait(#8)	wait(#0)	sleep	sleep	sleep
256	sleep	sleep	sleep	wait(#3)	if has(F3) push(F3: E -> J, #3) else pull(F3: D -> E, #3)	wait(#9)	if has(F2) push(F2: G -> H, #9) else pull(F2: F -> G, #9)	if has(F2) push(F2: H -> I, #1) else wait(#9)	wait(#1)	wait(#3)	sleep	sleep
257	sleep	sleep	sleep	wait(#4)	if has(F3) push(F3: E -> J, #4) else pull(F3: D -> E, #4)	sleep	if has(F2) push(F2: G -> H, #10)	wait(#10)	sleep	if has(F3) push(F3: J -> K, #6) else wait(#4)	wait(#6)	sleep
258	sleep	sleep	sleep	sleep	wait(#7)	sleep	sleep	if has(F2) push(F2: H -> I, #2)	wait(#2)	if has(F3) push(F3: J -> K, #7) else pull(F3: E -> J, #7)	if has(F3) push(F3: K -> L, #7) else wait(#7)	wait(#7)
259	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	wait(#8)	if has(F3) push(F3: K -> L, #8) else pull(F3: J -> K, #8)	wait(#8)
260	sleep	if has(F1) push(F1: B -> C, #11)	wait(#11)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: J -> K, #9)	wait(#9)	sleep
261	sleep	wait(#12)	if has(F1) push(F1: C -> D, #12) else pull(F1: B -> C, #12)	wait(#12)	sleep	sleep	sleep	sleep	sleep	sleep	if has(F3) push(F3: K -> L, #10)	wait(#10)
262	sleep	sleep	if has(F1) push(F1: C -> D, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
263	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
264	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
265	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
266	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
267	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
268	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
269	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
270	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
271	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
272	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
273	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
274	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
275	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
276	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
277	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
278	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
279	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
280	sleep	if has(F1) push(F1: B -> C, #13)	wait(#13)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
281	sleep	wait(#14)	if has(F1) push(F1: C -> D, #14) else pull(F1: B -> C, #14)	wait(#14)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
282	sleep	sleep	if has(F1) push(F1: C -> D, #15)	wait(#15)	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
283	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
284	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
285	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
286	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
287	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
288	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
289	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
290	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
291	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
292	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
293	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
294	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
295	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
296	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
297	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
298	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
299	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep	sleep
// All flows meet their deadlines
