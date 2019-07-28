package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func readBooks(numberOfBooks int) []int {
	books := make([]int, numberOfBooks)
	inputReader := bufio.NewReader(os.Stdin)
	for i := 0; i < numberOfBooks; i++ {
		input, _ := inputReader.ReadString('\n')
		books[i], _ = strconv.Atoi(strings.TrimSpace(input))
	}
	return books
}

func findMinPrice(books []int) int {
	totPrice := 0
	groupCount := 0
	group := make([]int, 3)
	isGroupActive := false
	for i, bookPrice := range books {
		if groupCount == 0 {
			if (len(books) - i) > 2 {
				isGroupActive = true
				group = make([]int, 3)
			} else {
				isGroupActive = false
			}
		}

		if isGroupActive {
			group[groupCount] = bookPrice
			groupCount++
		} else {
			totPrice += bookPrice
		}

		if groupCount == 3 {
			for i, g := range group {
				if i != len(group)-1 {
					totPrice += g
				}
			}
			groupCount = 0
		}
	}
	return totPrice
}
func main() {
	var numberOfBooks int
	fmt.Scanf("%d", &numberOfBooks)
	books := readBooks(numberOfBooks)
	sort.Sort(sort.Reverse(sort.IntSlice(books)))

	minPrice := findMinPrice(books)
	fmt.Println(minPrice)

}
