package com.example.lttddt01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lttddt01.repository.KhoanChi
import com.example.lttddt01.repository.KhoanChiRepository

class MainActivity : AppCompatActivity() {

    // Khai báo các biến thành phần giao diện theo quy tắc có đuôi MSSV: _204
    private lateinit var edtTenKhoanChi_204: EditText
    private lateinit var edtSoTien_204: EditText
    private lateinit var edtGhiChu_204: EditText
    private lateinit var btnThemKhoanChi_204: Button
    private lateinit var btnXemChiTiet_204: Button
    private lateinit var tvThongBao_204: TextView

    // Sử dụng lớp KhoanChi để quản lý dữ liệu thay vì các biến rời rạc (Câu 4)
    private var khoanChiHienTai_204: KhoanChi? = null

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

        // Khởi tạo dữ liệu ban đầu mang tính cá nhân từ KhoanChiRepository
        khoiTaoDuLieuBanDau_204()

        // Xử lý sự kiện nút Thêm khoản chi
        btnThemKhoanChi_204.setOnClickListener {
            xuLyThemKhoanChi_204()
        }

        // Xử lý sự kiện nút Xem chi tiết khoản chi
        btnXemChiTiet_204.setOnClickListener {
            xuLyXemChiTiet_204()
        }
    }

    // Hàm xử lý thêm khoản chi sử dụng lớp KhoanChi và hàm tiện ích kiểm tra (Câu 5)
    private fun xuLyThemKhoanChi_204() {
        val strTen_204 = edtTenKhoanChi_204.text.toString().trim()
        val strSoTien_204 = edtSoTien_204.text.toString().trim()
        val strGhiChu_204 = edtGhiChu_204.text.toString().trim()

        // Kiểm tra tên khoản chi: không để trống hoặc chỉ chứa khoảng trắng
        if (!com.example.lttddt01.repository.KiemTraTienIch_204.laTenKhoanChiHopLe_204(strTen_204)) {
            edtTenKhoanChi_204.error = "Tên khoản chi không được để trống hoặc chỉ chứa khoảng trắng"
            Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
            return
        }

        // Kiểm tra số tiền: không cho phép số bằng 0, số âm hoặc định dạng sai
        val (soTienHopLe_204, soTienChuan_204) = com.example.lttddt01.repository.KiemTraTienIch_204.chuyenDoiVaKiemTraSoTien_204(strSoTien_204)
        if (!soTienHopLe_204) {
            edtSoTien_204.error = "Số tiền phải là số lớn hơn 0"
            Toast.makeText(this, "Số tiền không hợp lệ (phải lớn hơn 0)!", Toast.LENGTH_SHORT).show()
            return
        }

        // Khởi tạo đối tượng KhoanChi (Câu 4)
        val maRandom_204 = "KC204_${System.currentTimeMillis() % 1000}"
        val doiTuongKhoanChi_204 = KhoanChi(
            maKhoanChi = maRandom_204,
            tenKhoanChi = strTen_204,
            soTien = soTienChuan_204,
            ghiChu = strGhiChu_204,
            daThanhToan = true
        )

        // Kiểm tra tính hợp lệ của số tiền thông qua hàm laSoTienHopLe() của lớp KhoanChi
        if (!doiTuongKhoanChi_204.laSoTienHopLe()) {
            edtSoTien_204.error = "Số tiền phải lớn hơn 0"
            Toast.makeText(this, "Số tiền chi tiêu phải lớn hơn 0!", Toast.LENGTH_SHORT).show()
            return
        }

        // Lưu đối tượng vào biến hiện tại và thêm vào repository
        khoanChiHienTai_204 = doiTuongKhoanChi_204
        KhoanChiRepository.themKhoanChi_204(doiTuongKhoanChi_204)

        // Hiển thị thông tin bằng hàm layThongTin() của lớp KhoanChi
        tvThongBao_204.text = doiTuongKhoanChi_204.layThongTin()
        Toast.makeText(this, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
    }

    // Hàm xử lý xem chi tiết khoản chi - Truyền đối tượng KhoanChi qua Intent
    private fun xuLyXemChiTiet_204() {
        val khoanChi_204 = khoanChiHienTai_204
        if (khoanChi_204 == null) {
            Toast.makeText(this, "Vui lòng thêm khoản chi trước khi xem chi tiết!", Toast.LENGTH_SHORT).show()
            return
        }

        val intent_204 = Intent(this, ChiTietChiTieuActivity::class.java).apply {
            putExtra("EXTRA_KHOAN_CHI_204", khoanChi_204)
        }
        startActivity(intent_204)
    }

    // Khởi tạo dữ liệu ban đầu từ KhoanChiRepository
    private fun khoiTaoDuLieuBanDau_204() {
        val khoanChiMau_204 = KhoanChiRepository.layKhoanChiHienTai_204()
        khoanChiHienTai_204 = khoanChiMau_204

        edtTenKhoanChi_204.setText(khoanChiMau_204.tenKhoanChi)
        edtSoTien_204.setText(String.format("%.0f", khoanChiMau_204.soTien))
        edtGhiChu_204.setText(khoanChiMau_204.ghiChu)
        tvThongBao_204.text = khoanChiMau_204.layThongTin()
    }
}