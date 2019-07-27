package main

import (
	"fmt"
)

func main() {
	var numberOfHands int
	var dominantSuite string
	fmt.Scanf("%d %s", &numberOfHands, &dominantSuite)

	pointSchemeDominant := map[string]int{"A": 11, "K": 4, "Q": 3, "J": 20, "T": 10, "9": 14, "8": 0, "7": 0}
	pointSchemeNotDominant := map[string]int{"A": 11, "K": 4, "Q": 3, "J": 2, "T": 10, "9": 0, "8": 0, "7": 0}

	totScore := 0
	for i := 0; i < numberOfHands*4; i++ {

		var cardTemp string
		fmt.Scanf("%s", &cardTemp)

		cardNumber := string(cardTemp[0])
		cardSuite := string(cardTemp[1])

		if cardSuite == dominantSuite {
			totScore += pointSchemeDominant[cardNumber]
		} else {
			totScore += pointSchemeNotDominant[cardNumber]
		}
	}
	fmt.Println(totScore)
}
