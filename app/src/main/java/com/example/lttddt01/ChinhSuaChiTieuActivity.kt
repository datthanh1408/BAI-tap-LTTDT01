package com.example.lttddt01

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lttddt01.repository.KhoanChi

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    // Khai báo các view với tiền tố/hậu tố MSSV: _204
    private lateinit var edtSuaTenKhoanChi_204: EditText
    private lateinit var edtSuaSoTien_204: EditText
    private lateinit var edtSuaGhiChu_204: EditText
    private lateinit var cbSuaDaThanhToan_204: CheckBox
    private lateinit var btnLuuThayDoi_204: Button
    private lateinit var btnHuyThayDoi_204: Button

    // Sử dụng đối tượng KhoanChi để quản lý dữ liệu chỉnh sửa (Câu 4)
    private var khoanChiHienTai_204: KhoanChi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_chi_tieu)

        khoiTaoGiaoDien_204()
        nhanVaHienThiDuLieuHienTai_204()
        caiDatSuKienNut_204()

        // Xử lý khi nhấn nút Back vật lý/hệ thống cũng tính là Hủy (giữ nguyên dữ liệu)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                xuLyHuyChinhSua_204()
            }
        })
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

    // Nhận và hiển thị sẵn dữ liệu từ đối tượng KhoanChi
    private fun nhanVaHienThiDuLieuHienTai_204() {
        khoanChiHienTai_204 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_KHOAN_CHI_204", KhoanChi::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_KHOAN_CHI_204") as? KhoanChi
        }

        val kc_204 = khoanChiHienTai_204
        if (kc_204 != null) {
            edtSuaTenKhoanChi_204.setText(kc_204.tenKhoanChi)
            edtSuaSoTien_204.setText(if (kc_204.soTien > 0) String.format("%.0f", kc_204.soTien) else "")
            edtSuaGhiChu_204.setText(kc_204.ghiChu)
            cbSuaDaThanhToan_204.isChecked = kc_204.daThanhToan
        }
    }

    // Cài đặt sự kiện nút Lưu và Hủy
    private fun caiDatSuKienNut_204() {
        btnLuuThayDoi_204.setOnClickListener {
            xuLyLuuThayDoi_204()
        }

        btnHuyThayDoi_204.setOnClickListener {
            xuLyHuyChinhSua_204()
        }
    }

    // Xử lý lưu thay đổi và cập nhật đối tượng KhoanChi
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
        if (soTienMoi_204 == null) {
            edtSuaSoTien_204.error = "Số tiền phải là số hợp lệ"
            Toast.makeText(this, "Số tiền không hợp lệ!", Toast.LENGTH_SHORT).show()
            return
        }

        val maKhoanChiHienTai = khoanChiHienTai_204?.maKhoanChi ?: "KC204_01"
        val khoanChiCapNhat_204 = KhoanChi(
            maKhoanChi = maKhoanChiHienTai,
            tenKhoanChi = tenMoi_204,
            soTien = soTienMoi_204,
            ghiChu = ghiChuMoi_204,
            daThanhToan = daThanhToanMoi_204
        )

        // Kiểm tra tính hợp lệ của số tiền thông qua hàm laSoTienHopLe() của lớp KhoanChi
        if (!khoanChiCapNhat_204.laSoTienHopLe()) {
            edtSuaSoTien_204.error = "Số tiền phải lớn hơn 0"
            Toast.makeText(this, "Số tiền chi tiêu phải lớn hơn 0!", Toast.LENGTH_SHORT).show()
            return
        }

        // Trả đối tượng KhoanChi đã sửa về cho ChiTietChiTieuActivity
        val resultIntent_204 = Intent().apply {
            putExtra("EXTRA_KHOAN_CHI_204", khoanChiCapNhat_204)
        }
        setResult(Activity.RESULT_OK, resultIntent_204)
        finish()
    }

    // Xử lý hủy chỉnh sửa
    private fun xuLyHuyChinhSua_204() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
