package com.example.lttddt01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    // Khai báo các view với tiền tố/hậu tố MSSV: _204
    private lateinit var edtSuaTenKhoanChi_204: EditText
    private lateinit var edtSuaSoTien_204: EditText
    private lateinit var edtSuaGhiChu_204: EditText
    private lateinit var cbSuaDaThanhToan_204: CheckBox
    private lateinit var btnLuuThayDoi_204: Button
    private lateinit var btnHuyThayDoi_204: Button

    // Các biến lưu dữ liệu hiện tại
    private var tenKhoanChi_204: String = ""
    private var soTien_204: Double = 0.0
    private var ghiChu_204: String = ""
    private var daThanhToan_204: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        khoiTaoGiaoDien_204()
        nhanVaHienThiDuLieuHienTai_204()
        caiDatSuKienNut_204()
    }

    private fun khoiTaoGiaoDien_204() {
        val toolbar_204 = findViewById<Toolbar>(R.id.toolbarChinhSua_204)
        setSupportActionBar(toolbar_204)
        supportActionBar?.title = getString(R.string.tieu_de_action_bar)

        edtSuaTenKhoanChi_204 = findViewById(R.id.edtSuaTenKhoanChi_204)
        edtSuaSoTien_204 = findViewById(R.id.edtSuaSoTien_204)
        edtSuaGhiChu_204 = findViewById(R.id.edtSuaGhiChu_204)
        cbSuaDaThanhToan_204 = findViewById(R.id.cbSuaDaThanhToan_204)
        btnLuuThayDoi_204 = findViewById(R.id.btnLuuThayDoi_204)
        btnHuyThayDoi_204 = findViewById(R.id.btnHuyThayDoi_204)
    }

    // Nhận và hiển thị sẵn dữ liệu hiện tại lên giao diện
    private fun nhanVaHienThiDuLieuHienTai_204() {
        tenKhoanChi_204 = intent.getStringExtra("EXTRA_TEN_KHOAN_CHI_204") ?: ""
        soTien_204 = intent.getDoubleExtra("EXTRA_SO_TIEN_204", 0.0)
        ghiChu_204 = intent.getStringExtra("EXTRA_GHI_CHU_204") ?: ""
        daThanhToan_204 = intent.getBooleanExtra("EXTRA_DA_THANH_TOAN_204", true)

        edtSuaTenKhoanChi_204.setText(tenKhoanChi_204)
        edtSuaSoTien_204.setText(if (soTien_204 > 0) String.format("%.0f", soTien_204) else "")
        edtSuaGhiChu_204.setText(ghiChu_204)
        cbSuaDaThanhToan_204.isChecked = daThanhToan_204
    }

    // Cài đặt sự kiện nút Lưu và Hủy
    private fun caiDatSuKienNut_204() {
        // Nút Lưu thay đổi - Trả kết quả về qua Activity Result API
        btnLuuThayDoi_204.setOnClickListener {
            xuLyLuuThayDoi_204()
        }

        // Nút Hủy chỉnh sửa - Giữ nguyên dữ liệu ban đầu
        btnHuyThayDoi_204.setOnClickListener {
            xuLyHuyChinhSua_204()
        }
    }

    // Xử lý lưu thay đổi
    private fun xuLyLuuThayDoi_204() {
        val tenMoi_204 = edtSuaTenKhoanChi_204.text.toString().trim()
        val strSoTienMoi_204 = edtSuaSoTien_204.text.toString().trim()
        val ghiChuMoi_204 = edtSuaGhiChu_204.text.toString().trim()
        val daThanhToanMoi_204 = cbSuaDaThanhToan_204.isChecked

        if (tenMoi_204.isEmpty()) {
            edtSuaTenKhoanChi_204.error = "Tên khoản chi không được để trống"
            Toast.makeText(this, "Vui lòng nhập tên khoản chi!", Toast.LENGTH_SHORT).show()
            return
        }

        val soTienMoi_204 = strSoTienMoi_204.toDoubleOrNull()
        if (soTienMoi_204 == null || soTienMoi_204 <= 0) {
            edtSuaSoTien_204.error = "Số tiền phải là số lớn hơn 0"
            Toast.makeText(this, "Số tiền không hợp lệ!", Toast.LENGTH_SHORT).show()
            return
        }

        // Trả dữ liệu đã sửa về bằng Activity Result API (RESULT_OK)
        val resultIntent_204 = Intent().apply {
            putExtra("EXTRA_TEN_KHOAN_CHI_204", tenMoi_204)
            putExtra("EXTRA_SO_TIEN_204", soTienMoi_204)
            putExtra("EXTRA_GHI_CHU_204", ghiChuMoi_204)
            putExtra("EXTRA_DA_THANH_TOAN_204", daThanhToanMoi_204)
        }
        setResult(Activity.RESULT_OK, resultIntent_204)
        finish()
    }

    // Xử lý hủy chỉnh sửa
    private fun xuLyHuyChinhSua_204() {
        // Trả mã RESULT_CANCELED để báo không thay đổi dữ liệu
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
