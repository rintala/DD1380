package main

import (
	"fmt"
	"io"
	"math"
)

func readMatrix(matrix[][] string){
	var outputString string
	for i:=0;i<len(matrix);i++ {
		for j:=0;j<len(matrix);j++{
			outputString+=matrix[i][j]
		}
	}
	fmt.Println(outputString)
}

func rotateMatrix(matrix [][]string) [][]string{
	rotatedMatrix := make([][]string, len(matrix))
	for i:=0;i<len(matrix);i++ {
		rotatedMatrix[i] = make([]string, len(matrix))
		rowVector := make([]string, len(matrix))

		for j:=0;j<len(matrix);j++ {
			rowVector[j] = matrix[j][len(matrix)-1-i]
			rotatedMatrix[i][j] = rowVector[j]
		}
	}

	return rotatedMatrix
}

func decryptPoem(poem string){
	squareSide := int(math.Sqrt(float64(len(poem))))
	squareMatrix := make([][]string, squareSide)

	for i:=0;i<squareSide; i++ {
		squareMatrix[i] = make([]string, squareSide)
		rowVector := make([]string, squareSide)

		for j:=0;j<squareSide;j++ {
			rowVector[j] = string(poem[i*squareSide+j])
			squareMatrix[i][j] = rowVector[j]
		}
	}

	rotatedMatrix := rotateMatrix(squareMatrix)
	readMatrix(rotatedMatrix)
	
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
		
		decryptPoem(poems[i])
	}
}