package com.example.a8_queens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    // Works without these, originally didn't know how to do. Did I figure it out? The world may never know.
//    public ImageButton a1 = findViewById(R.id.a1);
//    public ImageButton a2 = findViewById(R.id.a2);
//    public ImageButton a3 = findViewById(R.id.a3);
//    public ImageButton a4 = findViewById(R.id.a4);
//    public ImageButton a5 = findViewById(R.id.a5);
//    public ImageButton a6 = findViewById(R.id.a6);
//    public ImageButton a7 = findViewById(R.id.a7);
//    public ImageButton a8 = findViewById(R.id.a8);
//    public ImageButton b1 = findViewById(R.id.b1);
//    public ImageButton b2 = findViewById(R.id.b2);
//    public ImageButton b3 = findViewById(R.id.b3);
//    public ImageButton b4 = findViewById(R.id.b4);
//    public ImageButton b5 = findViewById(R.id.b5);
//    public ImageButton b6 = findViewById(R.id.b6);
//    public ImageButton b7 = findViewById(R.id.b7);
//    public ImageButton b8 = findViewById(R.id.b8);
//    public ImageButton c1 = findViewById(R.id.c1);
//    public ImageButton c2 = findViewById(R.id.c2);
//    public ImageButton c3 = findViewById(R.id.c3);
//    public ImageButton c4 = findViewById(R.id.c4);
//    public ImageButton c5 = findViewById(R.id.c5);
//    public ImageButton c6 = findViewById(R.id.c6);
//    public ImageButton c7 = findViewById(R.id.c7);
//    public ImageButton c8 = findViewById(R.id.c8);
//    public ImageButton d1 = findViewById(R.id.d1);
//    public ImageButton d2 = findViewById(R.id.d2);
//    public ImageButton d3 = findViewById(R.id.d3);
//    public ImageButton d4 = findViewById(R.id.d4);
//    public ImageButton d5 = findViewById(R.id.d5);
//    public ImageButton d6 = findViewById(R.id.d6);
//    public ImageButton d7 = findViewById(R.id.d7);
//    public ImageButton d8 = findViewById(R.id.d8);
//    public ImageButton e1 = findViewById(R.id.e1);
//    public ImageButton e2 = findViewById(R.id.e2);
//    public ImageButton e3 = findViewById(R.id.e3);
//    public ImageButton e4 = findViewById(R.id.e4);
//    public ImageButton e5 = findViewById(R.id.e5);
//    public ImageButton e6 = findViewById(R.id.e6);
//    public ImageButton e7 = findViewById(R.id.e7);
//    public ImageButton e8 = findViewById(R.id.e8);
//    public ImageButton f1 = findViewById(R.id.f1);
//    public ImageButton f2 = findViewById(R.id.f2);
//    public ImageButton f3 = findViewById(R.id.f3);
//    public ImageButton f4 = findViewById(R.id.f4);
//    public ImageButton f5 = findViewById(R.id.f5);
//    public ImageButton f6 = findViewById(R.id.f6);
//    public ImageButton f7 = findViewById(R.id.f7);
//    public ImageButton f8 = findViewById(R.id.f8);
//    public ImageButton g1 = findViewById(R.id.g1);
//    public ImageButton g2 = findViewById(R.id.g2);
//    public ImageButton g3 = findViewById(R.id.g3);
//    public ImageButton g4 = findViewById(R.id.g4);
//    public ImageButton g5 = findViewById(R.id.g5);
//    public ImageButton g6 = findViewById(R.id.g6);
//    public ImageButton g7 = findViewById(R.id.g7);
//    public ImageButton g8 = findViewById(R.id.g8);
//    public ImageButton h1 = findViewById(R.id.h1);
//    public ImageButton h2 = findViewById(R.id.h2);
//    public ImageButton h3 = findViewById(R.id.h3);
//    public ImageButton h4 = findViewById(R.id.h4);
//    public ImageButton h5 = findViewById(R.id.h5);
//    public ImageButton h6 = findViewById(R.id.h6);
//    public ImageButton h7 = findViewById(R.id.h7);
//    public ImageButton h8 = findViewById(R.id.h8);

    public Queen[][] gameState;
    public int score;
    public HashMap pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize gameState array
        gameState = new Queen[8][8];
        score = 0;
        pressed = new HashMap();

        // To create checkerboard pattern
        boolean color = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (color) {
                    gameState[i][j] = new Queen(i, j, "Light");
                    color = false;
                } else {
                    gameState[i][j] = new Queen(i, j, "Dark");
                    color = true;
                }
            }
            color = !color;
        }
        TextView view = findViewById(R.id.scoreDisplay);
        view.setText("Score: " + score);
    }

    // Square clicked
    public void squareClick(View v) {
        ImageButton button = findViewById(v.getId());
        TextView view = findViewById(R.id.scoreDisplay);

        // Get button's coordinates
        float column = (button.getX() / 125);      // X
        float row = (button.getY() / 125);         // Y

        // Get the Queen's state at that location
        Queen queen = gameState[(int)column][(int)row];

        pressed.put(queen, button);

        if (!queen.flipped) {
            if (checkKill(queen.xCoordinate, queen.yCoordinate)) {
                queen.setFlipped();
                if (queen.colorTile.equals("Dark")) {
                    button.setBackgroundResource(R.drawable.queen_dark);
                    Toast.makeText(this," Queen placed at: " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
                    score++;
                    view.setText("Score: " + score);
                } else {
                    button.setBackgroundResource(R.drawable.queen_light);
                    Toast.makeText(this, " Queen placed at: " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
                    score++;
                    view.setText("Score: " + score);
                }
            } else {
                Toast.makeText(this, "Unable to place queen at " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
            }
        } else {
            queen.setFlipped();
            if (checkKill(queen.xCoordinate, queen.yCoordinate)) {
                if (queen.colorTile.equals("Dark")) {
                    button.setBackgroundResource(R.drawable.dark);
                    Toast.makeText(this, " Queen removed at: " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
                    if (score > 0) {
                        score--;
                        view.setText("Score: " + score);
                    }
                } else {
                    button.setBackgroundResource(R.drawable.light);
                    Toast.makeText(this, " Queen removed at: " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
                    if (score > 0) {
                        score--;
                        view.setText("Score: " + score);
                    }
                }
            } else {
                Toast.makeText(this, "Unable to place queen at " + queen.coordinatesToString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // Reset the game and clear all spaces
    public void resetClick(View v) {

        // For every tile
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // Get the current tile (queen)
                Queen currentQueen = gameState[i][j];
                currentQueen.flipped = false;

                // Get the button associated with the Queen tile
                Object b = pressed.get(currentQueen);

                // Reset the button color
                if (b != null) {
                    if (currentQueen.colorTile.equals("Dark")) {
                        ((ImageButton) b).setBackgroundResource(R.drawable.dark);
                    } else {
                        ((ImageButton) b).setBackgroundResource(R.drawable.light);
                    }

                }
            }
        }

        // Reset and display score
        TextView scoreView = findViewById(R.id.scoreDisplay);
        score = 0;
        scoreView.setText("Score: " + score);

        Toast.makeText(this, "Reset clicked", Toast.LENGTH_LONG).show();


    }

    public class Queen {
        int xCoordinate;
        int yCoordinate;
        boolean flipped;
        String colorTile;
        ImageButton button;

        public Queen(int X, int Y, String colorTile) {
            this.flipped = false;
            this.colorTile = colorTile;
            this.xCoordinate = X;
            this.yCoordinate = Y;
        }

        public int getxCoordinate() {
            return this.xCoordinate;
        }

        public int getyCoordinate() {
            return this.yCoordinate;
        }

        public boolean getFlipped() {
            return this.flipped;
        }

        public void setFlipped() {
            flipped = !flipped;
        }

        public String coordinatesToString() {
            return "" + this.xCoordinate + ", " + this.yCoordinate;
        }


    }

    public boolean checkKill(int x, int y) {

        // Check down Y axis, X is constant
        for (int i = 0; i < 8; i++) {
            if (gameState[x][i].flipped) {
                return false;
            }
        }

        // Check down X axis, Y is constant
        for (int i = 0; i < 8; i++) {
            if (gameState[i][y].flipped) {
                return false;
            }
        }

        // Check upper left diagonal
        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8) {
            if (gameState[i][j].flipped) {
                return false;
            }
            i++;
            j++;
        }


        // Check lower left diagonal
        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
            if (gameState[i][j].flipped) {
                return false;
            }
            i++;
            j--;
        }

        // Check lower right diagonal
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            if (gameState[i][j].flipped) {
                return false;
            }
            i--;
            j--;
        }

        // Check upper right diagonal
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
            if (gameState[i][j].flipped) {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

}
