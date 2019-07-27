package main

import (
	"fmt"
	"io"
	"strconv"
)

func findM(n string) int {
	nSum := 0
	for _, char := range n {
		tempSum, _ := strconv.Atoi(string(char))
		nSum += tempSum
	}

	m := 11
	for {
		convertedN, _ := strconv.Atoi(n)
		mn := strconv.Itoa(m * convertedN)
		mnSum := 0
		for _, char := range mn {
			tempChar, _ := strconv.Atoi(string(char))

			mnSum += tempChar
		}
		if nSum == mnSum {
			return m
		}
		m++
	}

}

func main() {
	for {
		var n string
		_, err := fmt.Scanf("%s", &n)
		if err == io.EOF {
			break
		}
		if n != "0" {
			fmt.Println(findM(n))
		}
	}
}
