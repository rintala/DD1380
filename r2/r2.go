package main

import (
	"fmt"
	"io"
)

func findLostR(r1 int64, s int64 ) int64 {
	var r2 int64
	r2 = 2*s-r1
	return r2
}

func main() {
	var r1, s int64

    for {
        _, err := fmt.Scanf("%d%d", &r1, &s)
        if err == io.EOF {
            break
        }

        fmt.Printf("%d\n", findLostR(r1, s))
    }
}
