# fileApp

API:
1) /commonWords?path=<comma separated paths>
Input :path of different files on the server you want to compare
Return : common words in those file after ignoring special characters.
  
Usage:
http://localhost:8080/commonWords?path=C:/Users/shivgoel/Downloads/file1.txt,C:/Users/shivgoel/Downloads/file2.txt,C:/Users/shivgoel/Downloads/file3.txt

Output : 
["test","objective""judge"]

file1.txt:
This objective of the test is to judge the candidate on few points.

file2.txt
The Objective o*f life Test should not to JUDGE any other person.

file3.txt
Court has j#ud^*ge who t@e$st is always cleared with his thoughts and objective.
