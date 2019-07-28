package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

var totPrice int
var inputReader = bufio.NewReader(os.Stdin)

func readBooks(numberOfBooks int) []int {
	books := make([]int, numberOfBooks)

	for i := 0; i < numberOfBooks; i++ {
		input, _ := inputReader.ReadString('\n')
		books[i], _ = strconv.Atoi(strings.TrimSpace(input))
		totPrice += books[i]
	}
	return books
}

func main() {
	var numberOfBooks int
	input, _ := inputReader.ReadString('\n')
	numberOfBooks, _ = strconv.Atoi(strings.TrimSpace(input))
	books := readBooks(numberOfBooks)
	sort.Slice(books, func(i, j int) bool { return books[i] > books[j] })
	/* sort.Sort(sort.Reverse(sort.IntSlice(books))) */
	for ix := 2; ix < len(books); ix += 3 {
		totPrice -= books[ix]
	}
	fmt.Println(totPrice)
}
