WARP program for graph CustomWorkloadByZach
Scheduler Name: Priority
M: 0.9
E2E: 0.99
nChannels: 16
Time Slot	A	B	C	D
0	sleep	sleep	sleep	sleep
1	sleep	sleep	sleep	sleep
2	if has(RandomFlow1) push(RandomFlow1: A -> C, #15)	sleep	wait(#15)	sleep
3	wait(#8)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #8) else pull(RandomFlow1: A -> C, #8)	wait(#8)
4	sleep	wait(#7)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #7) else pull(RandomFlow1: C -> D, #7)
5	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #10)	wait(#10)
6	wait(#9)	sleep	wait(#9)	if has(RandomFlow2) push(RandomFlow2: D -> A, #9) else pull(RandomFlow2: C -> D, #9)
7	if has(RandomFlow2) push(RandomFlow2: A -> B, #1) else pull(RandomFlow2: D -> A, #1)	wait(#1)	sleep	wait(#1)
8	if has(RandomFlow2) push(RandomFlow2: A -> B, #2)	wait(#2)	sleep	sleep
9	if has(RandomFlow1) push(RandomFlow1: A -> C, #1)	wait(#8)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #8)
10	wait(#10)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #10) else pull(RandomFlow1: A -> C, #10)	wait(#10)
11	sleep	wait(#9)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #9) else pull(RandomFlow1: C -> D, #9)
12	if has(RandomFlow1) push(RandomFlow1: A -> C, #3)	wait(#10)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #10)
13	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #12) else pull(RandomFlow1: A -> C, #12)	wait(#12)
14	sleep	wait(#11)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #11) else pull(RandomFlow1: C -> D, #11)
15	sleep	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: D -> B, #12)
16	sleep	sleep	sleep	sleep
17	sleep	sleep	wait(#1)	if has(RandomFlow3) push(RandomFlow3: D -> C, #1)
18	wait(#2)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #2) else pull(RandomFlow3: D -> C, #2)	wait(#2)
19	wait(#3)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #3)	sleep
20	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #12)	wait(#12)
21	wait(#11)	sleep	wait(#11)	if has(RandomFlow2) push(RandomFlow2: D -> A, #11) else pull(RandomFlow2: C -> D, #11)
22	if has(RandomFlow2) push(RandomFlow2: A -> B, #3) else pull(RandomFlow2: D -> A, #3)	wait(#3)	sleep	wait(#3)
23	if has(RandomFlow2) push(RandomFlow2: A -> B, #4)	wait(#4)	sleep	sleep
24	if has(RandomFlow1) push(RandomFlow1: A -> C, #5)	sleep	wait(#5)	sleep
25	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #14) else pull(RandomFlow1: A -> C, #14)	wait(#14)
26	sleep	sleep	wait(#3)	if has(RandomFlow3) push(RandomFlow3: D -> C, #3)
27	wait(#4)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #4) else pull(RandomFlow3: D -> C, #4)	wait(#4)
28	wait(#5)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #5)	sleep
29	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #14)	wait(#14)
30	wait(#13)	sleep	wait(#13)	if has(RandomFlow2) push(RandomFlow2: D -> A, #13) else pull(RandomFlow2: C -> D, #13)
31	if has(RandomFlow2) push(RandomFlow2: A -> B, #5) else pull(RandomFlow2: D -> A, #5)	wait(#5)	sleep	wait(#5)
32	if has(RandomFlow2) push(RandomFlow2: A -> B, #6)	wait(#6)	if has(RandomFlow1) push(RandomFlow1: C -> D, #15)	wait(#15)
33	if has(RandomFlow1) push(RandomFlow1: A -> C, #7)	wait(#13)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #13)
34	if has(RandomFlow1) push(RandomFlow1: A -> C, #8)	wait(#14)	wait(#8)	if has(RandomFlow1) push(RandomFlow1: D -> B, #14)
35	sleep	sleep	wait(#5)	if has(RandomFlow3) push(RandomFlow3: D -> C, #5)
36	wait(#6)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #6) else pull(RandomFlow3: D -> C, #6)	wait(#6)
37	wait(#7)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #7)	sleep
38	sleep	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #0)	wait(#0)
39	sleep	wait(#15)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #15) else pull(RandomFlow1: C -> D, #15)
40	if has(RandomFlow1) push(RandomFlow1: A -> C, #9)	wait(#0)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #0)
41	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #0)	wait(#0)
42	wait(#15)	sleep	wait(#15)	if has(RandomFlow2) push(RandomFlow2: D -> A, #15) else pull(RandomFlow2: C -> D, #15)
43	if has(RandomFlow2) push(RandomFlow2: A -> B, #7) else pull(RandomFlow2: D -> A, #7)	wait(#7)	sleep	wait(#7)
44	if has(RandomFlow2) push(RandomFlow2: A -> B, #8)	wait(#8)	wait(#7)	if has(RandomFlow3) push(RandomFlow3: D -> C, #7)
45	wait(#8)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #8) else pull(RandomFlow3: D -> C, #8)	wait(#8)
46	wait(#9)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #9)	sleep
47	wait(#2)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #2) else pull(RandomFlow1: A -> C, #2)	wait(#2)
48	sleep	wait(#1)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #1) else pull(RandomFlow1: C -> D, #1)
49	if has(RandomFlow1) push(RandomFlow1: A -> C, #11)	wait(#2)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #2)
50	wait(#4)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #4) else pull(RandomFlow1: A -> C, #4)	wait(#4)
51	sleep	wait(#3)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #3) else pull(RandomFlow1: C -> D, #3)
52	if has(RandomFlow1) push(RandomFlow1: A -> C, #13)	wait(#4)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #4)
53	sleep	sleep	wait(#9)	if has(RandomFlow3) push(RandomFlow3: D -> C, #9)
54	wait(#10)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #10) else pull(RandomFlow3: D -> C, #10)	wait(#10)
55	wait(#11)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #11)	sleep
56	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #2)	wait(#2)
57	wait(#1)	sleep	wait(#1)	if has(RandomFlow2) push(RandomFlow2: D -> A, #1) else pull(RandomFlow2: C -> D, #1)
58	if has(RandomFlow2) push(RandomFlow2: A -> B, #9) else pull(RandomFlow2: D -> A, #9)	wait(#9)	sleep	wait(#9)
59	if has(RandomFlow2) push(RandomFlow2: A -> B, #10)	wait(#10)	sleep	sleep
60	wait(#6)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #6) else pull(RandomFlow1: A -> C, #6)	wait(#6)
61	sleep	wait(#5)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #5) else pull(RandomFlow1: C -> D, #5)
62	sleep	sleep	wait(#11)	if has(RandomFlow3) push(RandomFlow3: D -> C, #11)
63	wait(#12)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #12) else pull(RandomFlow3: D -> C, #12)	wait(#12)
64	wait(#13)	wait(#6)	if has(RandomFlow3) push(RandomFlow3: C -> A, #13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #6)
65	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #4)	wait(#4)
66	wait(#3)	sleep	wait(#3)	if has(RandomFlow2) push(RandomFlow2: D -> A, #3) else pull(RandomFlow2: C -> D, #3)
67	if has(RandomFlow2) push(RandomFlow2: A -> B, #11) else pull(RandomFlow2: D -> A, #11)	wait(#11)	sleep	wait(#11)
68	if has(RandomFlow2) push(RandomFlow2: A -> B, #12)	wait(#12)	sleep	sleep
69	if has(RandomFlow1) push(RandomFlow1: A -> C, #15)	sleep	wait(#15)	sleep
70	wait(#8)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #8) else pull(RandomFlow1: A -> C, #8)	wait(#8)
71	sleep	sleep	wait(#13)	if has(RandomFlow3) push(RandomFlow3: D -> C, #13)
72	wait(#14)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #14) else pull(RandomFlow3: D -> C, #14)	wait(#14)
73	wait(#15)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #15)	sleep
74	sleep	wait(#7)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #7) else pull(RandomFlow1: C -> D, #7)
75	if has(RandomFlow1) push(RandomFlow1: A -> C, #1)	wait(#8)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #8)
76	wait(#10)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #10) else pull(RandomFlow1: A -> C, #10)	wait(#10)
77	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #6)	wait(#6)
78	wait(#5)	sleep	wait(#5)	if has(RandomFlow2) push(RandomFlow2: D -> A, #5) else pull(RandomFlow2: C -> D, #5)
79	if has(RandomFlow2) push(RandomFlow2: A -> B, #13) else pull(RandomFlow2: D -> A, #13)	wait(#13)	sleep	wait(#13)
80	if has(RandomFlow2) push(RandomFlow2: A -> B, #14)	wait(#14)	wait(#15)	if has(RandomFlow3) push(RandomFlow3: D -> C, #15)
81	wait(#0)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #0) else pull(RandomFlow3: D -> C, #0)	wait(#0)
82	wait(#1)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #1)	sleep
83	sleep	wait(#9)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #9) else pull(RandomFlow1: C -> D, #9)
84	if has(RandomFlow1) push(RandomFlow1: A -> C, #3)	wait(#10)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #10)
85	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #12) else pull(RandomFlow1: A -> C, #12)	wait(#12)
86	sleep	wait(#11)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #11) else pull(RandomFlow1: C -> D, #11)
87	if has(RandomFlow1) push(RandomFlow1: A -> C, #5)	wait(#12)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #12)
88	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #14) else pull(RandomFlow1: A -> C, #14)	wait(#14)
89	sleep	sleep	wait(#1)	if has(RandomFlow3) push(RandomFlow3: D -> C, #1)
90	wait(#2)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #2) else pull(RandomFlow3: D -> C, #2)	wait(#2)
91	wait(#3)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #3)	sleep
92	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #8)	wait(#8)
93	wait(#7)	sleep	wait(#7)	if has(RandomFlow2) push(RandomFlow2: D -> A, #7) else pull(RandomFlow2: C -> D, #7)
94	if has(RandomFlow2) push(RandomFlow2: A -> B, #15) else pull(RandomFlow2: D -> A, #15)	wait(#15)	sleep	wait(#15)
95	if has(RandomFlow2) push(RandomFlow2: A -> B, #0)	wait(#0)	if has(RandomFlow1) push(RandomFlow1: C -> D, #0)	wait(#0)
96	if has(RandomFlow1) push(RandomFlow1: A -> C, #7)	wait(#13)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #13)
97	if has(RandomFlow1) push(RandomFlow1: A -> C, #8)	wait(#14)	wait(#8)	if has(RandomFlow1) push(RandomFlow1: D -> B, #14)
98	sleep	sleep	wait(#3)	if has(RandomFlow3) push(RandomFlow3: D -> C, #3)
99	wait(#4)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #4) else pull(RandomFlow3: D -> C, #4)	wait(#4)
100	wait(#5)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #5)	sleep
101	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #10)	wait(#10)
102	wait(#9)	sleep	wait(#9)	if has(RandomFlow2) push(RandomFlow2: D -> A, #9) else pull(RandomFlow2: C -> D, #9)
103	if has(RandomFlow2) push(RandomFlow2: A -> B, #1) else pull(RandomFlow2: D -> A, #1)	wait(#1)	sleep	wait(#1)
104	if has(RandomFlow2) push(RandomFlow2: A -> B, #2)	wait(#2)	if has(RandomFlow1) push(RandomFlow1: C -> D, #2)	wait(#2)
105	sleep	wait(#15)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #15) else pull(RandomFlow1: C -> D, #15)
106	if has(RandomFlow1) push(RandomFlow1: A -> C, #9)	wait(#0)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #0)
107	sleep	sleep	wait(#5)	if has(RandomFlow3) push(RandomFlow3: D -> C, #5)
108	wait(#6)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #6) else pull(RandomFlow3: D -> C, #6)	wait(#6)
109	wait(#7)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #7)	sleep
110	wait(#4)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #4) else pull(RandomFlow1: A -> C, #4)	wait(#4)
111	sleep	wait(#1)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #1) else pull(RandomFlow1: C -> D, #1)
112	if has(RandomFlow1) push(RandomFlow1: A -> C, #11)	wait(#2)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #2)
113	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #12)	wait(#12)
114	wait(#11)	sleep	wait(#11)	if has(RandomFlow2) push(RandomFlow2: D -> A, #11) else pull(RandomFlow2: C -> D, #11)
115	if has(RandomFlow2) push(RandomFlow2: A -> B, #3) else pull(RandomFlow2: D -> A, #3)	wait(#3)	sleep	wait(#3)
116	if has(RandomFlow2) push(RandomFlow2: A -> B, #4)	wait(#4)	wait(#7)	if has(RandomFlow3) push(RandomFlow3: D -> C, #7)
117	wait(#8)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #8) else pull(RandomFlow3: D -> C, #8)	wait(#8)
118	wait(#9)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #9)	sleep
119	wait(#6)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #6) else pull(RandomFlow1: A -> C, #6)	wait(#6)
120	sleep	wait(#3)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #3) else pull(RandomFlow1: C -> D, #3)
121	if has(RandomFlow1) push(RandomFlow1: A -> C, #13)	wait(#4)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #4)
122	wait(#8)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #8) else pull(RandomFlow1: A -> C, #8)	wait(#8)
123	sleep	wait(#5)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #5) else pull(RandomFlow1: C -> D, #5)
124	if has(RandomFlow1) push(RandomFlow1: A -> C, #15)	wait(#6)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #6)
125	sleep	sleep	wait(#9)	if has(RandomFlow3) push(RandomFlow3: D -> C, #9)
126	wait(#10)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #10) else pull(RandomFlow3: D -> C, #10)	wait(#10)
127	wait(#11)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #11)	sleep
128	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #14)	wait(#14)
129	wait(#13)	sleep	wait(#13)	if has(RandomFlow2) push(RandomFlow2: D -> A, #13) else pull(RandomFlow2: C -> D, #13)
130	if has(RandomFlow2) push(RandomFlow2: A -> B, #5) else pull(RandomFlow2: D -> A, #5)	wait(#5)	sleep	wait(#5)
131	if has(RandomFlow2) push(RandomFlow2: A -> B, #6)	wait(#6)	sleep	sleep
132	wait(#10)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #10) else pull(RandomFlow1: A -> C, #10)	wait(#10)
133	sleep	wait(#7)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #7) else pull(RandomFlow1: C -> D, #7)
134	sleep	sleep	wait(#11)	if has(RandomFlow3) push(RandomFlow3: D -> C, #11)
135	wait(#12)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #12) else pull(RandomFlow3: D -> C, #12)	wait(#12)
136	wait(#13)	wait(#8)	if has(RandomFlow3) push(RandomFlow3: C -> A, #13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #8)
137	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #0)	wait(#0)
138	wait(#15)	sleep	wait(#15)	if has(RandomFlow2) push(RandomFlow2: D -> A, #15) else pull(RandomFlow2: C -> D, #15)
139	if has(RandomFlow2) push(RandomFlow2: A -> B, #7) else pull(RandomFlow2: D -> A, #7)	wait(#7)	sleep	wait(#7)
140	if has(RandomFlow2) push(RandomFlow2: A -> B, #8)	wait(#8)	sleep	sleep
141	if has(RandomFlow1) push(RandomFlow1: A -> C, #1)	sleep	wait(#1)	sleep
142	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #12) else pull(RandomFlow1: A -> C, #12)	wait(#12)
143	sleep	sleep	wait(#13)	if has(RandomFlow3) push(RandomFlow3: D -> C, #13)
144	wait(#14)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #14) else pull(RandomFlow3: D -> C, #14)	wait(#14)
145	wait(#15)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #15)	sleep
146	sleep	wait(#9)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #9) else pull(RandomFlow1: C -> D, #9)
147	if has(RandomFlow1) push(RandomFlow1: A -> C, #3)	wait(#10)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #10)
148	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #14) else pull(RandomFlow1: A -> C, #14)	wait(#14)
149	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #2)	wait(#2)
150	wait(#1)	sleep	wait(#1)	if has(RandomFlow2) push(RandomFlow2: D -> A, #1) else pull(RandomFlow2: C -> D, #1)
151	if has(RandomFlow2) push(RandomFlow2: A -> B, #9) else pull(RandomFlow2: D -> A, #9)	wait(#9)	sleep	wait(#9)
152	if has(RandomFlow2) push(RandomFlow2: A -> B, #10)	wait(#10)	wait(#15)	if has(RandomFlow3) push(RandomFlow3: D -> C, #15)
153	wait(#0)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #0) else pull(RandomFlow3: D -> C, #0)	wait(#0)
154	wait(#1)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #1)	sleep
155	sleep	wait(#11)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #11) else pull(RandomFlow1: C -> D, #11)
156	if has(RandomFlow1) push(RandomFlow1: A -> C, #5)	wait(#12)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #12)
157	wait(#0)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #0) else pull(RandomFlow1: A -> C, #0)	wait(#0)
158	sleep	wait(#13)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #13) else pull(RandomFlow1: C -> D, #13)
159	if has(RandomFlow1) push(RandomFlow1: A -> C, #7)	wait(#14)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #14)
160	wait(#2)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #2) else pull(RandomFlow1: A -> C, #2)	wait(#2)
161	sleep	sleep	wait(#1)	if has(RandomFlow3) push(RandomFlow3: D -> C, #1)
162	wait(#2)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #2) else pull(RandomFlow3: D -> C, #2)	wait(#2)
163	wait(#3)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #3)	sleep
164	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #4)	wait(#4)
165	wait(#3)	sleep	wait(#3)	if has(RandomFlow2) push(RandomFlow2: D -> A, #3) else pull(RandomFlow2: C -> D, #3)
166	if has(RandomFlow2) push(RandomFlow2: A -> B, #11) else pull(RandomFlow2: D -> A, #11)	wait(#11)	sleep	wait(#11)
167	if has(RandomFlow2) push(RandomFlow2: A -> B, #12)	wait(#12)	if has(RandomFlow1) push(RandomFlow1: C -> D, #3)	wait(#3)
168	if has(RandomFlow1) push(RandomFlow1: A -> C, #9)	wait(#15)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #15)
169	if has(RandomFlow1) push(RandomFlow1: A -> C, #10)	wait(#0)	wait(#10)	if has(RandomFlow1) push(RandomFlow1: D -> B, #0)
170	sleep	sleep	wait(#3)	if has(RandomFlow3) push(RandomFlow3: D -> C, #3)
171	wait(#4)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #4) else pull(RandomFlow3: D -> C, #4)	wait(#4)
172	wait(#5)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #5)	sleep
173	sleep	sleep	if has(RandomFlow2) push(RandomFlow2: C -> D, #6)	wait(#6)
174	wait(#5)	sleep	wait(#5)	if has(RandomFlow2) push(RandomFlow2: D -> A, #5) else pull(RandomFlow2: C -> D, #5)
175	if has(RandomFlow2) push(RandomFlow2: A -> B, #13) else pull(RandomFlow2: D -> A, #13)	wait(#13)	sleep	wait(#13)
176	if has(RandomFlow2) push(RandomFlow2: A -> B, #14)	wait(#14)	if has(RandomFlow1) push(RandomFlow1: C -> D, #4)	wait(#4)
177	sleep	wait(#1)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #1) else pull(RandomFlow1: C -> D, #1)
178	if has(RandomFlow1) push(RandomFlow1: A -> C, #11)	wait(#2)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #2)
179	sleep	sleep	wait(#5)	if has(RandomFlow3) push(RandomFlow3: D -> C, #5)
180	wait(#6)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #6) else pull(RandomFlow3: D -> C, #6)	wait(#6)
181	wait(#7)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #7)	sleep
182	wait(#6)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #6) else pull(RandomFlow1: A -> C, #6)	wait(#6)
183	sleep	wait(#3)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #3) else pull(RandomFlow1: C -> D, #3)
184	if has(RandomFlow1) push(RandomFlow1: A -> C, #13)	wait(#4)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #4)
185	wait(#8)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #8) else pull(RandomFlow1: A -> C, #8)	wait(#8)
186	sleep	wait(#5)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #5) else pull(RandomFlow1: C -> D, #5)
187	if has(RandomFlow1) push(RandomFlow1: A -> C, #15)	wait(#6)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #6)
188	sleep	sleep	wait(#7)	if has(RandomFlow3) push(RandomFlow3: D -> C, #7)
189	wait(#8)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #8) else pull(RandomFlow3: D -> C, #8)	wait(#8)
190	wait(#9)	sleep	if has(RandomFlow3) push(RandomFlow3: C -> A, #9)	sleep
191	wait(#10)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #10) else pull(RandomFlow1: A -> C, #10)	wait(#10)
192	sleep	wait(#7)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #7) else pull(RandomFlow1: C -> D, #7)
193	if has(RandomFlow1) push(RandomFlow1: A -> C, #1)	wait(#8)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #8)
194	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #12) else pull(RandomFlow1: A -> C, #12)	wait(#12)
195	sleep	wait(#9)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #9) else pull(RandomFlow1: C -> D, #9)
196	if has(RandomFlow1) push(RandomFlow1: A -> C, #3)	wait(#10)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #10)
197	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #14) else pull(RandomFlow1: A -> C, #14)	wait(#14)
198	sleep	wait(#11)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #11) else pull(RandomFlow1: C -> D, #11)
199	if has(RandomFlow1) push(RandomFlow1: A -> C, #5)	wait(#12)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #12)
200	wait(#0)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #0) else pull(RandomFlow1: A -> C, #0)	wait(#0)
201	sleep	wait(#13)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #13) else pull(RandomFlow1: C -> D, #13)
202	if has(RandomFlow1) push(RandomFlow1: A -> C, #7)	wait(#14)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #14)
203	wait(#2)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #2) else pull(RandomFlow1: A -> C, #2)	wait(#2)
204	sleep	wait(#15)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #15) else pull(RandomFlow1: C -> D, #15)
205	if has(RandomFlow1) push(RandomFlow1: A -> C, #9)	wait(#0)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #0)
206	wait(#4)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #4) else pull(RandomFlow1: A -> C, #4)	wait(#4)
207	sleep	wait(#1)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #1) else pull(RandomFlow1: C -> D, #1)
208	if has(RandomFlow1) push(RandomFlow1: A -> C, #11)	wait(#2)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #2)
209	wait(#6)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #6) else pull(RandomFlow1: A -> C, #6)	wait(#6)
210	sleep	wait(#3)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #3) else pull(RandomFlow1: C -> D, #3)
211	if has(RandomFlow1) push(RandomFlow1: A -> C, #13)	wait(#4)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #4)
212	wait(#8)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #8) else pull(RandomFlow1: A -> C, #8)	wait(#8)
213	sleep	wait(#5)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #5) else pull(RandomFlow1: C -> D, #5)
214	if has(RandomFlow1) push(RandomFlow1: A -> C, #15)	wait(#6)	wait(#15)	if has(RandomFlow1) push(RandomFlow1: D -> B, #6)
215	wait(#10)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #10) else pull(RandomFlow1: A -> C, #10)	wait(#10)
216	sleep	wait(#7)	wait(#7)	if has(RandomFlow1) push(RandomFlow1: D -> B, #7) else pull(RandomFlow1: C -> D, #7)
217	if has(RandomFlow1) push(RandomFlow1: A -> C, #1)	wait(#8)	wait(#1)	if has(RandomFlow1) push(RandomFlow1: D -> B, #8)
218	wait(#12)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #12) else pull(RandomFlow1: A -> C, #12)	wait(#12)
219	sleep	wait(#9)	wait(#9)	if has(RandomFlow1) push(RandomFlow1: D -> B, #9) else pull(RandomFlow1: C -> D, #9)
220	if has(RandomFlow1) push(RandomFlow1: A -> C, #3)	wait(#10)	wait(#3)	if has(RandomFlow1) push(RandomFlow1: D -> B, #10)
221	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #14) else pull(RandomFlow1: A -> C, #14)	wait(#14)
222	sleep	wait(#11)	wait(#11)	if has(RandomFlow1) push(RandomFlow1: D -> B, #11) else pull(RandomFlow1: C -> D, #11)
223	if has(RandomFlow1) push(RandomFlow1: A -> C, #5)	wait(#12)	wait(#5)	if has(RandomFlow1) push(RandomFlow1: D -> B, #12)
224	wait(#0)	sleep	if has(RandomFlow1) push(RandomFlow1: C -> D, #0) else pull(RandomFlow1: A -> C, #0)	wait(#0)
225	sleep	wait(#13)	wait(#13)	if has(RandomFlow1) push(RandomFlow1: D -> B, #13) else pull(RandomFlow1: C -> D, #13)
226	sleep	wait(#14)	sleep	if has(RandomFlow1) push(RandomFlow1: D -> B, #14)
// All flows meet their deadlines