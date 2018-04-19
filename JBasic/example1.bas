10 read a1, a2, a3, a4
15 let d = a1*a4 - a3*a2
20 if d = 0 then 65
30 read b1, b2
40 let x1 = (b1*a4 - b2*a2)/d
50 let x2 = (a1*b2 - a3*b1)/d
55 print x1, x2
60 goto 30
65 print "No Unique Solution"
70 data 1, 2, 4, 2
75 data -7, 5
80 data 1, 3
85 data 4, -7
90 end
