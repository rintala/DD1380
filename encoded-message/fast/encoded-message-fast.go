
package main

import (
	"fmt"
	"io"
	"math"
	"strings"
)

func decryptPoemFast(encryptedPoem string){
	squareSide := int(math.Sqrt(float64(len(encryptedPoem))))
	var decryptedPoem strings.Builder

	for i:=squareSide;i>0;i--{
		for j:=0;j<squareSide;j++{
			decryptedPoem.WriteString(string(encryptedPoem[j*squareSide-1+i]))
		}
	}
	
	fmt.Println(decryptedPoem.String())
}

func main() {
	var numberOfPoems int
	fmt.Scanf("%d", &numberOfPoems)
	poems := make([]string, numberOfPoems)

    for i:=0; i<numberOfPoems; i++{
        _, err := fmt.Scanf("%s", &poems[i])
        if err == io.EOF {
            break
		}
		
		decryptPoemFast(poems[i])
	}
}