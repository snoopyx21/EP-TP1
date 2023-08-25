#!/bin/bash

javac *.java

val=0
val1=50000

for i  in `seq 1 50`;
do
	val=$(($val+$val1))
	for j in `seq 1 10`;
	do
		START=$(date +%s)
		res=`java MM1 5 6 $val 2` 
		
		END=$(date +%s)
		DIFF=$(( $END - $START ))
		aff="$DIFF $res"
		echo $aff >> results.dat
	
	done
done
cat -n results.dat > res.dat
rm *.class
