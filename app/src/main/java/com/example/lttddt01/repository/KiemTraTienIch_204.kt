package com.example.lttddt01.repository

// Hàm tiện ích kiểm tra dữ liệu hợp lệ (Câu 5 - Clean Code & Tái sử dụng)
object KiemTraTienIch_204 {

    // Kiểm tra tên khoản chi: không rỗng, không toàn khoảng trắng
    fun laTenKhoanChiHopLe_204(ten: String?): Boolean {
        return !ten.isNullOrBlank()
    }

    // Kiểm tra số tiền: phải là số lớn hơn 0
    fun laSoTienHopLe_204(soTien: Double?): Boolean {
        return soTien != null && soTien > 0
    }

    // Kiểm tra chuỗi nhập số tiền
    fun chuyenDoiVaKiemTraSoTien_204(strSoTien: String?): Pair<Boolean, Double> {
        if (strSoTien.isNullOrBlank()) {
            return Pair(false, 0.0)
        }
        val giaTri = strSoTien.trim().toDoubleOrNull()
        return if (giaTri != null && giaTri > 0) {
            Pair(true, giaTri)
        } else {
            Pair(false, 0.0)
        }
    }
}
