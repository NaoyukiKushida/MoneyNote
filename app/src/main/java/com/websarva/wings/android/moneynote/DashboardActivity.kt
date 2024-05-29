package com.websarva.wings.android.moneynote

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        logoutButton = findViewById<Button>(R.id.logoutButton)

        // アクションバーを設定
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false) // タイトルの表示を無効化する

        // ログアウトボタンのクリックリスナーを設定
        logoutButton.setOnClickListener {
            logout()
        }

        // TransactionDetailFragmentを追加
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, TransactionDetailFragment())
            .commit()
    }

    private fun logout() {
        // ログアウト処理を実行する
        // 例：ログアウトダイアログを表示するなど

        // ログアウト後、MainActivityに遷移（最初の画面へ遷移）
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}

