package com.websarva.wings.android.moneynote

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegisterActivity : AppCompatActivity() {

    private lateinit var rootLayout: RelativeLayout
    private lateinit var emailEditText: EditText
    private lateinit var emailErrorLabel: TextView
    private lateinit var passwordEditText: EditText
    private lateinit var passwordErrorLabel: TextView
    private lateinit var birthdayEditText: EditText
    private lateinit var birthdayErrorLabel: TextView
    private lateinit var agreementCheckbox: CheckBox
    private lateinit var registerButton: Button

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        rootLayout = findViewById(R.id.rootLayout)
        emailEditText = findViewById(R.id.emailEditText)
        emailErrorLabel = findViewById(R.id.emailErrorLabel)
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordErrorLabel = findViewById(R.id.passwordErrorLabel)
        birthdayEditText = findViewById(R.id.edit_txt_birthday)
        birthdayErrorLabel = findViewById(R.id.birthdayErrorLabel)
        agreementCheckbox = findViewById(R.id.agreementCheckbox)
        registerButton = findViewById<Button>(R.id.registerButton)

        // キーボードを閉じる処理を設定
//        KeyboardUtils.setupKeyboardHideOnTouchOutside(this)

        // アクションバーを設定
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false) // タイトルの表示を無効化する
        }

        rootLayout.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                emailEditText.clearFocus()
                passwordEditText.clearFocus()
                birthdayEditText.clearFocus()
                agreementCheckbox.clearFocus()
            }
        }

        birthdayEditText.setOnClickListener {
            val datePicker = DatePickerFragment()
            datePicker.show(supportFragmentManager, "datePicker")
        }

        // 登録ボタンの活性/非活性状態を設定
        registerButton.isEnabled = false

        registerButton.setOnClickListener {
            val hasError = isCheckInputError()

            // 未入力の項目がある場合、ログインボタンを非活性にする
            registerButton.isEnabled = !hasError

            // エラーがなければ登録処理を実行
            if (!hasError) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
        }
        // EditTextのテキストが変更されるたびにログインボタンの活性/非活性をチェックする
        emailEditText.addTextChangedListener(emailWatcher)
        passwordEditText.addTextChangedListener(passwordWatcher)
        birthdayEditText.addTextChangedListener(birthdayWatcher)

        // CheckBoxのチェック状態が変更されたときにログインボタンの活性/非活性をチェックする
        agreementCheckbox.setOnCheckedChangeListener { _, _ ->
            checkInputAndEnableButton()
        }
    }

    private val emailWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            checkInputAndEnableButton()
            if (emailErrorLabel.visibility == View.VISIBLE) {
                emailErrorLabel.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // no implementation needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // no implementation needed
        }
    }

    private val passwordWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            checkInputAndEnableButton()
            if (passwordErrorLabel.visibility == View.VISIBLE) {
                passwordErrorLabel.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // no implementation needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // no implementation needed
        }
    }

    private val birthdayWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            checkInputAndEnableButton()
            if (birthdayErrorLabel.visibility == View.VISIBLE) {
                birthdayErrorLabel.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // no implementation needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // no implementation needed
        }
    }

    /**
     * EditTextの入力をチェックし、ログインボタンの活性/非活性を更新する
     */
    private fun checkInputAndEnableButton() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val birth = birthdayEditText.text.toString()
        val isAgreementChecked = agreementCheckbox.isChecked

        // メールアドレス、パスワード、生年月日がすべて入力されており、かつ同意チェックボックスがチェックされている場合に登録ボタンを活性にする
        registerButton.isEnabled =
            email.isNotBlank() && password.isNotBlank() && birth.isNotBlank() && isAgreementChecked
    }

    /**
     *  登録ボタン押下時の入力チェックを行う
     *  メールアドレス、パスワード、生年月日の入力状態を確認
     */
    private fun isCheckInputError(): Boolean {
        var hasError = false

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val birth = birthdayEditText.text.toString()

        // Emailの入力チェック
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailErrorLabel.visibility = View.VISIBLE
            emailErrorLabel.text = getString(R.string.email_address_input_error)
            hasError = true
        } else {
            emailErrorLabel.visibility = View.GONE
        }

        // Passwordの入力チェック
        if (password.length < 4 || password.length >= 9) {
            passwordErrorLabel.visibility = View.VISIBLE
            passwordErrorLabel.text = getString(R.string.password_input_error)
            hasError = true
        } else {
            passwordErrorLabel.visibility = View.GONE
        }

        // 生年月日の入力チェック
        if (birth.isBlank()) {
            birthdayErrorLabel.visibility = View.VISIBLE
            birthdayErrorLabel.text = getString(R.string.birthday_input_error)
            hasError = true
        } else {
            birthdayErrorLabel.visibility = View.GONE
        }

        return hasError
    }

    /**
     * キーボードを閉じる
     */
    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = ContextCompat.getSystemService(this, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    closeKeyboard()
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

