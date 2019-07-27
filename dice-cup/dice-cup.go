package main

import (
	"fmt"
	"sort"
)

func setupDie(dNumberFaces int) []int {
	die := make([]int, dNumberFaces)
	for i := range die {
		die[i] = i + 1
	}
	return die
}

func findDieSums(d1 []int, d2 []int) map[int]int {

	dieSums := make(map[int]int)
	for _, f1 := range d1 {
		for _, f2 := range d2 {
			dieSums[f1+f2]++
		}
	}

	return dieSums
}

func findAndReturnMostLikely(dieSums map[int]int) {
	maxCount := 0

	var mostLikelySums []int

	for _, sumCount := range dieSums {
		if sumCount > maxCount {
			maxCount = sumCount
		}
	}
	for sum, sumCount := range dieSums {
		if sumCount == maxCount {
			mostLikelySums = append(mostLikelySums, sum)
		}
	}
	sortableMostLikelySums := sort.IntSlice(mostLikelySums)
	sort.Sort(sortableMostLikelySums)

	for _, sum := range sortableMostLikelySums {
		fmt.Println(sum)
	}

}

func main() {
	var d1NumberFaces, d2NumberFaces int
	_, err := fmt.Scanf("%d%d", &d1NumberFaces, &d2NumberFaces)

	if err == nil {
		d1 := setupDie(d1NumberFaces)
		d2 := setupDie(d2NumberFaces)

		dieSums := findDieSums(d1, d2)
		findAndReturnMostLikely(dieSums)
	}

}
