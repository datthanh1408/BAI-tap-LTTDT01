package com.example.lttddt01

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lttddt01.repository.KhoanChi

class ChiTietChiTieuActivity : AppCompatActivity() {

    // Khai báo các view với tiền tố/hậu tố MSSV: _204
    private lateinit var tvTenKhoanChi_204: TextView
    private lateinit var tvSoTien_204: TextView
    private lateinit var tvGhiChu_204: TextView
    private lateinit var tvTrangThai_204: TextView
    private lateinit var btnQuayLai_204: Button
    private lateinit var btnChinhSua_204: Button

    // Sử dụng đối tượng KhoanChi để quản lý dữ liệu (Câu 4)
    private var khoanChi_204: KhoanChi? = null

    // Activity Result API để nhận đối tượng KhoanChi đã chỉnh sửa (Câu 3 & 4)
    private val suaChiTieuLauncher_204 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua_204 ->
        if (ketQua_204.resultCode == Activity.RESULT_OK) {
            val duLieuTraVe_204 = ketQua_204.data
            val khoanChiDaSua_204 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                duLieuTraVe_204?.getSerializableExtra("EXTRA_KHOAN_CHI_204", KhoanChi::class.java)
            } else {
                @Suppress("DEPRECATION")
                duLieuTraVe_204?.getSerializableExtra("EXTRA_KHOAN_CHI_204") as? KhoanChi
            }

            if (khoanChiDaSua_204 != null) {
                khoanChi_204 = khoanChiDaSua_204
                hienThiDuLieu_204()
                Toast.makeText(this, "Đã cập nhật thông tin khoản chi mới!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Đã hủy chỉnh sửa. Giữ nguyên thông tin ban đầu!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_chi_tieu)

        khoiTaoGiaoDien_204()
        nhanDuLieuIntent_204()

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

        // Nút mở màn hình chỉnh sửa truyền cả đối tượng KhoanChi
        btnChinhSua_204.setOnClickListener {
            val doiTuongGui_204 = khoanChi_204
            if (doiTuongGui_204 != null) {
                val intent_204 = Intent(this, ChinhSuaChiTieuActivity::class.java).apply {
                    putExtra("EXTRA_KHOAN_CHI_204", doiTuongGui_204)
                }
                suaChiTieuLauncher_204.launch(intent_204)
            }
        }
    }

    // Nhận đối tượng KhoanChi từ Intent
    private fun nhanDuLieuIntent_204() {
        khoanChi_204 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_KHOAN_CHI_204", KhoanChi::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_KHOAN_CHI_204") as? KhoanChi
        }
    }

    // Kiểm tra tính hợp lệ của đối tượng KhoanChi
    private fun kiemTraDuLieuNhanDuoc_204(): Boolean {
        val kc_204 = khoanChi_204
        if (kc_204 == null || kc_204.tenKhoanChi.isBlank()) {
            Toast.makeText(this, "Cảnh báo: Không nhận được thông tin khoản chi hợp lệ!", Toast.LENGTH_LONG).show()
            tvTenKhoanChi_204.text = "Lỗi: Không nhận được tên khoản chi"
            tvTenKhoanChi_204.setTextColor(getColor(R.color.status_unpaid))
            btnChinhSua_204.isEnabled = false
            return false
        }

        if (!kc_204.laSoTienHopLe()) {
            Toast.makeText(this, "Cảnh báo: Số tiền chi tiêu không hợp lệ (nhỏ hơn hoặc bằng 0)!", Toast.LENGTH_LONG).show()
            tvSoTien_204.text = "Lỗi: Số tiền không hợp lệ"
            tvSoTien_204.setTextColor(getColor(R.color.status_unpaid))
            btnChinhSua_204.isEnabled = false
            return false
        }

        return true
    }

    // Hiển thị dữ liệu từ đối tượng KhoanChi
    private fun hienThiDuLieu_204() {
        val kc_204 = khoanChi_204 ?: return
        tvTenKhoanChi_204.text = kc_204.tenKhoanChi
        tvSoTien_204.text = "${String.format("%,.0f", kc_204.soTien)} VNĐ"
        tvGhiChu_204.text = if (kc_204.ghiChu.isNotEmpty()) kc_204.ghiChu else "(Không có ghi chú)"

        if (kc_204.daThanhToan) {
            tvTrangThai_204.text = "Đã thanh toán"
            tvTrangThai_204.setTextColor(getColor(R.color.status_paid))
        } else {
            tvTrangThai_204.text = "Chưa thanh toán"
            tvTrangThai_204.setTextColor(getColor(R.color.status_unpaid))
        }
    }
}
