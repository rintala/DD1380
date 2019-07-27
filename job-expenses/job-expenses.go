package main

import (
	"fmt"
)

func readEntries(n int) ([]int, error){
	
	in := make([]int, n)
	for i := range in {
		_, err := fmt.Scanf("%d", &in[i])
		if err != nil {
			return in[:i], err
		}
	}
	return in, nil
}

func calculateExpenses(entries []int) int {
	totExpenses := 0
	for _, entry := range entries{
		if(entry<0){
			totExpenses+= Abs(entry)
		}
	}
	return totExpenses
}

func Abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func main() {
	var numberOfEntries int
	fmt.Scanf("%d", &numberOfEntries)
	
	entries, _ := readEntries(numberOfEntries)
	fmt.Println(calculateExpenses(entries))
}