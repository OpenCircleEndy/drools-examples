package com.sample.model;

import java.awt.Point;

public class Move extends Point {
        private static final long serialVersionUID = 1L;

        private final ChessPiece piece;

        public Move(int x, int y, ChessPiece piece) {
            super(x,y);
            this.piece = piece;
            this.piece.setEvaluated(true);
        }

        public ChessPiece getPiece() {
            return piece;  
        }
        
        public Point getTargetPosition() {
            return new Point(piece.getPosition().x + x, piece.getPosition().y + y);
        }

        public String getPositionAsString() {
            return getPositionAsString(this);
        }
        public String getTargetPositionAsString() {
            return getPositionAsString(getTargetPosition());
        }
        private String getPositionAsString(Point location) {
            String column = null;
            try { 
                column = ""+"ABCDEFGH".charAt(location.x - 1);
            } catch (StringIndexOutOfBoundsException e) {
                column = ""+location.x;
            }
            return column + location.y;
        }
        public String toString() {
            return piece + " to " + getTargetPositionAsString();
        }
    }
