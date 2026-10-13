package com.example.lttddt01.repository

// Repository quản lý các khoản chi tiêu theo kiến trúc chuẩn
object KhoanChiRepository {

    // Danh sách lưu trữ các khoản chi trong bộ nhớ
    private val danhSachKhoanChi_204 = mutableListOf<KhoanChi>()

    init {
        // Khởi tạo dữ liệu mẫu độc nhất mang dấu ấn cá nhân của sinh viên Võ Văn Thành Đạt - 2415053122204
        danhSachKhoanChi_204.add(
            KhoanChi(
                maKhoanChi = "KC204_01",
                tenKhoanChi = "Học phí môn Lập trình thiết bị di động",
                soTien = 1850000.0,
                ghiChu = "Học phần thực hành Android cơ bản - 204",
                daThanhToan = true
            )
        )
        danhSachKhoanChi_204.add(
            KhoanChi(
                maKhoanChi = "KC204_02",
                tenKhoanChi = "Mua sách giáo trình Android Kotlin",
                soTien = 120000.0,
                ghiChu = "Tài liệu học tập kỳ 1",
                daThanhToan = true
            )
        )
        danhSachKhoanChi_204.add(
            KhoanChi(
                maKhoanChi = "KC204_03",
                tenKhoanChi = "Tiền ăn trưa căng tin trường",
                soTien = 35000.0,
                ghiChu = "Cơm trưa sinh viên",
                daThanhToan = false
            )
        )
    }

    // Lấy khoản chi hiện tại / mặc định
    fun layKhoanChiHienTai_204(): KhoanChi {
        return danhSachKhoanChi_204.first()
    }

    // Thêm một khoản chi mới
    fun themKhoanChi_204(khoanChi: KhoanChi) {
        danhSachKhoanChi_204.add(0, khoanChi)
    }

    // Lấy toàn bộ danh sách
    fun layTatCaKhoanChi_204(): List<KhoanChi> {
        return danhSachKhoanChi_204.toList()
    }
}
