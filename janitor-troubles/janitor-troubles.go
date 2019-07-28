package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

var scanner = bufio.NewScanner(os.Stdin)

func toInt(buf []byte) (n int) {
	for _, v := range buf {
		n = n*10 + int(v-'0')
	}
	return
}

func readBytes() (int, int, int, int) {
	var a, b, c, d int
	scanner.Scan()
	fmt.Sscanf(scanner.Text(), "%d %d %d %d", &a, &b, &c, &d)

	return a, b, c, d
}
func main() {
	a, b, c, d := readBytes()
	semiParam := float64(a+b+c+d) / 2

	// Bretschneider’s formula for max area
	maxArea := math.Sqrt((semiParam - float64(a)) * (semiParam - float64(b)) * (semiParam - float64(c)) * (semiParam - float64(d)))
	fmt.Println(maxArea)
}
