package main

import (
	"fmt"
	"sort"
	"strconv"
)

func readCup() (string, int) {
	var firstParam string
	var secondParam string
	fmt.Scanf("%s %s", &firstParam, &secondParam)

	if convertedFirstParam, err := strconv.Atoi(firstParam); err == nil {
		outRadius := convertedFirstParam / 2
		return secondParam, outRadius
	}
	convertedSecondParam, _ := strconv.Atoi(secondParam)
	return firstParam, convertedSecondParam
}

func main() {
	var numberOfCups int
	fmt.Scanf("%d", &numberOfCups)

	cupMap := make(map[int]string)

	for i := 0; i < numberOfCups; i++ {
		cupColor, cupRadius := readCup()
		cupMap[cupRadius] = cupColor
	}

	var keys []int
	for k := range cupMap {
		keys = append(keys, k)
	}

	sort.Ints(keys)

	for _, k := range keys {
		fmt.Println(cupMap[k])
	}
}
