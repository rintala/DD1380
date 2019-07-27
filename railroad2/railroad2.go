package main

import (
	"fmt"
)

func isClosedRailroad(x int, y int ) string {
    xEndpoints := 4
    yEndpoints := 3

    totEndPoints := x*xEndpoints+y*yEndpoints

    if(totEndPoints % 2 == 0){
        return "possible"
    } else{
        return "impossible"
    }
}

func main() {
	var x, y int
	fmt.Scanf("%d %d", &x, &y)
    fmt.Printf("%s\n", isClosedRailroad(x, y))
    
}