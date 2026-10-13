package com.example.lttddt01.repository

import java.io.Serializable

// Lớp KhoanChi biểu diễn một khoản chi (Câu 4)
class KhoanChi(
    var maKhoanChi: String,
    var tenKhoanChi: String,
    var soTien: Double,
    var ghiChu: String,
    var daThanhToan: Boolean = false
) : Serializable
