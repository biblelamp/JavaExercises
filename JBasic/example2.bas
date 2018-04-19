10 read d
20 let m = -1
30 for x = 0 to 3 step d
40 if sin(x) <= m then 80
50 let x0 = x
60 let m = sin(x)
80 next x
85 print x0, x, d
90 goto 10
100 data 0.1, 0.01, 0.001
110 end