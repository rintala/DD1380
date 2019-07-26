package main

import (
	"fmt"
	"io"
)

func findMixedFrac(numerator int64, denominator int64 ) int64 {
    var wholeNumber = numerator / denominator
    var remainder = numerator % denominator
    var properNumerator = remainder
    var properDenominator = denominator

    fmt.Printf("%d %d / %d\n", wholeNumber, properNumerator, properDenominator)
   
	return wholeNumber
}

func main() {
	var numerator, denominator int64

    for {
        _, err := fmt.Scanf("%d%d", &numerator, &denominator)
        if err == io.EOF {
            break
        }
        if(denominator != 0){
            findMixedFrac(numerator, denominator)
        }
    }
}
