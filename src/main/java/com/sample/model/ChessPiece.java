package com.sample.model;

import java.awt.Point;

public class ChessPiece {
        public static enum Type { PAWN, ROOK, BISHOP, KNIGHT, KING, QUEEN }
        public static enum Color { BLACK, WHITE }

        private final Type type;
        private final Color color;
        private Point position;
        private boolean evaluated;

        public ChessPiece(Type type, Color color, Point location) {
            super();
            this.type = type;
            this.color = color;
            this.position = location;
        }
        
        public boolean isEvaluated() {
            return evaluated;
        }

        public void setEvaluated(boolean evaluated) {
            this.evaluated = evaluated;
        }

        public Type getType() {
            return type;
        }

        public Color getColor() {
            return color;
        }

        public Point getPosition() {
            return position;
        }

        public String getPositionAsString() {
            return "" + "ABCDEFGH".charAt(position.x - 1) + position.y;
        }

        public String toString() {
            return color + " " + type + " at " + getPositionAsString();
        }
    }