package main

import (
	"fmt"
	"math"
)

func main() {
	var a, b, c, d int
	fmt.Scanf("%d %d %d %d", &a, &b, &c, &d)
	semiParam := float64(a+b+c+d) / 2

	// Bretschneider’s formula for max area
	maxArea := math.Sqrt((semiParam - float64(a)) * (semiParam - float64(b)) * (semiParam - float64(c)) * (semiParam - float64(d)))
	fmt.Println(maxArea)
}
