package com.example.lttddt01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    // Khai báo các biến thành phần giao diện theo quy tắc có đuôi MSSV: _204
    private lateinit var edtTenKhoanChi_204: EditText
    private lateinit var edtSoTien_204: EditText
    private lateinit var edtGhiChu_204: EditText
    private lateinit var btnThemKhoanChi_204: Button
    private lateinit var btnXemChiTiet_204: Button
    private lateinit var tvThongBao_204: TextView

    // Biến lưu thông tin khoản chi hiện tại
    private var tenKhoanChi_204: String = ""
    private var soTien_204: Double = 0.0
    private var ghiChu_204: String = ""
    private var coDuLieu_204: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thiết lập Toolbar tiêu đề theo đúng yêu cầu đề bài
        val toolbar_204 = findViewById<Toolbar>(R.id.toolbar_204)
        setSupportActionBar(toolbar_204)
        supportActionBar?.title = getString(R.string.tieu_de_action_bar)

        // Ánh xạ các view
        edtTenKhoanChi_204 = findViewById(R.id.edtTenKhoanChi_204)
        edtSoTien_204 = findViewById(R.id.edtSoTien_204)
        edtGhiChu_204 = findViewById(R.id.edtGhiChu_204)
        btnThemKhoanChi_204 = findViewById(R.id.btnThemKhoanChi_204)
        btnXemChiTiet_204 = findViewById(R.id.btnXemChiTiet_204)
        tvThongBao_204 = findViewById(R.id.tvThongBao_204)

        // Xử lý sự kiện nút Thêm khoản chi
        btnThemKhoanChi_204.setOnClickListener {
            xuLyThemKhoanChi_204()
        }

        // Xử lý sự kiện nút Xem chi tiết khoản chi
        btnXemChiTiet_204.setOnClickListener {
            xuLyXemChiTiet_204()
        }
    }

    // Hàm xử lý thêm khoản chi
    private fun xuLyThemKhoanChi_204() {
        val strTen_204 = edtTenKhoanChi_204.text.toString().trim()
        val strSoTien_204 = edtSoTien_204.text.toString().trim()
        val strGhiChu_204 = edtGhiChu_204.text.toString().trim()

        if (strTen_204.isEmpty()) {
            edtTenKhoanChi_204.error = "Vui lòng nhập tên khoản chi"
            Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
            return
        }

        val parsedSoTien_204 = strSoTien_204.toDoubleOrNull()
        if (parsedSoTien_204 == null || parsedSoTien_204 <= 0) {
            edtSoTien_204.error = "Số tiền phải là số lớn hơn 0"
            Toast.makeText(this, "Số tiền không hợp lệ!", Toast.LENGTH_SHORT).show()
            return
        }

        // Lưu thông tin khoản chi hợp lệ
        tenKhoanChi_204 = strTen_204
        soTien_204 = parsedSoTien_204
        ghiChu_204 = strGhiChu_204
        coDuLieu_204 = true

        tvThongBao_204.text = "Đã lưu: $tenKhoanChi_204 - ${String.format("%,.0f", soTien_204)} VNĐ"
        Toast.makeText(this, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
    }

    // Hàm xử lý xem chi tiết khoản chi
    private fun xuLyXemChiTiet_204() {
        if (!coDuLieu_204) {
            Toast.makeText(this, "Vui lòng thêm khoản chi trước khi xem chi tiết!", Toast.LENGTH_SHORT).show()
            return
        }

        // Sẽ được liên kết mở ChiTietChiTieuActivity ở câu 2
        Toast.makeText(this, "Đang mở màn hình chi tiết khoản chi...", Toast.LENGTH_SHORT).show()
    }
}