import sys
f = open("output.txt", "w")
sys.stdout = f
print "hello world"
