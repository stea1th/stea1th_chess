package stea1th.chess.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import stea1th.chess.figures.Figure;

import java.io.Serializable;


@Data
@AllArgsConstructor
public class RestoreData implements Serializable {

   private Figure figure;
   private Figure anotherFigure;
   private int newPosition;
   private int anotherFigurePosition;

    public RestoreData(Figure figure, Figure anotherFigure, int newPosition) {
        this.figure = figure;
        this.newPosition = newPosition;
        this.anotherFigure = anotherFigure;
    }
//
//    public RestoreData(RestoreData data) {
//        this.figure = data.figure;
//        this.newPosition = data.newPosition;
//        this.anotherFigure = data.anotherFigure;
//        this.anotherFigurePosition = data.anotherFigurePosition;
//    }
}
