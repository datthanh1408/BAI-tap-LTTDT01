package com.example.lttddt01.repository

import java.io.Serializable

// Lớp KhoanChi biểu diễn một khoản chi (Câu 4)
class KhoanChi(
    var maKhoanChi: String,
    var tenKhoanChi: String,
    var soTien: Double,
    var ghiChu: String,
    var daThanhToan: Boolean = false
) : Serializable {

    // Hàm trả về chuỗi mô tả thông tin khoản chi theo yêu cầu đề bài
    fun layThongTin(): String {
        val trangThaiText = if (daThanhToan) "Đã thanh toán" else "Chưa thanh toán"
        val ghiChuText = if (ghiChu.isNotBlank()) ghiChu else "Không có ghi chú"
        return "Mã: $maKhoanChi | Tên: $tenKhoanChi | Tiền: ${String.format("%,.0f VNĐ", soTien)} | Ghi chú: $ghiChuText | Trạng thái: $trangThaiText"
    }

    // Hàm kiểm tra số tiền có hợp lệ hay không (lớn hơn 0)
    fun laSoTienHopLe(): Boolean {
        return soTien > 0
    }
}
