package android.shirasusalad.janken_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
//import sun.jvm.hotspot.utilities.IntArray
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var countWin = 0
        var countLose = 0
        var countDraw = 0
        var gameFlag = 0


        hand_cpu.setImageResource(R.drawable.cpu)
        hand_player.setImageResource(R.drawable.player)

        progressBar_cpu.max = 5
        progressBar_player.max = 5

        progressBar_cpu.progress = progressBar_cpu.max
        progressBar_player.progress = progressBar_player.max

        fun updateResults(){
            textView_results.text = countWin.toString() + "勝" + countLose + "敗" + countDraw + "引き分け"
        }

        fun checkWinOrLose(){
            when {
                progressBar_cpu.progress == 0 -> {
                    result.text = "WIN!!!"
                    gameFlag = 1
                }
                progressBar_player.progress == 0 -> {
                    result.text = "LOSE..."
                    gameFlag = 1
                }
            }
            when (gameFlag) {
                1 -> {
                    // ３秒後にリロード
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        val intent = intent
                        overridePendingTransition(0, 0)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        finish()

                        overridePendingTransition(0, 0)
                        startActivity(intent)
                    }, 3000)
                }
            }
        }

        // グーを出したとき
        button_goo.setOnClickListener {
            hand_player.setImageResource(R.drawable.goo)
            when (Random().nextInt(3)) {
                0 -> {
                    hand_cpu.setImageResource(R.drawable.goo)
                    result.text = "引き分けです"
                    countDraw++
                }
                1 -> {
                    hand_cpu.setImageResource(R.drawable.choki)
                    result.text = "あなたの勝ちです"
                    progressBar_cpu.progress--
                    countWin++
                }
                2 -> {
                    hand_cpu.setImageResource(R.drawable.paa)
                    result.text = "あなたの負けです"
                    progressBar_player.progress--
                    countLose++
                }
            }
            updateResults()
            checkWinOrLose()
        }

        // チョキを出したとき
        button_choki.setOnClickListener {
            hand_player.setImageResource(R.drawable.choki)
            when (Random().nextInt(3)) {
                0 -> {
                    hand_cpu.setImageResource(R.drawable.goo)
                    result.text = "あなたの負けです"
                    progressBar_player.progress--
                    countLose++
                }
                1 -> {
                    hand_cpu.setImageResource(R.drawable.choki)
                    result.text = "引き分けです"
                    countDraw++
                }
                2 -> {
                    hand_cpu.setImageResource(R.drawable.paa)
                    result.text = "あなたの勝ちです"
                    progressBar_cpu.progress--
                    countWin++
                }
            }
            updateResults()
            checkWinOrLose()
        }

        // パーを出したとき
        button_paa.setOnClickListener {
            hand_player.setImageResource(R.drawable.paa)
            when (Random().nextInt(3)) {
                0 -> {
                    hand_cpu.setImageResource(R.drawable.goo)
                    result.text = "あなたの勝ちです"
                    progressBar_cpu.progress--
                    countWin++
                }
                1 -> {
                    hand_cpu.setImageResource(R.drawable.choki)
                    result.text = "あなたの負けです"
                    progressBar_player.progress--
                    countLose++
                }
                2 -> {
                    hand_cpu.setImageResource(R.drawable.paa)
                    result.text = "引き分けです"
                    countDraw++
                }
            }
            updateResults()
            checkWinOrLose()
        }

    }
}
