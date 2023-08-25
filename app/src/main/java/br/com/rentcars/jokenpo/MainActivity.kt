package br.com.rentcars.jokenpo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val ROCK = 1
        const val PAPER = 2
        const val SCISSOR = 3
        const val PLAY1WINS = 1
        const val PLAY2WINS = 2
        const val DRAW = 3
    }

    private var textMessage: TextView? = null
    private var imageViewPlayer: ImageView? = null
    private var imageViewPlayer2: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textMessage = findViewById(R.id.text_view_message)
        imageViewPlayer = findViewById(R.id.image_view_player)
        imageViewPlayer2 = findViewById(R.id.image_view_player2)

        val buttonRock = findViewById<Button>(R.id.button_rock)
        val buttonPaper = findViewById<Button>(R.id.button_paper)
        val buttonScissor = findViewById<Button>(R.id.button_scissor)

        buttonRock.setOnClickListener {
            startGame(ROCK)
        }

        buttonPaper.setOnClickListener {
            startGame(PAPER)
        }

        buttonScissor.setOnClickListener {
            startGame(SCISSOR)
        }
    }

    private fun startGame(play1Move: Int) {
        val play2Move = java.util.Random().nextInt(3) + 1

        imageViewPlayer?.setImageResource(getImage(play1Move))
        imageViewPlayer2?.setImageResource(getImage(play2Move))

        textMessage?.text = when (getWinner(play1Move, play2Move)) {
            PLAY1WINS -> {
                "Parabéns, você ganhou"
            }

            PLAY2WINS -> {
                "Você perdeu :( "
            }
            else -> {
                "O Jogo empatou"
            }
        }
    }

    private fun getImage(option: Int): Int {
        return when (option) {
            ROCK -> {
                R.drawable.ic_rock
            }

            PAPER -> {
                R.drawable.ic_paper
            }

            else -> {
                R.drawable.ic_scissor
            }
        }
    }

    private fun getWinner(play1Move: Int, play2Move: Int): Int {
        return if (play1Move == play2Move) {
            DRAW
        } else if (play1Move == ROCK && play2Move == SCISSOR) {
            PLAY1WINS
        } else if (play1Move == SCISSOR && play2Move == ROCK) {
            PLAY2WINS
        } else if (play1Move > play2Move) {
            PLAY1WINS
        } else {
            PLAY2WINS
        }
    }
}
