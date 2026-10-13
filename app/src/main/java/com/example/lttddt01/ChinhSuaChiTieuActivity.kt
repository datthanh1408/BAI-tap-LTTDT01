package com.example.lttddt01

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
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

    // Các biến lưu dữ liệu hiện tại nhận từ ChiTietChiTieuActivity
    private var tenKhoanChi_204: String = ""
    private var soTien_204: Double = 0.0
    private var ghiChu_204: String = ""
    private var daThanhToan_204: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        khoiTaoGiaoDien_204()
        nhanVaHienThiDuLieuHienTai_204()
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

    // Nhận và hiển thị sẵn dữ liệu hiện tại lên giao diện chỉnh sửa
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
}
