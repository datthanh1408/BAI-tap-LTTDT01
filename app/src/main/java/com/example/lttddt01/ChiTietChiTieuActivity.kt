package com.example.lttddt01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ChiTietChiTieuActivity : AppCompatActivity() {

    // Khai báo các view với tiền tố/hậu tố MSSV: _204
    private lateinit var tvTenKhoanChi_204: TextView
    private lateinit var tvSoTien_204: TextView
    private lateinit var tvGhiChu_204: TextView
    private lateinit var tvTrangThai_204: TextView
    private lateinit var btnQuayLai_204: Button
    private lateinit var btnChinhSua_204: Button

    // Các biến lưu dữ liệu
    private var tenKhoanChi_204: String = ""
    private var soTien_204: Double = 0.0
    private var ghiChu_204: String = ""
    private var daThanhToan_204: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        khoiTaoGiaoDien_204()
        nhanDuLieuIntent_204()

        // Xử lý trường hợp thiếu hoặc sai dữ liệu theo yêu cầu Câu 2
        val duLieuHopLe_204 = kiemTraDuLieuNhanDuoc_204()
        if (duLieuHopLe_204) {
            hienThiDuLieu_204()
        }
    }

    // Khởi tạo các thành phần giao diện
    private fun khoiTaoGiaoDien_204() {
        val toolbar_204 = findViewById<Toolbar>(R.id.toolbarChiTiet_204)
        setSupportActionBar(toolbar_204)
        supportActionBar?.title = getString(R.string.tieu_de_action_bar)

        tvTenKhoanChi_204 = findViewById(R.id.tvTenKhoanChi_204)
        tvSoTien_204 = findViewById(R.id.tvSoTien_204)
        tvGhiChu_204 = findViewById(R.id.tvGhiChu_204)
        tvTrangThai_204 = findViewById(R.id.tvTrangThai_204)
        btnQuayLai_204 = findViewById(R.id.btnQuayLai_204)
        btnChinhSua_204 = findViewById(R.id.btnChinhSua_204)

        // Nút quay lại màn hình chính
        btnQuayLai_204.setOnClickListener {
            finish()
        }
    }

    // Nhận dữ liệu truyền từ Intent
    private fun nhanDuLieuIntent_204() {
        tenKhoanChi_204 = intent.getStringExtra("EXTRA_TEN_KHOAN_CHI_204") ?: ""
        soTien_204 = intent.getDoubleExtra("EXTRA_SO_TIEN_204", 0.0)
        ghiChu_204 = intent.getStringExtra("EXTRA_GHI_CHU_204") ?: ""
        daThanhToan_204 = intent.getBooleanExtra("EXTRA_DA_THANH_TOAN_204", true)
    }

    // Kiểm tra và xử lý khi thiếu hoặc sai dữ liệu (Câu 2)
    private fun kiemTraDuLieuNhanDuoc_204(): Boolean {
        if (tenKhoanChi_204.isBlank()) {
            Toast.makeText(this, "Cảnh báo: Không nhận được tên khoản chi hợp lệ!", Toast.LENGTH_LONG).show()
            tvTenKhoanChi_204.text = "Lỗi: Không nhận được tên khoản chi"
            tvTenKhoanChi_204.setTextColor(getColor(R.color.status_unpaid))
            btnChinhSua_204.isEnabled = false
            return false
        }

        if (soTien_204 <= 0) {
            Toast.makeText(this, "Cảnh báo: Số tiền chi tiêu không hợp lệ (nhỏ hơn hoặc bằng 0)!", Toast.LENGTH_LONG).show()
            tvSoTien_204.text = "Lỗi: Số tiền không hợp lệ"
            tvSoTien_204.setTextColor(getColor(R.color.status_unpaid))
            btnChinhSua_204.isEnabled = false
            return false
        }

        return true
    }

    // Hiển thị dữ liệu lên màn hình
    private fun hienThiDuLieu_204() {
        tvTenKhoanChi_204.text = tenKhoanChi_204
        tvSoTien_204.text = "${String.format("%,.0f", soTien_204)} VNĐ"
        tvGhiChu_204.text = if (ghiChu_204.isNotEmpty()) ghiChu_204 else "(Không có ghi chú)"

        if (daThanhToan_204) {
            tvTrangThai_204.text = "Đã thanh toán"
            tvTrangThai_204.setTextColor(getColor(R.color.status_paid))
        } else {
            tvTrangThai_204.text = "Chưa thanh toán"
            tvTrangThai_204.setTextColor(getColor(R.color.status_unpaid))
        }
    }
}
